package com.ex01.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.LoginMemberDTO;
import com.ex01.dto.TMemberDTO;
import com.ex01.service.LoginMemberService;

@WebServlet("/find/*")
public class LoginFindController extends HttpServlet{
	
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
		
		String action = req.getPathInfo();
		String nextPage = null;
		
		if (action == null || action.equals("/계정찾기")) {
			
			nextPage = "/logintest/memberFindTest.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
		} else if (action.equals("/아이디찾기")) {
		
			
			nextPage = "/logintest/idFindTest.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/아이디")) {
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			
			LoginMemberDTO dto = loginMemberService.findId(name, phone);
			req.setAttribute("id", dto);//뷰에 보여질 데이터를 저장
			
			nextPage = "/logintest/idView.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
		} else if (action.equals("/비밀번호찾기")) {
		
			
			nextPage = "/logintest/pwdFindTest.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
		} else if (action.equals("/비밀번호")) {
			
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			
			LoginMemberDTO dto = loginMemberService.findPwd(id, name, phone);
			req.setAttribute("pwd", dto);
			
			nextPage = "/logintest/pwdView.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
		} else {
			nextPage = "/logintest/memberFindTest.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
		}
		
	}
}
