package com.member.dispachar;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{
	
	
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
//	private String ctxPath;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("service");

		String uri, path, viewName, view;
		
		
//		1. 요청 uri 분석
		uri = req.getRequestURI();
		path = uri.substring(uri.lastIndexOf("/"));
		
//		String action = req.getPathInfo();
//		log.info("getRequestURI(): "+uri+",req.getPathInfo(): "+action);
		
//		2. path에 해당하는 Controller검색 (추출)
		Controller controller = handlerMapping.getController(path);
		
//		3. 추출한 Controller 실행
		viewName = controller.handleRequest(req, resp);
		log.info("viewName: "+viewName);
		
//		4. viewResolver를 통해 vuewName에 해당하는 화면을 검색
		view = getNextViewPage(viewName);
		
//		5. 검색된 view페이지로 이동하기
		log.info("view: "+view);
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	public void init() throws ServletException {
		log.info("init");
//		url에 해당되는 Controller 객체 생성
		handlerMapping = new HandlerMapping();
//		view 페이지이동을 위한 view페이지 사전 설정하는 객체 생성
		viewResolver = new ViewResolver();
		
		viewResolver.setPrefix("./WEB-INF/");
		viewResolver.setSuffix(".jsp");
		
	}
	
//	viewName에서 이동할 페이지 경로및 이동할 
	public String getNextViewPage(String viewName) {
		String view = null;
		if(!viewName.contains(".do")) {
			if (viewName.equals("index")) {
				view = viewName+ ".jsp";
			} else {
				view = viewResolver.getView(viewName);
			}
		} else {
			view = viewName;
		}
		return view;
	}
}
