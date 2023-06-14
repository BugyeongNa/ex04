<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="<%=request.getContextPath() %>" />  
<c:set var="show" value="0"/>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
    <div class="main">
        <div class="container mt-5">
            <%-- <form action="${ctxPath}/boardlist/boardInsert.do" method="post"> --%>
            <!--  업로드(이미지파일) 기능이 포함된 form -->
             <form action="${ctxPath}/boardlist/board/boardInsert.do" enctype="multipart/form-data" method="post">
            <div class="w-75 m-auto">
                <h4 class="text-center">게시글 등록</h4>
                <hr>
            </div>
            <div class="w-75 m-auto">
                <input type="hidden" name="articleNO" value="1000">
                  <div class="mb-3 row">
                    <label for="id" class="col-sm-2 col-form-label">ID</label>
                    <div class="col-sm-10">
                      <input type="text" name="id" class="form-control border rounded-1 p-2" id="id" placeholder="아이디">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="title" class="col-sm-2 col-form-label">제목</label>
                    <div class="col-sm-10">
                      <input type="text" name="title" class="form-control border rounded-1 p-2" id="title" placeholder="제목">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="content" class="col-sm-2 col-form-label">내용</label>
                    <div class="col-sm-10">
                      <textarea rows="10" name="content" maxlength="4000" class="form-control border rounded-1 p-2" id="content"></textarea>
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="imageFileName" class="col-sm-2 col-form-label">이미지</label>
                    <div class="col-sm-10">
                    <div id="show">
                     <div class="alert alert-light">
                     <label id="previewName"></label>
                     </div>
                        <div>
                            <img src="#" id="preview" width="100%">
                        </div>
                       
                        </div>
                      <input type="file" name="imageFileName" onchange="readURL(this)"  class="form-control p-2 mt-2" id="imageFileName">
                    </div>
                  </div>
                
                  <input type="hidden" name="writeDate">
                  <hr>
            </div>
            <div class="w-75 m-auto">
                <div class="d-flex justify-content-between mb-3">
                    <div class="col-md-8">
                        <button type="button" class="btn btn-outline-success btn-sm" onclick="list()">목록</button>
                    </div>
                    <div class="col-md-4 d-flex justify-content-end">
                        <!-- <button type="button" class="btn btn-outline-primary btn-sm">답글쓰기</button> -->
                        <button type="submit" class="btn btn-outline-warning btn-sm">저장</button>
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
    $('#show').hide()// disply: none
    
      //  제이쿼리를 이용하여 파일 첨부시 미리보기 기능 구현
      function readURL(imageFileName){
    	$('#show').show()
        // console.log('첨부파일 클릭...')
        if (imageFileName.files && imageFileName.files[0]){
          // 파일 입출력 처리하는 객체 생성
          var reader = new FileReader()

          // .onload: 입력이 정상적으로 완료되면 처리하는 메소드
          reader.onload = function(e){
            var str = imageFileName.value;
            var idx = str.lastIndexOf('\\')
            var name = str.substring(idx+1)
            
            $('#preview').attr('src', e.target.result)
            $('#previewName').text(name)
            // console.log(str)
            // console.log(str.substring(idx+1))
          
          }
          reader.readAsDataURL(imageFileName.files[0])
        }
      }
    
	function list() {
		location.href = "${ctxPath}/boardlist/boardList.do?pageBlock=${section}&pageNum=${pageNum}"
	}
    </script>
</body>
</html>