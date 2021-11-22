<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 문의하기</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
  <h1>문의하기</h1>
  <form action="qnaregister" method="POST" enctype="multipart/form-data">  
    <div class="input-group mb-3">
      <span class="input-group-text " style="width: 10% ">제목</span>
      <input type="text" name="qnaboardTitle" id="qnaboardTitle" placeholder="제목을 입력하세요" required>
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text" style="width: 10%">작성자</span>
      <input type="text" id="memberNickname" name="memberNickname" value="${mo.memberNickname}" readonly>
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text" style="width: 10%">아이디</span>
      <input type="text" id="memberId" name="memberId" value="${sessionScope.memberId }" readonly>
    </div>
    <div>
      <textarea rows="20" cols="120" name="qnaboardContent" id="qnaboardContent" placeholder="내용을 입력하세요"></textarea>
    </div>
    <div>
      <input type="submit" class="btn btn-primary" value="등록">
      <a href="qnalist"><input type="button" value="글 목록" class="btn btn-primary"></a>
    </div>
  </form>

</body>
</html>