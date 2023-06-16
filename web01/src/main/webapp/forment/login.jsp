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
        <label for="id" class="col-md-3 p-1">���̵�</label>
        <input type="text" class="col-md-8 border-bottom" id="id" placeholder="���̵� �Է����ּ���" name="id"/>
       
        </div>
  <div class="row container m-auto mb-3">
    <label for="password" class="col-md-3 p-1">��й�ȣ</label>
    <input type="password" class="col-md-8 border-bottom" id="password" placeholder="��й�ȣ�� �Է����ּ���" name="pwd">
    </div>
    <div class="row container m-auto">
        <div class="">
        <input type="checkbox" name="auto"> �ڵ��α���
    </div>
        <div class="d-flex">
		<div id="message" class="col-md-8">
		<c:if test="${param.result == 'error'}">
		���̵� �Ǵ� ��й�ȣ�� �߸� �Է��߽��ϴ�.
		</c:if>
		</div>
            <div class="col-md-4 p-2"></div>  
		
          </div>
        </div>
  </div>

</div>
                  <div class="container row justify-content-around m-auto">
                    <button type="submit" class="btn btn-success col-md-3">�α���</button>
                  
                    
                    <input type="reset" class="btn btn-warning col-md-3" value="�ٽ��Է�">
                
                  </form>
                      <button type="button" class="btn btn-primary col-md-3" id="joinBtn"><a class="dropdown-item" href="/web01/testforment/join">ȸ������</a>
                      </button>
                    </div>    
            </div>
            <hr>
            <div class="container row m-auto align-items-center">
                <div class="col-md-4 d-flex justify-content-center">������ �ؾ�����̳���?</div>
                <div class="col-md-4"><a href="/web01/testforment/idFind" class="d-flex justify-content-center"> ���̵� ã�� </a></div>
                <div class="col-md-4"><a href="/web01/testforment/pwdFind" class="d-flex justify-content-center"> ��й�ȣ ã�� </a></div>
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
    			message.textContent = "���̵� �Է����ּ���"
                id.focus()
            }else if (password.value.length=0 || password.value ==""){
                message.textContent = "��й�ȣ�� �Է����ּ���"
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