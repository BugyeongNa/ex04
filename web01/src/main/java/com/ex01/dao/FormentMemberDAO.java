package com.ex01.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ex01.domain.FormentMemberVO;

import lombok.Cleanup;

public class FormentMemberDAO {
	public FormentMemberVO getWithPassword(String id, String pwd){
		FormentMemberVO VO = null;
		try {
			String sql = "SELECT * FROM tb_formentmem WHERE id = ? AND pwd = ?";
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		VO = FormentMemberVO.builder()
				.id(rs.getString(1))
				.pwd(rs.getString(2))
				.name(rs.getString(3))
				.phone(rs.getString(4))
				.email(rs.getString(5))
				.build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("DAO VO" +VO);
		return VO;
	}
	
	public void updateUuid(String id, String uuid){
		try {String sql = "update tb_formentmem set uuid=? where id=?";
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uuid);
		pstmt.setString(2, id);
		
		pstmt.executeUpdate();
			
		} catch (Exception e) {
		}
		
	}
	
	public FormentMemberVO selectUUID(String uuid) {
		
		FormentMemberVO VO = null;
		String sql = "SELECT * FROM tb_formentmem WHERE UUID = ?";
		try {
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uuid);
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		VO = FormentMemberVO.builder()
				.id(rs.getString(1))
				.pwd(rs.getString(2))
				.name(rs.getString(3))
				.phone(rs.getString(4))
				.email(rs.getString(5))
				.birthday(rs.getDate(6).toLocalDate())
				.joinday(rs.getDate(7).toLocalDate())
				.build();
		
			
		} catch (Exception e) {
		}
		
		return VO;
	}
	
	public FormentMemberVO findId (String name, String phone, String email) {
		FormentMemberVO VO = null; 
		try {
			String sql = "SELECT id FROM tb_formentmem WHERE name = ? AND (phone = ? OR email = ?)";
			@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			VO = FormentMemberVO.builder()
							.id(rs.getString("id"))
							.build();
			
		} catch (Exception e) {}
		
		return VO;
	}
	
	public FormentMemberVO findPwd (String id, String name, String phone, String email) {
		FormentMemberVO VO = null; 
		try {
			String sql = "SELECT pwd FROM tb_formentmem WHERE id =? and name = ? AND (phone = ? OR email = ?) ";
			@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			pstmt.setString(3, email);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			VO = FormentMemberVO.builder()
							.pwd(rs.getString("pwd"))
							.build();
			
		} catch (Exception e) {}
		
		return VO;
	}
	
	public int addMember(FormentMemberVO vo) {
		int result=0;
		try {
			String sql =
					"""
					INSERT INTO tb_formentmem (id, pwd, NAME, phone, email, birthday)
					 VALUES(?, ?, ?, ?, ?, ?);
	 
					""";
			
			@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getEmail());
			pstmt.setDate(6,Date.valueOf(vo.getBirthday()));
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {}

		return result;
		
	}
	
	public FormentMemberVO findMeber (String id) {
		FormentMemberVO vo = null;
		try {
			String sql = "select * from tb_formentmem where id=?";
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			vo = FormentMemberVO.builder()
					.id(rs.getString("id"))
					.pwd(rs.getString("pwd"))
					.name(rs.getString("name"))
					.phone(rs.getString("phone"))
					.email(rs.getString("email"))
					.birthday(rs.getDate("birthday").toLocalDate())
					.joinday(rs.getDate("joinday").toLocalDate())
					.build();
		} catch (Exception e) {
		}
		return vo;
	}
	

}
