<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<style type="text/css">>
.div1 {
  text-align: left;
  width: 400px;
  height: auto;
  margin: 20px;
  border-style: solid;
  border-color: gray;
  border-width: 1px;
}

/* 사이드바 래퍼 스타일 */
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

.aside {
  width: 30%;
  align: left;
}

</style>
<title>QnA 상세보기</title>
</head>
<body>
   <!-- header -->
	<nav class="navbar navbar-expand-lg navbar-light bg-warning sticky-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="../main">NFT-AUCTION</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="../arts/register">작품등록</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="../members/logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link"
						href="../members/my-page/member">마이페이지</a></li>
				</ul>
			</div>
		</div>
	</nav>
  <aside class="aside">
    <div id="page-wrapper">
      <!-- 사이드바 -->
      <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
          <li class="sidebar-brand"><a href="../members/my-page/member">마이페이지</a></li>
          <li><a href="../members/my-page/member">회원정보</a></li>
          <li><a href="../wishlist/wishpage">위시리스트</a></li>
          <li><a href="../members/my-page/artlist">등록작품내역</a></li>
          <li><a href="../members/my-page/shopping-list">구매작품내역</a></li>
          <li><a href="../qnaboard/qnalist">QnA게시판</a></li>
        </ul>
      </div>
      <!-- /사이드바 -->
    </div>
  </aside>
  <section class="section">
    <div id="page-wrapper">
      <div id="page-content-wrapper">
      <br>
        <p class="h1">${vo.qnaboardTitle }</p>
        <hr>
      </div>
      <!-- 로그인 아이디 : memberId, memberNickname
       게시글 작성자 아이디 : memberBoardId, memberBoardNickname
       댓글 작성자 아이디 : memberReplyId, memberReplyNickname 
       대댓글 작성자 아이디 : memberAddReplyId, memberAddReplyNickname -->
      <!-- 로그인한 아이디가 작성한 게시글만 볼 수 있도록 수정
       관리자는 전체 게시글 확인할 수 있도록 수정 해야됨 -->
     <div class="m-3">
      <div class="input-group mb-3">
        <span class="input-group-text " style="width: 10%">글 번호</span> <input
          type="text" value="${vo.qnaboardNo }" readonly>
      </div>
      <%-- <div class="input-group mb-3">
        <span class="input-group-text " style="width: 10%">제목</span> <input
          type="text" value="${vo.qnaboardTitle }" readonly>
      </div> --%>
      <div class="input-group mb-3">
        <span class="input-group-text " style="width: 10%">닉네임</span> <input
          type="text" value="${vo.memberNickname }" readonly>
      </div>
      <div class="input-group mb-3">
        <c:set var="qnaboardDate">
          <fmt:formatDate value="${vo.qnaboardDate }"
            pattern="YYYY-MM-dd hh:mm" />
        </c:set>
        <span class="input-group-text " style="width: 10%">작성일</span> <input
          type="text" value="${qnaboardDate }" readonly>
      </div>
      <div>
        <textarea rows="20" cols="120" readonly>${vo.qnaboardContent }</textarea>
      </div>
      <div>
        <a href="qnalist"><input type="button"
          class="btn btn-primary" value="글 목록"></a>
        <c:if test="${vo.memberId eq sessionScope.memberId}">
          <!-- 게시글 작성자와 로그인한 아이디가 같으면 글 수정, 삭제 가능 -->
          <a href="qnaupdate?qnaboardNo=${vo.qnaboardNo }"><button
              class="btn btn-primary">글 수정</button></a>
          <a href="qnadelete?qnaboardNo=${vo.qnaboardNo }"><input
            type="button" value="글 삭제" class="btn btn-primary"></a>
        </c:if>
        <hr>
      </div>
      <div style="text-align: left">
        <input type="hidden" id="qnaboardNo" value="${vo.qnaboardNo }">
        <c:if test="${not empty sessionScope.memberId }">
          <input type="hidden" id="memberReplyNo" readonly>
          <input type="hidden" id="memberReplyId"
            value="${mo.memberId }">
          <div class="input-group mb-3">
            <span class="input-group-text " style="width: 15%">닉네임</span>
            <input type="text" id="memberReplyNickname"
              value="${mo.memberNickname }" readonly>
          </div>
          <div>
            <textarea rows="5" cols="100" id="replyContent"
              placeholder="댓글 내용을 입력하세요"></textarea>
          </div>
          <button type="button" id="btn_add"
            class="btn btn-primary mt-3">댓글 등록</button>

        </c:if>
      </div>
      <hr>
      <div id="qnareplies" style="text-align: left;"></div>
	       </div>
        <!-- footer -->
       <div class="mt-5 p-3 bg-light">
        <div style="bottom: 0; height: 200px; text-align: center;">
          <h4>NFT-AUCTION</h4>
          <hr>
          <br>
          <a href="#">이용약관</a> &nbsp;
          <a href="#">사이트안내</a>
          <br><br>
          <small>문의 nft.auction.help@gmail.com</small>
        </div>
      </div>
    </div>
    
  </section>





  <script type="text/javascript">
            $(document).ready(function() {
                var qnaboardNo = $('#qnaboardNo').val();
				console.log("test");
                getAllReplies();

                // 댓글 작성 버튼 클릭
                $('#btn_add').click(function() {
                    var replyContent = $('#replyContent').val();
                    replyContent = replyContent.replace('/r/n', '<br/>');
                    var replyParentNo = "0";
                    var memberReplyNickname = $('#memberReplyNickname').val();
                    var memberReplyId = $('#memberReplyId').val();
                    var obj = {
                            'qnaboardNo' : qnaboardNo,
                            'replyParentNo' : replyParentNo,
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
                                if (result == 1) {
                                    alert('댓글 입력 성공');
                                    getAllReplies();
                                }
                        	}
                        });

                 }); // end btn_add()

                 // 대댓글 버튼 클릭
                 $('#qnareplies').on('click', '.btn_add_reply', function() {
                     console.log("대댓글 작성 클릭");
                     var replyAddContent = $('#replyAddContent').val();
                     var replyParentNo = $('#replyAddNo').val();
                     var memberNickname = $('#memberReplyAddNickname').val();
                     var memberAddId = $('#memberAddId').val();
                     var obj = {
                             'qnaboardNo' : qnaboardNo,
                             'replyParentNo' : replyParentNo,
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
                                if (result == 1) {
                                    alert('댓글 입력 성공');
                                    getAllReplies();
                                }
                            }
                         });
                    }); // end btn_add_reply()

                                // 답글 버튼 눌렀을 때 답글 입력창
                                $('#qnareplies').on('click','div .btn_reply',function() {
                                    console.log("btn_reply click()");
                                    var replyAddNo = $(this).closest('.reply_item').find('#replyNo').val();
                                    var memberReplyAddNickname = $('#memberReplyNickname').val();
                                    var inputAddReply = $('<div class="row offset-sm-1">'
                                            + '<input type="hidden" id="replyAddNo" value="'
                                            + parseInt(replyAddNo)
                                            + '">'
                                            + '<input type="hidden" id="memberAddId" value=" ${mo.memberId} " + readonly>'
                                            // + '<span class="input-group-text " style="width: 15% ">닉네임</span>'
                                            + '<input type="text" id="memberReplyAddNickname" value="${mo.memberNickname }" readonly><br>'
                                            + '</div>'
                                            + '<div class="row offset-sm-1">'
                                            + '<textarea rows="3" cols="100" id="replyAddContent" placeholder="답글 내용을 입력하세요"></textarea>'
                                            // + '<input type="text" id="replyAddContent" placeholder="댓글 내용을 입력하세요">'                    
                                            + '<button type="button" class="btn_add_reply btn btn-warning mt-3-sm">답글 등록</button>'
                                            + '</div>');
                                             // + '<button type="button" class="btn_add_reply btn btn-link" type="button">o</button>');    
                                     $(this).closest('.reply_item').find('#reply').html(inputAddReply).toggle();
                                   }); // end btn_reply()       

                                // 수정 버튼 눌렀을 때 수정 내용 입력창
                                $('#qnareplies').on('click','div .btn_update', function() {
                                                    console.log("btn_update click()");
                                                    var replyUpdateNo = $(this).closest('.reply_item').find('#replyNo').val();
                                                    var replyContent = $(this).closest('.reply_item').find('#replyContent').val();
                                                    var memberReplyAddNickname = $('#memberReplyNickname').val();
                                                    console.log(replyUpdateNo);

                                                    var inputUpdateReply = $('<div class="row offset-sm-1" >'
                                                            + '<input type="hidden" id="replyUpdateNo" value="'+replyUpdateNo+'">'
                                                            + '<input type="hidden" id="memberAddId" value=" ${mo.memberId} " + readonly>'
                                                            // + '<span class="input-group-text " style="width: 15% ">닉네임</span>'
                                                            + '<input type="text" id="memberReplyAddNickname" value="${mo.memberNickname }" readonly><br>'
                                                            + '</div>'
                                                            + '<div class="row offset-sm-1">'
                                                            + '<textarea rows="3" cols="100" id="replyAddContent">'
                                                            + replyContent
                                                            + '</textarea>'
                                                            // + '<input type="text" id="replyAddContent" placeholder="댓글 내용을 입력하세요">'                    
                                                            + '<button type="button" class="btn_update_reply btn btn-warning mt-3-sm">댓글 수정</button>'
                                                            + '</div>');
                                                    // + '<button type="button" class="btn_add_reply btn btn-link" type="button">o</button>');    
                                                    $(this) .closest('.reply_item').find('#reply') .html(inputUpdateReply).toggle();
                                                }); // end btn_reply()       

                                // 전체 댓글 목록 출력
                                function getAllReplies() {
                                    var url = 'qnareplies/rest/all/'+ qnaboardNo;
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
                                                    var offset = 'offset-sm-1';
                                                    var replyContent = this.replyContent;
                                                    replyContent = replyContent.split('<br/>').join("\r\n");
													
                                                    function getFormatDate(date) {
                                                    	var year = date.getFullYear();
                                                        var month = (1 + date.getMonth());
                                                        month = month >= 10 ? month: '0'+ month;
                                                        var day = date.getDate();
                                                        day = day >= 10 ? day: '0' + day;
                                                        var hour = date.getHours();
                                                        var minutes = date.getMinutes();
                                                        return year+ '-' + month + '-' + day + ' ' + hour + ':' + minutes;
                                                    }
                                                    
                                                    var replyDate = new Date(this.replyDate);
                                                    replyDate = getFormatDate(replyDate);
							                       
                                                    if (replyWriter == this.memberNickname) {
							                            disabled = '';
                                                        readonly = '';
                                                    }
                                                    if (replyWriter == this.memberReplyAddNickname) {
                                                        hidden = '';
                                                        readonly = '';
                                                    }
                                                	if (this.replyParentNo == 0) {
                                                        var dis = '';
                                                        var rep = '';
                                                        var offset = '';
                                                   } else {
                                                        var dis = 'disabled';
                                                        var rep = '└─RE: ';''
                                                   }
                                                       list += '<div class="reply_item ' + offset + ' card border-secondary mb-3" style="max-width: 40rem;">'
                                                          //+ '<div class="div1">'
                                                            + '<pre>'
                                                            + rep
                                                            + '<input type="hidden" id="replyNo" value="' + this.replyNo + '"/>'
                                                            + '<input type="hidden" id="memberId" value="'+ this.memberId + '"/>'
                                                            + '&nbsp;&nbsp;'
                                                            + '<input type="hidden" id="memberReplyNickname" value="' + this.memberNickname + '"/>'
                                                            + '<b>' + this.memberNickname + '</b><br>'
                                                            + '&nbsp;&nbsp;' // 공백
                                                            + '&nbsp;&nbsp;'
                                                            + '<div class="col-sm-5">'
                                                            + replyContent
                                                            + '</div>'
                                                            + '&nbsp;&nbsp;'
                                                            + '<span class="text-muted">' + replyDate + '</span>'
                                                            // + replyDate
                                                            + '&nbsp;&nbsp;'
                                                            + '<button class="btn_update btn btn-link btn-sm" type="button" '+ disabled +'>수정</button>'
                                                            + '<button class="btn_delete btn btn-link btn-sm" type="button"'+ disabled +'>삭제</button>'
                                                            + '<button class="btn_reply btn btn-link btn-sm" type="button" '+ dis +'>답글</button><br>'
                                                            + '<input type="hidden" id="replyContent" value="' + this.replyContent + '" '+ readonly +'/>'
                                                            //+ '<textarea id="replyContent" rows="3" cols="100"'+ readonly +'>'+ this.replyContent +'</textarea>'                       
                                                            + '<div id="reply" style="display: none; width:80%;">'
                                                            + '</div>'
                                                            + '</pre>'
                                                            + '</div>';
                                                    });
                                                        $('#qnareplies').html(list);
                                                    });
                                } // end getAllReplies()    

                                // 댓글 수정 버튼 클릭시
                                $('#qnareplies').on('click','.reply_item .btn_update_reply', function() {
                                    console.log(this);
                                    var replyUpdateNo = $('#replyUpdateNo').val();
                                    var replyContent = $(this).prevAll('#replyAddContent').val();
                                    console.log("선택된 댓글 번호 : "+ replyUpdateNo+ ", 댓글 내용 : "+ replyContent);
                                    $.ajax({
                                        type : 'PUT',
                                        url : 'qnareplies/rest/' + replyUpdateNo,
                                        data : JSON.stringify({
                                            'replyContent' : replyContent
                                    	}),
                                        headers : {
                                           'Content-Type' : 'application/json',
                                           'X-HTTP-Method-Override' : 'PUT'
                                           },
                                        success : function(result) {
                                            if (result == 'success') {
                                                   alert('댓글 수정 성공!');
                                                   getAllReplies();
                                             }
                                        }
                                    });
                                 }); // end btn_update()

                                // 댓글 삭제 버튼 클릭시
                                $('#qnareplies')
                                        .on(
                                                'click',
                                                '.reply_item .btn_delete',
                                                function() {
                                                    console.log(this);
                                                    console
                                                            .log("btn_delete() 클릭");
                                                    var qnaboardNo = $(
                                                            '#qnaboardNo')
                                                            .val();
                                                    var replyNo = $(this)
                                                            .prevAll('#replyNo')
                                                            .val();
                                                    console.log("선택된 댓글 번호 : "
                                                            + replyNo);

                                                    $
                                                            .ajax({
                                                                type : 'delete',
                                                                url : 'qnareplies/rest/'
                                                                        + replyNo,
                                                                data : JSON
                                                                        .stringify({
                                                                            'replyNo' : replyNo,
                                                                            'qnaboardNo' : qnaboardNo
                                                                        }),
                                                                headers : {
                                                                    'Content-Type' : 'application/json',
                                                                    'X-HTTP-Method-Override' : 'DELETE'
                                                                },
                                                                success : function(
                                                                        result) {
                                                                    if (result == 'success') {
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