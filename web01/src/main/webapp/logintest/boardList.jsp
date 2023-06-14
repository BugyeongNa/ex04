<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
            <div class="w-75 m-auto"> <hr>
                <h4 class="text-center">자유 게시판</h4>
                <hr>
                 <div class="d-flex justify-content-between">
                 	<a href="/web01/loginuitest/logout">로그아웃</a>
                    <button type="button" class="btn btn-outline-success btn-sm" onclick="regi()">글쓰기</button>
                  </div>
            </div>
            <div class="w-75 m-auto">
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
                    <form href="/web01/logintest/boardForm.jsp">
                        <tr>
                            <th scope="row">3</th>
                            <td>test07</td>
                            <td>
                            <a href="/web01/logintest/boardView3.jsp"
                            class="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            마지막 테스트글입니다.
                            </a>
                            </td>
                            <td>05-25</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>test04</td>
                            <td>
                            <a href="/web01/logintest/boardView2.jsp"
                            class="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            두번째 테스트글입니다.
                            </a>
                            </td>
                            <td>05-24</td>
                        </tr>
                    	<tr>
                    		<th scope="row">1</th>
                    		<td>test00</td>
			               	<td>
			                <a href="/web01/logintest/boardView1.jsp"
			                 class="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
			                 첫번째 테스트글입니다.
			                </a>
			                </td>
			                <td>05-23</td>
                    	</tr>
                      </form>
                    </tbody>
                  </table>
            </div>
            <div class="w-75 m-auto align-items-center">
            <br>
                 <div class="d-flex justify-content-around">
        <div>
            <button type="button" class="btn btn-outline-dark btn-sm"><</button>
        </div>
        <div>
            <c:forEach begin="1" end="10" step="1" varStatus="loop">
            <button type="button" class="btn btn-outline-dark btn-sm">${loop.index}</button>
            </c:forEach>
        </div>
        <div>
            <button type="button" class="btn btn-outline-dark btn-sm">></button>
        </div>
      </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    function regi() {
		location.href = "/web01/logintest/boardForm.jsp"
	}
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>