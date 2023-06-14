<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
            <form action="/web01/testforment/idView" method="post">
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
                <div class="row container m-auto mb-3">
                  <label for="email" class="p-1">이메일</label>
                  <input type="email" class="border-bottom p-1" id="email" placeholder="이메일을 입력해주세요" name="email">
                  
                </div>
                <div class="row container m-auto mb-3 errorFont">
            <c:if test="${param.result == 'error'}">
		입력하신 정보로 가입된 아이디가 없습니다.
		</c:if> 
          </div>
              </div>
              <div class="container row col-md-4 justify-content-center">
              <button type="submit" class="btn btn-primary btn-lg" onclick="findId()">아이디 찾기</button>
            
              <div class="d-flex justify-content-between align-items-center mt-3">
              <div>
              <a href="/web01/testforment/login" class="link-font">로그인 페이지로 이동</a>
            </div>
            <div>
            <a href="/web01/testforment/pwdFind" class="link-font">비밀번호 찾기</a>
           
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
</body>
</html>