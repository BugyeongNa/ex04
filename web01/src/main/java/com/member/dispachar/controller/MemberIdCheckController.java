package com.member.dispachar.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/tmemcheckId.do")
public class MemberIdCheckController extends HttpServlet{
	
	private MemberService memberService = MemberService.INSTANCE;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	}
	
}
