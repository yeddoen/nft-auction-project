<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>마이 페이지(일단헤더없이사이드바만추가함!)</title>
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
	align : right;
}
</style>
</head>
<body>
	<aside class="aside">
		<div id="page-wrapper">
			<!-- 사이드바 -->
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li class="sidebar-brand"><a href="#">마이페이지</a></li>
					<li><a href="member">회원정보</a></li>
					<li><a href="#">위시리스트</a></li>
					<li><a href="#">등록작품내역</a></li>
					<li><a href="#">구매작품내역</a></li>
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
				<!-- 회원 정보 기본적으로 보여주기 -->
				<!-- 아이디, 비밀번호(수정페이지로) 이름, 닉네임, 전화번호, 이메일 -->
				<p>아이디</p><p>${vo.memberId }</p>
				<p>비밀번호<button type="button" id="btn-password" onclick="location.href='password-change'">비밀번호 변경</button></p>
				<p>이름</p><p>${vo.memberName}</p>
				<p>닉네임</p><p>${vo.memberNickname }</p>
				<p>전화번호</p><p>${vo.memberPhone }</p>
				<p>이메일</p><p>${vo.memberEmail }</p>
			</div>
			
			<div class="content-btn">
				<button type="button" onclick="location.href='update'">회원정보수정</button>
				
			</div>
			
		</div>
	</section>
	
	<script type="text/javascript">
	
	</script>

</body>
</html>