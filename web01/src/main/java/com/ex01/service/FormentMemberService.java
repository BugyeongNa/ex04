package com.ex01.service;

import org.modelmapper.ModelMapper;

import com.ex01.dao.FormentMemberDAO;
import com.ex01.domain.FormentMemberVO;
import com.ex01.dto.FormentMemberDTO;
import com.ex01.util.MapperUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum FormentMemberService {
INSTANCE;
	
	private FormentMemberDAO dao;
	private ModelMapper modelMapper;
	
	private FormentMemberService() {
		dao = new FormentMemberDAO();
		modelMapper = MapperUtil.INSTANCE.get();
	}
	
//	로그인 서비스 
	public FormentMemberDTO login(String id, String pwd) {
		System.out.println("1번");
		
		FormentMemberVO VO = dao.getWithPassword(id, pwd);
		log.info("vo"+VO);
		FormentMemberDTO DTO = modelMapper.map(VO, FormentMemberDTO.class);
		log.info("dto"+DTO);
		
		return DTO; 
	}
	
//	자동로그인 체크시 uuid에 임의문자열을 저장(수정)
	public void updateUuid(String id, String uuid) {
		dao.updateUuid(id, uuid);
	}
//	자동 로그인 상태일 경우 쿠키값을 읽어 db정보를 추추하는 메서드
	public FormentMemberDTO getByUUID(String uuid) {
		FormentMemberVO VO = dao.selectUUID(uuid);
		
		FormentMemberDTO DTO = modelMapper.map(VO, FormentMemberDTO.class);
		return DTO;
	}
	
	public FormentMemberDTO findId(String name, String phone, String email) {
		FormentMemberVO vo = dao.findId(name, phone, email);
		FormentMemberDTO dto = modelMapper.map(vo, FormentMemberDTO.class);
		return dto;
	}
	public FormentMemberDTO findPwd(String id, String name, String phone, String email) {
		FormentMemberVO vo = dao.findPwd(id, name, phone, email);
		FormentMemberDTO dto = modelMapper.map(vo, FormentMemberDTO.class);
		return dto;
	}
	public int addMember(FormentMemberDTO dto) {
		
		FormentMemberVO vo = modelMapper.map(dto, FormentMemberVO.class);
		
		int result = dao.addMember(vo);
		
		return result;
	}
	public FormentMemberDTO findMember(String id) {
		FormentMemberVO vo = dao.findMeber(id);
		FormentMemberDTO dto = modelMapper.map(vo, FormentMemberDTO.class);
		return dto;
	}
}
