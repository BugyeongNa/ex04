package com.ex01.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@WebServlet("/logout")
public class LogoutController extends HttpServlet{
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("logout...post");
		doHandler(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("logout... get");
		doHandler(req, resp);
	
	}
	
	protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.info("logout...");
	
		Cookie kc = new Cookie("remember-me", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
    	kc.setMaxAge(0); // 유효시간을 0으로 설정
    	kc.setPath("/");
    	resp.addCookie(kc);
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("loginInfo");
		session.invalidate();
		
		resp.sendRedirect(req.getContextPath()+"/");
		
	
		

	
	}
}
