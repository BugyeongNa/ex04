package com.ex01.ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ex01.dao.ConnectionUtil;
import com.ex01.dto.LoginMemberDTO;
import com.ex01.service.LoginMemberService;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/testmembers")
public class AjaxJsonMember extends HttpServlet{

	private LoginMemberService loginMemberService = LoginMemberService.INSTACE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter writer = resp.getWriter();
		try {
			String sql = "SELECT id, pwd, name, phone FROM login_member";
			
			@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			JSONArray jsonMember = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getString("id"));
				obj.put("pwd", rs.getString("pwd"));
				obj.put("name", rs.getString("name"));
				obj.put("phone", rs.getString("phone"));
				
				jsonMember.add(obj);
				
			}
			
			String jsonInfo = jsonMember.toJSONString();
			writer.print(jsonInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LoginMemberDTO dto = new LoginMemberDTO();
		dto.setName(req.getParameter("name"));
		dto.setId(req.getParameter("id"));
		dto.setPwd(req.getParameter("pwd"));
		dto.setPhone(req.getParameter("phone"));
		
		int result = loginMemberService.addMember(dto);
		
	}
}
