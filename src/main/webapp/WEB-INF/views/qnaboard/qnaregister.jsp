<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 문의하기</title>
</head>
<body>
  <h1>문의하기</h1>
  <form action="qnaregister" method="POST">  
    <div>
      <p>제목 : <input type="text" name="qnaboardTitle" id="qnaboardTitle" placeholder="제목을 입력하세요" required>
    </div>
    <div>
      <p>작성자 : <input type="text" id="memberNickname" name="memberNickname" value="${sessionScope.memberNickname}" readonly></p>
      <p>아이디 : <input type="text" id="memberId" name="memberId" value="${sessionScope.memberId }" readonly></p>
    </div>
    <div>
      <textarea rows="20" cols="120" name="qnaboardContent" id="qnaboardContent" placeholder="내용을 입력하세요"></textarea>
    </div>
    <div>
      <input type="submit" value="등록">
    </div>
  </form>

</body>
</html>