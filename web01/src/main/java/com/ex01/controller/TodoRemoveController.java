package com.ex01.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.service.TodoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/todo/remove")
public class TodoRemoveController extends HttpServlet{

	private TodoService todoService = TodoService.INSTANCE;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long tno = Long.parseLong(req.getParameter("tno"));
		String ctxPath = req.getContextPath();
		
		log.info("remove tno: "+tno);
		
		try {
			todoService.remove(tno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect(ctxPath+"/todo/list");
		
	}
}
