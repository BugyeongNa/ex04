package com.ex01.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/set")
public class SetAttribute extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		String ctxMsg = "context에 바인딩";
		String sesMsg = "session에 바인딩";
		String reqMsg = "request에 바인딩";
		
		ServletContext ctx = getServletContext();
		HttpSession session = req.getSession();
		
		ctx.setAttribute("context", ctxMsg);
		session.setAttribute("session", sesMsg);
		req.setAttribute("request", reqMsg);
		
		out.print("바인딩을 수행");
	}
}


//	서블릿속성과 스코프
//	ServletContext: 애플리케이션 전체서 접근가능
//	HttpSession: 브라우저에서만 접근가능
//	HttpservletRequest: 요청/응답에 대해서만 접근 가능
//
//	- 로그인 상태 유지기능, 장바구니 기능, MVC의 Model과 View의 데이터 전달기능
