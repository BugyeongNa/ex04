package com.board.commonfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet{

	private static String ARTICLE_IMAGE_REPO = "D:\\SAMPLE\\ex01\\image";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		
		String fileName = req.getParameter("imageFileName");
		String articleNO = req.getParameter("articleNO");
			
		OutputStream out = resp.getOutputStream();
		String downFile = ARTICLE_IMAGE_REPO+File.separator+articleNO+File.separator+fileName;
		log.info("매개변수 파일명: "+fileName);	
		log.info("다운로드 파일명: "+downFile);	
		
		File f = new File(downFile);// 문자열 -> 파일 시스템 전환
//		"no-cache": 파일이름을 유지
		resp.setHeader("Catch-Control", "no-cache");
		resp.addHeader("Content-disposition", "attachment; fileName="+fileName);
		
		FileInputStream in = new FileInputStream(f);
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count = in.read(buffer);
			if (count == -1) {
				break;
			}
			out.write(buffer,0,count);
		}
		in.close(); out.close();
	}
}
