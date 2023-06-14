package com.ex01.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.ex01.dao.TodoDAO;
import com.ex01.domain.TodoVO;
import com.ex01.dto.TodoDTO;
import com.ex01.util.MapperUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum TodoService {
	INSTANCE;
	
	private TodoDAO dao;
	private ModelMapper modelMapper;
	
	TodoService() {
		dao = new TodoDAO();
		modelMapper = MapperUtil.INSTANCE.get();// ModelMapper 객체 생성
	}
	
	
//	목록 서비스
	public List<TodoDTO> listAll() throws Exception{
		List<TodoVO> list = dao.selectAll();
		log.info("vo list....");
		
		List<TodoDTO> dtoList = list.stream()
					.map(vo -> modelMapper.map(vo, TodoDTO.class))
					.collect(Collectors.toList());
		return dtoList;
	}
	
//	입력서비스
	public void register(TodoDTO dto) throws Exception{
		
		log.info("\n - register dto: "+dto);
		TodoVO todoVO = modelMapper.map(dto, TodoVO.class);

		log.info(todoVO);
//		System.out.println(todoVO);
		
		dao.insert(todoVO);
	}
	
//	조회 서비스
	public TodoDTO get(Long tno) throws Exception{
		log.info("tno: "+tno);
		
		TodoVO todoVO = dao.selectOne(tno);
		TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
		
		return todoDTO;
	}
	
//	수정 서비스
	public void modify(TodoDTO todoDTO) throws Exception{
		log.info("modify tno: "+todoDTO);
		
		TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
		
		dao.updateOne(todoVO);
	}
	
//	삭제 서비스
	public void remove(Long tno) throws Exception{
		log.info("remove tno: "+tno);
		
		dao.deletOne(tno);
	}
	
	
}
