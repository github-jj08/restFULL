<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
				        	<table id="list-table">
								<tr class="first-list">
									<td>글 번호</td>
									<td>게시글 제목</td>
									<td>게시 날짜</td>
								</tr>
								<c:forEach items="${userQna}" var="userBoard">
								<tr>
									<td>${userBoard.board_numbers}</td>
									<td><a href="content_view_qna?board_numbers=${userBoard.board_numbers}">${userBoard.title}</a></td>
									<td>${userBoard.dates}</td>
								</tr>
								</c:forEach>
							</table>
			        </div>
						<br>
						<a href="ask"><button type="button" class="user-modify-btn">문의글 작성하기</button></a>
				    </div>
		        </div>
	        </div>
		</section>
<%@ include file="/WEB-INF/include/js-footer.jsp"%>
	
	
</body>
</html>
