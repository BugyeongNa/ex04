<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="title" value="Hello world"/>
	<c:set var="title2" value="쇼핑몰 중심"/>
	<c:set var="str1" value="중심"/>
	
	<h4>여러 가지 문자열 함수 기능</h4>
	${title} <br>
	${title2} <br>
	${str1} <br>
	<hr>
	문자열길이: ${title} : ${fn:length(title)} <br>
	대문자: ${title} : ${fn:toUpperCase(title)} <br>
	소문자: ${title} : ${fn:toLowerCase(title)} <br>
	<hr>
	문자추출: ${title2} : ${fn:substring(title2,3,6)} <br>
	문자추출: ${fn:length(title2)} : ${fn:trim(title2)} <br>
	문자수정: ${title2} : ${fn:replace(title2," ","/")} <br>
	문자삭제: ${title2} : ${fn:replace(title2,"중심","")} <br>
	<hr>
	문자검색(포함): ${title2} : ${fn:indexOf(title2,str1)} <br>
	문자검색(포함): ${title2} : ${fn:contains(title2,str1)} <br>
	문자검색(포함): ${title} : ${fn:contains(title,str1)} <br>
</body>
</html>