package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.SqlMemberDTO;
import com.ex01.service.SqlMemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/sqlmember/*")
public class SqlMemberController  extends HttpServlet{

	private SqlMemberService memberService = SqlMemberService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {

		String action = req.getPathInfo();
		String nextPage = null;
		
		log.info("/sqlmember/*");
		log.info("getPathInfo(): "+action);

		
		if (action == null || action.equals("/listMember.do")) {
			try {
				List<SqlMemberDTO> memberList = memberService.memberList();
				req.setAttribute("memberList", memberList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			nextPage ="/sqltest/listMember.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/viewMember.do")) {
			String name = req.getParameter("name");
			
			SqlMemberDTO memberDTO =  memberService.findMember(name);
			req.setAttribute("member", memberDTO);
			
			nextPage ="/sqltest/viewMember.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/memberForm.do")) {
			
			nextPage ="/sqltest/memberForm.jsp";
			resp.sendRedirect(req.getContextPath()+ nextPage);
			
		} else if (action.equals("/insertMember.do")) {
			
			SqlMemberDTO dto = new SqlMemberDTO();
			
			dto.setName(req.getParameter("name"));
			dto.setId(req.getParameter("id"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setPhone(req.getParameter("phone"));
			dto.setEmail(req.getParameter("email"));
			

			int result = memberService.addMember(dto);
			
			nextPage ="/sqlmember/listMember.do";
			resp.sendRedirect(req.getContextPath()+ nextPage);
		}else if (action.equals("/checkId.do")) {
			String id = req.getParameter("id");

			boolean isCheck = memberService.checkID(id);
			if (isCheck) {
				log.info("이미 사용중인 아이디입니다."+isCheck);
			} else {
				log.info("사용할 수 있는 아이디입니다."+isCheck);
			}
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter writer = resp.getWriter();
			if (isCheck) {
				writer.print("not_usable");
			}else {
				writer.print("usable");
			}
			
		}else if (action.equals("/modifyMember.do")) {
			String name = req.getParameter("name");
			
			SqlMemberDTO memberDTO =  memberService.findMember(name);
			req.setAttribute("member", memberDTO);
			
			nextPage ="/sqltest/modifyMember.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		}else if (action.equals("/updateMember.do")) {

			SqlMemberDTO dto = new SqlMemberDTO();
			dto.setName(req.getParameter("name"));
			dto.setId(req.getParameter("id"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setPhone(req.getParameter("phone"));
			dto.setEmail(req.getParameter("email"));
			

			int result = memberService.modifyMember(dto);
			
			nextPage ="/sqlmember/listMember.do";
			resp.sendRedirect(req.getContextPath()+ nextPage);
			
		}else if (action.equals("/deleteMember.do")) {
			String id = req.getParameter("id");
			int result = memberService.deleteMember(id);
			
			nextPage ="/sqlmember/listMember.do";
			resp.sendRedirect(req.getContextPath()+ nextPage);
			
		} else {
			
			List<SqlMemberDTO> memberList = memberService.memberList();
			req.setAttribute("memberList", memberList);
			nextPage ="/sqltest/listMember.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
		}
		
	
	}
}








