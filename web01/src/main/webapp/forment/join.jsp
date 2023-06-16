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
<div class="form-control m-auto p-3 mb-5">
        <div class="row container m-auto p-2 justify-content-center">
            <label for="id" class="col-md-3 p-1 d-flex align-items-center">아이디</label>
            <input type="text" class="col-md-5 border-bottom" id="id"/>
            <div class="row">
                <div class="col-md-2 p-3"></div>
            <div id="idMes" class="col-md-10 pt-2 mes">
          </div>
            </div>
        </div>
            <div class="row container m-auto p-2 justify-content-center">
                <label for="pwd" class="col-md-3 p-1 d-flex align-items-center">비밀번호</label>
                <input type="text" class="col-md-5 border-bottom" id="pwd"/>
                <div class="row">
                    <div class="col-md-2 p-3"></div>
                <div id="pwdMes" class="col-md-3 pt-2 mes">
              </div>
                </div>
    
                </div>
                <div class="row container m-auto p-2 justify-content-center">
                    <label for="name" class="col-md-3 p-1 d-flex align-items-center">이름</label>
                    <input type="text" class="col-md-5 border-bottom" id="name"/>
                   
                    <div class="row">
                        <div class="col-md-2 p-3"></div>
                    <div id="nameMes" class="col-md-3 pt-2 mes">
                  </div>
                    </div>
        
                    </div>
                    <div class="row container m-auto p-2 justify-content-center">
                        <label for="phone" class="col-md-3 p-1 d-flex align-items-center">휴대폰</label>
                        <input type="text" oninput="autoHyphen(this)"  class="col-md-5 border-bottom" id="phone"/>
                       
                        <div class="row">
                            <div class="col-md-2 p-3"></div>
                        <div id="phoneMes" class="col-md-3 pt-2 mes">
                      </div>
                        </div>
            
                        </div>
                        <div class="row container m-auto p-2 justify-content-center">
                        <label for="email" class="col-md-3 p-1 d-flex align-items-center">email</label>
                        <input type="email" class="col-md-5 border-bottom" id="email"/>
                       
                        <div class="row">
                            <div class="col-md-2 p-3"></div>
                        <div id="emailMes" class="col-md-3 pt-2 mes">
                      </div>
                        </div>
            
                        </div><h2>두번째 코드 <span>- ^(\d{0,3})(\d{0,4})(\d{0,4})$</span></h2>
  <input type="text" oninput="autoHyphen2(this)" maxlength="13" placeholder="전화번호를 입력해보세요!">
 <select id="birthday" name="birthday" class="form-control">
  <option value="">년</option>
  <c:forEach var="i" begin="1900" end="2030">
    <option value="${i}">${i}</option>
  </c:forEach>
</select>
  
<select id="birthday" name="birthday" class="form-control">
  <option value="">월</option>
  <c:forEach var="i" begin="1" end="12">
  <c:choose>
      <c:when test="${i lt 10 }">
          <option value="0${i}">0${i}</option>
      </c:when>
      <c:otherwise>
          <option value="${i}">${i}</option>
      </c:otherwise>
  </c:choose>
  </c:forEach>
</select>
  
<select id="birthday" name="birthday" class="form-control">
  <option value="">일</option>
  <c:forEach var="i" begin="1" end="31">
  <c:choose>
      <c:when test="${i lt 10 }">
          <option value="0${i}">0${i}</option>
      </c:when>
      <c:otherwise>
          <option value="${i}">${i}</option>
      </c:otherwise>
  </c:choose>
  </c:forEach>
                        <div class="d-flex justify-content-end">
    <input type="button" class="btn btn-outline-success" value="회원등록" id="memberAdd">
</div>
    </div>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
    <script type="text/javascript">
    const autoHyphen = (target) => {
    	 target.value = target.value
    	   .replace(/[^0-9]/g, '')
    	  .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
    	}
    	 
    
    $('#memberAdd').click(function() {
        var _id = $('#id').val()
        var _pwd = $('#pwd').val()
        var _name = $('#name').val()
        var _phone = $('#phone').val()
        var _email = $('#email').val()

        if (_id.length=0 || _id==""){
            $('#idMes').text("아이디를 입력해주세요.")
            id.focus()
        }else if(_pwd.length=0 || _pwd==""){
            $('#pwdMes').text("비밀번호를 입력해주세요.")
            pwd.focus()
        }else if(_name.length=0 || _name==""){
            $('#nameMes').text("이름을 입력해주세요.")
            name.focus()
        }else if(_phone.length=0 || _phone==""){
            $('#phoneMes').text("휴대폰 번호를 입력해주세요.")
            phone.focus()
        }else if(_email.length=0 || _email==""){
            $('#emailMes').text("이메일 입력해주세요.")
            email.focus()
        }else if
        (confirm('입력하신 정보로 회원가입 하시겠습니까?')){
        	location.href = "/web01/testforment/joinAdd"
        }
    </script>
</body>
</html>