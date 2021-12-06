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
								<h1>이용약관</h1>
								<p id="p1" align="center">제1조(목적) 이 약관은 OO 회사(전자상거래 사업자)가
									운영하는 OO 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를
									이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다. ※「PC통신, 무선 등을
									이용하는 전자상거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다.」
								<p id="p2">제2조(정의) ① “몰”이란 OO 회사가 재화 또는 용역(이하 “재화 등”이라 함)을
									이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 등을 거래할 수 있도록 설정한 가상의 영업장을
									말하며, 아울러 사이버몰을 운영하는 사업자의 의미로도 사용합니다. ② “이용자”란 “몰”에 접속하여 이 약관에
									따라 “몰”이 제공하는 서비스를 받는 회원 및 비회원을 말합니다. ③ ‘회원’이라 함은 “몰”에 회원등록을 한
									자로서, 계속적으로 “몰”이 제공하는 서비스를 이용할 수 있는 자를 말합니다. ④ ‘비회원’이라 함은 회원에
									가입하지 않고 “몰”이 제공하는 서비스를 이용하는 자를 말합니다.</p>
								<p id="p3">제3조 (약관 등의 명시와 설명 및 개정) ① “몰”은 이 약관의 내용과 상호 및 대표자
									성명, 영업소 소재지 주소(소비자의 불만을 처리할 수 있는 곳의 주소를 포함),
									전화번호․모사전송번호․전자우편주소, 사업자등록번호, 통신판매업 신고번호, 개인정보관리책임자등을 이용자가 쉽게 알
									수 있도록 00 사이버몰의 초기 서비스화면(전면)에 게시합니다. 다만, 약관의 내용은 이용자가 연결화면을 통하여
									볼 수 있도록 할 수 있습니다. ② “몰은 이용자가 약관에 동의하기에 앞서 약관에 정하여져 있는 내용 중
									청약철회․배송책임․환불조건 등과 같은 중요한 내용을 이용자가 이해할 수 있도록 별도의 연결화면 또는 팝업화면 등을
									제공하여 이용자의 확인을 구하여야 합니다. ③ “몰”은 「전자상거래 등에서의 소비자보호에 관한 법률」, 「약관의
									규제에 관한 법률」, 「전자문서 및 전자거래기본법」, 「전자금융거래법」, 「전자서명법」, 「정보통신망 이용촉진 및
									정보보호 등에 관한 법률」, 「방문판매 등에 관한 법률」, 「소비자기본법」 등 관련 법을 위배하지 않는 범위에서
									이 약관을 개정할 수 있습니다. ④ “몰”이 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과
									함께 몰의 초기화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다. 다만, 이용자에게 불리하게
									약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 "몰“은 개정 전
									내용과 개정 후 내용을 명확하게 비교하여 이용자가 알기 쉽도록 표시합니다. ⑤ “몰”이 약관을 개정할 경우에는 그
									개정약관은 그 적용일자 이후에 체결되는 계약에만 적용되고 그 이전에 이미 체결된 계약에 대해서는 개정 전의
									약관조항이 그대로 적용됩니다. 다만 이미 계약을 체결한 이용자가 개정약관 조항의 적용을 받기를 원하는 뜻을
									제3항에 의한 개정약관의 공지기간 내에 “몰”에 송신하여 “몰”의 동의를 받은 경우에는 개정약관 조항이
									적용됩니다. ⑥ 이 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 전자상거래 등에서의 소비자보호에 관한
									법률, 약관의 규제 등에 관한 법률, 공정거래위원회가 정하는 전자상거래 등에서의 소비자 보호지침 및 관계법령 또는
									상관례에 따릅니다.</p>
								<p id="p4">제4조(서비스의 제공 및 변경) ① “몰”은 다음과 같은 업무를 수행합니다. 1. 재화
									또는 용역에 대한 정보 제공 및 구매계약의 체결 2. 구매계약이 체결된 재화 또는 용역의 배송 3. 기타 “몰”이
									정하는 업무 ② “몰”은 재화 또는 용역의 품절 또는 기술적 사양의 변경 등의 경우에는 장차 체결되는 계약에 의해
									제공할 재화 또는 용역의 내용을 변경할 수 있습니다. 이 경우에는 변경된 재화 또는 용역의 내용 및 제공일자를
									명시하여 현재의 재화 또는 용역의 내용을 게시한 곳에 즉시 공지합니다. ③ “몰”이 제공하기로 이용자와 계약을
									체결한 서비스의 내용을 재화등의 품절 또는 기술적 사양의 변경 등의 사유로 변경할 경우에는 그 사유를 이용자에게
									통지 가능한 주소로 즉시 통지합니다. ④ 전항의 경우 “몰”은 이로 인하여 이용자가 입은 손해를 배상합니다.
									다만, “몰”이 고의 또는 과실이 없음을 입증하는 경우에는 그러하지 아니합니다.</p>
								<p id="p5">제5조(서비스의 중단) ① “몰”은 컴퓨터 등 정보통신설비의 보수점검․교체 및 고장,
									통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다. ② “몰”은 제1항의
									사유로 서비스의 제공이 일시적으로 중단됨으로 인하여 이용자 또는 제3자가 입은 손해에 대하여 배상합니다. 단,
									“몰”이 고의 또는 과실이 없음을 입증하는 경우에는 그러하지 아니합니다. ③ 사업종목의 전환, 사업의 포기, 업체
									간의 통합 등의 이유로 서비스를 제공할 수 없게 되는 경우에는 “몰”은 제8조에 정한 방법으로 이용자에게 통지하고
									당초 “몰”에서 제시한 조건에 따라 소비자에게 보상합니다. 다만, “몰”이 보상기준 등을 고지하지 아니한 경우에는
									이용자들의 마일리지 또는 적립금 등을 “몰”에서 통용되는 통화가치에 상응하는 현물 또는 현금으로 이용자에게
									지급합니다.</p>
								<p id="p6">제6조(회원가입) ① 이용자는 “몰”이 정한 가입 양식에 따라 회원정보를 기입한 후 이
									약관에 동의한다는 의사표시를 함으로서 회원가입을 신청합니다. ② “몰”은 제1항과 같이 회원으로 가입할 것을
									신청한 이용자 중 다음 각 호에 해당하지 않는 한 회원으로 등록합니다. 1. 가입신청자가 이 약관 제7조제3항에
									의하여 이전에 회원자격을 상실한 적이 있는 경우, 다만 제7조제3항에 의한 회원자격 상실 후 3년이 경과한 자로서
									“몰”의 회원재가입 승낙을 얻은 경우에는 예외로 한다. 2. 등록 내용에 허위, 기재누락, 오기가 있는 경우 3.
									기타 회원으로 등록하는 것이 “몰”의 기술상 현저히 지장이 있다고 판단되는 경우 ③ 회원가입계약의 성립 시기는
									“몰”의 승낙이 회원에게 도달한 시점으로 합니다. ④ 회원은 회원가입 시 등록한 사항에 변경이 있는 경우, 상당한
									기간 이내에 “몰”에 대하여 회원정보 수정 등의 방법으로 그 변경사항을 알려야 합니다.</p>
								<p id="p7">제7조(회원 탈퇴 및 자격 상실 등) ① 회원은 “몰”에 언제든지 탈퇴를 요청할 수
									있으며 “몰”은 즉시 회원탈퇴를 처리합니다. ② 회원이 다음 각 호의 사유에 해당하는 경우, “몰”은 회원자격을
									제한 및 정지시킬 수 있습니다. 1. 가입 신청 시에 허위 내용을 등록한 경우 2. “몰”을 이용하여 구입한 재화
									등의 대금, 기타 “몰”이용에 관련하여 회원이 부담하는 채무를 기일에 지급하지 않는 경우 3. 다른 사람의 “몰”
									이용을 방해하거나 그 정보를 도용하는 등 전자상거래 질서를 위협하는 경우 4. “몰”을 이용하여 법령 또는 이
									약관이 금지하거나 공서양속에 반하는 행위를 하는 경우 ③ “몰”이 회원 자격을 제한․정지 시킨 후, 동일한 행위가
									2회 이상 반복되거나 30일 이내에 그 사유가 시정되지 아니하는 경우 “몰”은 회원자격을 상실시킬 수 있습니다.

									④ “몰”이 회원자격을 상실시키는 경우에는 회원등록을 말소합니다. 이 경우 회원에게 이를 통지하고, 회원등록 말소
									전에 최소한 30일 이상의 기간을 정하여 소명할 기회를 부여합니다.</p>
								<p id="p8">제8조(회원에 대한 통지) ① “몰”이 회원에 대한 통지를 하는 경우, 회원이 “몰”과
									미리 약정하여 지정한 전자우편 주소로 할 수 있습니다. ② “몰”은 불특정다수 회원에 대한 통지의 경우 1주일이상
									“몰” 게시판에 게시함으로서 개별 통지에 갈음할 수 있습니다. 다만, 회원 본인의 거래와 관련하여 중대한 영향을
									미치는 사항에 대하여는 개별통지를 합니다.</p>
								<p id="p9">제9조(구매신청 및 개인정보 제공 동의 등) ① “몰”이용자는 “몰”상에서 다음 또는
									이와 유사한 방법에 의하여 구매를 신청하며, “몰”은 이용자가 구매신청을 함에 있어서 다음의 각 내용을 알기 쉽게
									제공하여야 합니다. 1. 재화 등의 검색 및 선택 2. 받는 사람의 성명, 주소, 전화번호, 전자우편주소(또는
									이동전화번호) 등의 입력 3. 약관내용, 청약철회권이 제한되는 서비스, 배송료․설치비 등의 비용부담과 관련한
									내용에 대한 확인 4. 이 약관에 동의하고 위 3.호의 사항을 확인하거나 거부하는 표시 (예, 마우스 클릭) 5.
									재화등의 구매신청 및 이에 관한 확인 또는 “몰”의 확인에 대한 동의 6. 결제방법의 선택 ② “몰”이 제3자에게
									구매자 개인정보를 제공할 필요가 있는 경우 1) 개인정보를 제공받는 자, 2)개인정보를 제공받는 자의 개인정보
									이용목적, 3) 제공하는 개인정보의 항목, 4) 개인정보를 제공받는 자의 개인정보 보유 및 이용기간을 구매자에게
									알리고 동의를 받아야 합니다. (동의를 받은 사항이 변경되는 경우에도 같습니다.) ③ “몰”이 제3자에게 구매자의
									개인정보를 취급할 수 있도록 업무를 위탁하는 경우에는 1) 개인정보 취급위탁을 받는 자, 2) 개인정보 취급위탁을
									하는 업무의 내용을 구매자에게 알리고 동의를 받아야 합니다. (동의를 받은 사항이 변경되는 경우에도 같습니다.)
									다만, 서비스제공에 관한 계약이행을 위해 필요하고 구매자의 편의증진과 관련된 경우에는 「정보통신망 이용촉진 및
									정보보호 등에 관한 법률」에서 정하고 있는 방법으로 개인정보 취급방침을 통해 알림으로써 고지절차와 동의절차를
									거치지 않아도 됩니다.</p>
								<p id="p10">제10조 (계약의 성립) ① “몰”은 제9조와 같은 구매신청에 대하여 다음 각 호에
									해당하면 승낙하지 않을 수 있습니다. 다만, 미성년자와 계약을 체결하는 경우에는 법정대리인의 동의를 얻지 못하면
									미성년자 본인 또는 법정대리인이 계약을 취소할 수 있다는 내용을 고지하여야 합니다. 1. 신청 내용에 허위,
									기재누락, 오기가 있는 경우 2. 미성년자가 담배, 주류 등 청소년보호법에서 금지하는 재화 및 용역을 구매하는
									경우 3. 기타 구매신청에 승낙하는 것이 “몰” 기술상 현저히 지장이 있다고 판단하는 경우 ② “몰”의 승낙이
									제12조제1항의 수신확인통지형태로 이용자에게 도달한 시점에 계약이 성립한 것으로 봅니다. ③ “몰”의 승낙의
									의사표시에는 이용자의 구매 신청에 대한 확인 및 판매가능 여부, 구매신청의 정정 취소 등에 관한 정보 등을
									포함하여야 합니다.</p>
								<p>제11조(지급방법) “몰”에서 구매한 재화 또는 용역에 대한 대금지급방법은 다음 각 호의 방법중 가용한
									방법으로 할 수 있습니다. 단, “몰”은 이용자의 지급방법에 대하여 재화 등의 대금에 어떠한 명목의 수수료도
									추가하여 징수할 수 없습니다. 1. 폰뱅킹, 인터넷뱅킹, 메일 뱅킹 등의 각종 계좌이체 2. 선불카드, 직불카드,
									신용카드 등의 각종 카드 결제 3. 온라인무통장입금 4. 전자화폐에 의한 결제 5. 수령 시 대금지급 6.
									마일리지 등 “몰”이 지급한 포인트에 의한 결제 7. “몰”과 계약을 맺었거나 “몰”이 인정한 상품권에 의한 결제
									8. 기타 전자적 지급 방법에 의한 대금 지급 등</p>
								<p>제12조(수신확인통지․구매신청 변경 및 취소) ① “몰”은 이용자의 구매신청이 있는 경우 이용자에게
									수신확인통지를 합니다. ② 수신확인통지를 받은 이용자는 의사표시의 불일치 등이 있는 경우에는 수신확인통지를 받은
									후 즉시 구매신청 변경 및 취소를 요청할 수 있고 “몰”은 배송 전에 이용자의 요청이 있는 경우에는 지체 없이 그
									요청에 따라 처리하여야 합니다. 다만 이미 대금을 지불한 경우에는 제15조의 청약철회 등에 관한 규정에 따릅니다.
								</p>
								<p>제13조(재화 등의 공급) ① “몰”은 이용자와 재화 등의 공급시기에 관하여 별도의 약정이 없는 이상,
									이용자가 청약을 한 날부터 7일 이내에 재화 등을 배송할 수 있도록 주문제작, 포장 등 기타의 필요한 조치를
									취합니다. 다만, “몰”이 이미 재화 등의 대금의 전부 또는 일부를 받은 경우에는 대금의 전부 또는 일부를 받은
									날부터 3영업일 이내에 조치를 취합니다. 이때 “몰”은 이용자가 재화 등의 공급 절차 및 진행 사항을 확인할 수
									있도록 적절한 조치를 합니다. ② “몰”은 이용자가 구매한 재화에 대해 배송수단, 수단별 배송비용 부담자, 수단별
									배송기간 등을 명시합니다. 만약 “몰”이 약정 배송기간을 초과한 경우에는 그로 인한 이용자의 손해를 배상하여야
									합니다. 다만 “몰”이 고의․과실이 없음을 입증한 경우에는 그러하지 아니합니다.</p>
								<p>제14조(환급) “몰”은 이용자가 구매신청한 재화 등이 품절 등의 사유로 인도 또는 제공을 할 수 없을
									때에는 지체 없이 그 사유를 이용자에게 통지하고 사전에 재화 등의 대금을 받은 경우에는 대금을 받은 날부터
									3영업일 이내에 환급하거나 환급에 필요한 조치를 취합니다.</p>
								<p>제15조(청약철회 등) ① “몰”과 재화등의 구매에 관한 계약을 체결한 이용자는 「전자상거래 등에서의
									소비자보호에 관한 법률」 제13조 제2항에 따른 계약내용에 관한 서면을 받은 날(그 서면을 받은 때보다 재화 등의
									공급이 늦게 이루어진 경우에는 재화 등을 공급받거나 재화 등의 공급이 시작된 날을 말합니다)부터 7일 이내에는
									청약의 철회를 할 수 있습니다. 다만, 청약철회에 관하여 「전자상거래 등에서의 소비자보호에 관한 법률」에 달리
									정함이 있는 경우에는 동 법 규정에 따릅니다. ② 이용자는 재화 등을 배송 받은 경우 다음 각 호의 1에 해당하는
									경우에는 반품 및 교환을 할 수 없습니다. 1. 이용자에게 책임 있는 사유로 재화 등이 멸실 또는 훼손된
									경우(다만, 재화 등의 내용을 확인하기 위하여 포장 등을 훼손한 경우에는 청약철회를 할 수 있습니다) 2.
									이용자의 사용 또는 일부 소비에 의하여 재화 등의 가치가 현저히 감소한 경우 3. 시간의 경과에 의하여 재판매가
									곤란할 정도로 재화등의 가치가 현저히 감소한 경우 4. 같은 성능을 지닌 재화 등으로 복제가 가능한 경우 그
									원본인 재화 등의 포장을 훼손한 경우 ③ 제2항제2호 내지 제4호의 경우에 “몰”이 사전에 청약철회 등이 제한되는
									사실을 소비자가 쉽게 알 수 있는 곳에 명기하거나 시용상품을 제공하는 등의 조치를 하지 않았다면 이용자의 청약철회
									등이 제한되지 않습니다. ④ 이용자는 제1항 및 제2항의 규정에 불구하고 재화 등의 내용이 표시·광고 내용과
									다르거나 계약내용과 다르게 이행된 때에는 당해 재화 등을 공급받은 날부터 3월 이내, 그 사실을 안 날 또는 알
									수 있었던 날부터 30일 이내에 청약철회 등을 할 수 있습니다.</p>
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
			<br> <a href="#">이용약관</a> &nbsp; <a href="termsOfInformation">사이트안내</a> <br>
			<br> <small>문의 nft.auction.help@gmail.com</small>
		</div>
	</div>
	

</body>
</html>