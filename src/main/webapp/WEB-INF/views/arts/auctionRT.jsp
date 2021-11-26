<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실시간 거래 현황</title>
<style type="text/css">
.aside {
  width: 30%;
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
/* 사이드바 스타일 */
.sidebar-nav {
  width: 250px;
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

.section {
  width: 70%;
  align: right;
}
</style>
<meta http-equiv="refresh" content="30">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <!-- header -->
  <nav class="navbar navbar-expand-lg navbar-light bg-warning sticky-top">
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
        </c:if>
      </ul>
    </div>
  </nav>
 
  <section class="section m-3">
    <div id="page-wrapper">
      <div id="page-content-wrapper">
 <div id="list">
 <h1>현재 거래중인 상품 목록</h1>
 <table class="table">
    <thead>
      <tr>
        <!-- <th scope="col">경매 번호</th> -->
        <th scope="col">작품 번호</th>
        <th scope="col">작품 썸네일</th>
        <th scope="col">작품 이름</th>
        <th scope="col">현재 거래 가격(시작 가격)</th>
        <th scope="col">작가 닉네임</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${RTlist }" var="RTlist">
        <tr>
          <th scope="row"><c:out value="${RTlist.artNo }" /></th>      
            <td><a href="detail?artNo=${RTlist.artNo}">
              <img src="/nft-auction/arts/display?fileName=${RTlist.artFileName }" style="width: auto; height: 200px;"></td>
            <td><c:out value="${RTlist.artName }" /></td>
            <td><c:out value="${RTlist.auctionMoney }(${RTlist.artBasicFee })" /></td>
            <td><c:out value="${RTlist.memberNickname }" /></td>
          </tr>
        </c:forEach>
 
      </tbody>
    </table>
  </div>
  
    <div id="list2">
    <h1>거래가 진행되지 않은 작품 목록</h1>
    <table class="table">
  <thead>
    <tr>

      <th scope="col">작품 번호</th>
      <th scope="col">작품 썸네일</th>
      <th scope="col">작품 이름</th>
      <th scope="col">경매 시작 가격</th>
      <th scope="col">작가 닉네임</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${notAuction }" var="notAuction">
    <tr>
      <th scope="row"><c:out value="${notAuction.artNo }" /></th>
      <td><a href="detail?artNo=${notAuction.artNo}">
          <img src="/nft-auction/arts/display?fileName=${notAuction.artFileName }" style="width: auto; height: 200px;"></td>
      <td><c:out value="${notAuction.artName }" /></td>
      <td><c:out value="${notAuction.artBasicFee }" /></td>
      <td><c:out value="${notAuction.memberNickname }" /></td>
    </tr>
    </c:forEach>
 
  </tbody>
</table>
</div>
      </div>
    </div>
  </section>
  
  <script type="text/javascript">

  	/* function reloadTime() {
  	$('#list').load(location.href + ' #list');
  	    setInterval('reloadTime()', 120000);
  	}; */
  </script>

</body>
</html>