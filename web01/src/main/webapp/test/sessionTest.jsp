<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// jsp에서는 session객체가 내장되어 있으므로 별도 객체 생성 필요없다.
  	String user_id = (String) session.getAttribute("session");
	session.setAttribute("addr", "김해시");
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>세션 값: <%=user_id %></h3>
	<hr>
	<a href="sessionTest2.jsp">세션값 확인하기</a>
</body>
</html>