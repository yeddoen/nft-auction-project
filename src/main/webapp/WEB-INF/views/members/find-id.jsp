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
<title>아이디 찾기 페이지</title>
<!-- JavaScript -->
<script type="text/javascript">
	$(function() {
		/* 전화번호 형식적용 */
		$('#member_phone').keyup(function() {
			var member_phone = $('#member_phone').val();
			console.log(member_phone);
			//'-' 입력 막기
			member_phone = member_phone.replace(/[^0-9]/g, '');
			//전화번호 '-' 자동 삽입
			var regPhone = member_phone.replace(
					/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,
					"$1-$2-$3")
			$('#member_phone').val(regPhone);
		}); //end member_phone keyup()		
	}); //end document
	
	/* 전화번호로 찾기 버튼 활성화 */
	function submitPhone() {
		var phone=$('#member_phone').val();
		console.log(phone);
		//전화번호 정규표현식 적용
		var phone_check=
			/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
		//phone=phone.replace(/(.{3,4})/g,'-');
		
		if(phone_check.test(phone) === false){
			alert('올바른 전화번호 형식을 입력해주세요.');
			return false;
		}
		return true;
	}//end submitPhone()
	
	/* 이메일로 찾기 버튼 활성화 */
	function submitEmail() {
		var email=$('#member_email').val();
		console.log(email);
		//전화번호 정규표현식 적용
		var email_check=
		/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

		
		if (email_check.test(email) === false) {
	    	alert('올바른 이메일 형식을 입력해주세요.');
	    	return false;
	    }
		return true;
	}//end submitEmail()
</script>
</head>
<body style="text-align: center;">
	<h1>아이디 찾기</h1>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">등록된 휴대폰번호로 찾기</h5>
						<div class="card-text">
							<form action="find-id/phone" method="post" onsubmit="return submitPhone();">
								<input type="text" name="memberName" placeholder="이름 입력" required><br>
								<input type="text" id="member_phone" name="memberPhone" placeholder="전화번호 입력" required><br>
								<input type="submit" value="찾기">							
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">등록된 이메일로 찾기</h5>
						<div class="card-text">
							<form action="find-id/email" method="post" onsubmit="return submitEmail();">
								<input type="text" name="memberName" placeholder="이름 입력" required><br>
								<input type="text" id="member_email" name="memberEmail" placeholder="이메일 입력" required><br>
								<input type="submit" value="찾기">							
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>	
	</div>
</body>
</html>