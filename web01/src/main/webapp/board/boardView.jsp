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
</head>
<body>
    <div class="main">
        <div class="container mt-5">
            <div class="w-75 m-auto">
                <h4 class="text-center mt-3">글 상세 내용</h4>
                <hr>
            </div>
            <form action="${ctxPath}/boardlist/board/boardModify.do" method="get">
            <div class="w-75 m-auto">
                <div class="mb-3 row">
                    <label for="no" class="col-sm-2 col-form-label">글번호</label>
                    <div class="col-sm-10">
                      <input type="text" readonly name="articleNO" class="form-control-plaintext" id="no" value="${dto.articleNO}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="id" class="col-sm-2 col-form-label">ID</label>
                    <div class="col-sm-10">
                      <input type="text" readonly name="id" class="form-control-plaintext border rounded-1 p-2" id="id" value="${dto.id}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="title" class="col-sm-2 col-form-label">제목</label>
                    <div class="col-sm-10">
                      <input type="text" readonly name="title" class="form-control-plaintext border rounded-1 p-2" id="title" value="${dto.title}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="content" class="col-sm-2 col-form-label">내용</label>
                    <div class="col-sm-10">
                      <textarea rows="10" readonly name="content" class="form-control-plaintext border rounded-1 p-2" id="content">${dto.content}</textarea>
                    </div>
                  </div>
                  <!-- 첨부파일 있을 경우와 없을 경우 처리  -->
                  <c:if test="${not empty dto.imageFileName && dto.imageFileName != null}">
                  <div class="mb-3 row">
                    <label for="imageFileName" class="col-sm-2 col-form-label">이미지</label>
                    <div class="col-sm-10">
                    <input type="hidden" name="imageFileName" value="${dto.imageFileName }">
                        <div class="alert alert-light p-1"><label id="previewName">${dto.imageFileName}</label></div>
                            <img width="100%" src="${ctxPath}/download.do?imageFileName=${dto.imageFileName}&articleNO=${dto.articleNO}"
                            id="preview" alt="">
                        
                      <!-- <input type="file" readonly name="imageFileName"  class="form-control p-2" id="imageFileName" value="${dto.imageFileName}">  -->
                    </div>
                  </div>
                  </c:if>
                  <div class="mb-3 row">
                    <label for="writedate" class="col-sm-2 col-form-label">등록일자</label>
                    <div class="col-sm-10">
                      <input type="text" readonly name="writedate" class="form-control-plaintext border rounded-1 p-2" id="writedate" value="${dto.writedate}">
                    </div>
                  </div>
                   <hr>
            </div>
           
            <div class="w-75 m-auto">
                <div class="d-flex justify-content-between mb-3">
                    <div class="col-md-8">
                        <button type="submit" class="btn btn-outline-warning btn-sm">수정</button>
                        <button type="button" class="btn btn-outline-danger btn-sm" onclick="del()">삭제</button>
                        <button type="button" class="btn btn-primary btn-sm" id="delBtn">삭제</button>
                        <button type="button" class="btn btn-outline-success btn-sm" onclick="list()">목록</button>
                    </div>
                    <div class="col-md-4 d-flex justify-content-end">
                        <button type="button" class="btn btn-outline-primary btn-sm" onclick="reply()">답글쓰기</button>
                    </div>
                </div>
                <hr>
            </div>
       </form>
        </div>
    </div>
    <div class="modal" id="delModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">게시글 삭제</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>게시글을 삭제하시겠습니까?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="delOK">확인</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
    <script type="text/javascript">
    	// jq
    	$('#delBtn').click(function() {
			$('#delModal').modal('show')
		})	
		// 삭제모달창 확인버튼
		$('#delOK').click(function() {
			location.href = "${ctxPath}/boardlist/board/boardDelete.do?articleNO=${dto.articleNO}"
		})
    
    	function list() {
			location.href = "${ctxPath}/boardlist/boardList.do?pageBlock=${section}&pageNum=${pageNum}"
		}
    	function del() {
    		if(confirm("삭제하시겠습니까?")){
			location.href = "${ctxPath}/boardlist/board/boardDelete.do?articleNO=${dto.articleNO}"
    		}
		}
    	function reply() {
    		location.href = "${ctxPath}/boardlist/board/boardReply.do?parentNO=${dto.articleNO}&articleNO=${dto.articleNO}"
			
		}
    </script>
</body>
</html>