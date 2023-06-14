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
                        <li><a class="dropdown-item" href="/web01/loginuitest/board/boardMove">게시판</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
    </div>
        <div class="container mt-5">
            <form action="#">
            <div class="w-75 m-auto">
              <hr>
                <h4 class="text-center">게시글 수정</h4>
                <hr>
            </div>
            <div class="w-75 m-auto">
                <input type="hidden" name="articleNO" value="${articleNO}">
                  <div class="mb-3 row">
                    <label for="id" class="col-sm-2 col-form-label">ID</label>
                    <div class="col-sm-10">
                      <input type="text" name="id" class="form-control border rounded-1 p-2" id="id" placeholder="아이디" value="test07">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="title" class="col-sm-2 col-form-label">제목</label>
                    <div class="col-sm-10">
                      <input type="text" name="title" class="form-control border rounded-1 p-2" id="title" placeholder="제목" value="마지막 테스트글입니다.">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="content" class="col-sm-2 col-form-label">내용</label>
                    <div class="col-sm-10">
                      <textarea rows="10" name="content" maxlength="4000" class="form-control border rounded-1 p-2" id="content">테스트 내용입니다. 많은 기능도 추가할 예정입니다...</textarea>
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="imageFileName" class="col-sm-2 col-form-label">이미지</label>
                    <div class="col-sm-10">
                        <div>
                            <img src="#" id="preview">
                        </div>
                        <div><label id="previewName"></label></div>
                      <input type="file" name="imageFileName" onchange="readURL(this)"  class="form-control p-2" id="imageFileName">
                    </div>
                  </div>
                
                  <input type="hidden" name="writeDate">
                  <hr>
            </div>
            <div class="w-75 m-auto">
                <div class="d-flex justify-content-between mb-3">
                    <div class="col-md-8">
                        <button type="button" class="btn btn-outline-success btn-sm" onclick="cancel()">취소</button>
                    </div>
                    <div class="col-md-4 d-flex justify-content-end">
                        <button type="button" class="btn btn-outline-warning btn-sm" onclick="save()">저장</button>
                    </div>
                </div>
                <hr>
            </div>
        </div>
      </form>
    </div>
    <script
    src="https://code.jquery.com/jquery-3.7.0.min.js"
    integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
    crossorigin="anonymous"></script>
    <script>
      function readURL(imageFileName){
        if (imageFileName.files && imageFileName.files[0]){
          var reader = new FileReader()

          reader.onload = function(e){
            $('#preview').attr('src', e.target.result)
            $('#previewName').text(name)
            var str = imageFileName.value;
            var idx = str.lastIndexOf('\\')
            var name = str.substring(idx+1)
          }
          reader.readAsDataURL(imageFileName.files[0])
        }
      }
      
      function cancel(){
    	  if(confirm("글 수정을 취소하시겠습니까?")){
     		location.href = "/web01/logintest/boardView3.jsp"
        }
      }
        function save(){
          if(confirm("글을 작성하시겠습니까?")){
            alert("글이 작성되었습니다.")
            location.href = "/web01/logintest/boardList.jsp"
          }
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>