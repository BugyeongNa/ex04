<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포매팅 태그 라이브러리</title>
</head>
<body>
	<h3>fmt의 number</h3>
	<hr>
	<c:set var="price" value="100000000"/>
	<c:set var="num1" value="100"/>
	<fmt:formatNumber value="${price}"/>
	<fmt:formatNumber
		value="${num1}"
		type="number"
		var="priceNumber"
	/>
	fmt
	통화로 표시:
	<fmt:formatNumber
		value="${price}"
		type="currency"
		currencySymbol="\\"
		groupingUsed="true"
	/><!-- groupinUsed = "true" : 3자리마다 콤마(,): 기본값 true  -->
	<br>
	<c:set var="num1" value="0.1"/>
	일반 숫자로 표시: ${num1} <br>
	퍼센트 표시:
	<fmt:formatNumber
		value="${num1}"
		type="percent"
		groupingUsed="false"
	/>
	<hr>
	<h3>formatdate 형식</h3>
	<c:set var="now" value="<%=new Date() %>"/>
	${now}<br>
	<fmt:formatDate value="${now}"/><br>
	<fmt:formatDate value="${now}" type="date" dateStyle="full"/><br>
	<fmt:formatDate value="${now}" type="time"/><br>
	<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
	<fmt:formatDate value="${now}" pattern="YYYY-MM-dd 시간 hh:mm:ss"/><br>
	
	<%-- 
	pattern : 자바 클래스 SimpleDateFormat
	timeStyle : 자바 클래스 DateFormat
	dateStyle : 자바 클래스 DateFormat (full, long, medium, sort 등)
	type : date인 경우 날짜, time인 경우 시간, both인 경우 모두
	timeZone : 특정 나라 시간대를 시간을 설정 
	--%>
	한국 현재 시간:
	<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
	GMT: 
	<fmt:timeZone value="GMT">
	<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
	</fmt:timeZone>
	뉴욕 현재 시간:
	<fmt:timeZone value="America/new York">
	<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
	</fmt:timeZone>
</body>
</html>


<%-- 
<fmt:timeZone>, <fmt:setTimeZone>
 : 지정한 국가의 시간을 지정하는 태그
<fmt:formatNumber> : 표시할 숫자의 형식
<fmt:formatDate> : 지정한 형식의 날짜를 표시

 --%>
