package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.board.dto.BoardDTO;
import com.board.service.BoardService;

import lombok.extern.log4j.Log4j2;

@WebServlet("/boardlist/*")
@Log4j2
public class BoardController extends HttpServlet{
	
//	글에 첨부한 이미지 저장위치
	private static String ARTICLE_IMAGE_REPO = "D:\\SAMPLE\\ex01\\image";
	
	private BoardService boardService = BoardService.INSTANCE;
	
//	session에 부모글 보관
	HttpSession session;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		log.info("board Controller...");

//		공통돈 경로를 제외한 url을 작업구분자로 적용		
		String action = req.getPathInfo();
		String nextPage = null;
		resp.setContentType("text/html;charset=utf-8");
		
		String _section = req.getParameter("pageBlock");
		String _pageNum = req.getParameter("pageNum");
		log.info("_section : "+ _section);
		log.info("_page Number : "+ _pageNum);
		
		int section = Integer.parseInt((_section==null) ? "1" : _section);
		int pageNum = Integer.parseInt((_pageNum==null) ? "1" : _pageNum);
		log.info("section : "+ section);
		log.info("page Number : "+ pageNum);
		
		Map<String, Integer> pageinMap = new HashMap<>();
		pageinMap.put("section", section);
		pageinMap.put("pageNum", pageNum);
		
//		action: 목록, 등록, 조회, 삭제,...
		if(action == null || action.equals("/boardList.do")) {
				List<BoardDTO> boardList = boardService.boardList(pageinMap);
			
//				게시글 총 개수
				int totArticles = boardService.selectTotArticles();
//				 총페이지 블럭계산(1블록: 10개 페이지)
//				 총블럭 = 총페이수/10(블럭묶음단위) + 1
//				 991페잊/10페이지 = 99.1블럭 => (99.1)+1 => 100.1 => 100(정수만 처리)
				
//				 전체 페이지수 = 전체레코드수/10(1page: 10개묶음) + 1
				int totalPage = (int)Math.ceil(totArticles*1.0/10); // 소수점이하기 있으면 자리올림(10.1=>11)
				int totalPageBlock =(int)Math.ceil(totalPage*1.0/10);
				
				log.info("totArticles:"+totArticles);
				log.info("totalPage:"+totalPage);
				log.info("totalPageBlock:"+totalPageBlock);
				
//				마지막 페이지 계산 : 현재페이지 카운터에서 마지막페이지 초과하지않게 설정
//				페이지계산은 1-10형식으로 반복처리됨.
				int lastPage = 1;
				for (int i=1; i<=10; i++){
//					블럭에 대한 페이지번호 생성
					int endPage = (section-1)*10 + i;
					 
					log.info("("+section+"-1)*10) + "+i+": "+(endPage));
					
					if (endPage <=  totalPage){
						
						lastPage = i;
					}
				 }
				 log.info("section: "+section);
				 log.info("lastPage: "+lastPage);
				
				
				
//				articlesMap.put("section", section);
//				articlesMap.put("pageNum", pageNum);
				
				
//				mybatis 적용 : xml 또는 java query문 연결
//				List<BoardDTO> boardList = boardService.boardList3(pageingMap);
				req.setAttribute("boardList", boardList);
				
				req.setAttribute("totArticles", totArticles);
				req.setAttribute("section", section);
				req.setAttribute("pageNum", pageNum);
				req.setAttribute("totSection", totalPageBlock);
//				 페이지 블럭에서 마지막페이지 값 보관
				req.setAttribute("lastPage", lastPage);
				
			
			nextPage ="/board/boardList.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/boardView.do")) {
			int articleNO = Integer.parseInt(req.getParameter("articleNO"));
//			서비스 요청
			BoardDTO article = boardService.selectArticleOne(articleNO);
//			결과값을 보관
			req.setAttribute("dto", article);
			
			req.setAttribute("section", section);
			req.setAttribute("pageNum", pageNum);
			
//			전달
			nextPage ="/board/boardView.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/board/boardForm.do")) {
			
			
			req.setAttribute("section", section);
			req.setAttribute("pageNum", pageNum);
			
			
			nextPage ="/board/boardForm.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		}else if (action.equals("/board/boardInsert.do")) {
			
			BoardDTO dto = new BoardDTO();
			int isOK = 0;
			
//			dto.setArticleNO(Integer.parseInt(req.getParameter("articleNO")));
//			dto.setId(req.getParameter("id"));
//			dto.setTitle(req.getParameter("title"));
//			dto.setContent(req.getParameter("content"));
//			dto.setImageFileName(req.getParameter("imageFileName"));
			
//			업로드기능 호출
			Map<String, String> articleMap = upload(req, resp);
			dto.setId(articleMap.get("id"));
			dto.setTitle(articleMap.get("title"));
			dto.setContent(articleMap.get("content"));
			dto.setImageFileName(articleMap.get("imageFileName"));
			
			log.info(dto);
			
//			db에 게시글 등록 서비스 요청
			try {
				 isOK = boardService.insertBoard(dto);
				
				
//				첨부파일 있는 경우만 처리
				if (dto.getImageFileName() != null && dto.getImageFileName().length() != 0) {
//					temp폴더에 임시로 업로드
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+dto.getImageFileName());
//					ARTICLE_IMAGE_REPO 하위 경로에 글 번호 폴더를 생성
					File descFile = new File(ARTICLE_IMAGE_REPO+"\\"+dto.getArticleNO());
					descFile.mkdirs();
//					temp폴더 파일의 글 번호를 이름으로 하는 폴더로 이동
					FileUtils.moveFileToDirectory(srcFile, descFile, true);
					
//					메세지전송	
				}
				PrintWriter pw = resp.getWriter();
				if(isOK != 0) {
					pw.print("<script>"+
								"alert('게시글을 등록하였습니다.');"+
								"location.href='"+
								req.getContextPath()+"/boardlist/boardList.do'"+
								"</script>"
							);
				} else {
					pw.print("<script>"+
							"alert('게시글 등록을 실패하였습니다.');"+
							"location.href='"+
							req.getContextPath()+"/boardlist/boardList.do'"+
							"</script>"
							
							);
				}
				
				
			} catch (Exception e) {
			}
			return;
			
			
//			nextPage ="/boardlist/boardList.do";
//			resp.sendRedirect(req.getContextPath()+nextPage);
			
		} else if (action.equals("/board/boardModify.do")) {
			
			int articleNO = Integer.parseInt(req.getParameter("articleNO"));
//			서비스 요청
			BoardDTO article = boardService.selectArticleOne(articleNO);
//			결과값을 보관
			req.setAttribute("dto", article);
//			전달
			nextPage ="/board/boardModify.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/board/boardUpdate.do")) {

			BoardDTO dto = new BoardDTO();
			int isOK = 0;
			
//			dto.setArticleNO(Integer.parseInt(req.getParameter("articleNO")));
//			dto.setTitle(req.getParameter("title"));
//			dto.setContent(req.getParameter("content"));
//			dto.setImageFileName(req.getParameter("imageFileName"));
			
			
			
//			업로드기능 호출
			Map<String, String> articleMap = upload(req, resp);
			dto.setArticleNO(Integer.parseInt(articleMap.get("articleNO")));
			dto.setId(articleMap.get("id"));
			dto.setTitle(articleMap.get("title"));
			dto.setContent(articleMap.get("content"));
			dto.setImageFileName(articleMap.get("imageFileName"));
			
//			게시글 번호
//			int articleNO = Integer.parseInt(req.getParameter("articleNO"));
			
			log.info(dto);
			
//			db에 게시글 등록 서비스 요청
			try {
				 isOK = boardService.updateBoard(dto);
				
				
//				첨부파일 있는 경우만 처리
				if (dto.getImageFileName() != null && dto.getImageFileName().length() != 0) {
//					temp폴더에 임시로 업로드
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+dto.getImageFileName());
//					ARTICLE_IMAGE_REPO 하위 경로에 글 번호 폴더를 생성
					File descFile = new File(ARTICLE_IMAGE_REPO+"\\"+dto.getArticleNO());
					descFile.mkdirs();
//					temp폴더 파일의 글 번호를 이름으로 하는 폴더로 이동
					FileUtils.moveFileToDirectory(srcFile, descFile, true);
					
//					수정전 이미지 삭제
					String originalFileName = articleMap.get("originalFileName");
					File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+dto.getArticleNO()+"\\"+originalFileName);
					oldFile.delete();
	
				}
//				메세지전송
				PrintWriter pw = resp.getWriter();
				if(isOK != 0) {
					pw.print("<script>"+
								"alert('게시글을 수정하였습니다.');"+
								"location.href='"+
								req.getContextPath()+"/boardlist/boardList.do'"+
								"</script>"
							);
				} else {
					pw.print("<script>"+
							"alert('게시글 수정을 실패하였습니다.');"+
							"location.href='"+
							req.getContextPath()+"/boardlist/boardList.do'"+
							"</script>"
							
							);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
			
		} else if (action.equals("/board/boardDelete.do")) {

			
			int articleNO = Integer.parseInt(req.getParameter("articleNO"));
//			int isOK = 0;
			List<Integer> articleNOList = null;
			try {
				articleNOList = boardService.deleteBoard(articleNO);
			} catch (Exception e) {
			}
			
//			이미지 폴더 및 파일 삭제
			for(int _articleNO : articleNOList) {
			File imgDir = new File(ARTICLE_IMAGE_REPO+"\\"+_articleNO);
			if (imgDir.exists()) {// 폴더가 존재하면
				FileUtils.deleteDirectory(imgDir);
			}
			
			}
//			PrintWriter pw = resp.getWriter();
//			if(isOK!=0) {
//				pw.print("<script>"+
//							"alert('글을 삭제했습니다.');"+
//							"location.href='"+
//							req.getContextPath()+"/boardlist/boardList.do'"+
//							"</script>"
//						);
//			} else {
//				pw.print("<script>"+
//						"alert('삭제실패');"+
//						"location.href='"+
//						req.getContextPath()+"/boardlist/boardList.do'"+
//						"</script>"
//						
//						);
//			}
//			
//			return;
			nextPage ="/boardlist/boardList.do";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
			
			
			
		} else if (action.equals("/board/boardReply.do")) {
			log.info("답글입장");
			int parentNO = Integer.parseInt(req.getParameter("parentNO"));
			log.info("reply.do parentNO: "+ parentNO);
			
			req.setAttribute("parentNO", parentNO);
			
//			session = req.getSession();
//			session.setAttribute("parentNO", parentNO);
			
			nextPage ="/board/boardReply.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} else if (action.equals("/board/boardReplyAdd.do")) {
				
				BoardDTO dto = new BoardDTO();
				int isOK = 0;
				
//				dto.setArticleNO(Integer.parseInt(req.getParameter("articleNO")));
//				dto.setId(req.getParameter("id"));
//				dto.setTitle(req.getParameter("title"));
//				dto.setContent(req.getParameter("content"));
//				dto.setImageFileName(req.getParameter("imageFileName"));
				
//				업로드기능 호출
				Map<String, String> articleMap = upload(req, resp);
				dto.setId(articleMap.get("id"));
				dto.setParentNO(Integer.parseInt(articleMap.get("parentNO")));
				dto.setTitle(articleMap.get("title"));
				dto.setContent(articleMap.get("content"));
				dto.setImageFileName(articleMap.get("imageFileName"));
				
				log.info(dto);
				
//				db에 게시글 등록 서비스 요청
				try {
					 isOK = boardService.insertBoard(dto);
					
					
//					첨부파일 있는 경우만 처리
					if (dto.getImageFileName() != null && dto.getImageFileName().length() != 0) {
//						temp폴더에 임시로 업로드
						File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+dto.getImageFileName());
//						ARTICLE_IMAGE_REPO 하위 경로에 글 번호 폴더를 생성
						File descFile = new File(ARTICLE_IMAGE_REPO+"\\"+dto.getArticleNO());
						descFile.mkdirs();
//						temp폴더 파일의 글 번호를 이름으로 하는 폴더로 이동
						FileUtils.moveFileToDirectory(srcFile, descFile, true);
						
//						메세지전송	
					}
					PrintWriter pw = resp.getWriter();
					if(isOK != 0) {
						pw.print("<script>"+
									"alert('게시글을 등록하였습니다.');"+
									"location.href='"+
									req.getContextPath()+"/boardlist/boardList.do'"+
									"</script>"
								);
					} else {
						pw.print("<script>"+
								"alert('게시글 등록을 실패하였습니다.');"+
								"location.href='"+
								req.getContextPath()+"/boardlist/boardList.do'"+
								"</script>"
								
								);
					}
					
					
				} catch (Exception e) {
				}
				return;
			
			
			
		} else {
				List<BoardDTO> boardList = boardService.boardList(pageinMap);
			
//				게시글 총 개수
				int totArticles = boardService.selectTotArticles();
//				 총페이지 블럭계산(1블록: 10개 페이지)
//				 총블럭 = 총페이수/10(블럭묶음단위) + 1
//				 991페잊/10페이지 = 99.1블럭 => (99.1)+1 => 100.1 => 100(정수만 처리)
				
//				 전체 페이지수 = 전체레코드수/10(1page: 10개묶음) + 1
				int totalPage = (int)Math.ceil(totArticles*1.0/10); // 소수점이하기 있으면 자리올림(10.1=>11)
				int totalPageBlock =(int)Math.ceil(totalPage*1.0/10);
				
				log.info("totArticles:"+totArticles);
				log.info("totalPage:"+totalPage);
				log.info("totalPageBlock:"+totalPageBlock);
				
//				마지막 페이지 계산 : 현재페이지 카운터에서 마지막페이지 초과하지않게 설정
//				페이지계산은 1-10형식으로 반복처리됨.
				int lastPage = 1;
				for (int i=1; i<=10; i++){
//					블럭에 대한 페이지번호 생성
					int endPage = (section-1)*10 + i;
					 
					log.info("("+section+"-1)*10) + "+i+": "+(endPage));
					
					if (endPage <=  totalPage){
						
						lastPage = i;
					}
				 }
				 log.info("section: "+section);
				 log.info("lastPage: "+lastPage);
				
				
				
//				articlesMap.put("section", section);
//				articlesMap.put("pageNum", pageNum);
				
				
//				mybatis 적용 : xml 또는 java query문 연결
//				List<BoardDTO> boardList = boardService.boardList3(pageingMap);
				req.setAttribute("boardList", boardList);
				
				req.setAttribute("totArticles", totArticles);
				req.setAttribute("section", section);
				req.setAttribute("pageNum", pageNum);
				req.setAttribute("totSection", totalPageBlock);
//				 페이지 블럭에서 마지막페이지 값 보관
				req.setAttribute("lastPage", lastPage);
				
			
			nextPage ="/board/boardList.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
		}
	}
//	이미지 업로드 메서드 선언
	private Map<String, String> upload(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException{
		
		log.info("====asdsad");
		
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		
//		문자열 => 시스템 파일로 변환
		File currentPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentPath);
		factory.setSizeThreshold(1024*1024);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
//			request에 저장되어 있는 매개변수(정보)를 List에 저장
			List<FileItem> items = upload.parseRequest(req);
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem = items.get(i);
				if (fileItem.isFormField()) {// form요소이면
					log.info(fileItem.getFieldName()+ "=" +fileItem.getString(encoding));
					
//					게시글 등록 => title,content,...
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
//					file객체이면 처리
					log.info("파라미터이름: "+fileItem.getFieldName());
					log.info("파일이름: "+fileItem.getName());
					log.info("파일크기: "+fileItem.getSize()+"bytes");
					
					articleMap.put(fileItem.getFieldName(), fileItem.getName());
					
					if(fileItem.getSize() > 0) {
						String separator = File.separator;
						int idx = fileItem.getName().lastIndexOf(separator);
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx+1);

						File uploadFile = new File(currentPath + separator + "temp"+ separator + fileName);
						
//						파일시스템으로 전환된 파일경로+파일이름을 업로드 실해
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}
}
