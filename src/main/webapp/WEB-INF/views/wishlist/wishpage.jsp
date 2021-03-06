<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- 모바일 디바이스에서 터치/줌 등을 지원하기 위한 meta 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/zephyr/bootstrap.min.css">
<title>위시리스트페이지</title>
<style type="text/css">
/* 헤더 스타일 */
img {
	max-width: 100%;
	height: auto;
}
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
<title>위시리스트페이지</title>
</head>
<body>
		<!-- header -->
		<nav class="navbar navbar-expand-lg navbar-light bg-warning sticky-top">
			<div class="container-fluid">
				<a class="navbar-brand" href="../main">NFT-AUCTION</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNavDropdown">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="../arts/register">작품등록</a>
						</li>
						<c:if test="${empty sessionScope.memberId }">
							<li class="nav-item"><a class="nav-link" href="../members/login">로그인</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="../members/sign-up">회원가입</a></li>
						</c:if>
						<c:if test="${not empty sessionScope.memberId }">
							<li class="nav-item"><a class="nav-link"
								href="../members/logout">로그아웃</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../members/my-page/member">마이페이지</a></li>
							<li class="nav-itm"><a class="nav-link"> ${sessionScope.memberId } 님, 접속중입니다!</a>
						</c:if>
						
					</ul>
				</div>
			</div>
		</nav>

	<aside class="aside">
		<div id="page-wrapper">
			<!-- 사이드바 -->
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li class="sidebar-brand"><a href="../members/my-page/member">마이페이지</a></li>
					<li><a href="../members/my-page/member">회원정보</a></li>
					<li><a href="wishpage">위시리스트</a></li>
					<li><a href="../members/my-page/artlist">등록작품내역</a></li>
					<li><a href="../members/my-page/shopping-list">구매작품내역</a></li>
					<li><a href="../qnaboard/qnalist">QnA게시판</a></li>
				</ul>
			</div>
			<!-- /사이드바 -->
		</div>
	</aside>

	<section class="section" style="text-align: center;">
		<div id="page-wrapper">
			<!-- 본문 헤더 -->
			<div id="page-content-wrapper" style="text-align: left;">
				<div class="container-fluid">
					<h1>위시리스트</h1>
					<hr>
				</div>
			</div>

			<div class="content m-3" style="padding-bottom: 200px;">
				<!-- 위시리스트 리스트 보여주기!!! -->
				<div class="row">
					<c:forEach var="vo" items="${list }">
						<div class="col-sm-4">
							<div class="card border-primary mb-3" style="max-width: 20rem;">
								<div class="card-header">by ${sessionScope.memberId }</div>
								<div class="card-body">
									<a
										href="../arts/detail?artNo=${vo.artNo}&page=${pageMaker.criteria.page}">
										<h4 class="card-title">
											<img
												src="https://nftauctionbucket.s3.ap-northeast-2.amazonaws.com/${vo.artFileName }">
										</h4>
										<hr>
										<p class="card-text">${vo.artName }</p>
									</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- footer -->
	<div class="mt-5 p-3 bg-light" align="center">
		<div style="bottom: 0; height: 200px;">
			<h4>NFT-AUCTION</h4>
			<hr>
			<br> <a href="../terms/termsOfService">이용약관</a> &nbsp; <a href="../terms/termsOfInformation">사이트안내</a> <br>
			<br> <small>문의 nft.auction.help@gmail.com</small>
		</div>
	</div>
		</div>
	</section>

	<script type="text/javascript">
        
    </script>
</body>
</html>