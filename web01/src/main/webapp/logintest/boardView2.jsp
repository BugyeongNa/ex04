<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <style>
      textarea{
        resize: none;
      }
       body{
        margin-top: 100px;
      }
    </style>
</head>
<body>
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
                  <h5 class="offcanvas-title" id="offcanvasNavbarLabel">���</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                  <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="http://www.ysing.com/">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        �������
                      </a>
                      <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/web01/loginuitest/login">�α���</a></li>
                        <li>
                            <hr class="dropdown-divider">
                          </li>
                        <li><a class="dropdown-item" href="/web01/ajaxtest01/ajaxTest.jsp">REST API</a></li>
                        <li>
                          <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="/web01/loginuitest/board/boardMove">�Խ���</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
    </div>
        <div class="container mt-5">
            <div class="w-75 m-auto">
              <hr>
                <h4 class="text-center mt-3">�� ����</h4>
                <hr>
            </div>
            <div class="w-75 m-auto">
                <div class="mb-3 row">
                    <label for="no" class="col-sm-2 col-form-label">�۹�ȣ</label>
                    <div class="col-sm-10">
                      <input type="text" readonly name="articleNO" class="form-control-plaintext" id="no" value="1">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="id" class="col-sm-2 col-form-label">ID</label>
                    <div class="col-sm-10">
                      <input type="text" readonly name="id" class="form-control-plaintext border rounded-1 p-2" id="id" value="test04">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="title" class="col-sm-2 col-form-label">����</label>
                    <div class="col-sm-10">
                      <input type="text" readonly name="title" class="form-control-plaintext rounded-1 p-2" id="title" value="�ι�° �׽�Ʈ���Դϴ�.">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="content" class="col-sm-2 col-form-label">����</label>
                    <div class="col-sm-10">
                      <textarea rows="10" readonly name="content" class="form-control-plaintext border rounded-1 p-2" id="content">�׽�Ʈ �����Դϴ�. ���߿� ������ �����ϰ�...</textarea>
                    </div>
                  </div>
                  <!-- ÷������ ���� ���� ���� ��� ó��  -->
                  <c:if test="${not empty dto.imageFileName && dto.imageFileName != null}">
                  <div class="mb-3 row">
                    <label for="imageFileName" class="col-sm-2 col-form-label">�̹���</label>
                    <div class="col-sm-10">
                        <div>
                            <img src="" alt="�̹��� ����">
                        </div>
                      <input type="file" disabled name="imageFileName"  class="form-control p-2" id="imageFileName" value="">
                    </div>
                  </div>
                  </c:if>
                  <div class="mb-3 row">
                    <label for="writedate" class="col-sm-2 col-form-label">�������</label>
                    <div class="col-sm-10">
                      <input type="text" readonly name="writedate" class="form-control-plaintext border rounded-1 p-2" id="writedate" value="2023-05-24">
                    </div>
                  </div>
                   <hr>
            </div>
           
            <div class="w-75 m-auto">
                <div class="d-flex justify-content-between mb-3">
                    <div class="col-md-8">
                        <button type="button" class="btn btn-outline-warning btn-sm" onclick="modify()">����</button>
                        <button type="button" class="btn btn-outline-danger btn-sm" onclick="del()">����</button>
                        <button type="button" class="btn btn-outline-success btn-sm" onclick="list()">���</button>
                    </div>
                    <div class="col-md-4 d-flex justify-content-end">
                        <button type="button" class="btn btn-outline-primary btn-sm" onclick="reply()">��۾���</button>
                    </div>
                </div>
                <hr>
                
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	function list() {
			location.href = "/web01/logintest/boardList.jsp"
		}
    	function del() {
        if(confirm("���� �����Ͻðڽ��ϱ�?")){
          alert("�����Ǿ����ϴ�.")
          location.href = "/web01/logintest/boardList.jsp"
        }
		}
    function modify() {
			location.href = "/web01/logintest/boardModify2.jsp"
		}
    	function reply() {
        alert("�غ����Դϴ�..")
		}
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>