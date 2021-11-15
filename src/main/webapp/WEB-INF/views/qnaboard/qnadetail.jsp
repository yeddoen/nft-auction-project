<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>QnA 상세보기</title>
</head>
<body>
  <h1>QnA 게시글 상세보기</h1>
  <!-- 로그인 아이디 : memberId, memberNickname
       게시글 작성자 아이디 : memberBoardId, memberBoardNickname
       댓글 작성자 아이디 : memberReplyId, memberReplyNickname 
       대댓글 작성자 아이디 : memberAddReplyId, memberAddReplyNickname -->
  <div>
    <p>글 번호 : ${vo.qnaboardNo }</p>
  </div>
  <div>
    <p>제목 <input type="text" value="${vo.qnaboardTitle }" readonly>
  </div>
  <div>
    <p>닉네임 : ${vo.memberNickname }</p> <!-- 게시글 작성자 닉네임 -->
    <c:set var="qnaboardDate"><fmt:formatDate value="${vo.qnaboardDate }" pattern="YYYY-MM-DD hh:mm"/></c:set>
    <p>작성일 : ${qnaboardDate }</p>
  </div>
  <div>
    <textarea rows="20" cols="120" readonly>${vo.qnaboardContent }</textarea>
  </div>
  <div>
  <a href="qnalist"><input type="button" value="글 목록"></a>
   <c:if test="${vo.memberId eq sessionScope.memberId}"> 
   <!-- 게시글 작성자와 로그인한 아이디가 같으면 글 수정, 삭제 가능 -->
    <a href="qnaupdate?qnaboardNo=${vo.qnaboardNo }"><input type="button" value="글 수정"></a>
    <a href="qnadelete?qnaboardNo=${vo.qnaboardNo }"><input type="button" value="글 삭제"></a>
   </c:if>
 </div>
 <div style="text-align: center">
  <div>
    <input type="hidden" id="qnaboardNo" value="${vo.qnaboardNo }">
     <c:if test="${not empty sessionScope.memberId }">
      <input type="hidden" id="memberReplyNo" readonly>
      <input type="text" id="memberReplyId" value="${mo.memberId }">
      <input type="text" id="memberReplyNickname" value="${mo.memberNickname }" readonly>
      <input type="text" id="replyContent" placeholder="댓글 내용을 입력하세요">
      <button type="button" id="btn_add">등록</button>
     </c:if>
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
           var memberReplyNickname = $('#memberReplyNickname').val();
           var memberReplyId = $('#memberReplyId').val();
           var replyDepth = "0";
           var obj = {
                   'qnaboardNo' : qnaboardNo,
                   'replyParentNo' : replyParentNo,
                   'replyDepth' : replyDepth,
                   'replyContent' : replyContent,
                   'memberNickname' : memberReplyNickname,
                   'memberId' : memberReplyId
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
            var replyAddContent = $('#replyAddContent').val();
            var replyParentNo = $('#replyAddNo').val();
            var memberNickname = $('#memberReplyAddNickname').val();
            var memberAddId = $('#memberAddId').val();
            var replyDepth = "1";
            var obj = {
                    'qnaboardNo' : qnaboardNo,
                    'replyParentNo' : replyParentNo,
                    'replyDepth' : replyDepth,
                    'replyContent' : replyAddContent,
                    'memberNickname' : memberNickname,
                    'memberId' : memberAddId
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
            var replyAddNo = $(this).closest('.reply_item').find('#replyNo').val();
            var memberReplyAddNickname = $('#memberReplyNickname').val();
            // var qnaboardNo2 = $(this).closest('.reply_item').find('#replyNo').val();
            // console.log(replyNo2);
             
            var inputAddReply = $('<input type="hidden" id="replyAddNo" value="'+parseInt(replyAddNo)+'">'
                    + '<input type="text" id="memberAddId" value=" ${mo.memberId} " + readonly>'
                    + '<input type="text" id="memberReplyAddNickname" value="${mo.memberNickname }" readonly>'
                    + '<input type="text" id="replyAddContent" placeholder="댓글 내용을 입력하세요">'
                    + '<button type="button" class="btn_add_reply">작성</button>');
            $(this).closest('.reply_item').find('#reply').html(inputAddReply).toggle();   

            }); // end btn_reply()
   
        // 전체 댓글 목록 출력
        function getAllReplies() {
            var url = 'qnareplies/rest/all/' + qnaboardNo;
            $.getJSON(
                    url,
                  function(jsonData) {
                        console.log(jsonData);
                        var replyWriter = $('#memberReplyNickname').val(); // 로그인 한 사용자 닉네임
                        var list = '';
                      console.log(replyWriter);
                        
                        $(jsonData).each(function() {
                            console.log(this);

                            // var hidden = 'hidden="hidden"';
                            var disabled = 'disabled';
                            var readonly = 'readonly';
                            
                            
                            function getFormatDate(date){
                                var year = date.getFullYear();              
                                var month = (1 + date.getMonth());         
                                month = month >= 10 ? month : '0' + month;  
                                var day = date.getDate();                   
                                day = day >= 10 ? day : '0' + day; 
                                var hour = date.getHours();
                                var minutes = date.getMinutes();
                                return  year + '-' + month + '-' + day + ' ' + hour + ':' + minutes;      
                            }
                            var replyDate = new Date(this.replyDate);
                            replyDate = getFormatDate(replyDate);
                                                       
                            if(replyWriter == this.memberNickname) {
                                disabled = '';
                                readonly = '';
                             } if(replyWriter == this.memberReplyAddNickname) {
                                 hidden = '';
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
                        + '<input type="hidden" id="replyNo" value="' + this.replyNo + '"/>'
                        + '<input type="hidden" id="memberId" value="'+ this.memberId + '"/>'
                        + '&nbsp;&nbsp;'
                        + '<input type="hidden" id="memberReplyNickname" value="' + this.memberNickname + '"/>'
                        + this.memberNickname
                        + '&nbsp;&nbsp;' // 공백
                        + '<input type="text" id="replyContent" value="' + this.replyContent + '" '+ readonly +'/>'
                        + '&nbsp;&nbsp;'
                        + replyDate
                        + '&nbsp;&nbsp;'
                        + '<button class="btn_update" type="button" '+ disabled +'>수정</button>'
                        + '<button class="btn_delete" type="button" '+ disabled +'>삭제</button>'
                        + '<button type="button" class="btn_reply" '+ dis +'>답글</button><br>'                         
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