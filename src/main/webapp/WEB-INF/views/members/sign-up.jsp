<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- MetaMask api -->
<script src="https://unpkg.com/@metamask/detect-provider/dist/detect-provider.min.js"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- JavaScript : submitAble() -->
<script type="text/javascript">		
	var id_check=false; //아이디 중복체크
	var member_check=false; //본인인증 확인
	var pw_check=false; //비밀번호 확인
 		
	/* 가입하기 버튼 활성화 */
	function submitAble() {
		var phone=$('#member_phone').val();
		var email=$('#member_email').val();
		//전화번호 정규표현식 적용
		var phone_check=
			/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
		//phone=phone.replace(/(.{3,4})/g,'-');
		//이메일 정규표현식 적용
		var email_check=
			/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

		console.log(id_check+", "+member_check+", "+pw_check);
		if(id_check==false){
			alert("아이디 중복체크를 해주세요.");
			return false;
		}
		if(member_check==false){
			alert("본인인증을 해주세요.");
			return false;
		}
		if(pw_check==false){
			alert("비밀번호 확인을 진행해주세요.");
			return false;
		}
		if(phone_check.test(phone) === false){
			alert('올바른 전화번호를 입력해주세요.');
			return false;
		}
		if (email_check.test(email) === false) {
	    	alert('올바른 이메일 형식을 입력해주세요.');
	    	return false;
	    }
		return true;
	}//end submitAble()
</script>
<title>회원가입 페이지</title>
</head>
<body>
	<div class="header">
	</div>
	<div class="body" style="text-align: center;">
	<h1>회원가입</h1>
	<form action="sign-up" method="post" onsubmit="return submitAble();">
		<p><input type="text" id="member_id" name="memberId" placeholder="아이디 입력" required>
		<button type="button" id="check_id">아이디 중복체크</button></p>
		<span id="check_id_result"></span>
		<p><input type="text" id="member_name" name="memberName" placeholder="실명 입력" required>
		<button type="button" id="check_confirm">본인인증</button></p>
		<input type="hidden" id="member_uid" name="memberUid" value="">
		<p><input type="password" id="member_pw" name="memberPassword" placeholder="비밀번호 입력" required></p>
		<p><input type="password" id="confirm_pw" placeholder="비밀번호 확인" required></p>
		<span id="confirm_pw_result"></span>
		<p><input type="text" name="memberNickname" placeholder="닉네임 입력" required></p>

		<p><input type="text" name="memberPhone" placeholder="전화번호 입력" required></p>
		<p><input type="text" name="memberEmail" placeholder="이메일 입력" required></p>
		<p><button class="enableEthereumButton">메타마스크연동</button></p>

		<p><input type="text" id="member_phone" name="memberPhone" placeholder="전화번호 ('-'없이) 입력" required></p>
		<p><input type="email" id="member_email" name="memberEmail" placeholder="이메일 입력" required></p>

		<p><input type="submit" value="가입하기">
	</form>
	</div>
	<div class="foot">
	</div>
	<!-- JavaScript -->
	<script type="text/javascript">
		$(function() {
			var IMP = window.IMP;
			IMP.init('iamport'); //관리자 체험용
			
			/* 아이디 중복 체크 */
			$('#check_id').click(function(){
				var member_id=$('#member_id').val();
				if(!member_id){
            		$('#check_id_result').css('color', 'red');
					$('#check_id_result').html("아이디를 입력해주세요.");
				}else{
					$.ajax({
						type:'get',
		                url:'rest/id-check/'+member_id,
		                success:function(result, status){
		                	console.log(result + " : "+ status);
		                	if(result==0){
		                		id_check=true;
		                		$('#check_id_result').css('color', 'green');
			                	$('#check_id_result').html("사용가능한 아이디입니다.");
		                	}else{ // 1인 경우
		                		id_check=false;
		                		$('#check_id_result').css('color', 'red');
			                	$('#check_id_result').html("중복된 아이디입니다.");
		                	}
		                }
					}); //end ajax
				}
			}); //end check_id click
			
			$('#member_id').change(function(){
				if(id_check==true){ //중복체크하고 다시 id를 변경한 경우
					id_check=false;
				}
			}); //end member_id change()
	
			/* 회원 본인인증 */
			$("#check_confirm").click(function() {
				var member_name = $('#member_name').val();
				var member_id=$('#member_id').val();
				
				if(!member_name){
					alert("이름을 입력해주세요.");
					if(id_check!=true){
						alert("아이디 중복체크를 해주세요.");
					}
				}else{
					IMP.certification({
						name : member_name,
						merchant_uid : 'member_' + member_id // 고유 인증id
					}, function(rsp) {
						if (rsp.success) {
							// 인증성공
							console.log(rsp.imp_uid);
							console.log(rsp.merchant_uid);

							$.ajax({
								type : 'POST',
								url : 'rest/confirm',
								data : {memberUid : rsp.merchant_uid},
								success:function(result, status){
									$('#member_uid').attr('value', result);
									$('#member_name').attr('readonly',true);
									$('#check_confirm').attr('disalbed', true);
									member_check = true;
									alert("본인인증에 성공했습니다.");
								}
							});
						} else {
							// 인증취소 또는 인증실패
							member_check = false;
							var msg = '인증에 실패하였습니다.';
							msg += '에러내용 : ' + rsp.error_msg;
							alert(msg);
						}
					}); //end IMP.certification
				}
			}); //end check_confirm click()
			
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
			
			// TODO : 현재 request로 메타마스크 api만 실행하게 만듬. 
			// 이제 계좌 account와 지갑 정보를 불러와서 저장시키고 유지시켜야할듯!
			// 메타마스크연동 버튼 관련 코드 (메타마스크 창 띄우기)
			if (typeof window.ethereum !== 'undefined') {
		    	console.log('MetaMask is installed!'); // 메타마스크가 설치된경우

		  		const ethereumButton = document.querySelector('.enableEthereumButton');
		  		ethereumButton.addEventListener('click', () => {
		  		  //Will Start the metamask extension
		  		  ethereum.request({ method: 'eth_requestAccounts' });
		  		}); // 이더리움버튼 누르면 request 요청 실행.(앱실행)
		  	} else { // 아니라면 설치할 수 있도록 유도하기.
		  	    console.log('Please install MetaMask!');
		  		location.href = "https://metamask.io/";
		  	}


			
			/* 전화번호 형식적용 */
			$('#member_phone').keyup(function(){
				var member_phone=$('#member_phone').val();
				console.log(member_phone);
				//'-' 입력 막기
				member_phone=member_phone.replace(/[^0-9]/g,'');
				//전화번호 '-' 자동 삽입
				var regPhone=member_phone.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3")
				$('#member_phone').val(regPhone);
			}); //end member_phone keyup()

			
		}); //end document
	</script>
</body>
</html>