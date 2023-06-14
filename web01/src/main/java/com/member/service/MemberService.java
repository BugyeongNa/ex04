package com.member.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.modelmapper.ModelMapper;

import com.ex01.dao.ConnectionOracleUtil;
import com.ex01.dao.MemberDAO;
import com.ex01.domain.MemberVO;
import com.ex01.domain.TMemberVO;
import com.ex01.dto.MemberDTO;
import com.ex01.dto.TMemberDTO;
import com.ex01.util.MapperUtil;
import com.member.mapper.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum MemberService {
	INSTANCE;
	
	private ModelMapper modelMapper;
	private MemberMapper memberMapper;
	private SqlSessionFactory factory;
	private SqlSession session;
	
	private MemberService() {
		try {
			modelMapper = MapperUtil.INSTANCE.get();
			factory = ConnectionOracleUtil.INSTANCE.getSqlSessionFactory();
			session = factory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
//	회원목록 서비스
	public List<TMemberDTO> memberList(){
		
		
		memberMapper = session.getMapper(MemberMapper.class);
		
		List<TMemberVO> memberList = memberMapper.selectAll();
		
		List<TMemberDTO> dtoList = memberList.stream()
									.map(vo -> modelMapper.map(vo, TMemberDTO.class))
									.collect(Collectors.toList());
		
		log.info("\n mybatis mapper memberVO(): "+ memberList);
		
		return dtoList;
	}

//	회원등록 서비스
	public int addMember(TMemberDTO dto) {
		TMemberVO vo = modelMapper.map(dto, TMemberVO.class);
		int insertOK = memberMapper.addMember(vo);
		session.commit();
		return insertOK;
	}
	
//	회원조회 서비스
	public TMemberDTO findMember(String id) {
		TMemberVO vo = memberMapper.memberSelectOne(id);
		TMemberDTO dto = modelMapper.map(vo, TMemberDTO.class);
		return dto;
	}
	
//	회원수정 서비스
	public int modifyMember(TMemberDTO dto) {
		int updateOK= 0;
		TMemberVO vo = modelMapper.map(dto, TMemberVO.class);
		updateOK = memberMapper.memberUpdate(vo);
		session.commit();
		return updateOK;
	}
	
//	회원삭제 서비스
	public int deleteMember(String id) {
		int deleteOK = 0;
		deleteOK = memberMapper.memberDelete(id);
		session.commit();
		return deleteOK;
	}
	
//	ID중복 체크
	public Boolean checkID(String id) {
		String isCheck = memberMapper.checkID(id);
//		'true', 'false' => true, false로 변환
		return Boolean.parseBoolean(isCheck);
	}
	
////	로그인 서비스 
//	public MemberDTO login(String id, String pw) throws Exception {
//		MemberVO memberVO = dao.getWithPassword(id, pw);
//		
//		MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
//		
//		return memberDTO; 
//	}
//	
////	자동로그인 체크시 uuid에 임의문자열을 저장(수정)
//	public void updateUuid(String id, String uuid) throws Exception {
//		dao.updateUuid(id, uuid);
//	}
////	자동 로그인 상태일 경우 쿠키값을 읽어 db정보를 추추하는 메서드
//	public MemberDTO getByUUID(String uuid) throws Exception{
//		MemberVO vo = dao.selectUUID(uuid);
//		
//		MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);
//		return memberDTO;
//	}
}
