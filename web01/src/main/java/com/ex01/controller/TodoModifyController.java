package com.ex01.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.TodoDTO;
import com.ex01.service.TodoService;

@WebServlet("/todo/modify")
public class TodoModifyController extends HttpServlet{
	
	private TodoService todoService = TodoService.INSTANCE;
	private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-mm-dd");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		DATEFORMATTER.format(날짜데이터);
		
		Long tno = Long.parseLong(req.getParameter("tno"));
		try {
//			수정하고자하는 자료를 db에 요청하여 읽어오기
			TodoDTO todoDTO = todoService.get(tno);
			
//			db에 있는 자료를 담아서 수정페이지로 넘기기
			req.setAttribute("dto", todoDTO);
			req.getRequestDispatcher("/todo/modify.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String finishedStr = req.getParameter("finished");
		System.out.println("수정: "+finishedStr);
		
		TodoDTO todoDTO = TodoDTO.builder()
				.tno(Long.parseLong(req.getParameter("tno")))
				.title(req.getParameter("title"))
				.dueDate(LocalDate.parse(req.getParameter("dueDate")))
				.finished(finishedStr != null && finishedStr.equals("on"))
				.build();
		
		System.out.println("dto: "+todoDTO);
		
		try {
			todoService.modify(todoDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/web01/todo/list");
	}
}
