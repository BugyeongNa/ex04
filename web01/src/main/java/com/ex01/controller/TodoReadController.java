package com.ex01.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.TodoDTO;
import com.ex01.service.TodoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/todo/read")
public class TodoReadController extends HttpServlet{

	private TodoService todoService = TodoService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long tno =  Long.parseLong(req.getParameter("tno"));
		try {
//			1. DB로부터 받아와서 메모리 객체변수에 저장
			TodoDTO todoDTO = todoService.get(tno);
			
//			2. 객체변수에 있는 데이터를 req객체에 저장
			req.setAttribute("dto", todoDTO);
			
//			2-1. 쿠키 찾기(클라이언트로 부터 받은 쿠키정보를 가지고 쿠키찾기 기능을 수행하는 함수)
			Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
			String todoListStr = viewTodoCookie.getValue();
			boolean exist = false;
			
			if (todoListStr != null && todoListStr.indexOf(tno+"-")>0) {
				exist = true;
			}
			log.info("exit: "+exist);
			if(!exist) {// true이면 "번호-" 문자열이 있으면 처리
				todoListStr += tno+"-";
				viewTodoCookie.setValue(todoListStr);
				viewTodoCookie.setMaxAge(24*60*60);
				viewTodoCookie.setPath("/");
				
				resp.addCookie(viewTodoCookie);
			}
			
//			3. View페이지로 이동
			req.getRequestDispatcher("/todo/read.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	쿠키 저장소에서 찾고자하는 쿠키가 있으면 쿠키를 가져오는 함수
	private Cookie findCookie(Cookie[] cookies, String cookieName) {
		Cookie targetCookie = null;
		
		if (cookies != null && cookies.length > 0) {// 쿠키값이 있으면 처리
			for(Cookie ck : cookies) {
				if(ck.getName().equals(cookieName)) {
					targetCookie = ck;
					break;
				}
			}
		}
		
		if (targetCookie == null) {
			targetCookie = new Cookie(cookieName, "");
			targetCookie.setPath("/");
			targetCookie.setMaxAge(60*60*24);// 24시간(하루) 설정
		}
		return targetCookie;
	}
}
