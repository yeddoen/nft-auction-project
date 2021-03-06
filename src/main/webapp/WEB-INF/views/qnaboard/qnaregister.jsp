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

</style>
</head>
<body>
  <!-- header -->
  <nav class="navbar navbar-expand-lg navbar-light bg-warning sticky-top">
    <a class="navbar-brand" href="../main">NFT-AUCTION</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
      data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
      aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link"
          href="../arts/register">작품등록</a></li>
        <c:if test="${empty sessionScope.memberId }">
          <li class="nav-item"><a class="nav-link"
            href="../members/login">로그인</a></li>
          <li class="nav-item"><a class="nav-link"
            href="../members/sign-up">회원가입</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.memberId }">
          <li class="nav-item"><a class="nav-link"
            href="../members/logout">로그아웃</a></li>
          <li class="nav-item"><a class="nav-link"
            href="../members/my-page/member">마이페이지</a></li>
        </c:if>
      </ul>
    </div>
  </nav>
  <aside class="aside">
    <div id="page-wrapper">
      <!-- 사이드바 -->
      <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
          <li class="sidebar-brand"><a href="../members/my-page/member">마이페이지</a></li>
          <li><a href="../members/my-page/member">회원정보</a></li>
          <li><a href="../wishlist/wishpage">위시리스트</a></li>
          <li><a href="../members/my-page/artlist">등록작품내역</a></li>
          <li><a href="../members/my-page/shopping-list">구매작품내역</a></li>
          <li><a href="../qnaboard/qnalist">QnA게시판</a></li>
        </ul>
      </div>
      <!-- /사이드바 -->
    </div>
  </aside>
  <section class="section m-3">
    <div id="page-wrapper">
      <div id="page-content-wrapper">
        <h1>문의하기</h1>
      </div>
      <form action="qnaregister" method="POST"
        enctype="multipart/form-data">
        <div class="input-group mb-3">
          <span class="input-group-text " style="width: 10%">제목</span> <input
            type="text" name="qnaboardTitle" id="qnaboardTitle"
            placeholder="제목을 입력하세요" required>
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" style="width: 10%">작성자</span> <input
            type="text" id="memberNickname" name="memberNickname"
            value="${mo.memberNickname}" readonly>
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" style="width: 10%">아이디</span> <input
            type="text" id="memberId" name="memberId"
            value="${sessionScope.memberId }" readonly>
        </div>
        <div>
          <textarea rows="20" cols="120" name="qnaboardContent"
            id="qnaboardContent" placeholder="내용을 입력하세요"></textarea>
        </div>
        <div>
          <input type="submit" class="btn btn-primary" value="등록">
          <a href="qnalist"><input type="button" value="글 목록"
            class="btn btn-primary"></a>
        </div>
      </form>
    </div>
  </section>
  <!-- hidden -->
    <input type="hidden" id="insert_result" value="${insertResult }">
  <script type="text/javascript">
  	$(function() {
  	    confirmInsertResult();
  	    
  	    function confirmInsertResult() {
            var result = $('#insert_result').val();
            console.log(result);
            if (result == 'fail') {
                alert('문의글 등록 실패');
            } else if (result == 'success') {
                alert('문의글이 등록되었습니다.');
            }
        } // end confirmInsertResult()
  	});
  </script>
</body>
</html>