<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 문의하기</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<!-- header -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <a class="navbar-brand" href="main">NFT-AUCTION</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
      data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
      aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="arts/register">작품등록</a>
        </li>
        <c:if test="${empty sessionScope.memberId }">
          <li class="nav-item"><a class="nav-link" href="members/login">로그인</a>
          </li>
          <li class="nav-item"><a class="nav-link"
            href="members/sign-up">회원가입</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.memberId }">
          <li class="nav-item"><a class="nav-link" href="members/logout">로그아웃</a></li>
          <li class="nav-item"><a class="nav-link"
            href="members/my-page/member">마이페이지</a></li>
        </c:if>
      </ul>
    </div>
  </nav>
  <h1>수정 Page</h1>
  <form action="qnaupdate" method="POST">
    <div>
      <input type="hidden" name="qnaboardNo" value="${vo.qnaboardNo }">
      <p>제목 : <input type="text" name="qnaboardTitle" value="${vo.qnaboardTitle }" ></p>
    </div>
    <div>
      <p>작성자 : ${vo.memberNickname }</p>
    </div>
    <div>
      <textarea rows="20" cols="120" name="qnaboardContent" >${vo.qnaboardContent }</textarea>
    </div>
    <div>
      <input type="submit" value="수정">
    </div>
  </form>
  

</body>
</html>