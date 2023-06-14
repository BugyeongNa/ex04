<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
    
<%
// 	isELIgnored="true" => EL 비활성화
// 	isELIgnored="false" , 생략시 => EL 활성화
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>EL 표현식</h2>
	<hr>
	${100}, ${100+200}, ${3>2}, ${3>2&&4>2}, ${3>2 and 5>2 }
	<hr>
<%-- 	${100/3}, ${100 div 3}, ${100%3}, ${100 mod 3} --%>
</body>
</html>

<!--   서블릿     					JSP     		EL -->
<!--   this     					page  		 	pageScope -->
<!--   HttpServletRequest		request		 	requestScope -->
<!--   .getParameter("매개변수")	.getParameter 	param	 -->