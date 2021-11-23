<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 모바일 디바이스에서 터치/줌 등을 지원하기 위한 meta 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>

<title>회원정보수정페이지</title>
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

	<section class="section">
		<div id="page-wrapper">
			<!-- 본문 헤더 -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<h1>회원 정보</h1>
				</div>
			</div>

			<div class="content">
				<!-- 회원 정보 수정하기 -->
				<!-- 아이디, 비밀번호(수정페이지로) 이름, 닉네임, 전화번호, 이메일 -->
				<!-- ajax 방식 -->
				<form action="update" method="POST">
					<p>아이디</p>
					<p>${vo.memberId }</p>
					<!-- <input type="text" id="memberId" name="memerId" placeholder="${vo.memberId }" readonly="readonly"> -->
					<!-- 아이디도 form으로 보내야함!! -->
					<p>
						비밀번호
						<button type="button" id="btn-password"
							onclick="location.href='password-change'">비밀번호 변경</button>
					</p>
					<p>이름</p>
					<p>${vo.memberName}</p>
					<p>닉네임</p>
					<input type="text" id="memberNickname" name="memberNickname"
						placeholder="닉네임 입력" required="required"><br>
					<p>전화번호</p>
					<input type="text" id="memberPhone" name="memberPhone"
						placeholder="전화번호 입력" required="required"><br>
					<p>이메일</p>
					<input type="text" id="memberEmail" name="memberEmail"
						placeholder="이메일 입력" required="required"><br>
					<p>
						<input type="submit" id="btn-update" value="회원정보수정">
					</p>
				</form>
			</div>

			<div class="content-btn">
					<button type="button" id="btn-delete"
						onclick="location.href='delete'">회원탈퇴</button>
			</div>

		</div>
	</section>

</body>
</html>