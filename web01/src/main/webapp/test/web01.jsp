<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<!--      errorPage="paramException.jsp" -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="<%=request.getContextPath() %>" scope="page"></c:set>
<%!
	String name = "홍길동";
	public String getName() {return name+","+20;}
	
 %>
<%
	String path = request.getContextPath();
	String addr = request.getParameter("addr");
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	int result = num1 + num2;
	
	String user_id = (String) session.getAttribute("session");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${path}/login">로그인 연결하기</a>
	<hr>
	<a href="<%=path %>/login">로그인 연결하기</a>
	<hr>
	<a href="${pageContext.request.contextPath}/login">로그인 연결하기</a>
	<h3>메인 페이지</h3>
	<%@ include file="testInclude.jsp" %>
	<hr>
	<h1>안녕하세요 <%=getName() %>, <%=name %> </h1>
	<hr>
	<h1>주소: <%=addr %></h1>
	<hr>
	<h1><%=num1 %>+<%=num2 %>=<%=result %></h1>
	<hr>
	<h3>세션 값: <%=user_id %></h3>
	<hr>
	${param.name }, ${param.addr }, ${param.num1 }, ${param.num2}
	<hr>
	<%=request.getContextPath() %> => <%=path %>
	<hr>
	${pageContext.request.contextPath}
	
</body>
</html>

<!-- 
JSP 페이지 구성
디렉티브 태그
스크립트 요소(주석문, 스크립트릿, 표현식, 선언식)
표현언어(Expression Language)
내장객체
액션태그
커스텀태그

JSP 내장객체 (변수)
request : HttpServletRequest
response : HttpServletResponse
session : HttpSession
application : ServletContext
page : this

out : PrintWriter
pageContext
config
exception

 -->