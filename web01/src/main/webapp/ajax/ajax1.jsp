<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="ctxPath" value="<%=request.getContextPath() %>" />  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<input type="button" value="전송하기 1" id="sendBtn">
		<div id="message1"></div>
		<input type="button" value="전송하기 2" onclick="fn_process()">
		<div id="message2"></div>
		<hr>
		<input type="button" value="json형식문자열 -> json변환" id="checkJson">
		<div id="output1"></div>
		<hr>
		<input type="button" value="json객체 전송" id="checkJson2">
		<hr>
		<input type="button" value="json객체 받기" id="checkJson3">
		<div id="output2"></div>
	</div>
	<input type="text" id="id">

<script type="text/javascript">
	
	$(function() {
		console.log('jQuery')
		
			// json data 받기
			$('#checkJson3').click(function() {
				console.log('clickJson3')
				
				var _jsonInfo = 
				
				$.ajax({
					type: "post",
					async: false,
					url: "${ctxPath}/jsonSend",// 서버에 데이터를 요청하는 url
					success: function(data, textStatus) {
						// 서버로부터 받은 데이터 JSON객체로 전환
						var jsonInfo = JSON.parse(data)
						var memberInfo = ""
						// 객체 값 읽기 반복
						for (var i in jsonInfo.member){
							memberInfo += "<hr>"
							memberInfo += jsonInfo.member[i].name+"<br>"
							memberInfo += jsonInfo.member[i].gender+"<br>"
							memberInfo += jsonInfo.member[i].nickname+"<br>"
						}
						// 결과값 표시
						$('#output2').html(memberInfo);
					},
					error: function(data, textStatus) {
						alert('에러')
					}
				});
			})
		
			// json data 전송
			$('#checkJson2').click(function() {
				console.log('clickJson2')
				var _jsonInfo = '{"name":"홍길동","age":25,"gender":"남자","nickname":"나부랭이"}'
				var _id = $('#id').val()
				console.log(_jsonInfo)
				$.ajax({
					type: "post",
					async: false,
					url: "${ctxPath}/json",
					data: {
						jsonInfo : _jsonInfo,
						id : _id,
						pwd : '1234',
						name : '테스트11',
						phone : '010-1234-1234',
						age : 100,
					},
					success: function(data, textStatus) {
						alert('정상')
					},
					error: function(data, textStatus) {
						alert('에러')
					}
				});
			});
		
		
		
			// json data 형식 문자열 => json객체 전환
			$('#checkJson').click(function () {
				console.log('clickJson')
				
				// json형식을 가지고 있는 문자열 => json객체로 전환
				var jsonStr = '{"age":[22,33,44]}'
				var jsonInfo = JSON.parse(jsonStr)
				
				console.log("jsonStr => "+jsonStr)
				console.log("jsonInfo => "+jsonInfo)
				console.log("jsonInfo => "+jsonInfo.age)
				console.log("jsonInfo => "+jsonInfo.age[0])
				
				output = "";
				for (var i in jsonInfo.age) {// 객체명칭.속성
					output += jsonInfo.age[i]+"<br>"
				}
				$('#output1').html(output)
			})
		
		
			$('#sendBtn').click(function name() {
				console.log('click1')
			
				$.ajax({
					type:"get",
					dataType:"text",
					async:false,// false이면 동기식
					url: "/web01/ajaxTest1",
					data: {param:"Hello jQeury!!!"},
					success: function(data){
						$('#message1').append(data)
					},
					error: function(data, status) {
						alert('에러1')
					},
					complete: function(data, status) {
						alert('작업완료1')
					}
				});
			});
		});
	
	function fn_process() {
		console.log('click2')
		$.ajax({
			type:"get",
			dataType:"text",
			async:false,// false이면 동기식
			url: "/web01/ajaxTest1",
			data: {param:"Hello jQeury!!!"},
			success: function(data){
				$('#message2').append(data)
			},
			error: function(data, status) {
				alert('에러2')
			},
			complete: function(data, status) {
				alert('작업완료2')
			}
		});
}

</script>
</body>
</html>


<!--

Ajax: Asychronous Javascript(비동기 자바스크립트) + XML
	- 자바스크립트를 사용한 비동기 통신
	- 클라이언트와 서버간의 XML,JSON데이터를 주고받는 기술
	- Ajax은 페이지 이동 없이 데이터 처리가 가능하며, 서버의 처리를 기다리지 않고 비동기 요청이 가능
	

$.ajax({
	type: 통신(전송방식), //post,get...
	async: true, or false,
	url: 요청 url,
	data: {서버로 전송할 데이터} ,
	dataType: 서버에서 전송받을 데이터 형식, //xml, text, html, json...
	success: {
		// 정상요청, 응답시 처리
		},
	error: function(xhr,status,error) {오류 발생시 처리},
	complete: function(data,textStatus) {작업 완료후 처리}
});  

-->
