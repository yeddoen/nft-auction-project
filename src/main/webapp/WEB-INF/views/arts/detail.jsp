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

tr {
width: 100%;
display: inline-table;
height:60px;
table-layout: fixed;
  
}

table{
  max-width: 100%;
  height: auto;
  table-layout: fixed;
 display: -moz-groupbox;
}
tbody{
  overflow-y: scroll;
  height: 300px;
  width: 100%;
  position: absolute;
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
						<table class="table table-hover w-auto">
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
		<!-- 작품설명 & 댓글 -->
		<div class="row">
			<div class="col-sm-7">
				<div class="card bg-light mb-3">
					<div class="card-body">
						<ul class="nav nav-tabs">
							<li class="nav-item"><a class="nav-link active"
								data-toggle="tab" href="#content">작품설명</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#art_reply">댓글 (${vo.artReplyCount })</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade show active" id="content">
								<br>
								<p>${vo.artContent }</p>
								<c:if test="${not empty sessionScope.memberId }">
									<div style="text-align: right;">
										<a href="update?artNo=${vo.artNo }"><button type="button" class="btn btn-primary">수정</button></a>
										<a href="delete?artNo=${vo.artNo }"><button type="button" class="btn btn-primary">삭제</button></a>
									</div>
								</c:if>
							</div>
							<div class="tab-pane fade" id="art_reply">
								<br>
								<div class="input-group mb-3">
									<c:if test="${empty sessionScope.memberId }">
										<p>로그인한 회원만 댓글 작성이 가능합니다. <a href="../members/login">로그인하기</a></p>
									</c:if>
							    	<c:if test="${not empty sessionScope.memberId }">
							     		<input type="hidden" id="memberReplyNo" readonly>
							     		<input type="text" id="memberReplyId" value="${vo.memberId }">
									    <input type="text" id="memberReplyNickname" value="${vo.memberNickname }" readonly>
							     		<input type="text" id="artReplyContent" class="form-control" placeholder="댓글 내용을 입력하세요">
							     		<button type="button" id="btn_add" class="btn btn-outline-primary">등록</button>
							     	</c:if>
								</div>
								<hr>								
								<div id="replies">
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
	<input type="hidden" id="basic_money" value="${vo.artBasicFee }">
	<input type="hidden" id="creator" value="${vo.memberId }">
	<!-- JavaScript -->
	<script type="text/javascript">
		$(function(){
			var art_no = new URLSearchParams(location.search);
			art_no = art_no.get('artNo');
			console.log(art_no);
			imgShow();
			getAllBidsList();
			getAllReplies();
			
			var timer=setInterval(auctionTimer, 1000); //1초마다 timer 반복하기							
			
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
				auction_money=parseInt(auction_money);
				console.log(member_id+", "+auction_money);
				
				var max_money=$('#max_money').val();
				max_money=parseInt(max_money);
		
				var basic_money=$('#basic_money').val();
				basic_money=parseInt(basic_money);
				
				var money=basic_money;
				
				if(max_money){ //입력값이 존재한다면
					money=max_money;
				}
		
				if(auction_money>money){ //현재까지 금액보다 높은 값인 경우만 입력
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
 					clearInterval(timer);
					$('#btn_auction').attr('disabled', 'disabled');
					$('#btn_auction').text('경매 종료');
					$('#auction_money').attr('disabled', 'disabled');
					$('#btn_bid').attr('disabled', 'disabled'); 
					
					//21.11.15
					/*경매 종료되면 maxmoney를 입력한 사람의 result T로 수정 */
					auctionEndWinner();	
				}
				
			} //end auctionTimer()
			
			//21.11.17
			/* 경매 종료 낙찰자 채택 */
			function auctionEndWinner() {
				
				var max_money=$('#max_money').val();
				var member_id=$('#member_id').val();
				var creator=$('#creator').val();
				console.log(max_money+', '+member_id+', '+creator);
				
				if(!max_money){ 
					//입찰자가 한명도 없는 경우 게시기간 갱신 필요
					if(member_id==creator){
						alert('작품 게시기간을 갱신해주세요!');
						location.href='update?artNo='+art_no;
						return;
					}
				}else{
					$.ajax({
						type:'PUT',
						url:'auction/'+art_no, 
						headers : {
			                  'Content-Type' : 'application/json',
			                  'X-HTTP-Method-Override' : 'PUT'
			            },
			            data:JSON.stringify({
			            	'maxMoney':max_money
			            }),
			            success:function(result, status){
			            	console.log(result);
			            	if(result==member_id){
			            		//낙찰자에게 결제하라는 모달띄우기
			            		//낙찰자 아이디를 반환
			            		var pay=confirm(result+'님, 낙찰되었습니다. 지금 결제하시겠습니까?');
			            		if(pay){
			            			location.href='purchase'; //결제페이지
			            		}
			            	}
			            } //end success
						
					}); //end ajax
				}
			} //end auctionEndWinner()
			
			/* 댓글 입력 */
	        $('#btn_add').click(function() {
	           
	           var artReplyContent = $('#artReplyContent').val();
	           var artReplyParentNo = "0";
	           var memberReplyNickname = $('#memberReplyNickname').val();
	           var memberReplyId = $('#memberReplyId').val();
	           var obj = {
	                   'artNo' : art_no,
	                   'artReplyParentNo' : artReplyParentNo,
	                   'artReplyContent' : artReplyContent,
	                   'memberNickname' : memberReplyNickname,
	                   'memberId' : memberReplyId
	           };
	           
	           var JSONObj = JSON.stringify(obj);
	           
	           $.ajax({
	             type : 'POST',
	             url : 'replies/rest',
	             headers : { 
	                 'Content-Type' : 'application/json',
	               'X-HTTP-Method-Override' : 'POST'
	             },
	             data : JSONObj,
	             success : function(result, status) {
	                if(result == 1) {
	                    alert('댓글 입력 성공');
	                    getAllReplies();
	                }
	            }
	           });
	           
	        }); // end btn_add()	
	        
	        /* 답글 입력창 생성 */
         	$('#replies').on('click', 'div .btn_reply', function() {
            console.log("btn_reply click()");
            var replyAddNo = $(this).closest('.reply_item').find('#artReplyNo').val();
            var memberReplyAddNickname = $('#memberReplyNickname').val();
            // var qnaboardNo2 = $(this).closest('.reply_item').find('#artReplyNo').val();
            // console.log(artReplyNo2);
             
            var inputAddReply = $('<input type="hidden" id="replyAddNo" value="'+parseInt(replyAddNo)+'">'
                    + '<input type="text" id="memberAddId" value=" ${vo.memberId} " + readonly>'
                    + '<input type="text" id="memberReplyAddNickname" value="${vo.memberNickname }" readonly>'
                    + '<input type="text" id="replyAddContent" placeholder="댓글 내용을 입력하세요">'
                    + '<button type="button" class="btn_add_reply">작성</button>');
            $(this).closest('.reply_item').find('#reply').html(inputAddReply).toggle();   

            }); // end btn_reply()	        
	        
	        /* 답글 입력 */
	        $('#replies').on('click', '.btn_add_reply', function() {
	        // $('.btn_add_reply').click(function() {          
	          console.log("대댓글 작성 클릭");
	            var replyAddContent = $('#replyAddContent').val();
	            var artReplyParentNo = $('#replyAddNo').val();
	            var memberNickname = $('#memberReplyAddNickname').val();
	            var memberAddId = $('#memberAddId').val();
	            var obj = {
	                    'artNo' : art_no,
	                    'artReplyParentNo' : artReplyParentNo,
	                    'artReplyContent' : replyAddContent,
	                    'memberNickname' : memberNickname,
	                    'memberId' : memberAddId
	            };
	            
	            var JSONObj = JSON.stringify(obj);
	            
	            $.ajax({
	              type : 'POST',
	              url : 'replies/rest',
	              headers : { 
	                  'Content-Type' : 'application/json',
	                 'X-HTTP-Method-Override' : 'POST'
	              },
	              data : JSONObj,
	              success : function(result, status) {
	                 if(result == 1) {
	                     alert('답글 입력 성공');
	                     getAllReplies();
	                }
	            }
	           });
	            
	        }); // end btn_add_reply()
				         
	        /* 댓글 목록 */ 
	        function getAllReplies() {
	            var url = 'replies/rest/all/' + art_no;
	            $.getJSON(
	                    url,
	                  function(jsonData) {
	                  	console.log(jsonData);
	                    var replyWriter = $('#memberReplyNickname').val(); // 로그인 한 사용자 닉네임
	                    var list = '';
						console.log(replyWriter);
	                        
						$(jsonData).each(function() {
							console.log(this);
	
							// var hidden = 'hidden="hidden"';
							var disabled = 'disabled';
							var readonly = 'readonly';
	                            
							function getFormatDate(date){
								var year = date.getFullYear();              
								var month = (1 + date.getMonth());         
								month = month >= 10 ? month : '0' + month;  
								var day = date.getDate();                   
								day = day >= 10 ? day : '0' + day; 
								var hour = date.getHours();
								var minutes = date.getMinutes();
								return  year + '-' + month + '-' + day + ' ' + hour + ':' + minutes;      
							} //end getFormatDate()
							
							var artReplyDate = new Date(this.artReplyDate);
							artReplyDate = getFormatDate(artReplyDate);
	                                                       
							if(replyWriter == this.memberNickname) {
								disabled = '';
								readonly = '';
							} 
							if(replyWriter == this.memberReplyAddNickname) {
								hidden = '';
								readonly = '';
							}
	                            
							if(this.artReplyParentNo == 0) {
								var dis='';
								var rep='';
							} else {
								var dis='disabled';
								var rep='└RE: ';
							}
							
							list += '<div class="reply_item">'
								+ '<pre>'
		                        + rep
		                        + '<input type="hidden" id="artReplyNo" value="' + this.artReplyNo + '"/>'
		                        + '<input type="hidden" id="memberId" value="'+ this.memberId + '"/>'
		                        + '&nbsp;&nbsp;'
		                        + '<input type="hidden" id="memberReplyNickname" value="' + this.memberNickname + '"/>'
		                        + this.memberNickname
		                        + '&nbsp;&nbsp;' // 공백
		                        + '<input type="text" id="artReplyContent" value="' + this.artReplyContent + '" '+ readonly +'/>'
		                        + '&nbsp;&nbsp;'
		                        + artReplyDate
		                        + '&nbsp;&nbsp;'
		                        + '<button class="btn_update" type="button" '+ disabled +'>수정</button>'
		                        + '<button class="btn_delete" type="button" '+ disabled +'>삭제</button>'
		                        + '<button type="button" class="btn_reply" '+ dis +'>답글</button><br>'                         
		                        + '<div id="reply" style="display: none;">'  
		                        + '</div>'
		                        + '</pre>'
		                        + '</div>';
	 
							});
						$('#replies').html(list);
					}    
	            );
	        } // end getAllReplies()
	        
			/* 댓글 수정 */
	        $('#replies').on('click', '.reply_item .btn_update', function() {
	            console.log(this);
	            
	            var artReplyNo = $(this).prevAll('#artReplyNo').val();
	            var artReplyContent = $(this).prevAll('#artReplyContent').val();
	            console.log("선택된 댓글 번호 : " + artReplyNo + ", 댓글 내용 : " + artReplyContent);
	            
	            $.ajax({
	               type : 'PUT',
	               url : 'replies/rest/' + artReplyNo,              
	               data : JSON.stringify({
	                   'artReplyContent' : artReplyContent
	               }),
	               headers : {
	                  'Content-Type' : 'application/json',
	                  'X-HTTP-Method-Override' : 'PUT'
	                 },
	               success : function(result) {
	                   if(result == 'success') {
	                       alert('댓글 수정 성공!');
	                       getAllReplies();
	                   }
	               }
	            });
	        }); // end btn_update()
			
			/* 댓글 삭제 */
	        $('#replies').on('click', '.reply_item .btn_delete', function() {
	            console.log(this);
	            console.log("btn_delete() 클릭");
	            var artReplyNo = $(this).prevAll('#artReplyNo').val();
	            console.log("선택된 댓글 번호 : " + artReplyNo);
	            
	            $.ajax({
	                type : 'delete',
	                url : 'replies/rest/' + artReplyNo,
	                data : JSON.stringify({
	                    'artReplyNo' : artReplyNo,  
	                    'artNo' : art_no
	                }),
	                headers : {
	                  'Content-Type' : 'application/json',
	                  'X-HTTP-Method-Override' : 'DELETE'
	                },
	                success : function(result) {
	                    if(result == 'success') {
	                        alert('댓글 삭제 성공!');
	                        getAllReplies();
	                    }
	                }
	            }); // end ajax
	        }); // end btn_delete()
			
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