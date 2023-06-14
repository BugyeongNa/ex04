<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	String ctxPath = request.getContextPath();// 컨텍스트이름(애플리케이션이름)
	
%>    
<c:set var="path" value="<%=request.getContextPath() %>" scope="page"/>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <%=ctxPath %>, ${path } --%>
<form action="<%=ctxPath %>/todo/register" method="post">
	<div>
		<input type="text" name="title" placeholder="insert title">
	</div>
	<div>
		<input type="date" name="dueDate">
	</div>
	<div>
		<button type="reset">RESET</button>
		<button type="submit">REGISTER</button>
		<button><a href="${path}/todo/list" style="text-decoration:none">LIST</a></button>
		<input type="button" value="List" id="list">
	</div>
</form>

<!-- <script type="text/javascript">
	window.onload = function() {
		const list = document.getElementById('list')
		list.addEventListener('click',function(){
  				location.href = '/web01/todo/list';
		});
}
	
</script> -->
<script type="text/javascript">
	window.onload = function(){
		const list = document.getElementById('list');
		
		list.addEventListener('click',function(){
 			location.href = '/web01/todo/list';
			
			
		});
	}


</script>

</body>
</html>