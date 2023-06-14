package com.ex01.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieset")
public class SetCookieValue extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
	
		Date d = new Date();
		
//		쿠키 객체 생성
		Cookie c = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍에서 쿠키파일 테스트","utf-8"));
		
//		setMaxAge(): 쿠키 유효시간 설정
//		c.setMaxAge(24*60*60);
		c.setMaxAge(-1);// 유효시간이 음수이면 Session쿠키로 설정됨
		
//		생성된 쿠키를 브라우저로 전송
		resp.addCookie(c);
		
		out.println("현재 시간: "+d);
		out.println("현재시간을 Cookie로 저장합니다. ");
	}
}


//	세션 트래킹
//	HTTP프로토클 방식으로 통신하는 웹페이지들은 서로 어떤 정보도 공유하지 않는다는 특징을 가지고 있다
//	웹페이지 사이의 상태나 정보를 공유하려면 프로그래머가 세션트래킹(Session Tracking)이라는 웹페이지 연결기능을 구현
//
//	서버-클라이언트 통신시 stateless방식으로 통신
//	- <hidden>태그
//	- URL Rewriting: GET방식으로 URL뒤에 정보를 붙여서 다른 페이지 전송
//	- 쿠키: 클라이언트 PC의 Cookie파일에 정보를 저장한 후 웹페이지를 공유
//	- 세션: 서버 메모리에 정보를 저장한 후 웹페이지들이 공유
//
//	쿠키
//	서버에서 자동으로 생성하는 쿠키: Session쿠키, 경로:"/", 브라우저 메모리에 생성, 브라우저 종료시 삭제(사이트 접속시 Session 인증)
//	개발자가 생성하는 쿠키: Persistence쿠키, 파일로 생성, 유효기간 직접 지정(로그인유뮤, 팝업창 등에 사용)
//
//	1. 브라우저 사이트에 접속
//	2. 서버는 정보를 저장한 쿠키를 생성
//	3. 생성된 쿠키를 브라우저에 전송
//	4. 브라우저는 서버로부터 받은 쿠키 정보를 쿠키 파일에 저장
//	5. 브라우저가 다시 접속해 서버가 브라우저에게 쿠키전송을 요청하면 쿠키정보를 서버에 넘겨줌
//	6. 서버 쿠키 정보를 이용해 작업