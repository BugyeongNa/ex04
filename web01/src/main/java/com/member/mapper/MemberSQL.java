package com.member.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import com.ex01.domain.TMemberVO;
import com.ex01.dto.TMemberDTO;

public class MemberSQL {
//	public static final String LIST_MEMBER = """
//			select * from t_member
//			""";
//	
//	public static final String ADD_MEMBER = """
//			t_member (id,pwd,name,email)
//			""";
	
	public String listMember() {
		return new SQL() {{
			SELECT(" * ");
			FROM(" t_member ");
		}}.toString();
	}
	
	public String findMember() {
		return new SQL() {{
			SELECT(" * ");
			FROM(" t_member ");
			WHERE(" id = #{id} ");
		}}.toString();
	}
	
	public String addMember() {
		return new SQL() {{
			INSERT_INTO(" t_member ");
			INTO_COLUMNS("id", "pwd", "name", "email");
			INTO_VALUES("#{id}", "#{pwd}", "#{name}", "#{email}");
		}}.toString();
	}
	
	public String modifyMember() {
		return new SQL() {{
			UPDATE(" t_member ");
			SET("pwd = #{pwd}", "name = #{name}", "email = #{emil}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	public String deleteMember() {
		return new SQL() {{
			DELETE_FROM(" t_ member ");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	public String checkID() {
		return new SQL() {{
			SELECT(" decode(count(*),1,'true','false') as isCheck ");
			FROM(" t_member ");
			WHERE(" id = #{id}");
		}}.toString();
	}
	
	
//	public String findMembersName(String memberName) {
//
//				StringBuilder query = new StringBuilder();
//		
//		query.append("select * from t_member");
//		
//		if(memberName != null && memberName.length() > 0) {
//			query.append(" where ");
//			query.append("name like "+memberName);
//		}
//		return query.toString();
//	}
//	public String findMembersByName(String name) {
//
//		return new SQL() {{
//			SELECT(" * ");
//			FROM(" t_member ");
//			if (name != null) {
//				WHERE(" name like #{name} || '%' ");
//			}
//			ORDER_BY("name desc");
//		}}.toString();
//	
//	}
	
//	public String addMember2() {
//		return new SQL()
//			.INSERT_INTO(" t_member ")
//			.INTO_COLUMNS("id", "pwd", "name", "email")
//			.INTO_VALUES("#{id}", "#{pwd}", "#{name}", "#{email}")
//			.toString();
//	}
//	
//	
//	
//	public String addMember4() {
//		return new SQL() {{
//				INSERT_INTO(" t_member ");
//				VALUES("id, pwd, name, email", "#{id}, #{pwd} , #{name}, #{email}");
//		}}.toString();
//	}
//	
//	public String addMember5() {
//		String sql = new SQL()
//				.INSERT_INTO(" t_member ")
//				.VALUES("id, pwd, name, email", "#{id}, #{pwd} , #{name}, #{email}")
//				.toString();
//		return sql;
//	}
}
