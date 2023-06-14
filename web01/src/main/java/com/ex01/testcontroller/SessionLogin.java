package com.ex01.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/testlogin")
public class SessionLogin extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		
		
		log.info("아이디: "+user_id);
		log.info("비밀번호: "+user_pw);
		
//		세션 생성(데이터 유지)
		HttpSession session = req.getSession();
		if(session.isNew()) {// 최초 생성시
			if(user_id != null) {
				session.setAttribute("user_id", user_id);
				out.println("<a href='login'>로그인 상태 확인</a>");
			}else {
				out.println("<a href='/web01/login/login.html'>다시 로그인 하세요!!</a>");
			}
		}else {// 로그인 상태일 경우 처리부분
			user_id = (String) session.getAttribute("user_id");
			if(user_id != null && user_id.length() != 0) {
				out.println("안녕하세요 "+user_id+"님!!!");
			}else {
				out.println("<a href='/web01/login/login.html'>다시 로그인 하세요!!</a>");
				session.invalidate();// 세션 소멸
			}
		}
		
	}


}
