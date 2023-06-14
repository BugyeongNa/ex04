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
                  <label for="name" class="p-1">�̸�</label>
                  <input type="text" class="border-bottom p-1" id="name" placeholder="�̸��� �Է����ּ���" name="name"/>
                  </div>
                <div class="row container m-auto mb-3">
                  <label for="phone" class="p-1">�޴��� ��ȣ</label>
                  <input type="text" class="border-bottom p-1" id="phone" placeholder="�޴��� ��ȣ�� �Է����ּ���" name="phone">
                </div>
                <div class="row container m-auto mb-3">
                  <label for="email" class="p-1">�̸���</label>
                  <input type="email" class="border-bottom p-1" id="email" placeholder="�̸����� �Է����ּ���" name="email">
                  
                </div>
                <div class="row container m-auto mb-3 errorFont">
            <c:if test="${param.result == 'error'}">
		�Է��Ͻ� ������ ���Ե� ���̵� �����ϴ�.
		</c:if> 
          </div>
              </div>
              <div class="container row col-md-4 justify-content-center">
              <button type="submit" class="btn btn-primary btn-lg" onclick="findId()">���̵� ã��</button>
            
              <div class="d-flex justify-content-between align-items-center mt-3">
              <div>
              <a href="/web01/testforment/login" class="link-font">�α��� �������� �̵�</a>
            </div>
            <div>
            <a href="/web01/testforment/pwdFind" class="link-font">��й�ȣ ã��</a>
           
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