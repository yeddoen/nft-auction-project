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
		<a href="sign-up"><button type="button">회원가입</button></a>
		<a href="find-id"><button>아이디 찾기</button></a>
		<a href="find-password"><button>비밀번호 찾기</button></a>
	</div>
	<!-- hidden -->
	<input type="hidden" id="login_result" value="${loginResult }">
	<input type="hidden" id="email_result" value="${emailResult }">
	<!-- JavaScript -->
	<script type="text/javascript">
		$(function() {
			confirmLoginReslut();
			confirmEmailResult();
			
			function confirmLoginReslut() {
				var result=$('#login_result').val();
				console.log(result);
				if(result=='fail'){
					alert('일치하는 회원정보가 없습니다.');
				}
			}//end confirmLoginReslut()
			
			function confirmEmailResult() {
				var result = $('#email_result').val();
				console.log(result);
				if(result=='success'){
					alert('입력한 이메일주소로 임시 비밀번호가 발급되었습니다.');
				}
			}//end confirmEmailResult()
		});//end document
	</script>
</body>
</html>