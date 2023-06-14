<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디 보기</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<style>
	 body{
        margin-top: 100px;
      }
      .loginCenter{
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .loginCard{
            padding: 5%;
            border-radius: 10px;
        }
        label{
          background-color: #f5f6f7;
          font-size: 1.0em;
          border-radius: 5px;
        }
        input{
          border: 0;
        }
        .btn{
          font-size: 0.8em;
          margin: 1%;
        }
        .message{
          font-size: 0.8em;
        }
        .top_logo{
          width: 7.5em;height: 1.2em;
        }
        .logo{
          max-width: 200px;max-height: 100px;
        }
</style>
</head>
<body class="bg-light">
    <div>
    <div class="topBanner">
        <nav class="navbar bg-white fixed-top">
            <div class="container-fluid">
              <a class="navbar-brand" href="http://www.ysing.com/"><img src="http://www.ysing.com/images/common/logo.jpg"></a>
              <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                  <h5 class="offcanvas-title" id="offcanvasNavbarLabel">목록</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                  <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="http://www.ysing.com/">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        과제목록
                      </a>
                      <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/web01/loginuitest/login">로그인</a></li>
                        <li>
                            <hr class="dropdown-divider">
                          </li>
                        <li><a class="dropdown-item" href="/web01/ajaxtest01/ajaxTest.jsp">REST API</a></li>
                        <li>
                          <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="/web01/loginuitest/board/boardList">게시판</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
    </div>
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
                  
                      <button type="button" class="btn btn-primary col-md-3" onclick="wait()">회원가입
                      </button>
                  </div>
</div>
</div>


  
 
</div>
</div>
</div>
<script>
    function wait(){
      alert("준비중...")
    }
    function pwdFind(){
    	location.href="/web01/loginuitest/findPwd"
    } 
    function login(){
    	location.href="/web01/loginuitest/login"
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>         	
	
</body>
</html>