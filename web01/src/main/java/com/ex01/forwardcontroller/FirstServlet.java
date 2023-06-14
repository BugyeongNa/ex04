package com.ex01.forwardcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="FirstServlet", value="/first")
public class FirstServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
//		1. redirect
//		resp.sendRedirect("second");
//		resp.sendRedirect("second?name=Hong");
		
//		2. Refresh
//		resp.addHeader("Refresh", "2;url=second");
		
//		3. location
//		out.print("<script type='text/javascript'>");
//		out.print("location.href='second'");
//		out.print("</script>");
		
//		4. dispatch
//		RequestDispatcher dispatcher =
//				req.getRequestDispatcher("second?name=Lee");
//				dispatcher.forward(req, resp);
		
//		req.getRequestDispatcher("second").forward(req, resp);
		
		
		req.setAttribute("address", "서울시");
//		redirect방식: 서블릿에서 바인딩한 데이터 다른 서블릿으로 전송 할 수 없음
//		resp.sendRedirect("second");
		req.getRequestDispatcher("second").forward(req, resp);
	}
}

//	redirect 방법
//	HttpServletResponse객체의 sendRedirect()메서드
//	웹브라우저에서 재요청하는 방식
//	형식: sendRedirect("포워드할 서블릿 또는 JSP");
//	재요청하는 방식이므로 request객체가 새로 생성되는 구조

//	Refresh 방식
//	HttpServletResponse객체의 addHeader()메서드
//	웹브라우저에서 재요청하는 방식
//	형식: addHeader("Refresh","경과시간(초);url=요청할 서블릿,JSP");

//	location 방식
//	자바스크립트 location객체 href 속성을 이용
//	웹브라우저에서 재요청하는 방식
//	형식: location.href="요청할 서블릿, JSP";

//	dispatch 방법
//	일반적으로 포워딩 기능 지칭
//	서블릿이 직접 요청하는 방식
//	Requestdispatcher클래스의 forward()메서드
//	형식:
//	Requestdispatcher dis = request.gerRequestDispatcher(포워드할 서블릿,JSP);
//	dispatcher.forward(req, resp);

//	서블릿 관련 객체에 저장하는 방법
//	HttpServletRequest, HttpSession, ServletContext
//	 : 서블릿이나 JSP에서 저장된 데이터(정보) 공유
//	setAttribute("키","값")
//	getAttribute("키","값")
//	removeAttribute("키")

//	ServletContext
//	톰캣 컨테이너 실행시 각 컨텍스트(웹어플리케이션)마다 한 개의 ServletContext
//	 : 어플리케이션 전체의 공통 자원이나 정보를 미리 바인딩해서 서블릿에 제공
	