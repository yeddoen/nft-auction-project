<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 문의하기</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style type="text/css">
/* 사이드바 래퍼 스타일 */
.aside {
  width: 30%;
}

#page-wrapper {
  padding-left: 250px;
}

#sidebar-wrapper {
  position: fixed;
  width: 250px;
  height: 100%;
  margin-left: -250px;
  background: #000;
  overflow-x: hidden;
  overflow-y: auto;
}

#page-content-wrapper {
  width: 100%;
  padding: 20px;
}
/* 사이드바 스타일 */
.sidebar-nav {
  width: 250px;
  margin: 0;
  padding: 0;
  list-style: none;
}

.sidebar-nav li {
  text-indent: 1.5em;
  line-height: 2.8em;
}

.sidebar-nav li a {
  display: block;
  text-decoration: none;
  color: #999;
}

.sidebar-nav li a:hover {
  color: #fff;
  background: rgba(255, 255, 255, 0.2);
}

.sidebar-nav>.sidebar-brand {
  font-size: 1.3em;
  line-height: 3em;
}

.aside {
  width: 30%;
  align: left;
}

.section {
  width: 70%;
  align: right;
}
</style>
</head>

<body>
  <!-- header -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <a class="navbar-brand" href="../main">NFT-AUCTION</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
      data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
      aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link"
          href="arts/register">작품등록</a></li>
        <c:if test="${empty sessionScope.memberId }">
          <li class="nav-item"><a class="nav-link"
            href="members/login">로그인</a></li>
          <li class="nav-item"><a class="nav-link"
            href="members/sign-up">회원가입</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.memberId }">
          <li class="nav-item"><a class="nav-link"
            href="members/logout">로그아웃</a></li>
          <li class="nav-item"><a class="nav-link"
            href="members/my-page/member">마이페이지</a></li>
        </c:if>
      </ul>
    </div>
  </nav>
  <aside class="aside">
    <div id="page-wrapper">
      <!-- 사이드바 -->
      <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
          <li class="sidebar-brand"><a href="member">마이페이지</a></li>
          <li><a href="member">회원정보</a></li>
          <li><a href="../../wishlist/wishpage">위시리스트</a></li>
          <li><a href="artlist">등록작품내역</a></li>
          <li><a href="shopping-list">구매작품내역</a></li>
          <li><a href="../../qnaboard/qnalist">QnA게시판</a></li>
        </ul>
      </div>
      <!-- /사이드바 -->
    </div>
  </aside>
  <section class="section m-3">
    <div id="page-wrapper">
      <div id="page-content-wrapper">
        <h1>QnA 수정</h1>
      </div>

      <form action="qnaupdate" method="POST">
        <div>
          <input type="hidden" name="qnaboardNo"
            value="${vo.qnaboardNo }">
          <p>
            제목 : <input type="text" name="qnaboardTitle"
              value="${vo.qnaboardTitle }">
          </p>
        </div>
        <div>
          <p>작성자 : ${vo.memberNickname }</p>
        </div>
        <div>
          <textarea rows="20" cols="120" name="qnaboardContent">${vo.qnaboardContent }</textarea>
        </div>
        <div>
          <input type="submit" value="수정">
          <button type="button" onclick="location.href='qnalist'">글
            목록</button>
        </div>
      </form>
    </div>
  </section>

</body>
</html>