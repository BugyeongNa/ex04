package com.ex01.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ex01.dto.TodoDTO;
import com.ex01.service.TodoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/todo/register")
public class TodoRegisterController extends HttpServlet{

	private TodoService todoService = TodoService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("/todo/register ... Get");
		
//		등록할 때 로그인 체크하기
		HttpSession session = req.getSession();
		
		if (session.isNew()) {// 기존에 세션이 없음, 새로운 사용자
			log.info("JSESSIONID쿠키가 새로 만들어진 사용자");
			resp.sendRedirect("/web01/login");
			return;
		}
		if (session.getAttribute("loginInfo") == null) {// 로그인 상태가 아님
			log.info("로그인한 정보가 없는 사용자");
			resp.sendRedirect("/web01/login");
			return;
		}
//		정상적인 경우라면 입력화면으로 포워딩
		req.getRequestDispatcher("/todo/register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("/todo/register ... Post");
		
//		매개변수 -> 객체변수(DTO)
		TodoDTO todoDTO = TodoDTO.builder()
				.title(req.getParameter("title"))
//				util.date -> localDate변환하여 저장
				.dueDate(LocalDate.parse(req.getParameter("dueDate")))
				.build();
		
		log.info(todoDTO);
		
//		db처리하는 서비스 요청
		try {
			todoService.register(todoDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
