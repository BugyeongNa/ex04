package com.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import com.board.dto.BoardDTO;
import com.ex01.util.MapperUtil;

public enum BoardService {

	INSTANCE;
	
	private BoardDAO BDao;
	private ModelMapper modelMapper;
	
	private BoardService() {
		BDao = new BoardDAO();
		modelMapper = MapperUtil.INSTANCE.get();
	}
	

//	게시글 목록 서비스
	public List<BoardDTO> boardList(Map<String, Integer> pageingMap){
		
		// 게시글 목록
				List<BoardVO> bordList = BDao.boardList(pageingMap);
				
				// VO -> DTO
				List<BoardDTO> dtoList = bordList.stream()
								.map(vo -> modelMapper.map(vo, BoardDTO.class))
								.collect(Collectors.toList());
				
				return dtoList;
	}
	
//	등록된 게시글 전체 개수
	public int selectTotArticles() {
		int totArticles = BDao.selectTotArticles();
		return totArticles;
	}
	
//	게시글 조회
	public BoardDTO selectArticleOne(int articleNO) {
		
		BoardVO vo = BDao.selectArtcleOne(articleNO);
		
		BoardDTO dto = modelMapper.map(vo, BoardDTO.class);
		
		return dto;
	}
	
//	게시글 쓰기
	public int insertBoard(BoardDTO dto) {
		
		int articleNO = BDao.getNewArticleNO();
		dto.setArticleNO(articleNO);
//		dao 요청
//		dto -> vo
		
		BoardVO vo = modelMapper.map(dto, BoardVO.class);
		int rs = BDao.insertNewArticle(vo);
		
		return rs;
	}
	
//	게시글 수정
	public int updateBoard(BoardDTO dto) {
		BoardVO vo = modelMapper.map(dto, BoardVO.class);
		int rs = BDao.updateArticle(vo);
		
		return rs;
	}

//	게시글 삭제
	public List<Integer> deleteBoard(int articleNO) {
//		특정 글번호 기준으로 자식 게시글번호 추출
		List<Integer> articleNOList = BDao.selectRemobeArticles(articleNO);
		
//		db에 있는 특정 글게시글 번호 삭제(답글 게시글 포함) 
		int rs = BDao.deleteArticle(articleNO);
		return articleNOList;
	}
}
