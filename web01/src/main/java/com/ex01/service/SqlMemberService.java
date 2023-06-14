package com.ex01.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.ex01.dao.SqlMemberDAO;
import com.ex01.domain.SqlMemberVO;
import com.ex01.dto.SqlMemberDTO;
import com.ex01.util.MapperUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum SqlMemberService {
	INSTANCE;
	
	private SqlMemberDAO sqlDao;
	private ModelMapper modelMapper;
	
	private SqlMemberService() {
		sqlDao = new SqlMemberDAO();
		modelMapper = MapperUtil.INSTANCE.get();
	}
	
	// 회원목록 서비스
	public List<SqlMemberDTO> memberList(){
		List<SqlMemberVO> memberList = sqlDao.memberList();
		
		List<SqlMemberDTO> sqlList = memberList.stream()
							.map(vo -> modelMapper.map(vo, SqlMemberDTO.class))
							.collect(Collectors.toList());
		return sqlList;
	}
	
	// 회원등록
	public int addMember(SqlMemberDTO dto) {
		log.info("service dto:"+dto);
		
		SqlMemberVO vo = modelMapper.map(dto, SqlMemberVO.class);
		log.info("controller vo:"+vo);
		int result = sqlDao.addMember(vo);
		
		return result;
	}
	// 회원조회
	public SqlMemberDTO findMember(String name) {
		SqlMemberVO vo = sqlDao.findMember(name);
		SqlMemberDTO dto = modelMapper.map(vo, SqlMemberDTO.class);
		return dto;
	}
	// ID 중복 체크
		public Boolean checkID(String id) {

			Boolean isCheck = sqlDao.checkId(id);
			
			return isCheck;
	}
	
	// 회원수정
	public int  modifyMember(SqlMemberDTO dto) {
		log.info("service dto:"+dto);
		
		SqlMemberVO vo = modelMapper.map(dto, SqlMemberVO.class);
		log.info("controller vo:"+vo);
		int result = sqlDao.modifyMember(vo);
		
		return result;
	}
	// 회원삭제
	public int deleteMember(String id) {
		int result = sqlDao.deleteMember(id);
		
		return result;
	}
}










