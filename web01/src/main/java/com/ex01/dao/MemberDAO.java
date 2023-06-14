package com.ex01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ex01.domain.MemberVO;

import lombok.Cleanup;

public class MemberDAO {
	public MemberVO getWithPassword(String id, String pw) throws Exception{
		MemberVO memberVO = null;
		String sql = "SELECT * FROM tbl_member WHERE id = ? AND pw = ?";
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		memberVO = MemberVO.builder()
				.id(rs.getString(1))
				.pw(rs.getString(2))
				.name(rs.getString(3))
				.build();
		
		return memberVO;
	}
	
//	자동 로그인 체크시 수행하는 메서드
	public void updateUuid(String id, String uuid) throws Exception{
		String sql = "update tbl_member set uuid=? where id=?";
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uuid);
		pstmt.setString(2, id);
		
		pstmt.executeUpdate();
	}
	
//	쿠키값으로 DB정보 추출하는 매서드
	public MemberVO selectUUID(String uuid) throws Exception{
		String sql = "SELECT * FROM tbl_member WHERE UUID = ?";
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uuid);
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		MemberVO memberVO = MemberVO.builder()
				.id(rs.getString(1))
				.pw(rs.getString(2))
				.name(rs.getString(3))
				.uuid(rs.getString(4))
				.build();
		
		return memberVO;
	}
}
