package com.ex01.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ex01.domain.TodoVO;

import lombok.Cleanup;

public class TodoDAO {
	
	
//	삭제
	public void deletOne(Long tno) throws Exception{
		String sql = "DELETE FROM tbl_todo WHERE tno = ?";
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, tno);
		
		pstmt.executeUpdate();
		
	}
	
//	수정
	public void updateOne(TodoVO vo) throws Exception{
		String sql = "UPDATE tbl_todo SET title = ?, dueDate = ?, finished = ? WHERE tno = ?";
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getTitle());
		pstmt.setDate(2, Date.valueOf(vo.getDueDate()));// localdate => sql date
		pstmt.setBoolean(3, vo.isFinished());
		pstmt.setLong(4, vo.getTno());
		
		pstmt.executeUpdate();
	}
	
//	조회: select one
	public TodoVO selectOne(Long tno) throws Exception{
		String sql = "SELECT * FROM tbl_todo WHERE tno = ?";
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, tno);
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		TodoVO vo = null;
		
		while(rs.next()) {
			vo = TodoVO.builder()
					.tno(rs.getLong("tno"))
					.title(rs.getString("title"))
					.dueDate(rs.getDate("dueDate").toLocalDate())
					.finished(rs.getBoolean("finished"))
					.build();
		}
		return vo;
	}
	
//	목록: select all
	public List<TodoVO> selectAll() throws Exception{
		String sql = "SELECT * FROM tbl_todo";
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		List<TodoVO> list = new ArrayList<>();
		while(rs.next()) {
			TodoVO vo = TodoVO.builder()
					.tno(rs.getLong("tno"))
					.title(rs.getString("title"))
					.dueDate(rs.getDate("dueDate").toLocalDate())
					.finished(rs.getBoolean("finished"))
					.build();
			
			list.add(vo);
		}
		
		return list;
		
	}
	
//	입력: insert
	public void insert(TodoVO vo) throws Exception{
		String sql ="INSERT INTO tbl_todo(title,dueDate,finished) VALUES (?,?,?)";
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setDate(2, Date.valueOf(vo.getDueDate()));// LocalDate => Date
		pstmt.setBoolean(3, vo.isFinished());
		
		pstmt.executeUpdate();
		
	}
	
	
	public String getTime() throws Exception{
		String now = null;
		
//		try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement("select now()");
//				ResultSet rs = pstmt.executeQuery();
//				){
//			rs.next();
//			now = rs.getString(1);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		@Cleanup => auto close
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement("select now()");
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		now = rs.getString(1);
		
		return now;
	}
}
