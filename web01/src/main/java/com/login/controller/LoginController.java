package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ex01.dto.MemberDTO;
import com.ex01.dto.TMemberDTO;
import com.ex01.service.MemberService;
import com.ex01.service.TMemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("/login Get...");
		
		
		String ctxPath = req.getContextPath();
		
//		로그인 페이지 전환
		resp.sendRedirect(ctxPath+"/board/login/login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("/login Post...");
		
		req.setCharacterEncoding("utf-8");
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		String login_auto = req.getParameter("auto");
//		String str = user_id + user_pw;
		
		log.info("user_id => "+user_id);
		log.info("user_pw => "+user_pw);
		log.info("login_auto => "+login_auto);
		
//		클라이언트에서 json구조형식 데이터를 ajax()으로 보냈을 경우
		String logindata = req.getParameter("logindata");
		
		log.info(logindata);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		try {
			
			jsonObject = (JSONObject) jsonParser.parse(logindata);
			log.info("logindata(JSON) => "+jsonObject);
			log.info("logindata(JSON) => "+jsonObject.get("user_id"));
			log.info("logindata(JSON) => "+jsonObject.get("user_pw"));
			log.info("logindata(JSON) => "+jsonObject.get("auto"));
			
			
			user_id = (String)jsonObject.get("user_id");
			user_pw = (String)jsonObject.get("user_pw");
			login_auto = (String)jsonObject.get("auto");
			
		} catch (Exception e) {
//			e.printStackTrace();
			
			user_id = (String) jsonObject.get("user_id");
			user_pw = (String) jsonObject.get("user_pw");
		}
		
//		자동로그인 체크항목이 on일 경우 처리
		boolean rememberMe = (login_auto != null) && (login_auto.equals("on"));
		log.info("rememberMe status: "+rememberMe);

		TMemberDTO member = new TMemberDTO();
		String memberId = "";
		String memberPwd = "";
		
		
		try {
			member = TMemberService.INSTANCE.findMember(user_id);
			
			memberId = member.getId();
			memberPwd = member.getPwd();
		} catch (Exception e) {
			// TODO: handle exception
		}
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject sendData = new JSONObject();
		
//		id,pwd체크
		
		if (memberId.equals(user_id) && memberId.length() != 0) {
//			아이디 일치
			if(memberPwd.equals(user_pw)) {
				if(rememberMe) {// rememberMe true면 난수 발생
					String uuid = UUID.randomUUID().toString();
					log.info("UUID: "+uuid);
					
//					사용자가 로그인할때 임의의 문자열을 생성하고 이를 데이터베이스에
					try {
						TMemberService.INSTANCE.updateUuid(user_id, uuid);
					} catch (Exception e) {
						// TODO: handle exception
					}

					member.setUuid(uuid);
					
//					DB에 저장된 난수와 같은 문자열을 쿠키값 생성
					Cookie rememberCookie = new Cookie("remember-me", uuid);
					rememberCookie.setMaxAge(7*24*24*60);
					rememberCookie.setPath("/");
					
//					클라이언트에 쿠키전송
					resp.addCookie(rememberCookie);
				}
				
//				등록회원으로 로그인 승인
				
				HttpSession session = req.getSession();
				session.setAttribute("loginInfo", member.getName());
				
				sendData.put("code", "ok");
				sendData.put("message", "로그인승인");
				pw.print(sendData);
			} else {
//				아이디는 일치, 패스워드 불일치
				sendData.put("code", "pw_fail");
				sendData.put("message", "비밀번호가 일치하지 않습니다.");
				pw.print(sendData);
			}
		} else {
//			아이디 불일치
			sendData.put("code", "id_fail");
			sendData.put("message", "아이디가 일치하지 않습니다.");
			pw.print(sendData);
		}
		 if (rememberMe) {
		    	String uuid = UUID.randomUUID().toString();
		    }
	
		
		
	}
}




//쿠키와 세션을 같이 활용하기
//
// 자동 로그인 준비
// - 사용자가 로그인할때 임의의 문자열을 생성하고 이를 데이터베이스에 보관
// - 쿠키에는 생성된 문자열을 값으로 삼고 유효기간은 1주일로 지정
// 
// 로그인 체크 방식
// - 현재 사용자의 HttpSession에 로그인 정보가 없는 경우에만 쿠키를 확인
// - 쿠키의 값과 데이터베이스의 값을 비교하고 같다면 사용자의 정보를 읽어와서
//   HttpSession에 사용자 정보를 추가
