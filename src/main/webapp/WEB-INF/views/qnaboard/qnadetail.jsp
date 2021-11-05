<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>QnA 상세보기</title>
</head>
<body>
  <h1>QnA 게시글 상세보기</h1>
  <div>
    <p>글 번호 : ${vo.qnaboardNo }</p>
  </div>
  <div>
    <p>제목 <input type="text" value="${vo.qnaboardTitle }" readonly>
  </div>
  <div>
    <p>닉네임 : ${vo.memberNickname }</p>
    <p>작성일 : ${vo.qnaboardDate }</p>
  </div>
  <div>
    <textarea rows="20" cols="120" readonly>${vo.qnaboardContent }</textarea>
  </div>
  <div>
  <a href="qnalist"><input type="button" value="글 목록"></a>
   <c:if test="${vo.memberId eq sessionScope.memberId}">
    <a href="qnaupdate?qnaboardNo=${vo.qnaboardNo }"><input type="button" value="글 수정"></a>
    <a href="qnadelete?qnaboardNo=${vo.qnaboardNo }"><input type="button" value="글 삭제"></a>
   </c:if>
 </div>
 <div style="text-align: center">
  <div>
    <input type="text" id="qnaboardNo" value="${vo.qnaboardNo }">
<%--     <c:if test="${not empty sessionScope.nickname }"> --%>
      <input type="text" id="replyNo">
      <input type="text" id="memberNickname">
      <input type="text" id="replyContent">
      <button type="button" id="btn_add">등록</button>
<%--     </c:if> --%>
  </div>
 </div>
 <hr>
 <div style="text-align: left;">
  <div id="qnareplies"></div>
 </div>
 <div>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
 </div>

  
  <script type="text/javascript">
  	$(document).ready(function() {
        var qnaboardNo = $('#qnaboardNo').val();
        console.log("test");
        getAllReplies();
        
        // 댓글 작성 버튼 클릭
        $('#btn_add').click(function() {
           
           var replyContent = $('#replyContent').val();
           var replyParentNo = "0";
           var memberNickname = $('#memberNickname').val();
           var memberId = memberNickname;
           var replyDepth = "0";
           var obj = {
                   'qnaboardNo' : qnaboardNo,
                   'replyParentNo' : replyParentNo,
                   'replyDepth' : replyDepth,
                   'replyContent' : replyContent,
                   'memberNickname' : memberNickname,
                   'memberId' : memberId
           };
           
           var JSONObj = JSON.stringify(obj);
           
           $.ajax({
             type : 'POST',
             url : 'qnareplies/rest',
             headers : { 
                 'Content-Type' : 'application/json',
           	 	 'X-HTTP-Method-Override' : 'POST'
             },
             data : JSONObj,
             success : function(result, status) {
                if(result == 1) {
                    alert('댓글 입력 성공');
                    getAllReplies();
                }
            }
           });
           
        }); // end btn_add()

        // 대댓글 버튼 클릭
        $('#qnareplies').on('click', '.btn_add_reply', function() {
        // $('.btn_add_reply').click(function() {          
         	console.log("대댓글 작성 클릭");
            var replyContent = $('#replyContent2').val();
            var replyParentNo = $('#replyNo2').val();
            var memberNickname = $('#memberNickname2').val();
            var memberId = memberNickname;
            var replyDepth = "1";
            var obj = {
                    'qnaboardNo' : qnaboardNo,
                    'replyParentNo' : replyParentNo,
                    'replyDepth' : replyDepth,
                    'replyContent' : replyContent,
                    'memberNickname' : memberNickname,
                    'memberId' : memberId
            };
            
            var JSONObj = JSON.stringify(obj);
            
            $.ajax({
              type : 'POST',
              url : 'qnareplies/rest',
              headers : { 
                  'Content-Type' : 'application/json',
            	 	 'X-HTTP-Method-Override' : 'POST'
              },
              data : JSONObj,
              success : function(result, status) {
                 if(result == 1) {
                     alert('댓글 입력 성공');
                     getAllReplies();
                 }
             }
            });
            
         }); // end btn_add_reply()
        
       	// 답글 버튼 눌렀을 때 답글 입력창
         $('#qnareplies').on('click', 'div .btn_reply', function() {
            console.log("btn_reply click()");
            var replyNo2 = $(this).closest('.reply_item').find('#replyNo').val();
            // var qnaboardNo2 = $(this).closest('.reply_item').find('#replyNo').val();
            console.log(replyNo2);
           	var inputReplyNo = $('<input type="text" id="replyNo2" value="' + replyNo2 + '"/>'
           	  	    + '<input type="text" id="memberNickname2">'
           	        + '<input type="text" id="replyContent2">'
           	        + '<button type="button" class="btn_add_reply">작성</button>');
            $(this).closest('.reply_item').find('#reply').html(inputReplyNo).toggle();   

            }); // end btn_reply()
   
       	// 전체 댓글 목록 출력
        function getAllReplies() {
            var url = 'qnareplies/rest/all/' + qnaboardNo;
            $.getJSON(
                    url,
            	    function(jsonData) {
                        console.log(jsonData);
                        var replyWriter = $('#memberNickname').val(); // 로그인 한 사용자 닉네임
                        var list = '';
                    
                        
                        $(jsonData).each(function() {
                            console.log(this);

                            var disabled = '';
                            var readonly = '';
                            var replyDate = new Date(this.replyDate);
                            // var replyDate = new Date(this.replyDate);
                            if(replyWriter == this.replyNickname) {
                                disabled = '';
                                readonly = '';
                             }
                            
                            if(this.replyDepth == 0) {
                                var dis='';
                                var rep='';
                                
                            } else {
                                var dis='disabled';
                                var rep='└RE: ';
                            }
                            list += '<div class="reply_item">'
                				+ '<pre>'
                				+ rep
                				+ '<input type="text" id="replyNo" value="' + this.replyNo + '"'+ readonly +'/>'
                				+ '<strong>'+ this.replyNo +'</strong>'
                				+ '<input type="hidden" id="memberId" value="'+ this.memberId +'"' + readonly+'/>'
                				+ '&nbsp;&nbsp;'
                				+ '<input type="hidden" id="memberNickname" value="' + this.memberNickname + '"'+ readonly +'/>'
                				+ this.memberNickname
                				+ '&nbsp;&nbsp;' // 공백
                				+ '<input type="text" id="replyContent" value="' + this.replyContent + '" '+ readonly +'/>'
                				+ '&nbsp;&nbsp;'
               				 	+ this.replyDate 
               					+ '&nbsp;&nbsp;'
               					+ '<button class="btn_update" type="button" '+ disabled +'>수정</button>'
               					+ '<button class="btn_delete" type="button" '+ disabled +'>삭제</button>'
               					+ '<button type="button" class="btn_reply" '+dis+'>답글</button><br>'                					
               					+ '<div id="reply" style="display: none;">'  
               					+ '</div>'
                                + '</pre>'
               					+ '</div>';
 
                       		 });
                        $('#qnareplies').html(list);
                    }    
            );
        } // end getAllReplies()
        

        
        // 댓글 수정 버튼 클릭시
        $('#qnareplies').on('click', '.reply_item .btn_update', function() {
            console.log(this);
            
            var replyNo = $(this).prevAll('#replyNo').val();
            var replyContent = $(this).prevAll('#replyContent').val();
            console.log("선택된 댓글 번호 : " + replyNo + ", 댓글 내용 : " + replyContent);
            
            $.ajax({
               type : 'PUT',
               url : 'qnareplies/rest/' + replyNo,              
               data : JSON.stringify({
                   'replyContent' : replyContent
               }),
               headers : {
               	  'Content-Type' : 'application/json',
               	  'X-HTTP-Method-Override' : 'PUT'
                 },
               success : function(result) {
                   if(result == 'success') {
                       alert('댓글 수정 성공!');
                       getAllReplies();
                   }
               }
            });
        }); // end btn_update()
        
        // 댓글 삭제 버튼 클릭시
        $('#qnareplies').on('click', '.reply_item .btn_delete', function() {
            console.log(this);
            console.log("btn_delete() 클릭");
            var qnaboardNo = $('#qnaboardNo').val();
            var replyNo = $(this).prevAll('#replyNo').val();
            console.log("선택된 댓글 번호 : " + replyNo);
            
            $.ajax({
                type : 'delete',
                url : 'qnareplies/rest/' + replyNo,
                data : JSON.stringify({
                    'replyNo' : replyNo,	
                    'qnaboardNo' : qnaboardNo
                }),
                headers : {
              	  'Content-Type' : 'application/json',
              	  'X-HTTP-Method-Override' : 'DELETE'
                },
                success : function(result) {
                    if(result == 'success') {
                        alert('댓글 삭제 성공!');
                        getAllReplies();
                    }
                }
            }); // end ajax
        }); // end btn_delete()
    }); // end document()
  
  </script>
  
</body>
</html>