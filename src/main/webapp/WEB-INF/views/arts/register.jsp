<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>작품 등록 페이지</title>
<style type="text/css">
.file-drop{
	width: 100;
	height: 100px;
	border: 1px solid grey;
}	
</style>
</head>
<body style="text-align: center;">
	<h1>작품 등록 페이지</h1>
	<form method="post">
		<input type="hidden" name="memberId" value="${sessionScope.memberId }">
		<input type="text" name="artName" placeholder="작품명 입력"><br>
		<input type="number" name="artPrice" placeholder="즉시판매가 입력"><br>
		<input type="text" name="artContent" placeholder="작품설명 입력"><br>
		작품 게시기간 설정<br>
		<input type="datetime-local" name="artShowDate"><br>
		이미지 등록<br>
		<div class="file-drop" style="color:grey;">이미지를 drop해주세요.</div>
		<div class="upload-list"></div>
		<input type="hidden" id="file_name" name="artFileName" value="">
		<input type="submit" value="등록하기">
	</form>
	<!-- JavaScript -->
	<script type="text/javascript">
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
					success:function(fileName){
						console.log(fileName); //서버에 업로드된 파일이름을 가지고옴
						$('.upload-list').html("<img src='/nft-auction/arts/display?fileName="+fileName+"'>");
						$('#file_name').attr('value', fileName);
					}//success
				}); //end ajax
			}); //end drop
		}); //end documnet
	</script>
</body>
</html>