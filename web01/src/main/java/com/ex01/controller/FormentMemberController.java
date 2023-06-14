package com.ex01.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ex01.dto.FormentMemberDTO;
import com.ex01.service.FormentMemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/testforment/*")
public class FormentMemberController extends HttpServlet{
	
	private FormentMemberService Service = FormentMemberService.INSTANCE;
	
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
		
		if (action == null || action.equals("/main")) {
			
			nextPage ="/forment/main.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/login")) {
			
			nextPage = "/forment/login.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/idFind")) {
			
			nextPage = "/forment/idFind.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/idView")) {
			
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			
			System.out.println(name);
			System.out.println(phone);
			System.out.println(email);
			
			try {
				FormentMemberDTO dto = Service.findId(name, phone, email);
				req.setAttribute("id", dto);
				
				nextPage = "/forment/idView.jsp";
				req.getRequestDispatcher(nextPage).forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				nextPage = "/testforment/idFind?result=error";
				req.getRequestDispatcher(nextPage).forward(req, resp);
			}
			
			
		} else if (action.equals("/pwdFind")) {
			
			nextPage = "/forment/pwdFind.jsp";
			resp.sendRedirect(req.getContextPath()+nextPage);
			
		} else if (action.equals("/pwdView")) {
			 
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			
			try {
				FormentMemberDTO dto = Service.findPwd(id, name, phone, email);
				req.setAttribute("pwd", dto);
				
				nextPage = "/forment/pwdView.jsp";
				req.getRequestDispatcher(nextPage).forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				nextPage = "/testforment/pwdFind?result=error";
				req.getRequestDispatcher(nextPage).forward(req, resp);
			}
			
		} else if (action.equals("/join")) {
			
			nextPage = "/forment/join.jsp";
			resp.sendRedirect(req.getContextPath()+nextPage);
			
		} else if (action.equals("/joinAdd")) {
			
			FormentMemberDTO dto = new FormentMemberDTO();
			
			dto.setId(req.getParameter("id"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setName(req.getParameter("name"));
			dto.setPhone(req.getParameter("phone"));
			dto.setEmail(req.getParameter("email"));
			dto.setBirthday(LocalDate.parse(req.getParameter("birthday")));
			
			int result = Service.addMember(dto);
			
			nextPage = "/testforment/login";
			resp.sendRedirect(req.getContextPath()+nextPage);
			
		} else if (action.equals("/logout")) {
			Cookie kc = new Cookie("rememberMe", null); 
	    	kc.setMaxAge(0);
	    	kc.setPath("/");
	    	resp.addCookie(kc);
			
			HttpSession session = req.getSession();
			
			session.removeAttribute("formentTest");
			session.invalidate();
			
			nextPage ="/forment/main.jsp";
			resp.sendRedirect(req.getContextPath()+nextPage);
		
		} else if (action.equals("/memberLogin")) {
			
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String login_auto = req.getParameter("auto");
			System.out.println(id);
			System.out.println(pwd);
			System.out.println(login_auto);
			System.out.println("-----");
			
			boolean rememberMe = (login_auto != null) && (login_auto.equals("on"));
			
			try {
				FormentMemberDTO DTO = FormentMemberService.INSTANCE.login(id, pwd);
				
				if(rememberMe) {
					String uuid = UUID.randomUUID().toString();
					
					FormentMemberService.INSTANCE.updateUuid(id, uuid);
					DTO.setUuid(uuid);
					
					Cookie rememberCookie = new Cookie("rememberMe", uuid);
					rememberCookie.setMaxAge(7*24*24*60);
					rememberCookie.setPath("/");
					
					resp.addCookie(rememberCookie);
				}
				
				HttpSession session = req.getSession();
				session.setAttribute("formentTest", DTO);
				
				nextPage =  "/testforment/member/myPage";
				resp.sendRedirect(req.getContextPath()+ nextPage);
			
			} catch (Exception e) {
				System.out.println(id);
				System.out.println(pwd);
				System.out.println(login_auto);
				e.printStackTrace();
				nextPage = "/testforment/login?result=error";
				resp.sendRedirect(req.getContextPath()+ nextPage);
			}
		} else if (action.equals("/member/myPage")) {
			
			String id = req.getParameter("id");
			
			FormentMemberDTO dto = Service.findMember(id);
			
			req.setAttribute("member", dto);
			
			nextPage = "/forment/myPage.jsp";
			resp.sendRedirect(req.getContextPath()+nextPage);
		} else {
			
			nextPage ="/forment/main.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
		}
	}
}
