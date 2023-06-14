<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"
    %>
<%
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div><%=exception.getMessage() %></div>
	<hr>
<%-- 	<div><%=exception.printStackTrace() %></div> --%>
	<hr>
	<div>매개변수 확인하세요</div>
</body>
</html>