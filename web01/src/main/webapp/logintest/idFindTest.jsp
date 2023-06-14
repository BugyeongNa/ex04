<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <style>

      body{
        padding-top: 100px;
      }
        .registerCenter{
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .registerCard{
            border-radius: 10px;
            padding: 5%;
        }
        label{
            font-size: 1.0em;
            font-weight: 600;
            background-color: #f5f6f7;
            border-radius: 5px;
        }
        input{
          border: 0;
          font-size: 0.8em;
        }
        .btn{
          font-size: 0.8em;
        }
        .logo{
         width: 7.2em; height: 3.6em;
        }
        .top_font{
          font-size: 1.2em;
          font-weight: 600;
        }
        .link-font{
          font-size: 0.8em;
        }
        .errorFont{
          color: red;
        }
        .m_logo{
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
                        <li><a class="dropdown-item" href="/web01/loginuitest/board/boardMove">게시판</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
    </div>
    <div class="container registerCenter">
    <div class="registerCard border bg-white m-1 w-100">
      <div class="container">
      <div class="row align-items-end m-auto">
        <img  src="https://scontent-ssn1-1.xx.fbcdn.net/v/t1.6435-9/94443863_104104627948854_7822159765651324928_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=973b4a&_nc_ohc=BAAVG34IIKEAX8kWkIO&_nc_oc=AQmNuhzcFsu3VsQ6gtjQ9DQO82XpRooMWldA6m7ptbR_ywB2akN1RQUXxO1CA6XvAIY&_nc_ht=scontent-ssn1-1.xx&oh=00_AfAAvwwiNXH5PBVRAJcswirsvXBhW0aUmRQxX3AJQeQ4Vw&oe=6478D005"
         class="col-md-2 m-1 logo">
          <div class="col-md-3 top_font">아이디 찾기</div>
        </div>
      </div>

            <hr>
            <form action="/web01/loginuitest/viewId" method="post">
              <div class="row justify-content-between">
                <div class="col-md-8 m-auto row align-content-center">
                <div class="row container m-auto mb-3">
                  <label for="name" class="p-1">이름</label>
                  <input type="text" class="border-bottom p-1" id="name" placeholder="이름을 입력해주세요" name="name"/>
                 
                  </div>
                <div class="row container m-auto mb-3">
                  <label for="phone" class="p-1">휴대폰 번호</label>
                  <input type="text" class="border-bottom p-1" id="phone" placeholder="휴대폰 번호를 입력해주세요" name="phone">
                  
                </div>
                <div class="row container m-auto mb-3 errorFont">
            <c:if test="${param.result == 'error'}">
		입력하신 정보로 가입된 아이디가 없습니다.
		</c:if> 
          </div>
              </div>
              <div class="container row col-md-4 justify-content-center">
               <img  src="https://scontent-ssn1-1.xx.fbcdn.net/v/t1.6435-9/94443863_104104627948854_7822159765651324928_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=973b4a&_nc_ohc=BAAVG34IIKEAX8kWkIO&_nc_oc=AQmNuhzcFsu3VsQ6gtjQ9DQO82XpRooMWldA6m7ptbR_ywB2akN1RQUXxO1CA6XvAIY&_nc_ht=scontent-ssn1-1.xx&oh=00_AfAAvwwiNXH5PBVRAJcswirsvXBhW0aUmRQxX3AJQeQ4Vw&oe=6478D005"
         class="m_logo mb-3 w-100 h-100">
              <button type="submit" class="btn btn-primary btn-lg" onclick="findId()">아이디 찾기</button>
            
              <div class="d-flex justify-content-between align-items-center mt-3">
              <div>
              <a href="/web01/loginuitest/login" class="link-font">로그인 페이지로 이동</a>
            </div>
            <div>
            <a href="/web01/loginuitest/findPwd" class="link-font">비밀번호 찾기</a>
           
          </div>
        </div>
        
            </div>
          </div>
          
              </div>
    </div>
    </div>
      
             </form> 
              
            
    
            
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>