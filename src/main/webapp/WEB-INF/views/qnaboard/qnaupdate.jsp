<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 문의하기</title>
</head>
<body>
  <h1>수정 Page</h1>
  <form action="qnaupdate" method="POST">
    <div>
      <input type="hidden" name="qnaboardNo" value="${vo.qnaboardNo }">
      <p>제목 : <input type="text" name="qnaboardTitle" value="${vo.qnaboardTitle }" ></p>
    </div>
    <div>
      <p>작성자 : ${vo.memberNickname }</p>
    </div>
    <div>
      <textarea rows="20" cols="120" name="qnaboardContent" value="${vo.qnaboardContent }" placeholder="내용을 입력하세요"></textarea>
    </div>
    <div>
      <input type="submit" value="수정">
    </div>
  </form>
  

</body>
</html>