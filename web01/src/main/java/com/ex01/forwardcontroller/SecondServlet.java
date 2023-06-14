package com.ex01.forwardcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="SecondServlet", value="/second")
public class SecondServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
//		out.println("sendRedirect를 이용한 redirect실습");
//		String name = req.getParameter("name");
//		out.println("이름:"+name);
//		out.println("addHeader를 이용한 Refresh실습");
//		out.println("href를 이용한 location실습");
		out.println("forward를 이용한 dispatch실습");
		String address = (String)req.getAttribute("address");
		out.print("주소: "+address);
		out.println("</body></html>");
	}
}
