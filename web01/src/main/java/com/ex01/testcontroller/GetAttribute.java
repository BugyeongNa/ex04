package com.ex01.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/get")
//@WebServlet("*.do")
//@WebServlet("/*")
//@WebServlet("/hello/*.do")// url맵핑에러
public class GetAttribute extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		ServletContext ctx = getServletContext();
		HttpSession session = req.getSession();
		
		String ctxMsg = (String) ctx.getAttribute("context");
		String sesMsg = (String) session.getAttribute("session");
		String reqMsg = (String) req.getAttribute("request");
		
		out.print("context값: "+ctxMsg+"<br>");
		out.print("session값: "+sesMsg+"<br>");
		out.print("request값: "+reqMsg+"<br>");
		out.print("<hr>");
		
		String ctxPath = req.getContextPath();
		String ctxUrl = req.getRequestURL().toString();
		String ctxUri = req.getRequestURI();
		String ctxMapping = req.getServletPath();
				
				
		out.print("<div>컨텍스트 이름: "+ctxPath+"</div>");
		out.print("<div>컨텍스트 전체경로: "+ctxUrl+"</div>");
		out.print("<div>컨텍스트 경로: "+ctxUri+"</div>");
		out.print("<div>매핑이름: "+ctxMapping+"</div>");
		
		
	}
}
