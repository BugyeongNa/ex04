package com.ex01.ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/jsonSend")
public class AjaxJsonSendTest extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		응답기능(JSON객체 생성하여 보내기)
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter writer = resp.getWriter();
		
		JSONObject totalObject = new JSONObject();
		JSONArray memberArray = new JSONArray();
		
		JSONObject memberInfo = new JSONObject();
//		첫번째 JSON객체에 데이터 초기화
		memberInfo.put("name", "홍길동");
		memberInfo.put("age", "22");
		memberInfo.put("gender", "남자");
		memberInfo.put("nickname", "날쌘돌이");
		
//		배열에 추가
		memberArray.add(memberInfo);
		
		memberInfo = new JSONObject();
//		두번째 JSON객체에 데이터 초기화
		memberInfo.put("name", "동순이");
		memberInfo.put("age", "22");
		memberInfo.put("gender", "여자");
		memberInfo.put("nickname", "날쌘순이");
//		배열에 추가
		memberArray.add(memberInfo);
		
//		배열을 객체에 저장
		totalObject.put("member", memberArray);
		
//		객체 => 문자열로 전환하여 전송
		String jsonInfo = totalObject.toJSONString();
//		클라이언트에게 전송(데이터 보내기)
		writer.print(jsonInfo);
		
		log.info("totalObject: "+totalObject);
		log.info("jsonInfo: "+jsonInfo);
		

	}
}
