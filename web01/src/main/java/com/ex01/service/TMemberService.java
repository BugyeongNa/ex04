package com.ex01.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.ex01.dao.TMemberDAO;
import com.ex01.domain.MemberVO;
import com.ex01.domain.TMemberVO;
import com.ex01.dto.MemberDTO;
import com.ex01.dto.TMemberDTO;
import com.ex01.util.MapperUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum TMemberService {
	INSTANCE;
	
	private TMemberDAO tmemberDao;
	private ModelMapper modelMapper;
	
	private TMemberService() {
		tmemberDao = new TMemberDAO();
		modelMapper = MapperUtil.INSTANCE.get();
	}
	
	// 회원목록 서비스
	public List<TMemberDTO> memberList(){
		List<TMemberVO> memberList = tmemberDao.memberList();
		// vo-> dto
		List<TMemberDTO> dtoList = memberList.stream()
							.map(vo -> modelMapper.map(vo, TMemberDTO.class))
							.collect(Collectors.toList());
		return dtoList;
	}
	
	// 회원등록
	public int addMember(TMemberDTO dto) {
		log.info("service dto:"+dto);
		
		TMemberVO vo = modelMapper.map(dto, TMemberVO.class);
		log.info("controller vo:"+vo);
		int result = tmemberDao.addMember(vo);
		
		return result;// 0:비정상, 1:정상처리
	}
	// 회원조회
	public TMemberDTO findMember(String id) {
		TMemberVO vo = tmemberDao.findMember(id);
		TMemberDTO dto = modelMapper.map(vo, TMemberDTO.class);
		return dto;
	}
	// ID 중복 체크
		public Boolean checkID(String id) {

			Boolean isCheck = tmemberDao.checkId(id);
			
			return isCheck;
	}
	
	// 회원수정
	public int  modifyMember(TMemberDTO dto) {
		log.info("service dto:"+dto);
		
		TMemberVO vo = modelMapper.map(dto, TMemberVO.class);
		log.info("controller vo:"+vo);
		int result = tmemberDao.modifyMember(vo);
		
		return result;// 0:비정상, 1:정상처리
	}
	// 회원삭제
	public int deleteMember(String id) {
		int result = tmemberDao.deleteMember(id);
		
		return result;
	}
	
//	자동로그인 체크시 uuid에 임의문자열을 저장(수정)
	public void updateUuid(String id, String uuid) throws Exception {
		tmemberDao.updateUuid(id, uuid);
	}
//	자동 로그인 상태일 경우 쿠키값을 읽어 db정보를 추추하는 메서드
	public TMemberDTO getByUUID(String uuid) throws Exception{
		TMemberVO vo = tmemberDao.selectUUID(uuid);
		
		TMemberDTO memberDTO = modelMapper.map(vo, TMemberDTO.class);
		return memberDTO;
	}
}










