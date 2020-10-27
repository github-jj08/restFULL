<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>RestFuLL | 마이페이지</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  
</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>
	<div class="text-center"><h2>내 문의내역</h2></div>
	<hr>
	    <section class="blog-section spad">
	        <div class="container">
	            <div class="row">
	                <div class="col-sm-3 order-1">
	                    <div class="blog-sidebar">
							<div class="blog-catagory menu-background">
						        <h4>마이페이지</h4>
						        <ul>
						            <li><p><a href="<c:url value="userModify" />">개인정보 수정</a></p></li>
						            <li><p><a href="myList?member_id=<sec:authentication property="principal.user.member_id"/>">내 게시글 보기</a></p></li>
						            <li><p><a href="myLikeList?member_id=<sec:authentication property="principal.user.member_id"/>">좋아요 한 글 목록</a></p></li>
						            <li><p><a href="myCourseList?member_id=<sec:authentication property="principal.user.member_id"/>">내 여행코스 보기</a></p></li>
						            <li><p><a href="paymentList?member_id=<sec:authentication property="principal.user.member_id"/>">내 결제내역 보기</a></p></li>
						            <li><p><a href="reportList?member_id=<sec:authentication property="principal.user.member_id"/>">내 신고내역 보기</a></p></li>
						            <li style="background-color: #8a93c0;"><p><a href="qnaList?member_id=<sec:authentication property="principal.user.member_id"/>">내 문의내역 보기</a></p></li>
						            <li><p><a href="<c:url value="userDeleteView" />">회원탈퇴</a></p></li>
						        </ul>
					        </div>
				        </div>
			        </div>
			        <div class="col-sm-9 order-2">
			        	<div class="notice-table">
							<table id="list-table-admin">
								<tr>
									<td class="content-view-table-rep">제목</td>
									<td>${content_view.title}</td>
								</tr>
								<tr>
									<td class="content-view-table-rep">작성자</td>
									<td>${content_view.member_id}</td>
								</tr>
								<tr>
									<td class="content-view-table-rep">작성날짜</td>
									<td> ${content_view.dates}</td>
								</tr>								
								<tr style="height: auto;">
									<td class="content-view-table-rep">문의내용</td>
									
									<td height = "0"><div style="padding:5px; height:100%; word-break:break-all">${content_view.contents}</div></td>
								</tr>
							</table>
			        </div>
						<button type="button" onclick="history.go(-1);" class="gobackbtn">돌아가기</button>
				    </div>
		        </div>
	        </div>
		</section>
		
		
<%--           <!-- 메인 컨텐츠  -->
         <div class="contentswrapper">
            <input type="hidden" name="board_numbers" value="${content_view.board_numbers}">
			        	<div class="notice-table">
				        	<table id="list-table" class="" style="table-layout: fixed; text-overflow:ellipsis; border: 1px solid;">
								<tr>
									<td class="content-view-table-rep">제목</td>
									<td>${content_view.title}</td>
								</tr>
								<tr>
									<td class="content-view-table-rep">작성자</td>
									<td>${content_view.member_id}</td>
								</tr>
								<tr>
									<td class="content-view-table-rep">작성날짜</td>
									<td> ${content_view.dates}</td>
								</tr>								
								<tr style="height: auto;">
									<td class="content-view-table-rep">문의내용</td>
									
									<td height = "0"><div style="padding:5px; height:100%; word-break:break-all">${content_view.contents}</div></td>
								</tr>
							</table>
						</div> --%>
               
            <%-- <!-- 답변 글 -->
            <div id="rightbox"style="float:right">            
               <div>
                  <div id="commentlist">
                     <div id="reply">
                        <section class="replyForm">
                           <table id="list-table">
                           </table>
                        </section>
                     </div>
                  </div>
               </div>
            </div>
         </div> --%>
         
         
<%@ include file="/WEB-INF/include/js-footer.jsp"%>
                   <script>
                     $(function(){
                        var board_numbers = ${content_view.board_numbers};
                        var member_id = $('#myId').text();
                        
                        console.log("member_id : " + member_id);
                        
                        //댓글 목록 출력(문서 로딩되자마자 실행)
                        function commentList(){ 
                           
                           $.ajax({
                           url: "${pageContext.request.contextPath}/getComments/" + board_numbers,
                               type: "POST",
                               dataType:"json",
                               success: function (result) {
                                  //html삽입 : 표시될 데이터 - 아이디,댓글내용,작성일,삭제버튼,[히든]댓글번호
                                  var htmls="";
                                  $("#list-table").html("");   

                                $("<tr>" , {
                                   html : "<td>" + "아이디" + "</td>"+  // 컬럼명들
                                         "<td>" + "댓글내용" + "</td>"+
                                         "<td>" + "작성일" + "</td>"+
                                         "<td>" + "삭제버튼+댓글번호[히든]" + "</td>"         
                                }).appendTo("#list-table-admin") // 이것을 테이블에붙임

                                if(result.length < 1){
                                   htmls.push("등록된 댓글이 없습니다.");
                                } else {
                                           $(result).each(function(index,item){
                                              //사용자와 작성자의 아이디가 같다면 삭제 버튼 생성
                                              if(this.member_id == member_id){
                                                 
                                                 htmls += '<tr>';
                                                 htmls += '<td>'+ item.member_id + '</td>';
                                                htmls += '<td>'+ item.contents + '</td>';
                                                htmls += '<td>'+ item.dates + '</td>';
                                                 htmls += '<td><button type="button" name="delete" value="' + item.comments_numbers+'">삭제</button>';
                                                   htmls += '<button type="button" name="comment-modify" value="'+item.comments_numbers+'">수정</button></td>';
                                                   htmls += '</tr>';
                                                 

                                              } else {
                                                 //아니라면 그냥 출력
                                                 htmls += '<tr>';
                                                 htmls += '<td>'+ item.member_id + '</td>';
                                                htmls += '<td>'+ item.contents + '</td>';
                                                htmls += '<td>'+ item.dates + '</td>';
                                                 htmls += '<td>'+ item.comments_numbers + '<input type="hidden" value="'+ item.board_numbers + '"> 게시글번호숨김 </td>';   
                                                 htmls += '</tr>';
                                              }
                                                                                                    
                                          });   //each end
                                }
                                $("#list-table-admin").append(htmls);
                                }
                           });
                        };
                            commentList(); // 처음 시작했을 때 실행되도록 해당 함수 호출
                     });
                  </script> 
</body>
</html>