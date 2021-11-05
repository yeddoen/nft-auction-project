<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>로그인 페이지</title>
</head>
<body>
	<div class="body" style="text-align: center;">
		<h1>로그인</h1>
		<form action="login" method="post">
			<input type="text" name="memberId" required placeholder="아이디 입력"><br>
			<input type="password" name="memberPassword" required placeholder="비밀번호 입력"><br>
			<input type="submit" value="로그인">
		</form>	
		<input type="hidden" id="login_result" value="${loginResult }">
		<a href="sign-up"><button>회원가입</button></a>
	</div>
	<!-- JavaScript -->
	<script type="text/javascript">
		$(function() {
			confirmLoginReslut();
			
			function confirmLoginReslut() {
				var result=$('#login_result').val();
				if(result=='fail'){
					alert('일치하는 회원정보가 없습니다.');
				}
			}
		});//end document
	</script>
</body>
</html>