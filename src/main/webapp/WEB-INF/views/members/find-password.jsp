<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 모바일 디바이스에서 터치/줌 등을 지원하기 위한 meta 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<!-- Popper JS -->
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<title>비밀번호 찾기 페이지</title>
</head>
<body style="text-align: center;">
	<h1>비밀번호 찾기</h1>
	<div class="card">
		<div class="card-body">
			<h5 class="card-title">등록된 이메일로 임시 비밀번호 발급하기</h5>
			<div class="card-text">
				<form action="" method="post">
					<input type="text" name="memberId" placeholder="아이디 입력" required><br>
					<input type="text" name="memberEmail" placeholder="이메일 입력" required><br> 
					<input type="submit" value="발급하기">
				</form>
			</div>
		</div>
	</div>
	<input type="hidden" id="email_result" value="${emailResult }">
	<!-- JavaScript -->
	<script type="text/javascript">
		$(function(){
			confirmEmailResult();
			
			function confirmEmailResult() {
				var result = $('#email_result').val();
				console.log(result);
				if(result=='fail'){
					alert('입력한 정보와 일치하는 회원이 없습니다.');
				}
			}//end confirmEmailResult()
		}); //end document	
	</script>
</body>
</html>