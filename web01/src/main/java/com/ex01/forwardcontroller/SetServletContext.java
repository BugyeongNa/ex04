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

@WebServlet(name="SetServletContext", value="/cset")
public class SetServletContext extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		
		ServletContext ctx = getServletContext();
		String name = "홍길동";
		int age = 30;
				
		String menu_member = ctx.getInitParameter("menu_member");
		String menu_order = ctx.getInitParameter("menu_order");

		
		List mem = new ArrayList<>();
		mem.add(name);
		mem.add(age);
		
		ctx.setAttribute("mem",mem);
		
		out.println("<html><body>");
		out.println(name+","+age);
		out.print("<hr>");
		out.println(menu_member);
		out.println(menu_order);
		out.println("</body></html>");
		
	}
}
