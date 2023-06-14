<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="ctxPath" value="<%=request.getContextPath() %>" />  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
   <style>
        label{
            font-size: 1.0em;
            font-weight: 600;
            background-color: #f5f6f7;
            border-radius: 5px;
        }
        .mes{
            color: red;
        }
        input{
          border: 0;
          font-size: 0.8em;
        }
        .btn{
          font-size: 0.8em;
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
                        <li><a class="dropdown-item" href="/web01/loginuitest/board/boardList">�Խ���</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
    </div>
    <div class="container m-auto">
    <hr>
    <h3 class="text-center">ȸ�� �������� �� ȸ�� ���</h3>
    <hr>
    <div class="d-flex justify-content-end">
    <input type="button" class="btn btn-outline-primary" value="ȸ�� �������� �ҷ�����" id="memberList">
</div>
    <table class="table border mt-3">
      <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">���̵�</th>
          <th scope="col">��й�ȣ</th>
          <th scope="col">�̸�</th>
          <th scope="col">����ȣ</th>
        </tr>
      </thead>
      <tbody id="output2">
      </tbody>
    </table>
    <div class="form-control m-auto p-3 mb-5">
        <div class="row container m-auto p-2 justify-content-center">
            <label for="id" class="col-md-3 p-1 d-flex align-items-center">���̵�</label>
            <input type="text" class="col-md-5 border-bottom" id="id"/>
            <div class="row">
                <div class="col-md-2 p-3"></div>
            <div id="idMes" class="col-md-10 pt-2 mes">
          </div>
            </div>
        </div>
            <div class="row container m-auto p-2 justify-content-center">
                <label for="pwd" class="col-md-3 p-1 d-flex align-items-center">��й�ȣ</label>
                <input type="text" class="col-md-5 border-bottom" id="pwd"/>
                <div class="row">
                    <div class="col-md-2 p-3"></div>
                <div id="pwdMes" class="col-md-3 pt-2 mes">
              </div>
                </div>
    
                </div>
                <div class="row container m-auto p-2 justify-content-center">
                    <label for="name" class="col-md-3 p-1 d-flex align-items-center">�̸�</label>
                    <input type="text" class="col-md-5 border-bottom" id="name"/>
                   
                    <div class="row">
                        <div class="col-md-2 p-3"></div>
                    <div id="nameMes" class="col-md-3 pt-2 mes">
                  </div>
                    </div>
        
                    </div>
                    <div class="row container m-auto p-2 justify-content-center">
                        <label for="phone" class="col-md-3 p-1 d-flex align-items-center">�޴���</label>
                        <input type="text" class="col-md-5 border-bottom" id="phone"/>
                       
                        <div class="row">
                            <div class="col-md-2 p-3"></div>
                        <div id="phoneMes" class="col-md-3 pt-2 mes">
                      </div>
                        </div>
            
                        </div>
                        <div class="d-flex justify-content-end">
    <input type="button" class="btn btn-outline-success" value="ȸ�����" id="memberAdd">
</div>
    </div>
    </div>
    </div>
    
    <script type="text/javascript">
    
    
    $('#memberList').click(function() {
        var _jsonInfo = 
        
        $.ajax({
            type: "get",
            async: false,
            url: "${ctxPath}/testmembers",
            success: function(data, textStatus) {
                var jsonInfo = JSON.parse(data)
                var obj = ""
                for (var i in jsonInfo){
                    obj += "<tr>"
                    obj += "<th>-</th>"
                    obj += "<td>"+jsonInfo[i].id+"</td>"
                    obj += "<td>"+jsonInfo[i].pwd+"</td>"
                    obj += "<td>"+jsonInfo[i].name+"</td>"
                    obj += "<td>"+jsonInfo[i].phone+"</td>"
                    obj += "</tr>"
                }
                // ����� ǥ��
                $('#output2').html(obj);
            },
            error: function(data, textStatus) {
                alert('����')
            }
        });
    })
    
                $('#memberAdd').click(function() {
                    var _id = $('#id').val()
                    var _pwd = $('#pwd').val()
                    var _name = $('#name').val()
                    var _phone = $('#phone').val()

                    if (_id.length=0 || _id==""){
                        $('#idMes').text("���̵� �Է����ּ���.")
                        id.focus()
                    }else if(_pwd.length=0 || _pwd==""){
                        $('#pwdMes').text("��й�ȣ�� �Է����ּ���.")
                        pwd.focus()
                    }else if(_name.length=0 || _name==""){
                        $('#nameMes').text("�̸��� �Է����ּ���.")
                        name.focus()
                    }else if(_phone.length=0 || _phone==""){
                        $('#phoneMes').text("�޴��� ��ȣ�� �Է����ּ���.")
                        name.focus()
                    }else if(confirm('�Է��Ͻ� ������ ȸ������ �Ͻðڽ��ϱ�?')){
                    $.ajax({
                        type: "post",
                        async: false,
                        url: "${ctxPath}/testmembers",
                        data: {
                            id : _id,
                            pwd : _pwd,
                            name : _name,
                            phone : _phone,
                        },
                        success: function(data, textStatus) {
                            alert('ȸ������ ������ �����Ͽ����ϴ�.')
                        },
                        error: function(data, textStatus) {
                            alert('ȸ������ ������ ���۽����Ͽ����ϴ�.')
                        }
                    });
                    
                }
                });
            
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
    </html>