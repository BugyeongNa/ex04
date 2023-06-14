<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="ctxPath" value="${pageContext.request.contextPath }"/>
<c:set var="file1" value="${param.param1}" />
<c:set var="file2" value="${param.param2}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
	<div class="container m-3 p-3">
	매개변수1: <c:out value="${file1}"/> <br>
	매개변수2: ${file2} <br>
	<!-- 웹폴더 기준 -->
	<img src="../img/3.png" width="100">
	<hr>
	
	<c:if test="${not empty file1}">
		<!-- 서버에 접속해서 이미지 요청: 스트림방식 처리 -->
		<img src="${ctxPath}/download?fileName=${file1}" width="300" height="300"> <br>
	</c:if>
	<c:if test="${not empty file2}">
		<img src="${ctxPath}/download?fileName=${file2}" width="300" height="300"> <br>
	</c:if>
	<hr>
	파일 내려받기: <br>
	<a href="${ctxPath}/download?fileName=${file2}">내려받기</a>
	<hr>
	<c:out value="홍길동"/> <br>
	<c:out value="${100*2}"/> <br>
	<c:out value="${ctxPath}"/> <br>
	<c:out value="${pageContext.request.contextPath }"/> <br>
	<c:out value="${param.param1}"/>, <%=request.getParameter("param1") %>
	
	</div>
</body>
</html>