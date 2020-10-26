<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>
	<div class="text-center"><h2>내 여행코스</h2></div>
	<hr>
	    <section class="blog-section spad">
	        <div class="container">
	            <div class="row">
	                <div class="col-sm-3 order-1">
	                    <div class="blog-sidebar">
							<div class="blog-catagory menu-background">
						        <h4>마이페이지</h4>
						        <ul>
						            <li><p>[<a href="<c:url value="userModify" />">개인정보 수정</a>]</p></li>
						            <li><p>[<a href="myList?member_id=<sec:authentication property="principal.user.member_id"/>">내 게시글 보기</a>]</p></li>
						            <li><p>[<a href="myLikeList?member_id=<sec:authentication property="principal.user.member_id"/>">좋아요 한 글 목록</a>]</p></li>
						            <li style="background-color: #8a93c0;"><p>[<a href="myCourseList?member_id=<sec:authentication property="principal.user.member_id"/>">내 여행코스 보기</a>]</p></li>
						            <li><p>[<a href="paymentList?member_id=<sec:authentication property="principal.user.member_id"/>">내 결제내역 보기</a>]</p></li>
						            <li><p>[<a href="reportList?member_id=<sec:authentication property="principal.user.member_id"/>">내 신고내역 보기</a>]</p></li>
						            <li><p>[<a href="qnaList?member_id=<sec:authentication property="principal.user.member_id"/>">내 문의내역 보기</a>]</p></li>
						            <li><p>[<a href="<c:url value="userDeleteView" />">회원탈퇴</a>]</p></li>
						        </ul>
					        </div>
				        </div>
			        </div>
			        <div class="col-sm-9 order-2">
					   <div class="notice-table">
							<table id="list-table">
								<tr class="firs-list">
							         <td>여행코스명</td>
							         <td>작성일</td>
						     	 </tr>
						      	<tr>
						      		<td>여행 코스명 | ${alias}</td>
						      		<td>작성일 | ${dates}</td>
						      	</tr>
						   </table>
						      	<div>
							      	<c:forEach items="${list}" var="vo">
								      <p>${vo.tcContents}</p>
								    </c:forEach>
						      	</div>
						      	<button type="button" class="user-modify-btn" onClick="history.go(-1)">이전 페이지</button>
				   	 	</div>
			        </div>
		        </div>
		</section>
<%@ include file="/WEB-INF/include/js-footer.jsp"%>
</body>
</html>