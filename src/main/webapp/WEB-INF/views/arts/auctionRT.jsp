<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실시간 거래 현황</title>
<meta http-equiv="refresh" content="30">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

  <div id="list">
    <p>현재 거래중인 상품 목록</p>
    <table class="table">
  <thead>
    <tr>
      <th scope="col">경매 번호</th>
      <th scope="col">작품 번호</th>
      <th scope="col">작품 썸네일</th>
      <th scope="col">작품 이름</th>
      <th scope="col">현재 거래 가격(시작 가격)</th>
      <th scope="col">작가 닉네임</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${RTlist }" var="RTlist" varStatus="status">
    <tr>
      <th scope="row"><c:out value="${RTlist.auctionNo }" /></th>
      <td><c:out value="${RTlist.artNo }" /></td>
      <td><img src="/nft-auction/arts/display?fileName=${artList[status.index].artFileName }"></td>
      <td><c:out value="${artList[status.index].artName }" /></td>
      <td><c:out value="${RTlist.auctionMoney }" /></td>
      <td><c:out value="${RTlist.memberNickname }" /></td>
    </tr>
    </c:forEach>
 
  </tbody>
</table>
  </div>
  
  <script type="text/javascript">
  	/* setTimeout(function() {
        location.reload();
    }, 60000); */
  	function reloadTime() {
  	$('#list').load(location.href + '#list');
  	    setInterval('reloadTime()', 5000);
  	};
  </script>

</body>
</html>