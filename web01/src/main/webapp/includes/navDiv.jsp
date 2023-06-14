<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="<%=request.getContextPath() %>" />      

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
                    <a href="${ctxPath}/login" class="dropdown-item">·Î±×ÀÎ</a>
                    </button>
                    </c:if>
                	<c:if test="${loginName != null}">
                		<p class="text-white m-0"> ${loginName}´Ô 
                		<a href="${ctxPath}/logout" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">·Î±×¾Æ¿ô</a>
                	</c:if>
                	</div>
      </div>
    </div>
  </div>
</nav>