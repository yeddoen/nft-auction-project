<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>결제 결과</title>
</head>
<body>
	<br>
	<h4 align="center">결제가 완료되었습니다.</h4>
	<h5 align="center">내 계정을 확인해보세요!</h5>
	<br>
	<div align="center">
		<div>
			<ul class="list-group list-group-horizontal justify-content-center">
				<li class="list-group-item list-group-item-primary"
					style="width: 10rem">주문 번호</li>
				<li class="list-group-item" style="width: 30rem">${vo.merchantUid }</li>
			</ul>
			<ul class="list-group list-group-horizontal justify-content-center">
				<li class="list-group-item list-group-item-primary"
					style="width: 10rem">아이디</li>
				<li class="list-group-item" style="width: 30rem">${vo.memberId }</li>
			</ul>
			<ul class="list-group list-group-horizontal justify-content-center">
				<li class="list-group-item list-group-item-primary"
					style="width: 10rem">작품 가격</li>
				<li class="list-group-item" style="width: 30rem">${vo.artPrice }</li>
			</ul>
			<ul class="list-group list-group-horizontal justify-content-center">
				<li class="list-group-item list-group-item-primary"
					style="width: 10rem">작품 이름</li>
				<li class="list-group-item" style="width: 30rem">${vo.artName }</li>
			</ul>
			<ul class="list-group list-group-horizontal justify-content-center">
				<li class="list-group-item list-group-item-primary"
					style="width: 10rem">작품 번호</li>
				<li class="list-group-item" style="width: 30rem">${vo.artNo }</li>
			</ul>
			<ul class="list-group list-group-horizontal justify-content-center">
				<li class="list-group-item list-group-item-primary"
					style="width: 10rem">작품</li>
				<li class="list-group-item" style="width: 30rem">
					<img src="https://nftauctionbucket.s3.ap-northeast-2.amazonaws.com/${vo.artFileName }"></li>
			</ul>
		</div>
		<br>
		<button type="button" class="btn btn-primary" onclick="myPage()">마이페이지</button>
		<button type="button" class="btn btn-primary" onclick="main()">메인</button>
		<button type="button" class="btn btn-primary" onclick="window.close()">창닫기</button>

		<!-- 12.01 현아 추가 -->
		<input type="hidden" id="memberId" value="${mvo.memberId }">
		<input type="hidden" id="toAccount" value="${mvo.memberAccount }">
		<!-- art구매자의 account -->
		<input type="hidden" id="artMemberId" value="${avo.memberId }">
		<!-- art소유자의 contract alias -->
		<input type="hidden" id="fromAccount" value="${avo.memberAccount }">
		<!-- art소유자의 account -->
		<input type="hidden" id="artTokenId" value="${avo.artTokenId }">
		<!-- art의 NFT 토큰 아이디 -->
	</div>

	<script type="text/javascript">
        function myPage() {
            opener.parent.location = '../members/my-page/member';
            window.close();
        }

        function main() {
            opener.parent.location = '../main';
            window.close();
        }
        
        // 12.01 현아 결제가 완료되면 NFT 등록자의 계정에서 구매자의 계정으로 전송하기
        console.log('현재 구매자의 아이디 : ' + memberId);
        var fromAccount = $('#fromAccount').val(); // art 소유자
        var toAccount = $('#toAccount').val(); // art 구매자(현재 세션유지아이디)
        var artMemberId = $('#artMemberId').val(); // art 소유자의 아이디, 즉 contract alias
        var artTokenId = $('#artTokenId').val(); // 해당 art의 Nft 토큰아이디
        
        console.log("작품 등록자의 계정 : " + fromAccount);
        console.log("작품 구매자의 계정 : " + toAccount);
        console.log("작품 등록자의 contract alias : " + artMemberId);
        console.log("작품의 토큰 아이디 : " + artTokenId);
        
        const settings = {
                "async": true,
                "crossDomain": true,
                "url": "https://kip17-api.klaytnapi.com/v1/contract/" + artMemberId + "/token/" + artTokenId,
                "method": "POST",
                "headers": {
                  "Content-Type": "application/json",
                  "x-chain-id": "1001",
                  "x-krn": "",
                  "Authorization": "Basic S0FTS0VNTkMxRDg4UTdHSDFUTlZMWkhSOkhPa3lvbEpnbnFlaGhrNDRGOWVjSWNiSENONm0tSEJrLUFSV01PWXQ="
                },
                "processData": false,
                "data": "{\n  \"sender\": \"" + fromAccount + "\",\n  \"owner\": \"" + fromAccount + 
                    "\",\n  \"to\": \"" + toAccount + "\"\n}"
        };

        $.ajax(settings).done(function (response) {
                console.log("nft 토큰 transfer 전송 결과 : " + response + ", " + response.status);
        });
        
    </script>

</body>
</html>