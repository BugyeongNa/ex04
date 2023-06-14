package com.ex01.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.TodoDTO;
import com.ex01.service.TodoService;

import lombok.extern.log4j.Log4j2;

@WebServlet("/todo/list")
@Log4j2
public class TodoListController extends HttpServlet{

	private TodoService todoService
			= TodoService.INSTANCE;
	
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doHandler(req, resp);
	 }
	 
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doHandler(req, resp);
	}
	 
	 protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 log.info("todo list.....");
//		 컨트롤러 -> 서비스 기능호출
		 
		 ServletContext servletContext = req.getServletContext();
		 log.info("appName: "+servletContext.getAttribute("appName"));
		 
		 
		 try {
			List<TodoDTO> dtoList = todoService.listAll();
			
//			포워드 -> jsp 전환
			req.setAttribute("dtoList", dtoList);
			req.getRequestDispatcher("/todo/list.jsp").forward(req, resp);
			
//			 resp.setContentType("text/html;charset=utf-8");
//			 PrintWriter out = resp.getWriter();
//			 
//			 String webout = "";
//			 webout += "<html>";
//			 webout += "<body>";
//			 for(int i=0; i<dtoList.size(); i++) {
//				 TodoDTO dto = new TodoDTO();
//				 
//				 webout += dtoList.get(i).toString();
//				 webout += "<hr>";
//			 }
//			 webout += "</body>";
//			 webout += "</html>";
//			 
//			 out.print(webout);
//			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
}

//	구현할 컨트롤러 설계
//	목록 		GET TodoListController 		/todo/list.jsp
//	등록(입력) GET/POST TodoRegisterController	/todo/register.jsp

//	포워드(forward) : 하나의 서블릿에서 다른 서블릿이나 JSP와 연동하는 방법
//	- 요청에 대한 추가 작업을 다른 서블릿에 수행
//	- 요청에 포함된 정보를 다른 서블릿이나 JSP와 공유
//	- 요청에 정보를 포함시켜 다른 서블릿에 전달
//	- 모델2 개발시 서블릿에서 JSP로 데이터를 전달하는 데 사용

//	redirect
//	Refresh
//	location
//	dispatch

