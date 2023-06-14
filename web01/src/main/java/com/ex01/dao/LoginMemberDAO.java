package com.ex01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ex01.domain.LoginMemberVO;

import lombok.Cleanup;

public class LoginMemberDAO {
	public LoginMemberVO getWithPassword(String id, String pwd){
		LoginMemberVO loginMemberVO = null;
		try {String sql = "SELECT * FROM login_member WHERE id = ? AND pwd = ?";
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		loginMemberVO = LoginMemberVO.builder()
				.id(rs.getString(1))
				.pwd(rs.getString(2))
				.name(rs.getString(3))
				.phone(rs.getString(4))
				.build();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return loginMemberVO;
	}
	
	public void updateUuid(String id, String uuid){
		try {String sql = "update login_member set uuid=? where id=?";
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uuid);
		pstmt.setString(2, id);
		
		pstmt.executeUpdate();
			
		} catch (Exception e) {
		}
		
	}
	
	public LoginMemberVO selectUUID(String uuid) {
		
		LoginMemberVO loginMemberVO = null;
		String sql = "SELECT * FROM login_member WHERE UUID = ?";
		try {
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uuid);
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		loginMemberVO = LoginMemberVO.builder()
				.id(rs.getString(1))
				.pwd(rs.getString(2))
				.name(rs.getString(3))
				.phone(rs.getString(4))
				.uuid(rs.getString(5))
				.build();
		
			
		} catch (Exception e) {
		}
		
		return loginMemberVO;
	}
	
	public LoginMemberVO findId (String name, String phone) {
		LoginMemberVO loginMemberVO = null; 
		try {
			String sql = "SELECT id FROM login_member WHERE name = ? and phone = ? ";
			@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			loginMemberVO = LoginMemberVO.builder()
							.id(rs.getString("id"))
							.build();
			
		} catch (Exception e) {}
		
		return loginMemberVO;
	}
	
	public LoginMemberVO findPwd (String id, String name, String phone) {
		LoginMemberVO loginMemberVO = null; 
		try {
			String sql = "SELECT pwd FROM login_member WHERE id =? and name = ? and phone = ? ";
			@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			loginMemberVO = LoginMemberVO.builder()
							.pwd(rs.getString("pwd"))
							.build();
			
		} catch (Exception e) {}
		
		return loginMemberVO;
	}
	
	public int addMember(LoginMemberVO vo) {
		int result=0;
		try {
			String sql = " insert INTO login_member (id,pwd,name,phone) "
					   + " values (?,?,?,?)";
			
			@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {}

		return result;
		
	}
	
}
