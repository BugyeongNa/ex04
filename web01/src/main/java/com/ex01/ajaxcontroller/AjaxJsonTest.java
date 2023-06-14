package com.ex01.ajaxcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/json")
public class AjaxJsonTest extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String jsonInfo = req.getParameter("jsonInfo");
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		int age = Integer.parseInt(req.getParameter("age"));
		
		log.info("========");
		log.info(jsonInfo);
		log.info(id);
		log.info(pwd);
		log.info(name);
		log.info(phone);
		log.info(age);
		log.info("========");
		
//		JSON을 변환해주는 객체 생성
		JSONParser jsonParser = new JSONParser();
		try {
//			문자열을 JSIN object로 변환
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);
			log.info(jsonObject.get("name"));
			log.info(jsonObject.get("age"));
			log.info(jsonObject.get("gender"));
			log.info(jsonObject.get("nickname"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
