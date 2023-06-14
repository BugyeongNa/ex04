<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="ctxPath" value="<%=request.getContextPath() %>" />   
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
</head>
<body>


    <div class="main">
        <div class="container">
            <div>
                <h3 class="text-center m-3">회원가입</h3>
            </div>
            <hr>
            <div class="w-75 border p-3 m-3 m-auto ">
                <form action="${ctxPath}/member//insertMember.do" id="updateForm">
                    <div class="mb-3">
                        <label  class="form-label">ID</label>
                        <input type="text" name="id" value="${member.id}" class="form-control" id="id" >
                        <input type="button" id="idChk" value="중복체크">
                    </div>
                    <div class="messageId text-danger"></div>
                    <div class="mb-3">
                        <label for="pwd" class="form-label">password</label>
                        <input type="password" name="pwd" value="${member.pwd}" class="form-control" id="pwd" >
                    </div>
                    <div class="messagePwd text-danger"></div>
                    <div class="mb-3">
                        <label for="pwd2" class="form-label">password2</label>
                        <input type="password" name="pwd2" value="${member.pwd2}" class="form-control" id="pwd2" >
                    </div>
                    <div class="messagePwd2 text-danger"></div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" name="name" value="${member.name}" class="form-control" id="name" >
                    </div>
                    <div class="messageName text-danger"></div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" name="email" value="${member.email}" class="form-control" id="email" >
                    </div>
                    <div class="messageEmail text-danger"></div>
                    <div class="mb-3">
                        <input type="submit" id="updateBtn" class="btn btn-success" value="수정하기">
                        <a href="${ctxPath}/member/listMember.do" class="btn btn-secondary">목록</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    
    
    <script type="text/javascript">
    	$(function(){
    		console.log('jQuery Ok...');
    		$('#idChk').click(function(){
    			console.log('click')
    			
    			var send_id = $('#id').val()
    			console.log('id: '+send_id)
    			
    			if (send_id.length <= 0 || send_id =='') {
    				alert("ID를 입력하세요")
    				$('#id').focus()
    				return;
    			}
    			// 중복체크 요청(Ajax방식)
    			$.ajax({
    				type: 'post',
    				async: false,
    				url: "/web01/member/checkId.do",
    				dataType: "text",
    				data: { id: send_id},
    				success: function(data,textStatus) {
						if(data=='usable'){
							alert('사용할 수 있는 아이디입니다.');
							$('.messageId').text('사용할 수 있는 아이디입니다.')
							$('#idChk').prop('disabled',true)
						} else {
							alert('이미 사용중인 아이디입니다.');
							$('.messageId').text('이미 사용중인 아이디입니다.')
					}
    				}
    			}); // ajax
    			
    		})// idChk
    		
    		//저장 버튼
    		$('#updateBtn').click(function(e){
    			e.preventDefault();// submit()기능 중지
    			
    			let pwd = $('#pwd').val()
    			let pwd2 = $('#pwd2').val()
    			
    			if ($('#id').val() == '' || $('#id').val().lenght == 0){
    				$('.messageId').text('아이디는 필수항목입니다.')
    				$('#id').focus()
    				return;
    			}
    			if ($('#pwd').val() == '' || $('#pwd').val().lenght == 0){
    				$('.messagePwd').text('비밀번호는 필수항목입니다.')
    				$('#pwd').focus()
    				return;
    			}
    			if(pwd != pwd2){
    				alert('비밀번호가 일치하지 않습니다.')
    				//$('.messagePwd2').text('비밀번호가 일치하지 않습니다.')
    				$('#pwd').focus()
    				return;
    			}
    			if ($('#name').val() == '' || $('#name').val().lenght == 0){
    				$('.messageName').text('이름을 입력해주세요.')
    				$('#name').focus()
    				return;
    			}
    			if ($('#email').val() == '' || $('#email').val().lenght == 0){
    				$('.messageEmail').text('이메일은 필수항목입니다.')
    				$('#email').focus()
    				return;
    			}
    			
    			var isUpdate = confirm('가입하시겠습니까?')
    			console.log("isUpdate = "+isUpdate)
    			
    			if (isUpdate){
    				$('#updateForm').submit();
    			}
    		});
    	});
    </script>
</body>
</html>