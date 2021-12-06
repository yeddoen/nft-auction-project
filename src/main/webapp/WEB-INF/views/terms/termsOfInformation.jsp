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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/zephyr/bootstrap.min.css">
<style type="text/css">
img {
	max-width: 100%;
	height: auto;
}

ul {
	list-style-type: none;
}

li {
	display: inline-block;
}
/* 사이드바 래퍼 스타일 */
#sidebar-wrapper {
	position: fixed;
	width: 350px;
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
	width: 350px;
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

.carousel-indicators {
	position: absolute;
	right: 0;
	bottom: 10px;
	left: 0;
	z-index: 15;
	display: flex;
	justify-content: center;
	padding-left: 0; // override <ol> default // Use the
	.carousel-control's width as margin so we don't overlay those
	margin-right : $ carousel-control-width;
	margin-left: $carousel-control-width;
	list-style: none; li { position : relative;
	flex: 0 1 auto;
	width: $carousel-indicator-width;
	height: $carousel-indicator-height;
	margin-right: $carousel-indicator-spacer;
	margin-left: $carousel-indicator-spacer;
	text-indent: -999px;
	cursor: pointer;
	background-color: rgba($ carousel-indicator-active-bg, .5); // Use
	pseudo classes to increase the hit area by 10px on top and bottom. & :
	: before {
      position : absolute;
	top: -10px;
	left: 0;
	display: inline-block;
	width: 100%;
	height: 10px;
	content: "";
}

&
::after {
	position: absolute;
	bottom: -10px;
	left: 0;
	display: inline-block;
	width: 100%;
	height: 10px;
	content: "";
}

}
.active {
	background-color: $carousel-indicator-active-bg;
}

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
</style>
<title>이용약관</title>
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
					<li class="nav-item"><a class="nav-link"
						href="../arts/register">작품등록</a></li>
					<c:if test="${empty sessionScope.memberId }">
						<li class="nav-item"><a class="nav-link"
							href="../members/login">로그인</a></li>
						<li class="nav-item"><a class="nav-link"
							href="../members/sign-up">회원가입</a></li>
					</c:if>
					<c:if test="${not empty sessionScope.memberId }">
						<li class="nav-item"><a class="nav-link"
							href="../members/logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link"
							href="../members/my-page/member">마이페이지</a></li>
						<li class="nav-itm"><a class="nav-link">
								${sessionScope.memberId } 님, 접속중입니다!</a>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>

	<article>
		<div>
			<div class="container">
				<div class="row">
					<div class="">
						<div class="">
							<div class="">
								<br>
								<h1>사이트안내</h1>
								<p>1 개인정보의 처리 목적 ① <ㅇㅇㅇ>은(는) 다음의 목적을 위하여 개인정보를 처리하고 있으며, 다음의
									목적 이외의 용도로는 이용하지 않습니다. 고객 가입의사 확인, 고객에 대한 서비스 제공에 따른 본인 식별·인증,
									회원자격 유지·관리, 물품 또는 서비스 공급에 따른 금액 결제, 물품 또는 서비스의 공급·배송, 마케팅 및
									광고에의 활용</p>
								<p>2 개인정보의 처리 및 보유 기간 작성 ① <ㅇㅇㅇ>은(는) 정보주체로부터 개인정보를 수집할 때 동의
									받은 개인정보 보유·이용기간 또는 법령에 따른 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다. ②
									구체적인 개인정보 처리 및 보유 기간은 다음과 같습니다. 고객 가입 및 관리 : 서비스 이용계약 또는 회원가입
									해지시까지, 다만 채권·채무관계 잔존시에는 해당 채권·채무관계 정산시까지 전자상거래에서의 계약·청약철회,
									대금결제, 재화 등 공급기록 : 5년</p>
								<p>3 정보주체와 법정대리인의 권리·의무 및 그 행사방법 정보주체는 <ㅇㅇㅇ>에 대해 언제든지 다음 각
									호의 개인정보 보호 관련 권리를 행사할 수 있습니다. 1. 개인정보 열람요구 2. 오류 등이 있을 경우 정정 요구
									3. 삭제요구 4. 처리정지 요구</p>
								<p>4 처리하는 개인정보 항목 개인정보 처리업무: 홈페이지 회원가입 및 관리, 민원사무 처리, 재화 또는
									서비스 제공, 마케팅 및 광고에의 활용 필수항목: 로그인ID, 비밀번호, 서비스 이용 기록, 접속 로그, 쿠키,
									접속 IP 정보, 결제기록 선택항목: 이메일, 성별, 이름</p>
								<p>5 개인정보의 파기 파기절차 이용자가 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져(종이의 경우
									별도의 서류) 내부 방침 및 기타 관련 법령에 따라 일정기간 저장된 후 혹은 즉시 파기됩니다. 이 때, DB로
									옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다. 파기기한 이용자의 개인정보는
									개인정보의 보유기간이 경과된 경우에는 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당
									서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는
									날로부터 5일 이내에 그 개인정보를 파기합니다.</p>
								<p>6 개인정보 자동 수집 장치의 설치·운영 및 그 거부에 관한 사항 ① <ㅇㅇㅇ>은 개별적인 맞춤서비스를
									제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠키(cookie)’를 사용합니다. ② 쿠키는 웹사이트를
									운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 컴퓨터 내의
									하드디스크에 저장되기도 합니다. 가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문
									및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다. 나.
									쿠키의 설치·운영 및 거부 : 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을
									거부 할 수 있습니다. 다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.</p>
								<p>7 개인정보 보호책임자 이름: 홍길동 소속: 운영팀 전화: 02-1234-5678 이메일:
									nft.auction.help@gmail.com</p>
								<p>8 개인정보의 안전성 확보 조치 개인정보의 암호화 이용자의 비밀번호는 암호화 되어 저장 및 관리되고
									있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화 하거나 파일 잠금 기능을 사용하는
									등의 별도 보안기능을 사용하고 있습니다.</p>
								<p>9 개인정보처리방침의 변경 이 개인정보처리방침은 시행일로부터 적용되며, 법령 및 방침에 따른 변경내용의
									추가, 삭제 및 정정이 있는 경우에는 변경사항의 시행 7일 전부터 공지사항을 통하여 고지할 것입니다. 공고일자 :
									2000년 00월 00일 시행일자 : 2000년 00월 00일</p>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</article>
	<!-- footer -->
	<div class="mt-5 p-3 bg-light" align="center">
		<div style="bottom: 0; height: 200px;">
			<h4>NFT-AUCTION</h4>
			<hr>
			<br> <a href="termsOfService">이용약관</a> &nbsp; <a href="#">사이트안내</a> <br>
			<br> <small>문의 nft.auction.help@gmail.com</small>
		</div>
	</div>


</body>
</html>