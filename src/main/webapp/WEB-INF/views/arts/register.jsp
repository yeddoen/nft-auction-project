<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- 모바일 디바이스에서 터치/줌 등을 지원하기 위한 meta 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/zephyr/bootstrap.min.css">
<title>작품 등록 페이지</title>
<style type="text/css">
.file-drop {
	width: 100;
	height: 100px;
	border: 1px solid grey;
}
img {
	max-width: 100%;
	height: auto;
}
</style>
</head>
<body style="text-align: center;">
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
					<li class="nav-item"><a class="nav-link" href="register">작품등록</a>
					</li>
					<c:if test="${empty sessionScope.memberId }">
						<li class="nav-item"><a class="nav-link" href="../members/login">로그인</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="../members/sign-up">회원가입</a></li>
					</c:if>
					<c:if test="${not empty sessionScope.memberId }">
						<li class="nav-item"><a class="nav-link" href="../members/logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link"
							href="../members/my-page/member">마이페이지</a></li>
						<li class="nav-itm"><a class="nav-link"> ${sessionScope.memberId } 님, 접속중입니다!</a>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- body -->
	<br>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-sm-8">
				<h2 class="pt-3">작품 등록</h2>
				<hr>
				<div class="p-5">
					<form method="post">
						<input type="hidden" name="memberId" value="${sessionScope.memberId }" id="member_id">
						<input type="text" class="form-control" name="artName" placeholder="작품명 입력" required id="art_name"><br>
						<input type="number" class="form-control" name="artPrice" placeholder="즉시판매가 입력" required><br>
						<input type="text" class="form-control" name="artContent" placeholder="작품설명 입력" required id="art_content"><br>
						작품 게시기간 설정<br>
						<input type="datetime-local" class="form-control" name="artShowDate" required><br>
						<input type="number" class="form-control" name="artBasicFee" placeholder="경매시작금 입력" required><br>
						<div class="file-drop" style="color:grey;">첨부할 이미지를 drop해주세요.</div>
						<div class="upload-list m-5"></div>
						<input type="hidden" id="file_name" name="artFileName" value="" required>
						<input type="hidden" id="memberAccount" name="memberAccount" value="" required>	
						<input type="hidden" id="uri" name="artJsonUri" value="" required>
						<div class="d-grid gap-2 mt-4">
						<input type="submit" class="btn btn-primary rounded-pill" value="등록하기">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<div class="mt-5 p-3 bg-light">
		<div style="bottom: 0; height: 200px;">
			<h4>NFT-AUCTION</h4>
			<hr>
			<br> <a href="../terms/termsOfService">이용약관</a> &nbsp; <a href="../terms/termsOfInformation">사이트안내</a> <br>
			<br> <small>문의 nft.auction.help@gmail.com</small>
		</div>
	</div>
	<!-- JavaScript -->
	<script type="text/javascript">
		/* if (typeof window.klaytn == 'undefined') {
			console.log('kaikas installed!') // 카이카스가 설치된 경우
			const accounts = klaytn.enable();
			const account = accounts[0];
		}
		console.log('현재 네트워크 : ' + klaytn.networkVersion);
		console.log('현재 지갑의 주소 : ' + klaytn.selectedAddress);
	 */
		$(function(){
			$('.file-drop').on('dragenter dragover', function(event){
				//drop 이벤트의 기본 동작을 막음
				event.preventDefault();
			}); //end dragenter dragover
			
			$('.file-drop').on('drop', function(event){
				event.preventDefault();
				
				/*ajax를 이용해 서버로 파일업로드*/
				var formData=new FormData();
				var files=event.originalEvent.dataTransfer.files; //드래그한 file 정보
				var i=0;
				
				for(i=0;i<files.length; i++){
					console.log(files[i]);
					//서버로 보낼 form-data 작성
					formData.append("files", files[i]);					
				}
				
				$.ajax({
					type:'post',
					url:'upload-ajax',
					data:formData,
					processData:false,
					contentType:false,
					success: function(fileName){            			
                  			// metadata api로 json파일 서버에 올리기
                  			var artName = $('#art_name').val();
                  			var description = $('#art_content').val();
                  			var image = "https://nftauctionbucket.s3.ap-northeast-2.amazonaws.com/" + fileName;
                  			var minter = $('#member_id').val();
                  			
                  			const settings = {
                  			        "async": true,
                  			        "crossDomain": true,
                  			        "url": "https://metadata-api.klaytnapi.com/v1/metadata",
                  			        "method": "POST",
                  			        "headers": {
                  			          "Content-Type": "application/json",
                  			          "x-chain-id": "1001",
                  			          "Authorization": "Basic S0FTS0VNTkMxRDg4UTdHSDFUTlZMWkhSOkhPa3lvbEpnbnFlaGhrNDRGOWVjSWNiSENONm0tSEJrLUFSV01PWXQ="
                  			        },
                  			        "processData": false,
                  			        "data": "{\n  \"metadata\": {\n    \"name\": \"" + artName + "\"," 
                  			            + "\n    \"description\": \"" + description + "\"," 
                  			            + "\n    \"image\": \"" + image + "\"," 
                  			            + "\n   \"minter\": \"" + minter + "\""  
                  			            + "}\n}"
                  			};

                  			$.ajax(settings).done(function (response) {
                  			  	console.log("metadata api 응답 : "  + JSON.stringify(response));
                  			   	console.log("metadata의 uri : " + typeof(response['uri']));
                  			   	// 이거 DB에 넣어버리고 서버단으로 전송하기!
                  			   	$('#uri').attr('value', response['uri']);    
                  			});
                  			
             			 
					    
						console.log(fileName); //서버에 업로드된 파일이름을 가지고옴
						
						if(fileName=='fail'){
							alert('jpg, png, gif 파일만 등록할 수 있습니다.');
						}else{
							$('.upload-list').html("<img src='https://nftauctionbucket.s3.ap-northeast-2.amazonaws.com/"+fileName+"'>");
							$('#file_name').attr('value', fileName);							
						}
					}//success
				}); //end ajax
			}); //end drop
			
			// 제출을 할때 정보들이 db에도 들어가고, json형식으로 서버에 올라감.
			
		}); //end documnet
	</script>
</body>
</html>