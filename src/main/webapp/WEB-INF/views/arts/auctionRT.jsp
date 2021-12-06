<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실시간 거래 현황</title>
<style type="text/css">
.section {
  background-color: ;
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
	<section class="section bg-light p-3">
			<div id="list">
				<p>경매중인 상품 목록</p>
				<c:forEach items="${RTlist }" var="RTlist">
					<div class="card bg-light" style="width: 13rem;">
						<a href="detail?artNo=${RTlist.artNo}" target="_top"> 
							<img src="https://nftauctionbucket.s3.ap-northeast-2.amazonaws.com/${RTlist.artFileName }"
							class="card-img-top" alt="${RTlist.artName }"></a>
						<div class="card-body">
							<p class="card-text">
								<c:out value="${RTlist.artName }" />
							</p>
							<p class="card-text">
								<c:out value="${RTlist.auctionMoney } 원" />
								<span class="float-right"><a
									href="detail?artNo=${RTlist.artNo}" target="_top">
										<button class="btn btn-primary">이동</button>
								</a> </span>
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
	</section>

	<script type="text/javascript">

  	function reloadTime() {
  	$('#list').load(location.href + ' #list');
  	    setInterval('reloadTime()', 120000);
  	};
  </script>

</body>
</html>