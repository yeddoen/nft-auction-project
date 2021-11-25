<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>결제 결과</title>
</head>
<body>
  <br>
  <h4>결제가 완료되었습니다.</h4><br>
  <div align="center">
    <div>
      <ul class="list-group list-group-horizontal justify-content-center" >
       <li class="list-group-item list-group-item-primary" style="width: 10rem">주문 번호</li>
       <li class="list-group-item" style="width: 30rem">${vo.merchantUid }</li>
      </ul>
      <ul class="list-group list-group-horizontal justify-content-center" >
       <li class="list-group-item list-group-item-primary" style="width: 10rem">아이디</li>
       <li class="list-group-item" style="width: 30rem">${vo.memberId }</li>
      </ul>
      <ul class="list-group list-group-horizontal justify-content-center">
       <li class="list-group-item list-group-item-primary" style="width: 10rem">작품 가격</li>
       <li class="list-group-item" style="width: 30rem">${vo.artPrice }</li>
      </ul>
      <ul class="list-group list-group-horizontal justify-content-center">
        <li class="list-group-item list-group-item-primary" style="width: 10rem">작품 이름</li>
        <li class="list-group-item" style="width: 30rem">${vo.artName }</li>
      </ul>
      <ul class="list-group list-group-horizontal justify-content-center">
        <li class="list-group-item list-group-item-primary" style="width: 10rem">작품 번호</li>
        <li class="list-group-item" style="width: 30rem">${vo.artNo }</li>
      </ul>
      <ul class="list-group list-group-horizontal justify-content-center">
        <li class="list-group-item list-group-item-primary" style="width: 10rem">파일 이름</li>
        <li class="list-group-item" style="width: 30rem">${vo.artFileName }</li>
      </ul>
    </div>    
    <br>
      <button type="button" class="btn btn-primary" onclick= "myPage()">마이페이지</button>
      <button type="button" class="btn btn-primary" onclick= "main()">메인</button>
      <button type="button" class="btn btn-primary" onclick="window.close()">창닫기</button>
  </div>
  
  <script type="text/javascript">

	function myPage() {
	    opener.parent.location='../members/my-page/member'; 
    	window.close(); 
    }  			    
  
    function main() {
        opener.parent.location='../main'; 
        window.close(); 
    } 
      
  </script>
  
</body>
</html>