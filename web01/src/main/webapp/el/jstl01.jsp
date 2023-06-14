<%@page import="java.util.List"%>
<%@page import="com.ex01.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--  scope : page, request, session, application -->
<c:set var="id" 	value="hong" scope="page" />
<c:set var="pwd" 	value="1234" scope="page" />
<c:set var="name" 	value="${'홍길동'}" scope="page" />
<c:set var="age" 	value="${22}" scope="page" />
<c:set var="height" value="${177}" scope="page" />
<c:set var="path" 	value="<%=request.getContextPath() %>" scope="page" />

<%
	MemberDTO dto = MemberDTO.builder()
				.id("user01")
				.pw("1234")
				.name("홍길동")
				.build();
				
	System.out.println(dto);
	//ArrayList memberList = new ArrayList();
%>
<jsp:useBean id="memberList"  class="java.util.ArrayList" />
<jsp:useBean id="memberMap"   class="java.util.HashMap"/>
<%
memberMap.put("id", "park");
memberMap.put("pwd", "1234");

MemberDTO dto2 = MemberDTO.builder()
	.id("user02").pw("1234").name("홍길동2").build();

MemberDTO dto3 = MemberDTO.builder()
	.id("user03").pw("1234").name("홍길동3").build();

memberList.add(dto2);
memberList.add(dto3);

memberMap.put("memberList", memberList);
%>
<c:set var="membersList" value="${memberMap.memberList }" />
<c:set var="fruits" value="사고,바나나,파인애플, 망고, 귤" />
<c:set var="num" value="${10}" />

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
<div>
	<c:choose>
		<c:when test="${num==10}" >
			<div>${num*10 }</div>
		</c:when>
		<c:when test="${num==9}" >
			<div>${num/2 }</div>
		</c:when>
		<c:when test="${num==8}" >
			<div>${num-8}</div>
		</c:when>
		<c:when test="${num == null}" >
			<div>널임</div>
		</c:when>
		<c:when test="${empty num }" >
			<div>비어있음</div>
		</c:when>
		<c:otherwise>
			<div>기타</div>
		</c:otherwise>
	</c:choose>
	
	<hr>
	
	<c:if test="${'hello'=='hello'}">
		<h3>Hello</h3>
	</c:if>
	<hr>
	<c:forTokens var="data" items="${fruits}" delims=",">
		${data }<br>
	</c:forTokens>
	<hr>
	<c:forEach var="data" items="${membersList}">
		${data }<br>
	</c:forEach>
	<hr>
	<c:forEach var="list" items="${memberList}">
		${list }<br>
	</c:forEach>
	<hr>
	<c:forEach var="i" begin="1" end="5" step="2" varStatus="loop">
		i=${i}, 반복횟수: ${loop.count },${loop.index}<br>
	</c:forEach>

</div>
<hr>
	${memberMap.id }, ${memberMap.pwd } <br>
	${memberList[0].id },${memberList[0].pw },${memberList[0].name}<br>
	${memberList[1] }<br>
<hr>
	<%=dto %>
	<%=dto.getId() %>
<hr>
	<div>
	id: ${id }<br>
	pwd: ${pwd }<br>
	name: ${name }<br>
	age: ${age }<br>
	height: ${height }<br>
	path: ${path } => ${pageContext.request.contextPath}
	
	
	</div>
</body>
</html>