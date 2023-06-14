package com.ex01.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ex01.dto.MemberDTO;
import com.ex01.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("/login Get...");
		
		
//		로그인 페이지 전환
		req.getRequestDispatcher("/todo/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("/login Post...");
		
		req.setCharacterEncoding("utf-8");
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		String login_auto = req.getParameter("auto");
//		String str = user_id + user_pw;
		
		log.info("user_id => "+user_id);
		log.info("user_pw => "+user_pw);
		log.info("login_auto => "+login_auto);
		
//		자동로그인 체크항목이 on일 경우 처리
		boolean rememberMe = (login_auto != null) && (login_auto.equals("on"));
		log.info("rememberMe status: "+rememberMe);

	

//		db에 회원여부 확인후 => 로그인 상태로 전환 => 세션 작업
		try {
			MemberDTO memberDTO = MemberService.INSTACE.login(user_id, user_pw);
			
//			자동로그인 체크시 처리부분
			if(rememberMe) {// rememberMe true면 난수 발생
				String uuid = UUID.randomUUID().toString();
				log.info("UUID: "+uuid);
				
//				사용자가 로그인할때 임의의 문자열을 생성하고 이를 데이터베이스에
				MemberService.INSTACE.updateUuid(user_id, uuid);
				memberDTO.setUuid(uuid);
				
//				DB에 저장된 난수와 같은 문자열을 쿠키값 생성
				Cookie rememberCookie = new Cookie("remember-me", uuid);
				rememberCookie.setMaxAge(7*24*24*60);
				rememberCookie.setPath("/");
				
//				클라이언트에 쿠키전송
				resp.addCookie(rememberCookie);
			}
			
			
			HttpSession session = req.getSession();
			session.setAttribute("loginInfo", memberDTO);
			
			resp.sendRedirect(req.getContextPath()+ "/todo/list");
		
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath()+"/login?result=error");
		}
		
		
	}
}




//쿠키와 세션을 같이 활용하기
//
// 자동 로그인 준비
// - 사용자가 로그인할때 임의의 문자열을 생성하고 이를 데이터베이스에 보관
// - 쿠키에는 생성된 문자열을 값으로 삼고 유효기간은 1주일로 지정
// 
// 로그인 체크 방식
// - 현재 사용자의 HttpSession에 로그인 정보가 없는 경우에만 쿠키를 확인
// - 쿠키의 값과 데이터베이스의 값을 비교하고 같다면 사용자의 정보를 읽어와서
//   HttpSession에 사용자 정보를 추가
