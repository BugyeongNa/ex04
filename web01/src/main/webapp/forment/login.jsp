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
<form action="/web01/testforment/memberLogin" class="login" method="post">
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

</div>
                  <div class="container row justify-content-around m-auto">
                    <button type="submit" class="btn btn-success col-md-3">로그인</button>
                  
                    
                    <input type="reset" class="btn btn-warning col-md-3" value="다시입력">
                
                  </form>
                      <button type="button" class="btn btn-primary col-md-3" id="joinBtn"><a class="dropdown-item" href="/web01/testforment/join">회원가입</a>
                      </button>
                    </div>    
            </div>
            <hr>
            <div class="container row m-auto align-items-center">
                <div class="col-md-4 d-flex justify-content-center">계정을 잊어버리셨나요?</div>
                <div class="col-md-4"><a href="/web01/testforment/idFind" class="d-flex justify-content-center"> 아이디 찾기 </a></div>
                <div class="col-md-4"><a href="/web01/testforment/pwdFind" class="d-flex justify-content-center"> 비밀번호 찾기 </a></div>
        </div>
       
        
    </div>
</div>
</div>

</div>
<script>
    const loginForm = document.querySelector('.login')
    const message = document.querySelector('#message')
    const joinBtn = document.querySelector('#joinBtn')
    var id = document.querySelector('#id')
    var password = document.querySelector('#password')

    
    joinBtn.addEventListener('click',() => {
    	location.href = "/web01/testforment/join"
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