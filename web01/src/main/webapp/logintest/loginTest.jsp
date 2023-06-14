<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
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
          font-size: 0.8em;
        }
        .btn{
          font-size: 0.8em;
          margin: 1%;
        }
        #message{
          font-size: 0.8em;
          color: red;
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
            
    <form action="/web01/loginuitest/boardMove" class="login" method="post">
      <div class="row">
      <div class="col-md-8">
      <div class="row container m-auto mb-4">
        <label for="id" class="col-md-3 p-1">아이디</label>
        <input type="text" class="col-md-8 border-bottom" id="id" placeholder="아이디를 입력해주세요" name="id"/>
       
        </div>
  <div class="row container m-auto mb-3">
    <label for="password" class="col-md-3 p-1">비밀번호</label>
    <input type="password" class="col-md-8 border-bottom" id="password" placeholder="비밀번호를 입력해주세요" name="pwd">
    </div>
    <div class="row container m-auto">
        <div class="">
        <input type="checkbox" name="auto"> 자동로그인
    </div>
        <div class="d-flex">
		<div id="message" class="col-md-8">
		<c:if test="${param.result == 'error'}">
		아이디 또는 비밀번호를 잘못 입력했습니다.
		</c:if>
		</div>
            <div class="col-md-4 p-2"></div>  
		
          </div>
        </div>
  </div>
  <div class="col-md-3 mt-2 mb-4 row justify-content-center m-auto">
    <img  src="data:image/jpeg;base64,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"
         class="logo w-100 h-100">
  </div>
</div>
                  <div class="container row justify-content-around m-auto">
                    <button type="submit" class="btn btn-success col-md-3">로그인</button>
                  
                    
                    <input type="reset" class="btn btn-warning col-md-3" value="다시입력">
                
                  </form>
                      <button type="button" class="btn btn-primary col-md-3" id="waitBtn"><a class="dropdown-item" href="#">회원가입</a>
                      </button>
                    </div>    
            </div>
            <hr>
            <div class="container row m-auto align-items-center">
                <div class="col-md-4 d-flex justify-content-center">계정을 잊어버리셨나요?</div>
                <div class="col-md-4"><a href="/web01/loginuitest/findId" class="d-flex justify-content-center"> 아이디 찾기 </a></div>
                <div class="col-md-4"><a href="/web01/loginuitest/findPwd" class="d-flex justify-content-center"> 비밀번호 찾기 </a></div>
        </div>
       
        
    </div>
</div>
</div>

</div>
<script>
    const loginForm = document.querySelector('.login')
    const message = document.querySelector('#message')
    const waitBtn = document.querySelector('#waitBtn')
    var id = document.querySelector('#id')
    var password = document.querySelector('#password')

    
    waitBtn.addEventListener('click',() => {
    	alert('아직 준비중입니다..')
    })
    
    loginForm.addEventListener('submit',login)
    
    	function login(e){
    		e.preventDefault()
    		if (id.value.length=0 || id.value==""){
    			message.textContent = "아이디를 입력해주세요"
                id.focus()
            }else if (password.value.length=0 || password.value ==""){
                message.textContent = "비밀번호를 입력해주세요"
                password.focus()
            }else {
                message.textContent = ""
                loginForm.submit()
            }
    	}
    
    
   
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>