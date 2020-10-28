<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
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
	
	<div class="text-center"><h2>신고내역</h2></div>
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
						            <li style="background-color: #8a93c0;"><p><a href="reportList?member_id=<sec:authentication property="principal.user.member_id"/>">내 신고내역 보기</a></p></li>
						            <li><p><a href="qnaList?member_id=<sec:authentication property="principal.user.member_id"/>">내 문의내역 보기</a></p></li>
						            <li><p><a href="<c:url value="userDeleteView" />">회원탈퇴</a></p></li>
						        </ul>
					        </div>
				        </div>
			        </div>
			        <div class="col-sm-9 order-2">
			        	<div class="notice-table">
							<table id="list-table" class="" style="table-layout: fixed; text-overflow:ellipsis; border: 1px solid;">
								<tr>
									<td class="content-view-table-rep">신고대상글</td>
									<td> ${content_view.title}</td>
								</tr>
								<tr>
									<td class="content-view-table-rep">작성자</td>
									<td> ${content_view.member_id}</td>
								</tr>
								<tr>
									<td class="content-view-table-rep">신고날짜</td>
									<td> ${content_view.dates}</td>
								</tr>								
								<tr>
									<td class="content-view-table-rep">신고내용</td>
									<td height = "0"><div style="padding:5px; height:100%; word-break:break-all">${content_view.contents}</div></td>
								</tr>
						</table>
						
						<button type="button" onclick="history.go(-1);" class="gobackbtn">돌아가기</button>
						
				    </div>
		        </div>
	        </div>
		</section>




         <!-- 메인 컨텐츠  -->
         <div class="contentswrapper">
            <input type="hidden" name="board_numbers" value="${content_view.board_numbers}">
               
                        <!-- 답변 글 -->
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
         </div>
            <%@ include file="/WEB-INF/include/js-footer.jsp"%>
            
</body>
</html>