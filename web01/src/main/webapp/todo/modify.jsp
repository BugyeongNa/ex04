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
수정페이지
<hr>
${dto}
<hr>
<form action="${path}/todo/modify" method="post" name="form1">
	<div>
		<div>
			<input type="text" name="tno" value="${dto.tno}" readonly="readonly">
		</div>
		<div>
			<input type="text" name="title" value="${dto.title}">
		</div>
		<div>
			<input type="date" name="dueDate" value="${dto.dueDate}">
		</div>
		<div>
			<input type="checkbox" name="finished" ${dto.finished ? "checked": ""} readonly>
		</div>
		<div>
			<button>modify</button>
		</div>
	</div>
	</form>
	<form action="${path}/todo/remove" method="post" name="form2">
		<input type="hidden" name="tno" value="${dto.tno}" readonly="readonly">
		<div>
			<button>Remove</button>
		</div>
	</form>
</body>
</html>