<%@page import="project.spring.nft.domain.QnABoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>QnA 목록 페이지</title>
</head>
<body>
  <h1>QnA List</h1>
  <hr>
  <c:if test="${empty sessionScope.memberId }">
    <button type="button" id="btn_login">로그인</button>
  </c:if>
  
  <c:if test="${not empty sessionScope.memberId }">
    <button type="button" id="btn_logout">로그아웃</button>
  </c:if>
  <a href="qnaregister">문의하기</a>
  
  <div align="center">
    <table>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
      </tr>
      <c:forEach items="${qnalist }" var="qnalist">
        <tr>
          <td><c:out value="${qnalist.qnaboardNo }" /></td>
          <td><a href="qnadetail?qnaboardNo=${qnalist.qnaboardNo }"><c:out value="${qnalist.qnaboardTitle }" /></td>
          <td><c:out value="${qnalist.memberNickname }" /></td>
          <td><c:out value="${qnalist.qnaboardDate }" /></td>
        </tr>
      </c:forEach>
      
    </table>  
  </div>
  
   <hr>
  <ul>
    <c:if test="${pageMaker.hasPrev }">
      <li><a href="qnalist?page=${pageMaker.startPageNo - 1 }">이전</a></li>
    </c:if>
    <c:forEach begin="${pageMaker.startPageNo }" 
    end="${pageMaker.endPageNo }" var="num">
      <li><a href="qnalist?page=${num }">${num }</a></li>   
    </c:forEach>
    <c:if test="${pageMaker.hasNext }">
      <li><a href="qnalist?page=${pageMaker.endPageNo + 1 }">다음</a></li>
    </c:if>

  </ul>
  
  <script type="text/javascript">
    $(document).ready(function() {
        // 로그인 버튼 클릭
        $('#btn_login').click(function() {
            var targetURL = encodeURI('/nft-auction-project/member/login');
            location = targetURL;
        }); // end btn_login.click()
        
        // 로그아웃 버튼 클릭
        $('#btn_logout').click(function() {
            location = '../member/login';
        }); // end btn_logout.click()
    }); // end document()
  </script>

</body>
</html>