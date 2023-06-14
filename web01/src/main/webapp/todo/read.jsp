<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath()%>" scope="page"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${dto}
<hr>
<form action="">
	<div>
		<div>
			<input type="text" name="tno" value="${dto.tno}" readonly>
		</div>
		<div>
			<input type="text" name="title" value="${dto.title}" readonly>
		</div>
		<div>
			<input type="date" name="dueDate" value="${dto.dueDate}" readonly>
		</div>
		<div>
			<input type="checkbox" name="finished" ${dto.finished ? "checked": ""} readonly>
		</div>
		<div>
			<a href="/web01/todo/modify?tno=${dto.tno}">modify</a>
			<a href="/web01/todo/list">list</a>
		</div>
		<div></div>
		<div></div>
	</div>
</form>
</body>
</html>