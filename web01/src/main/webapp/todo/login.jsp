<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
<!--<%=request.getParameter("error") %>  -->

	<c:if test="${param.result == 'error'}">
		<h1>로그인 에러</h1>
	</c:if>
    <div class="mt-5">
        <div class="row justify-content-center">
        <form action="/web01/login" method="post" 
                class="form-control w-75 p-3  bg-light">
            <div class="row m-2 justify-content-around">
                <div class="col-md-4 d-flex align-items-center">
                    <label>아이디</label>    
                </div>
                <div class="col-md-6">
                    <input type="text" name="user_id" class="form-control">
                </div>
            </div>
            <div class="row m-2 justify-content-around">
                <div class="col-md-4 d-flex align-items-center">
                    <label>비밀번호</label>    
                </div>
                <div class="col-md-6">
                    <input type="password" name="user_pw" class="form-control">
                </div>
            </div>
            <div class="row m-2 justify-content-around">
                <div class="col-md-4 d-flex align-items-center">
                    <input type="checkbox" name="auto"><label>자동로그인</label> 
                </div>
                	<div class="col-md-6">
                </div>
            </div>
            <div class="row m-auto mt-4 justify-content-around">
                    <input type="submit" value="로그인" class="btn btn-outline-info col-md-3 m-2 bg-white">
                    <input type="reset" value="다시입력" class="btn btn-outline-success col-md-3 m-2 bg-white">
            </div>
        </div>
        </form>
    </div>
</body>
</html>