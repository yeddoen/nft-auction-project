<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 모바일 디바이스에서 터치/줌 등을 지원하기 위한 meta 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- css -->
<style type="text/css">
	img { max-width: 100%; height: auto; }
</style>
<title>메인 페이지</title>
</head>
<body style="text-align: center;">
	<!-- header -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">NTF-AUCTION</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarColor02">
	      <ul class="navbar-nav ml-auto">
	        <li class="nav-item">
	          <a class="nav-link" href="arts/register">작품등록</a>
	        </li>
			<c:if test="${empty sessionScope.memberId }">
				<li class="nav-item">
		          <a class="nav-link" href="members/login">로그인</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="members/sign-up">회원가입</a>
		        </li>
			</c:if>
			<c:if test="${not empty sessionScope.memberId }">
				<li class="nav-item">
		          <a class="nav-link" href="members/logout">로그아웃</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="members/my-page/member">마이페이지</a>
		        </li>
			</c:if>	

	      </ul>
	    </div>
	  </div>
	</nav>
	<!-- 검색 -->
	<br>
	<div class="search" >
		<input type="text" id="search_art" placeholder="제목 혹은 작가명 입력">
		<button type="button" class="btn btn-primary">검색</button>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-sm-8"></div>
			<div class="col-sm-4">
				<button type="button" id="current_list" class="btn">등록순</button>
				<button type="button" id="wish_list" class="btn">찜하기순</button>
				<button type="button" id="view_list" class="btn">조회수순</button>
			</div>
		</div>
		<br>
		<!-- 작품 목록 -->
		<div class="row" id="list_body">
			<c:forEach var="vo" items="${list }">
				<div class="col-sm-4">
					<div class="card border-primary mb-3" style="max-width: 20rem;">
						<div class="card-header">by ${vo.memberNickname }</div>
						<div class="card-body">
							<h4 class="card-title">
							<img src="/nft-auction/arts/display?fileName=${vo.artFileName }"></h4>
							<hr>
							<p class="card-text">${vo.artName }
								<button type="button" class="btn float-right">찜수 ${vo.artWishCount }</button>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<input type="hidden" id="login_result" value="${loginResult }">
	<input type="hidden" id="logout_result" value="${logoutResult }">
	<input type="hidden" id="join_result" value="${joinResult }">
	<input type="hidden" id="register_result" value="${registerResult }">
	<!-- JavaScript -->
	<script type="text/javascript">
		$(function(){
	  		confirmLoginResult();
	  		confirmLogoutResult();
	  		confirmJoinResult();
	  		confirmRegisterResult();
	  		
			function confirmLoginResult() {
				var result=$('#login_result').val();
				if(result=='success'){
					alert('로그인 성공');
				}
			}//end confirmLoginRslut()
			
			function confirmLogoutResult() {
				var result=$('#logout_result').val();
				if(result=='success'){
					alert('로그아웃 성공');
				}
			}//end confirmLogoutResult()
			
			function confirmJoinResult() {
				var result=$('#join_result').val();
				if(result=='success'){
					alert('회원가입 성공');
				}
			}//end confirmJoinResult()
			
			function confirmRegisterResult() {
				var result=$('#register_result').val();
				if(result=='success'){
					alert('작품 등록 성공');
				}
			}//end confirmRegisterResult()
			
		}); //end document
	</script>
</body>
</html>