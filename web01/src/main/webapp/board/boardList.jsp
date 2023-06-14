<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="<%=request.getContextPath()%>" />
<c:set var="loginName" value='<%=session.getAttribute("loginInfo") %>'/>   
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <style>
     body{
        margin-top: 100px;
      }
    </style>
</head>
<body class="bg-light">

<jsp:include page="../includes/navDiv.jsp" />

<nav class="navbar navbar-expand-lg bg-dark fixed-top" data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled">Disabled</a>
        </li>
      </ul>
      <form class="d-none d-lg-block d-lg-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
      <div>
      <div class="p-1">
                    <c:if test="${loginName == null}">
                    <button type="button" class="btn btn-light">
                    <a href="${ctxPath}/login" class="dropdown-item">로그인</a>
                    </button>
                    </c:if>
                	<c:if test="${loginName != null}">
                		<p class="text-white m-0"> ${loginName}님 
                		<a href="${ctxPath}/logout" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">로그아웃</a>
                	</c:if>
                	</div>
      </div>
    </div>
  </div>
</nav>
    <div class="main">
        <div class="container">
            <div class=" m-auto">
            <hr>
                	<h4 class="text-center">답변형 게시판</h4>
                
                <hr>
                 <div class="d-flex align-items-center justify-content-between">
                    <div>
                    	<button type="button" class="btn btn-outline-success btn-sm" onclick="regi()">글쓰기</button>
                    </div>	
                    <div class="p-1">
                    <c:if test="${loginName == null}">
                    <a href="${ctxPath}/login">로그인</a>
                    </c:if>
                	<c:if test="${loginName != null}">
                		${loginName}님
                		<a href="${ctxPath}/logout">로그아웃</a>
                	</c:if>
                </div>
                  </div>
                  <br>
            </div>
            <div class=" m-auto  center">
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">글번호</th>
                        <th scope="col">작성자</th>
                        <th scope="col">제목</th>
                        <th scope="col">작성일</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                    	<c:when test="${boardList.size() == 0 || boardList == null}">
                    	<tr>
                    		<td colspan="4">
                    		<div class="alert alert-light" role="alert">
                    		등록된 게시글이 없습니다.
                    		</div>
                    		</td>
                    	</tr>
                    	</c:when>
                    	<c:when test="${boardList != null}">
                    		<c:forEach var="bo" items="${boardList}">
                    			<tr>
                    				<th scope="row">${bo.articleNO}</th>
                    				<td>${bo.id}</td>
			                        <td>
			                        <c:choose>
			                        <c:when test="${bo.level > 1 }">
			                        <c:forEach begin = "1" end = "${bo.level}" step="1">
			                        	<span style="padding-left:10px"></span> 
			                        </c:forEach>
			                        <span>
			                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewBox="0 0 16 16">
									<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
								    </svg>
			                        </span>
			                        </c:when>
			                        </c:choose>
			                        
			                        <a href="${ctxPath}/boardlist/boardView.do?articleNO=${bo.articleNO}&pageBlock=${section}&pageNum=${pageNum}"
			                        class="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
			                        ${bo.title}
			                        </a>
			                        </td>
			                        <td>${bo.writedate}</td>
                    			</tr>
                    		</c:forEach>
                    	</c:when>
                      
                      </c:choose>
                    </tbody>
                  </table>
            </div>
             </div>
             
            <div class="align-items-center p-3 bg-dark mt-5">
                 <div class="contanier w-75 m-auto d-flex justify-content-around">
                 <c:if test="${totArticles != null}">
                 <c:forEach var="page" begin="1" end="${lastPage}" step="1">
                 <c:if test="${section>1 && page==1}">
			        <div>
			            <button type="button" class="btn btn-outline-light btn-sm">
			             <a class="dropdown-item" href="${ctxPath}/boardlist/boardList.do?pageBlock=${section-1}&pageNum=10">
			             <
			             </a> 
			             </button>
			        </div>
       			 </c:if>
        <c:choose>
        <c:when test="${page==pageNum }">
        <div>
            <button type="button" class="btn btn-outline-light btn-sm disabled">
            <a class="dropdown-item" href="${ctxPath}/boardlist/boardList.do?pageBlock=${section}&pageNum=${page}">
            ${(section-1)*10 + page}
            </a>
            </button>
        </div>
        </c:when>
        <c:otherwise>
        <div>
            <button type="button" class="btn btn-outline-light btn-sm">
            <a class="dropdown-item" href="${ctxPath}/boardlist/boardList.do?pageBlock=${section}&pageNum=${page}">
            ${(section-1)*10 + page}
            </a>
            </button>
        </div>
        </c:otherwise>
        </c:choose>
        </c:forEach>
        
        <c:if test="${section < totSection}">
        <div>
            <button type="button" class="btn btn-outline-light btn-sm">
            <a class="dropdown-item" href="${ctxPath}/boardlist/boardList.do?pageBlock=${section+1}&pageNum=1">
            >
            </a>
            </button>
        </div>
        </c:if>
        </c:if>
      </div>
            </div>
       
    </div>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script type="text/javascript">
    function regi() {
		location.href = "${ctxPath}/boardlist/board/boardForm.do?pageBlock=${section}&pageNum=${pageNum}"
	}
    </script>
</body>
</html>