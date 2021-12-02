<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.margin {
  margin : 20px 20px 20px 20px;
}
</style>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- 모바일 디바이스에서 터치/줌 등을 지원하기 위한 meta 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/zephyr/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<title>Insert title here</title>
</head>
<body>
  <br>
  <div class="margin">
  <p class="h2">작품 신고하기</p>
  <br>
  <p><strong>※ 신고하시는 이유를 선택해 주세요.</strong></p>
  <form action="declare" method="post">
  <div class="btn-group-vertical">
    <label class="form-check-label">
      <input type="radio" class="form-check-input" name="declareContent" value="저작권 위반">
      창작물에 대한 저작권 위반
    </label>
    <label class="form-check-label">
      <input type="radio" class="form-check-input" name="declareContent" value="욕설">
      비방/비하, 명예훼손, 사생활 침해
    </label>
    <label class="form-check-label">
      <input type="radio" class="form-check-input" name="declareContent" value="음란">
      부적절한 홍보, 음란/청소년에게 부적합한 내용, 악성코드
    </label>
    <label class="form-check-label">
      <input type="radio" class="form-check-input" name="declareContent" value="기타">
      기타(이유를 작성해주세요)
      <input type="text" placeholder="내용을 입력하세요" name="declareContent">
    </label>
  </div>
 
 <input type="hidden" name="memberEmail" value="${memberEmail }">
 <input type="hidden" name="memberId" value="${memberId }">
 <input type="hidden" name="artNo" value="${artNo }">
 <input type="hidden" id="email_result" value="${emailResult }"><br><br>
 <div align="center">
 <input type="submit" class="btn btn-warning" value="신고" onclick="confirmEmailResult()">
 <button type="button" class="btn btn-warning" onclick="window.close()">창닫기</button>
 </div>
 </form>
</div>
  <script type="text/javascript">
	confirmEmailResult();
	
  	function confirmEmailResult() {
      var result = $('#email_result').val();
      console.log(result);
      if (result == 'fail') {
          alert('신고가 접수되었습니다.');
      } else if (result == 'success') {
          alert('신고 접수 실패');
      }
  } // end confirmEmailResult()
  </script>
 
</body>
</html>