<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="ctxPath" value="<%=request.getContextPath() %>" />   
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
</head>
<body>

    <div class="main">
        <div class="container">
            <div>
                <h3 class="text-center m-3">회원정보 수정</h3>
            </div>
            <hr>
            <div class="w-75 border p-3 m-3 m-auto ">
                <form action="${ctxPath}/tmemUpdate.do" method="post" id="updateForm">
                    <div class="mb-3">
                        <label  class="form-label">ID</label>
                        <label  class="form-label">${member.id}</label>
                        <input type="hidden" name="id" value="${member.id}" class="form-control" id="id" readonly >
                    </div>
                    <div class="mb-3">
                        <label for="pwd" class="form-label">password</label>
                        <input type="password" name="pwd" value="${member.pwd}" class="form-control" id="pwd" >
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" name="name" value="${member.name}" class="form-control" id="name" >
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" name="email" value="${member.email}" class="form-control" id="email" >
                    </div>
                    <div class="mb-3">
                        <input type="submit" id="updateBtn" class="btn btn-success" value="수정하기">
                        <a href="${ctxPath}/tmemList.do" class="btn btn-secondary">목록</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    
    
    <script type="text/javascript">
    	$(function(){
    		console.log('jQuery Ok...');
    		
    		$('#updateBtn').click(function(e){
    			e.preventDefault();// submit()기능 중지
    			
    			var isUpdate = confirm('수정하시겠습니까?')
    			console.log("isUpdate = "+isUpdate)
    			
    			if (isUpdate){
    				$('#updateForm').submit();
    			}
    		});
    	});
    </script>
</body>
</html>