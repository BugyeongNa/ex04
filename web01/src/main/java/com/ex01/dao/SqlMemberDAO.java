package com.ex01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.board.util.OracleConnectUtil;
import com.ex01.domain.SqlMemberVO;

import lombok.Cleanup;

public class SqlMemberDAO {
	
	// 회원목록
	public List<SqlMemberVO> memberList() {
		
		List<SqlMemberVO> memberList = new ArrayList<>();
		
		try {
			String sql = "select * from tb_sqlmember";
			
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SqlMemberVO vo = SqlMemberVO.builder()
						.id(rs.getString("id"))
						.pwd(rs.getString("pwd"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.joindate(rs.getDate("joinDate").toLocalDate()) 
						.memno(rs.getInt("memno"))
						.build();
				
				memberList.add(vo);
			}// while 
		}catch (Exception e) {}
		
		return memberList;
		
	}
	
	// 회원등록
	public int addMember(SqlMemberVO vo) {
		int result=0;
		try {
			String sql = "insert into  tb_sqlmember values(?, ?, ?, ?, ?, sysdate, sql_seq.nextval)";
					  
			
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getEmail());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {}

		return result;
		
	}
	// 회원수정
	public int modifyMember(SqlMemberVO vo) {
		int result = 0;
		try {
			String sql = "update tb_sqlmember set pwd = ?, name = ?, phone = ?, email = ? where id = ?";
			
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
		}
		return result;
	}
	// 회원조회
	public SqlMemberVO findMember(String name) {
		SqlMemberVO vo = null; 
		try {
			String sql = "select * from tb_sqlmember where name=?";
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			vo = SqlMemberVO.builder()
					.id(rs.getString("id"))
					.pwd(rs.getString("pwd"))
					.name(rs.getString("name"))
					.phone(rs.getString("phone"))
					.email(rs.getString("email"))
					.joindate(rs.getDate("joindate").toLocalDate())
					.memno(rs.getInt("memno"))
					.build();
			
		} catch (Exception e) {}
		
		return vo;
	}
	
	// 중복ID체크
	public Boolean checkId(String id) {
		Boolean isCheck = false; 
		try {
			String sql = "select decode(count(*),1,'true','false')as isCheck from tb_sqlmember where id  = ?";
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			isCheck = Boolean.parseBoolean(rs.getString("isCheck"));
			
		} catch (Exception e) {}
		
		return isCheck;
	}
	
	// 회원삭제
	public int deleteMember(String id) {
		int result = 0;
		String sql = "delete from tb_sqlmember where id = ?";
		
		try {
			@Cleanup Connection conn = OracleConnectUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
		}
		
		return result;
	}
}











