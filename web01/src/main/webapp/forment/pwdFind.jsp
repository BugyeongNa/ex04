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
<form action="/web01/forment/pwdView" method="post">
              <div class="row justify-content-between">
                <div class="col-md-8">
                  <div class="row container m-auto mb-3">
                    <label for="id" class="p-1">아이디</label>
                    <input type="text" class="border-bottom p-1" id="id" placeholder="아이디를 입력해주세요" name="id">
                    
                  </div>
                <div class="row container m-auto mb-3">
                  <label for="name" class="p-1">이름</label>
                  <input type="text" class="border-bottom p-1" id="name" placeholder="이름을 입력해주세요" name="name"/>
                 
                  </div>
                <div class="row container m-auto mb-3">
                  <label for="phone" class="p-1">휴대폰 번호</label>
                  <input type="text" class="border-bottom p-1" id="phone" placeholder="휴대폰 번호를 입력해주세요" name="phone">
                </div>
                <div class="row container m-auto mb-3">
                  <label for="email" class="p-1">이메일</label>
                  <input type="email" class="border-bottom p-1" id="email" placeholder="이메일을 입력해주세요" name="email">
                  
                </div>
              </div>
              <div class="container row col-md-4 justify-content-center m-auto">
                <img  src="https://scontent-ssn1-1.xx.fbcdn.net/v/t1.6435-9/94443863_104104627948854_7822159765651324928_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=973b4a&_nc_ohc=BAAVG34IIKEAX8kWkIO&_nc_oc=AQmNuhzcFsu3VsQ6gtjQ9DQO82XpRooMWldA6m7ptbR_ywB2akN1RQUXxO1CA6XvAIY&_nc_ht=scontent-ssn1-1.xx&oh=00_AfAAvwwiNXH5PBVRAJcswirsvXBhW0aUmRQxX3AJQeQ4Vw&oe=6478D005"
         class="m_logo mb-3 w-100 h-100">
              <button type="submit" class="btn btn-primary btn-lg" onclick="findPwd()">비밀번호 찾기</button>
              <div class="d-flex justify-content-between align-items-center mt-3">
              <div class="">
              <a href="/web01/loginuitest/login" class="link-font">로그인 페이지로 이동</a>
            </div>
            <div class="">
            <a href="/web01/loginuitest/findId" class="link-font">아이디 찾기</a>
          </div>
        </div>
        
            </div>
          </div>
          <div class="d-flex container m-auto errorFont">
          <c:if test="${param.result == 'error'}">
		입력하신 정보로 비밀번호를 찾을 수 없습니다. 다시 한번 확인해주세요
		</c:if> 
          </div>
              </form>
              </div>
    </div>
    </div>
      
              
              
            
    <script>
    function findPwd(){
		location.href = "/web01/testforment/pwdView"
        
      }
    </script>
            
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>