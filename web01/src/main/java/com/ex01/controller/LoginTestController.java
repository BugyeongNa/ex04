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

import com.ex01.dto.LoginMemberDTO;
import com.ex01.service.LoginMemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/loginuitest/*")
public class LoginTestController extends HttpServlet{
	
	private LoginMemberService loginMemberService = LoginMemberService.INSTACE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String action = req.getPathInfo();
		String nextPage = null;
		
		if (action == null || action.equals("/login")) {
			
			nextPage ="/logintest/loginTest.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/findId")) {
			
			nextPage = "/logintest/idFindTest.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/viewId")) {
			
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			
			try {
				LoginMemberDTO dto = loginMemberService.findId(name, phone);
				req.setAttribute("id", dto);
				
				nextPage = "/logintest/idView.jsp";
				req.getRequestDispatcher(nextPage).forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				nextPage = "/loginuitest/findId?result=error";
				req.getRequestDispatcher(nextPage).forward(req, resp);
			}
			
			
		} else if (action.equals("/findPwd")) {
			
			nextPage = "/logintest/pwdFindTest.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/viewPwd")) {
			 
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			
			try {
				LoginMemberDTO dto = loginMemberService.findPwd(id, name, phone);
				req.setAttribute("pwd", dto);
				
				nextPage = "/logintest/pwdView.jsp";
				req.getRequestDispatcher(nextPage).forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				nextPage = "/loginuitest/findPwd?result=error";
				req.getRequestDispatcher(nextPage).forward(req, resp);
			}
			
		} else if (action.equals("/logout")) {
			Cookie kc = new Cookie("remember", null); 
	    	kc.setMaxAge(0);
	    	kc.setPath("/");
	    	resp.addCookie(kc);
			
			HttpSession session = req.getSession();
			
			session.removeAttribute("loginTest");
			session.invalidate();
			
			nextPage ="/logintest/loginTest.jsp";
			resp.sendRedirect(req.getContextPath()+nextPage);
		
		} else if (action.equals("/boardMove")) {
			
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String login_auto = req.getParameter("auto");
			System.out.println(login_auto);
			System.out.println("-----");
			
			boolean rememberMe = (login_auto != null) && (login_auto.equals("on"));
			
			try {
				LoginMemberDTO loginMemberDTO = LoginMemberService.INSTACE.login(id, pwd);
				
				if(rememberMe) {
					String uuid = UUID.randomUUID().toString();
					
					LoginMemberService.INSTACE.updateUuid(id, uuid);
					loginMemberDTO.setUuid(uuid);
					
					Cookie rememberCookie = new Cookie("remember", uuid);
					rememberCookie.setMaxAge(7*24*24*60);
					rememberCookie.setPath("/");
					
					resp.addCookie(rememberCookie);
				}
				
				HttpSession session = req.getSession();
				session.setAttribute("loginTest", loginMemberDTO);
				
				nextPage =  "/loginuitest/board/boardList";
				resp.sendRedirect(req.getContextPath()+ nextPage);
			
			} catch (Exception e) {
				nextPage = "/loginuitest/login?result=error";
				resp.sendRedirect(req.getContextPath()+ nextPage);
			}
		} else if (action.equals("/board/boardList")) {
			
			nextPage = "/logintest/boardList.jsp";
			resp.sendRedirect(req.getContextPath()+nextPage);
		} else {
			
			nextPage ="/logintest/loginTest.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
		}
	}
}
