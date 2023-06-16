package com.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ex01.domain.TMemberVO;
import com.ex01.dto.TMemberDTO;

@Mapper
public interface MemberSqlDivMapper {
	
//	동적쿼리 추가
	
	@SelectProvider(type = MemberSQL.class, method = "listMember")
	public List<TMemberVO> listMember();
	
	@SelectProvider(type = MemberSQL.class, method = "findMember")
	public TMemberVO findMember(String id);
	
	@InsertProvider(type = MemberSQL.class, method = "addMember")
	public void addMember(TMemberVO vo);
	
	@UpdateProvider(type = MemberSQL.class, method = "modifyMember")
	public void modifyMember(TMemberVO vo);
	
	@DeleteProvider(type = MemberSQL.class, method = "deleteMember")
	public void deleteMember(String id);
	
	@SelectProvider(type = MemberSQL.class, method = "checkID")
	public String checkId(String id);
	
	
//	@Select(MemberSQL.LIST_MEMBER)
//	public List<TMemberVO> selectAll1();
//	
//	@SelectProvider(type = MemberSQL.class, method = "listMember")
//	public List<TMemberVO> selectAll2();
//
//	@InsertProvider(type = MemberSQL.class, method = "addMember2")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
//	public void addMember2(TMemberVO vo);
//	
//	@InsertProvider(type = MemberSQL.class, method = "addMember3")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
//	public void addMember3(TMemberVO vo);
//	
//	@InsertProvider(type = MemberSQL.class, method = "addMember4")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
//	public void addMember4(TMemberVO vo);
//	
//	@InsertProvider(type = MemberSQL.class, method = "addMember5")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
//	public void addMember5(TMemberVO vo);
//	
//	@SelectProvider(type = MemberSQL.class, method = "findMembersByName")
//	public List<TMemberVO> findMembersByName(String name);
//	
//	@SelectProvider(type = MemberSQL.class, method = "viewMemberId")
//	public TMemberVO viewMemberId(String id);
//	@Select("select sysdate from dual")
//	public String getTime();
//	
//	@Select("select * from t_member")
//	public List<TMemberVO> selectAll();
//	
////	Java객체의 속성명과 매개변수 #{}부분의 필드명이 일치해야 값이 전달됨.
//	@Insert("insert into t_member (id, pwd, name, email) values (#{id}, #{pwd}, #{name}, #{email})")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
//	public int addMember(TMemberVO vo);
//	
//	@Select("select * from t_member where id = #{id}")
//	public TMemberVO memberSelectOne(String id);
//
//	String update_sql = """
//			update t_member set pwd=#{pwd}, name=#{name}, email=#{email} where id=#{id}
//			""";
//	@Update(update_sql)
//	public int memberUpdate(TMemberVO vo);
//	
//	@Delete("delete from t_member where id = #{id}")
//	public int memberDelete(@Param("id") String id);
//	
////	id중복 체크
//	String checkId = """
//			select decode(count(*),1,'true','false') as is Check
//			from t_member where id = #{id}
//			""";
//	@Select(checkId)
//	public String checkID(@Param("id") String id);
//	
////	동적쿼리 추가
//	@Select(MemberSQL.LIST_MEMBER)
//	public List<TMemberVO> findMembersAll();
	

	
	
	
	
	
}
