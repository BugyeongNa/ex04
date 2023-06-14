<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="ctxPath" value="<%=request.getContextPath() %>"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<!--<%=request.getParameter("error") %>  

	<c:if test="${param.result == 'error'}">
		<h1>로그인 에러</h1>
	</c:if>
	-->
    <div class="mt-5 container">
        <div class="row justify-content-center">
        <form action="${ctxPath}/login" method="post" 
                class="form-control w-75 p-3  bg-light">
            <div class="row m-2 justify-content-around">
                <div class="col-md-4 d-flex align-items-center">
                    <label>아이디</label>    
                </div>
                <div class="col-md-6">
                    <input type="text" name="user_id" id="user_id" class="form-control">
                    <div class="text-danger" id="id_message"></div>
                </div>
            </div>
            <div class="row m-2 justify-content-around">
                <div class="col-md-4 d-flex align-items-center">
                    <label>비밀번호</label>
                </div>
                <div class="col-md-6">
                    <input type="password" name="user_pw" id="user_pw" class="form-control">
                    <div class="text-danger" id="pw_message"></div>  
                </div>
            </div>
            <div class="row m-2 justify-content-around">
                <div class="col-md-6 d-flex align-items-center">
                    <input type="checkbox" name="user_auto" id="auto"><label class="m-1">자동로그인</label> 
                </div>
                	<div class="col-md-6 d-flex align-items-center">
                    <input type="checkbox" name="keepLogin" id="keepLogin"><label class="m-1">로그인 유지</label> 
                </div>
            </div>
            <div class="row m-auto mt-4 justify-content-around">
                    <input type="submit" id="btnLogin" value="로그인" class="btn btn-outline-info col-md-3 m-2 bg-white">
                    <input type="reset" value="다시입력" class="btn btn-outline-success col-md-3 m-2 bg-white">
            </div>
        </div>
        </form>
    </div>
    <!-- jQuery CDN  -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
    <script type="text/javascript">
    	console.log('jQuery OK')
    	$('#btnLogin').click(function (e) {
			e.preventDefault();
			
			var user_id = $('#user_id').val();
			var user_pw = $('#user_pw').val();
			var auto = $('#auto').is(':checked');
			
			// html요소 값 => js 변수 저장 => json형식변환 => ajax()로 전송
			var _jsonData = JSON.stringify({"user_id" : user_id, "user_pw" : user_pw, "auto" : auto})
			console.log(_jsonData)
			//var jsontm = JSON.stringify(_jsonData)
			//console.log(jsontm)
			
			$.ajax({
				type: "post",
				async: false,
				url: "${ctxPath}/login",
				data: {logindata : _jsonData},
				success: function(data, textStatus){
					console.log(data, textStatus);
					$('#message').text(data);
					
					var jsonMessage = JSON.parse(data)
					
					console.log(jsonMessage);
					
					$('#id_message').text('')
					$('#pw_message').text('')
					if (jsonMessage.code === 'id_fail'){
						$('#id_message').text(jsonMessage)
					} else if(jsonMessage.code === 'pw_fail') {
						$('#pw_message').text(jsonMessage)
					} else 
						location.href="${ctxPath}/boardlist/boardList.do"
				},
				error: function(){},
				complete: function(){}
					
			});
			$('#user_id').focus(function() {
				$(this).val('')
			});
			$('#user_pw').focus(function() {
				$(this).val('')
			});
			
			
			// 로그인 유지: 쿠기값 읽어오기
			$('#keepLogin').click(function() {
				let result;
				// 자바스크립트
				 if (document.cookie != ""){
		                // "notShowPop=false;path=/; expires=-1"
		                // [0]=>notShowPop=false, [1]=> path=/, [2]=>expires=-1
		                cookie = document.cookie.split(";")

		                for (let i=0; i<cookie.length; i++){
		                    // notShowPop=false 
		                    // [0]=>notShow, [1]=>false
		                    element = cookie[i].split("=")
		                    value = element[0]
		                    value = value.replace(/^s*/,'')// 정규식 공백제거
		                    
	                        result = element[1];

		                }

		            }
				
				if (result){
					console.log('쿠키값: '+result)
				} else {
					console.log('쿠키값없음')
					
				}
			});
			
		});
    </script>
</body>
</html>