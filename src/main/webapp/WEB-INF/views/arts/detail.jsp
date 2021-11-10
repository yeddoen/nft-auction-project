<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
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
<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<style type="text/css">
img {
	max-width: 100%;
	height: auto;
}
</style>
<title>상세 페이지 - ${vo.artName} </title>
</head>
<body style="text-align: center;">
	<!-- header -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
		<a class="navbar-brand" href="../main">NTF-AUCTION</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="register">작품등록</a>
				</li>
				<c:if test="${empty sessionScope.memberId }">
					<li class="nav-item"><a class="nav-link" href="../members/login">로그인</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="/members/sign-up">회원가입</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.memberId }">
					<li class="nav-item"><a class="nav-link" href="../members/logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link"
						href="../members/my-page/member">마이페이지</a></li>
				</c:if>
			</ul>
		</div>
	</nav>
	<br>
	<!-- layout -->
	<div class="container">
		<div class="row">
			<div class="col-sm-7">
				<div class="card bg-light mb-3">
					<div class="card-body">
						<div id="image"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-5">
				<div class="card bg-light" style="padding: 1em;">
					<div class="card-body">
						<div class="row">
							<div class="col">
								<h4 class="card-title">${vo.artName }</h4>
								<p>Creator by ${vo.memberNickname }</p>
							</div>
						</div>
						<div class="row">
							<div class="col" style="text-align: right;">
								<i class="bi bi-eye"></i> 조회수 ${vo.artViewCount }&nbsp;						
								<button type="button" id="btn_wish" class="btn btn-outline-danger btn-sm">
								찜하기 ${vo.artWishCount }</button>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col" style="text-align: left;">
								<p style="font-size: 80%;">최저입찰가 ${vo.artBasicFee }원</p>
								<p>즉시 구매금액<p>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<h4 style="text-align: right;"><fmt:formatNumber value="${vo.artPrice }" type="currency" currencySymbol=""/>원</h4><br>
							</div>
						</div>
						<div style="margin: 10px;">
							<button class="btn btn-primary" type="button" id="btn_auction"
								data-toggle="collapse" data-target="#collapseAuction" style="margin: 3px;"
								aria-expanded="false" aria-controls="collapseAuction">
								경매 참여하기</button>
							<button id="btn_buy" class="btn btn-primary" style="margin: 3px;" type="button">
								즉시 구매하기</button>	
						</div>
						<div class="collapse" id="collapseAuction">
							<div class="card card-body">
								<p id="show"></p>
								<c:if test="${empty maxMoney}">
									<p>입찰 시작액 : ${vo.artBasicFee }원</p>
								</c:if>
								<c:if test="${maxMoney >= 0 }">
									<p>현재 최고 입찰액 : ${maxMoney}원</p>
								</c:if>
								<input type="hidden" id="member_id" value="${sessionScope.memberId }"><br> 
								<input type="number" id="auction_money" class="form-control" placeholder="금액 입력"><br>
								<p id="money_check" style="font-size: 80%;"></p>
								<button type="button" id="btn_bid" class="btn btn-outline-primary">입찰하기</button>
							</div>
						</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col">
						<table class="table table-hover table-fixed">
							<thead>
								<tr>
									<th scope="col">닉네임</th>
									<th scope="col">입찰가격</th>
									<th scope="col">입찰시각</th>
								</tr>
							</thead>
							<tbody id="auctionTable">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-7">
				<div class="card bg-light mb-3">
					<div class="card-body">
						<ul class="nav nav-tabs">
							<li class="nav-item"><a class="nav-link active"
								data-toggle="tab" href="#content">작품설명</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#reply">댓글 (${vo.artReplyCount })</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade show active" id="content">
								<br>
								<p>${vo.artContent }</p>
							</div>
							<div class="tab-pane fade" id="reply">
								<br>
								<input type="text" id="art_reply" class="form-control" placeholder="댓글 입력">
								<button type="button" id="btn_reply" class="btn btn-outline-primary">입력</button>
								<hr>
								<div id="">
									댓글목록..~
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<div class="jumbotron text-center mt-5 mb-0">
		<h4>NFT-AUCTION</h4>
		<p>이용약관 고객센터..주소..어쩌구</p>
	</div>
	<!-- hidden -->
	<input type="hidden" id="show_date" value="${vo.artShowDate }">
	<input type="hidden" id="show_img" value="${vo.artFileName }">
	<input type="hidden" id="max_money" value="${maxMoney }">
	<!-- JavaScript -->
	<script type="text/javascript">
		$(function(){
			var art_no = new URLSearchParams(location.search);
			art_no = art_no.get('artNo');
			console.log(art_no);
			imgShow();
			getAllBidsList();
			setInterval(auctionTimer, 1000); //1초마다 timer 반복하기
			
			/* 원본이미지 출력 */
			function imgShow() {
				var show_img=$('#show_img').val();
				show_img=show_img.replace('/s_','/');
				$('#image').html('<img src="/nft-auction/arts/display?fileName='+show_img+'">');
			} //end imgShow()
			
			/* 로그아웃 상태에서 버튼제어 */
			$('#btn_auction').click(function(){
				var session=$('#member_id').val();
				if(!session){
					alert('로그인 해주세요!');
					$('#btn_auction').attr('disabled', 'disabled');
				}
			}); //end btn_auction click
			
			/* 입찰하기 버튼 클릭 */
			$('#btn_bid').click(function(){
				var member_id=$('#member_id').val();
				var auction_money=$('#auction_money').val();
				console.log(member_id+", "+auction_money);
				
				var max_money=$('#max_money').val();
				if(auction_money>max_money){ //현재까지 최고금액보다 높은 값인 경우
					var obj={
							'memberId':member_id,
							'artNo':art_no,
							'auctionMoney':auction_money
						}
						
						$.ajax({
							type:'post',
							url:'auction',
							headers : {
			                	'Content-Type':'application/json',
			                	'X-HTTP-Method-Override':'POST'
			                },
			                data:JSON.stringify(obj),
			                success:function(result, status){
			                	if(result==1){
			                		alert('입찰 참여 완료');
			                	}else{
			                		alert('등록 실패');
			                	}
			                	getAllBidsList();
			                }
						}); //end ajax
				}else{
					$('#money_check').css('color','red');
					$('#money_check').html('최고 입찰액보다 적은 금액은 신청할 수 없습니다.');
				}
				
			}); //end btn_bid click
			
			/* 경매 참가 리스트 */
			function getAllBidsList() {
				var url = 'auction/all/'+art_no;
				$.getJSON(
					url,
					function(jsonData){
						console.log(jsonData);
						var list = '';
												
						$(jsonData).each(function(){
							var auction_date=new Date(this.auctionDate);
							auction_date=formatDate(auction_date);
							
							list+='<tr>'
								+'<th scope="row">'+this.memberNickname+'</th>'
								+'<td>'+AddComma(this.auctionMoney)+'</td>'
								+'<td>'+auction_date+'</td>'
								+'</tr>';	
						}); //end each
						$('#auctionTable').html(list);
					}
				); //end getJSON()
			} //end getAllBidsList()
			
			/* 경매시간 타이머 */
			function auctionTimer() {
				var show_time=$('#show_date').val();
				show_time=new Date(show_time);
				
				var now_time=new Date();
				
				var difference = parseInt(((show_time.getTime() - now_time.getTime()) / 1000) + 0.999);
				if(difference>0){
					var secs = difference % 60; //초
					 
					difference = parseInt(difference / 60);
					var minutes = difference % 60; //분
					 
					difference = parseInt(difference / 60); 
					var hours = difference % 24 //시
					 
					difference = parseInt(difference / 24); 
					var days = difference //일	
					$('#show').html(days+'일 '+hours+'시 '+minutes+'분 '+secs+'초 남았습니다.');
				}else{ //타이머 종료
					clearInterval(auctionTimer);
					$('#btn_auction').attr('disabled', 'disabled');
					$('#btn_auction').text('경매 종료');
					$('#auction_money').attr('disabled', 'disabled');
					$('#btn_bid').attr('disabled', 'disabled');
				}
				
			} //end auctionTimer()
			
			/* 위시리스트 찜하기 */
			
			/* date format */
			const formatDate = (current_datetime)=>{
			    let formatted_date = current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds();
			    return formatted_date;
			} //end formatDate()
			
			/* number format */
			function AddComma(num)
			{
			var regexp = /\B(?=(\d{3})+(?!\d))/g;
			return num.toString().replace(regexp, ',');
			} //end AddComma
		}); //end document	
	</script>
</body>
</html>