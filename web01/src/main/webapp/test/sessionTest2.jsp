<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_id = (String) session.getAttribute("session");
	String addr = (String)session.getAttribute("addr");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	세션값(주소)<%= addr %>
	<hr>
	<h3>세션 값: <%=user_id %></h3>
</body>
</html>