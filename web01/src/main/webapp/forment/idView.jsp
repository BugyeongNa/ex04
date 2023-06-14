<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container loginCenter">
            <div class="container loginCard bg-white border m-1">
                <div class="container"><a href="http://www.ysing.com/"> <img src="http://www.ysing.com/images/common/logo.jpg" class="top_logo"></a>
                 
            </div>
            <hr>
            <div>
      <div class="row">
      <div class="col-md-8">
      <div class="row container m-auto mb-4">
        <label class="col-md-12 p-1">아이디 찾기</label>
       
        </div>
  <div class="row container m-auto">
    <label for="idView" class="col-md-5 p-1 border-bottom"> 고객님의 아이디는 </label>
    <input type="text" readonly class="col-md-7" id="idView" value="${id.id} 입니다.">
   
    </div>
  </div>
  <div class="col-md-3 mt-4 mb-4 row justify-content-center m-auto">
    <img  src="https://scontent-ssn1-1.xx.fbcdn.net/v/t1.6435-9/94443863_104104627948854_7822159765651324928_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=973b4a&_nc_ohc=BAAVG34IIKEAX8kWkIO&_nc_oc=AQmNuhzcFsu3VsQ6gtjQ9DQO82XpRooMWldA6m7ptbR_ywB2akN1RQUXxO1CA6XvAIY&_nc_ht=scontent-ssn1-1.xx&oh=00_AfAAvwwiNXH5PBVRAJcswirsvXBhW0aUmRQxX3AJQeQ4Vw&oe=6478D005"
         class="logo w-100 h-100">
  </div>
</div>
                  <div class="container row d-flex justify-content-around m-auto">
                    <button type="button" class="btn btn-success col-md-3" onclick="login()">로그인하러 가기</button>
                    <button type="button" class="btn btn-warning col-md-3" onclick="pwdFind()">비밀번호찾기</button>
                  
                      <button type="button" class="btn btn-primary col-md-3" onclick="join()">회원가입
                      </button>
                  </div>
</div>
</div>


  
 
</div>
</div>
</div>
<script>
    function join(){
    	location.href="/web01/testforment/join"
    }
    function pwdFind(){
    	location.href="/web01/testforment/pwdFind"
    } 
    function login(){
    	location.href="/web01/testforment/login"
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>         	
	
</body>
</html>