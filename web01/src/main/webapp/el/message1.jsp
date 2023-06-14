<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setCharacterEncoding("utf-8"); %>

	<fmt:bundle basename="src.main.resources.member" prefix="member">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다국어 표시</title>
</head>
<body>
	<fmt:setLocale value="ko_KR"/>
	
	<h3>jstl 다국어 기능</h3>
	<hr>
	<h4>회원 정보</h4>
	<hr>
		이름 : <fmt:message key="mem.name" /> <br>
	
</body>
</html>



	</fmt:bundle>