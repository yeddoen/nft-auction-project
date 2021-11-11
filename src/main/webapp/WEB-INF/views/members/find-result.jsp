<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과 페이지</title>
</head>
<body>
	<p>입력한 정보와 일치하는 아이디 결과입니다.</p>
	<c:forEach var="memberId" items="${list }">
		<p>아이디 : ${memberId }</p>
	</c:forEach>
</body>
</html>