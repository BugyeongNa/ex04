<%@page import="com.ex01.dto.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ex01.dto.TodoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath()%>" scope="page"/>
   
<%
	List<TodoDTO> todoList = (ArrayList)request.getAttribute("dtoList");
%>
<%
	MemberDTO memberdto = (MemberDTO)session.getAttribute("loginInfo"); 
%>

<c:set var="logincheck" value="<%=memberdto%>" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo List</title>
</head>
<body>
<h1>Todo List</h1>
<%=memberdto %>/${logincheck}
<hr>
${appName}
<hr>
<a href="${path}/todo/register"> 등록</a>
<c:if test="${not empty logincheck.id}">
	${logincheck.id}님 로그인 상태입니다.
</c:if>
<hr>
 <ul>
 	<c:forEach var="dto" items="${dtoList}">
	<li>
		<span>
			<a href="${path}/todo/read?tno=${dto.tno}">${dto.tno}</a>
		</span>
		<span>${dto.title}</span>
		<span>${dto.dueDate}</span>
		<span>${dto.finished ? "DONE" : "NOT YET" }</span>
	</li>
	</c:forEach>
</ul>
<hr>
<form action="/web01/logout" method="post">
	<button>LOGOUT...post</button>
</form>
<a href="/web01/logout">LOGOUT...get</a>
</body>
</html>