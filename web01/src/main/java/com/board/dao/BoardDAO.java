package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.board.domain.BoardVO;
import com.board.util.OracleConnectUtil;

import lombok.Cleanup;



public class BoardDAO {
	
//	1. 게시글 목록
	public List<BoardVO> boardList(Map<String, Integer> pageingMap) {
		int section = pageingMap.get("section");
		int pageNum = pageingMap.get("pageNum");
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			String sql_backup = "select level, articleNo, parentNo, title, content, writedate, id from t_board start with parentno = 0 connect by prior articleno = parentno order siblings by articleno desc";
			
			String sql = """
					select * from (
					select rownum as recNum, LVL, articleNo, parentNo, title, content, writedate, id
					from (
					        select level as LVL, articleNo, parentNo, title, content, writedate, id from t_board 
					        start with parentno = 0
					        connect by prior articleno = parentno
					        order siblings by articleno desc
					        )
					    ) where recNum between (?-1)*100+(?-1)*10+1 and (?-1)*100+(?)*10
					""";
			
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = BoardVO.builder()
						.level(rs.getInt("LVL"))
						.articleNO(rs.getInt("articleNO"))
						.parentNO(rs.getInt("parentNO"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.writedate(rs.getDate("writedate").toLocalDate())
						.id(rs.getString("id"))
						.build();

				boardList.add(vo);
			}
			
		} catch (Exception e) {}
		
		return boardList;
				
	}
	
//	1-1. 게시글 전체 개수
	public int selectTotArticles() {
		
		try {
			String sql = "select max(articleNo) from t_board";
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return (rs.getInt(1));
			}
		}catch (Exception e) {
		}
		
		return 0;
		
	}
	
//	2. 게시글 등록시 게시글 번호
	public int getNewArticleNO() {
		String sql = "select max (articleNO) from t_board";
		
		try {
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return (rs.getInt(1)+1);
			}
			
		} catch (Exception e) {
		}
		return 0;
		
	}
	
//	3. 게시글 등록
	public int insertNewArticle(BoardVO boardVO) {
		int isOK = 0;
		
		String sql = " INSERT into t_board "
				+ " (articleNO, parentNO, title, content, imageFileName, id ) "
				+ " VALUES (?, ?, ?, ?, ?, ?) ";
		
		try {
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardVO.getArticleNO());
			pstmt.setInt(2, boardVO.getParentNO());
			pstmt.setString(3, boardVO.getTitle());
			pstmt.setString(4, boardVO.getContent());
			pstmt.setString(5, boardVO.getImageFileName());
			pstmt.setString(6, boardVO.getId());
			
			isOK = pstmt.executeUpdate();
			
		} catch (Exception e) {
			
		}
		return isOK;
	}
	
//	4. 게시글 조회
	public BoardVO selectArtcleOne(int articleNO) {
		BoardVO vo = null;
		String sql = "select * from t_board where articleno = ?";
		
		try {
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
//			vo = new BoardVO(articleNO, articleNO, sql, sql, sql, null, sql, articleNO)
			vo = BoardVO.builder()
					.articleNO(rs.getInt("articleNO"))
					.parentNO(rs.getInt("parentNO"))
					.title(rs.getString("title"))
					.content(rs.getString("content"))
					.imageFileName(rs.getString("imageFileName"))
					.writedate(rs.getDate("writedate").toLocalDate())
					.id(rs.getString("id"))
					.build();
		} catch (Exception e) {
		}
		
		return vo;
	}
//	5. 게시글 수정
	public int updateArticle(BoardVO boardVO) {
		int isOK = 0;
		
		String sql = """
				update t_board set 
				title = ?,
				 content = ?,
				  imagefilename = ?
				   where articleno = ?
				""";
		
		try {
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getContent());
			pstmt.setString(3, boardVO.getImageFileName());
			pstmt.setInt(4, boardVO.getArticleNO()); 
			
			isOK = pstmt.executeUpdate();
			
		} catch (Exception e) {
			
		}
		return isOK;
	}
//	6. 게시글 삭제
	public int deleteArticle(int articleNO) {
		int isOK = 0;
		String sql = "delete from t_board where articleno in ( "
				   + "select articleno from t_board start with articleno = ? connect by prior articleno = parentno)";
		
		
		try {
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			isOK = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
		}
		
		return isOK;
	}
//	6-2. 게시글 삭제(이미지 파일)
	public List<Integer> selectRemobeArticles(int articleNO){
		List<Integer> articleNOList = new ArrayList<>();
		
		String sql = " select articleno from t_board start with articleno = ? connect by prior articleno = parentno";
		
		
		try {
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			@Cleanup ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				articleNO = rs.getInt("articleNO");
				
				articleNOList.add(articleNO);
			}
			
			
		} catch (Exception e) {
		}
		
		return articleNOList;
	}
}
