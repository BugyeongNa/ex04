package com.ex01.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.vo.NumberVO;

@WebServlet("/calc/makeResult")
public class CalcController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		int num1 = Integer.parseInt(req.getParameter("num1"));
		int num2 = Integer.parseInt(req.getParameter("num2"));
		
		System.out.println(num1+"+"+num2+"="+(num1+num2));
		
		NumberVO numberVO = new NumberVO();
		numberVO.setNum1(num1);
		numberVO.setNum2(num2);
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String htmlstr = "";
		htmlstr += "<html>";
		htmlstr += "<body>";
		htmlstr += (numberVO.getNum1()+numberVO.getNum2());
		htmlstr += "</body>";
		htmlstr += "</html>";
		
		out.print(htmlstr);
		out.flush();// 버퍼 강제 비우기
	}

}
