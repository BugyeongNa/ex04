package com.ex01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.ex01.domain.TMemberVO;
import com.ex01.dto.TMemberDTO;
import com.ex01.util.MapperUtil;
import com.ex01.util.MybatisManager;
import com.member.mapper.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class OracleDAOTest {

//	private TMemberDAO tdao;
	private ModelMapper modelMapper;
	private MemberMapper memberMapper;
	
	SqlSessionFactory factory , factoryXml;
	SqlSession session, sessionXml; 
	
	
	@BeforeEach
	public void read() {
//		tdao = new TMemberDAO();
		modelMapper = MapperUtil.INSTANCE.get();
		
//		hikariCP적용시 java source로 sqlSessionFactory객체 정의
		factory = ConnectionOracleUtil.INSTANCE.getSqlSessionFactory();

		try {
			session =  factory.openSession();
//			java 어노테이션으로 연결한 query문
			memberMapper = session.getMapper(MemberMapper.class);
		} catch (Exception e) {
		}

	}
	
	@BeforeEach
	public void readXml() {
		factoryXml = ConnectionOracleUtil.INSTANCE.getSqlSessionFactoryXML();
		sessionXml = MybatisManager.getInstance().openSession();
	}
	
	@After
	public void close() {
		session.close();
//		sessionXml.close();
	}
	
	@Test
	public void testGetTime() {
		try {
			String getTime = memberMapper.getTime();
			log.info(" getTime(): mybatis: "+getTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectAll() {
		try {
			List<TMemberVO> memberVO = memberMapper.selectAll();
			log.info("mybatis mapper memberVO(): "+memberVO);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testMemberRegister() {
			TMemberVO vo = TMemberVO.builder()
					.id("ysa")
					.pwd("1111")
					.name("양산아")
					.email("ysa@naver.com")
					.build();
//			mybatis에서 제공하는 sqlsession활용 테스트
			System.out.println("vo"+ vo);
			int insertResult = memberMapper.addMember(vo);
			session.commit();
			log.info("\n mybatis mapper insert(): "+ insertResult);
	
	}
	
	@Test
	public void testSelectOne() {
		TMemberVO vo = memberMapper.memberSelectOne("lee");
		log.info("\n mybatis mapper select one(): " + vo);
	}
	
	@Test
	public void testUpdate() {
		TMemberVO vo = TMemberVO.builder()
				.id("ysa")
				.pwd("1111")
				.name("양산아")
				.email("ysa@naver.com")
				.build();
		int updateOK = memberMapper.memberUpdate(vo);
		session.commit();
		log.info("\n mybatis mapper update(): "+ updateOK);
	}
	
	@Test
	public void testDelete() {
			int deleteOK = memberMapper.memberDelete("ysa");
			session.commit();
			log.info("\n mybatis mapper delete(): "+ deleteOK);	
	}
	
	@Test
	public void testFindMembersAll() {
		List<TMemberVO> vo = memberMapper.findMembersAll();
		log.info("\n mybatis mapper findMembersAll(): "+ vo);	
	}
	
	@Test
	public void testFindMembersAll2() {
		List<TMemberVO> vo = memberMapper.findMembersAll2();
		log.info("\n mybatis mapper findMembersAll2(): "+ vo);	
	}
	
	@Test
	public void testFindMembersByNameAll() {
		List<TMemberVO> vo = memberMapper.findMembersByName("이");
		log.info("\n mybatis mapper findMembersByNameAll(): "+ vo);
	}
	
	@Test
	public void testViewMemberId() {
		TMemberVO vo = memberMapper.viewMemberId("lee");
		log.info("\n mybatis mapper ViewMemberId(): "+ vo);
	}
	
	@Test
	public void testAddMember2() {
			
		TMemberVO vo = TMemberVO.builder()
					.id("ysa")
					.pwd("1111")
					.name("양산아")
					.email("ysa@naver.com")
					.build();
			System.out.println("vo"+ vo);
			memberMapper.addMember2(vo);
			System.out.println(vo);
			session.commit();
	}
	
	@Test
	public void testAddMember3() {
			
		TMemberVO vo = TMemberVO.builder()
					.id("ysa")
					.pwd("1111")
					.name("양산아")
					.email("ysa@naver.com")
					.build();
			System.out.println("vo"+ vo);
			memberMapper.addMember3(vo);
			System.out.println(vo);
			session.commit();
			
	}
	
	
	
	
//	xml
	@Test
	public void testGetTimeXml() {
		try {
			String getTime = sessionXml.selectOne("member.getTime");
			log.info(" getTime(): mybatis: "+getTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMemberList() {
		try {
			List<TMemberVO> vo = sessionXml.selectList("member.list");
			
			log.info("\n xml mapper memberList");
			vo.stream().forEach(System.out::println);
		} catch (Exception e) {
		}
	}
	
//	@Test
//	public void  testTime() throws Exception{
//		log.info(tdao.getTime());
//	}
//	
//	@Test
//	public void testMemberListAll() throws Exception{
//		List<TMemberVO> list = tdao.memberList();
//		list.forEach(vo -> {
//			log.info(vo);
//		});
//	}
//	
//	@Test
//	public void testAddMember() throws Exception{
////		vo -> db전달
//		TMemberVO vo = TMemberVO.builder()
//								.id("nabu1")
//								.pwd("7890")
//								.name("나부경")
//								.email("nabu7890@naver.com")
//								.build();
//		
//		tdao.addMember(vo);
//	}
}
