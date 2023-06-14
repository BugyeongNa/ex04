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
    
</head>
<body>
	
	    <div class="main">
        <div class="container">
            <div>
                <h2 align="center" class="mt-3 p-3 border rounded-2">회원정보 조회</h2>
            </div>
            <div class="border p-3 m-3 m-auto form-control">
                <div class="p-4 m-3">
                    <div class="mb-3 d-flex justify-content-center">
                        <div  class="col-md-2 border-0 rounded-1 m-1 p-1 ">아이디</div>
                        <div  class="col-md-6 border rounded-1 m-1 p-1">${member.id}</div>
                    </div>
                    <div class="mb-3 d-flex justify-content-center">
                        <div  class="col-md-2 border-0 rounded-1 m-1 p-1 ">비밀번호</div>
                        	<div   class="col-md-6 border rounded-1 m-1 p-1">
                        	 <input type="password" value="${member.pwd}" class="m-0 p-0 readonly border-0">
                        	 </div>
                    </div>   
                    <div class="mb-3 d-flex justify-content-center">
                        <div  class="col-md-2 border-0 rounded-1 m-1 p-1 ">이름</div>
                        <div  class="col-md-6 border rounded-1 m-1 p-1">${member.name}</div>
                    </div>
                    <div class="mb-3 d-flex justify-content-center">
                        <div class="col-md-2 border-0 rounded-1 m-1 p-1 ">휴대폰</div>
                        <div class="col-md-6 border rounded-1 m-1 p-1">${member.phone}</div>
                    </div>
                    <div class="mb-3 d-flex justify-content-center">
                        <div class="col-md-2 border-0 rounded-1 m-1 p-1 ">이메일</div>
                        <div class="col-md-6 border rounded-1 m-1 p-1">${member.email}</div>
                    </div>
                    <div class="mb-3 d-flex justify-content-center">
                        <div  class="col-md-2 border-0 rounded-1 m-1 p-1 ">가입일자</div>
                        <div  class="col-md-6 border rounded-1 m-1 p-1">${member.joindate}</div>
                    </div>
                
                    <div class=" d-flex justify-content-center">
                    <div class="col-md-2"></div>
                        <div class="col-md-6">
                            <a href="${ctxPath}/sqlmember/modifyMember.do?name=${member.name}" class="btn btn-success btn-sm m-1">수정</a>
                            <a href="${ctxPath}/sqlmember/deleteMember.do?id=${member.id}" class="btn btn-warning btn-sm m-1" id="delBtn">삭제</a>
                            <input type="hidden" value="${member.id}" id="memberId">
                            <input type="hidden" value="${ctxPath}" id="path">
                        </div>
                        <div class="col-md-2 d-flex justify-content-end">
                            <a href="${ctxPath}/sqlmember/listMember.do" class="btn btn-dark btn-sm m-1">목록</a>
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
    <script type="text/javascript">
    	$(function(){
    		console.log('jQuery Ok...');
    		
    		$('#delBtn').click(function(e){
    			e.preventDefault();// submit()기능 중지
    			
    			var isDelete = confirm('삭제하시겠습니까?')
    			console.log("isDelete = "+isDelete)
    			
    			var id = $('#memberId').val()
    			var path = $('#path').val()
    			console.log('id: '+id, 'path: '+path)
    			
    			if (isDelete){
    				location.href=path+"/sqlmember/deleteMember.do?id="+id;
    			}
    		});
    	});
    </script>
</body>
</html>










