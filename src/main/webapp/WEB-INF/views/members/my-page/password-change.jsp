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

<title>비밀번호 변경 페이지</title>
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
					<h1>비밀번호를 변경하시겠습니까?</h1>
				</div>
			</div>
			
			<div class="content">
				<!-- 회원 정보 기본적으로 보여주기 -->
				<!-- 아이디, 비밀번호(수정페이지로) 이름, 닉네임, 전화번호, 이메일 -->
				<form action="password-change" method="POST">
					변경할 비밀번호를 입력해주세요 <input type="password" id="member_pw" name="updatePassword"
						placeholder="비밀번호 입력" required="required">
					<p>다시한번 확인해주세요 <input type="password" id="confirm_pw" placeholder="비밀번호 확인" required>
					<span id="confirm_pw_result"></span>
					</p>
					<input type="submit" id="btn-password-change" value="비밀번호변경">
				</form>
			</div>
		</div>
	</section>
	
	<script type="text/javascript">
	/* 비밀번호 확인 */
	$('#member_pw').blur(function(){
		var member_pw=$('#member_pw').val();
		var confirm_pw=$('#confirm_pw').val();
		
		if(confirm_pw){ //비밀번호 확인 값이 입력되었을때만 
			if(member_pw==confirm_pw){
				pw_check=true;
				$('#confirm_pw_result').css('color','green');
				$('#confirm_pw_result').html("비밀번호가 일치합니다.");
			}else{
				pw_check=false;
				$('#confirm_pw_result').css('color','red');
				$('#confirm_pw_result').html("비밀번호가 일치하지 않습니다.");
			}
		}
		
	}); //end member_pw blur()
	
	$('#confirm_pw').blur(function(){
		var member_pw=$('#member_pw').val();
		var confirm_pw=$('#confirm_pw').val();
		
		if(!member_pw || !confirm_pw){
			pw_check=false;
			$('#confirm_pw_result').css('color','red');
			$('#confirm_pw_result').html("비밀번호를 입력하세요.");
		}else{
			if(member_pw==confirm_pw){
				pw_check=true;
				$('#confirm_pw_result').css('color','green');
				$('#confirm_pw_result').html("비밀번호가 일치합니다.");
			}else{
				pw_check=false;
				$('#confirm_pw_result').css('color','red');
				$('#confirm_pw_result').html("비밀번호가 일치하지 않습니다.");
			}
		}
	}); //end confirm_pw blur()
	
	
	</script>

</body>
</html>