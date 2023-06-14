package com.ex01.forwardcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="GetServletContext", value="/cget")
public class GetServletContext extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		ServletContext ctx = getServletContext();
		List mem = (ArrayList) ctx.getAttribute("mem");
		String name = (String) mem.get(0);
		int age = (Integer) mem.get(1);
		
//		Servlet Context초기값
		String menu_member = ctx.getInitParameter("menu_member");
		String menu_order = ctx.getInitParameter("menu_order");
		
		out.println("<html><body>");
		out.println("<hr>");
		out.println(name+","+age);
		out.println("<hr>");
		out.println(menu_member);
		out.println(menu_order);
		out.println("</body></html>");
	}
}
