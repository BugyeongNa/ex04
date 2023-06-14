package com.board.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.board.domain.BoardVO;
import com.board.dto.BoardDTO;
import com.ex01.util.MapperUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardDAOTest {

	private BoardDAO Bdao;
	private ModelMapper modelMapper;
	
	@BeforeEach
	public void read() {
		Bdao = new BoardDAO();
		modelMapper = MapperUtil.INSTANCE.get();
		
	}
	
//	@Test
//	public void testMemberListAll() throws Exception{
//		List<BoardVO> boardlist = Bdao.boardList();
////		boardlist.forEach(vo -> log.info(vo) );
//		
//		List<BoardDTO> dtoList = boardlist.stream()
//				.map(vo -> modelMapper.map(vo, BoardDTO.class))
//				.collect(Collectors.toList());
//		System.out.println("dtoList");
//		dtoList.forEach(dto -> log.info(dto));
//	}
	
	@Test
	public void insertNewArticle() throws Exception{
//		새글 등록하기위해 새글번호 생성
		
//		새글 등록 
		
		for (int i=1; i<=1000; i++) {
		int newArticleNo = Bdao.getNewArticleNO();
		
		BoardVO vo = BoardVO.builder()
				.articleNO(newArticleNo)
				.parentNO(0)
				.title("게시글 제목 "+i)
				.content("게시글 내용 "+i)
				.imageFileName("imagefile"+i+".png")
				.id("lee")
				.build();
		Bdao.insertNewArticle(vo);
		}
	}
	
	@Test
	public void selectArticleOne() {
		int articleNO = 7;
		BoardVO vo = Bdao.selectArtcleOne(articleNO);
		log.info("vo: "+vo);
		
		BoardDTO dto = modelMapper.map(vo, BoardDTO.class);
		log.info("dto: "+dto);
	}
	
	@Test
	public void testUpdate() throws Exception{
		
		BoardVO vo = BoardVO.builder()
				.title("수정제목입니다.")
				.content("수정내용입니다.")
				.imageFileName("4.jpg")
				.articleNO(8)
				.build();
		log.info(vo);
		int result = Bdao.updateArticle(vo);
		if(result != 0)
			log.info("수정완료");
		else
			log.info("수정실패");
	}
	
	@Test
	public void testDelete() throws Exception{
		int articleNO = 10;
		int result = Bdao.deleteArticle(articleNO);
		
		if(result!=0)
			log.info("삭제완료");
		else
			log.info("삭제실패");
	}
}
