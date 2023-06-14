package com.ex01.service;

import org.modelmapper.ModelMapper;

import com.ex01.dao.MemberDAO;
import com.ex01.domain.MemberVO;
import com.ex01.dto.MemberDTO;
import com.ex01.util.MapperUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum MemberService {
	INSTACE;
	
	private MemberDAO dao;
	private ModelMapper modelMapper;
	
	private MemberService() {
		dao = new MemberDAO();
		modelMapper = MapperUtil.INSTANCE.get();
	}
	
//	로그인 서비스 
	public MemberDTO login(String id, String pw) throws Exception {
		MemberVO memberVO = dao.getWithPassword(id, pw);
		
		MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
		
		return memberDTO; 
	}
	
//	자동로그인 체크시 uuid에 임의문자열을 저장(수정)
	public void updateUuid(String id, String uuid) throws Exception {
		dao.updateUuid(id, uuid);
	}
//	자동 로그인 상태일 경우 쿠키값을 읽어 db정보를 추추하는 메서드
	public MemberDTO getByUUID(String uuid) throws Exception{
		MemberVO vo = dao.selectUUID(uuid);
		
		MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);
		return memberDTO;
	}
}
