package com.ex01.service;

import org.modelmapper.ModelMapper;

import com.ex01.dao.LoginMemberDAO;
import com.ex01.domain.LoginMemberVO;
import com.ex01.dto.LoginMemberDTO;
import com.ex01.util.MapperUtil;

public enum LoginMemberService {
INSTACE;
	
	private LoginMemberDAO Ldao;
	private ModelMapper modelMapper;
	
	private LoginMemberService() {
		Ldao = new LoginMemberDAO();
		modelMapper = MapperUtil.INSTANCE.get();
	}
	
//	로그인 서비스 
	public LoginMemberDTO login(String id, String pwd) {
		LoginMemberVO loginMemberVO = Ldao.getWithPassword(id, pwd);
		
		LoginMemberDTO loginMemberDTO = modelMapper.map(loginMemberVO, LoginMemberDTO.class);
		
		return loginMemberDTO; 
	}
	
//	자동로그인 체크시 uuid에 임의문자열을 저장(수정)
	public void updateUuid(String id, String uuid) {
		Ldao.updateUuid(id, uuid);
	}
//	자동 로그인 상태일 경우 쿠키값을 읽어 db정보를 추추하는 메서드
	public LoginMemberDTO getByUUID(String uuid) {
		LoginMemberVO loginMemberVO = Ldao.selectUUID(uuid);
		
		LoginMemberDTO loginMemberDTO = modelMapper.map(loginMemberVO, LoginMemberDTO.class);
		return loginMemberDTO;
	}
	
	public LoginMemberDTO findId(String name, String phone) {
		LoginMemberVO vo = Ldao.findId(name, phone);
		LoginMemberDTO dto = modelMapper.map(vo, LoginMemberDTO.class);
		return dto;
	}
	public LoginMemberDTO findPwd(String id, String name, String phone) {
		LoginMemberVO vo = Ldao.findPwd(id, name, phone);
		LoginMemberDTO dto = modelMapper.map(vo, LoginMemberDTO.class);
		return dto;
	}
	public int addMember(LoginMemberDTO dto) {
		
		LoginMemberVO vo = modelMapper.map(dto, LoginMemberVO.class);
		
		int result = Ldao.addMember(vo);
		
		return result;
	}
}
