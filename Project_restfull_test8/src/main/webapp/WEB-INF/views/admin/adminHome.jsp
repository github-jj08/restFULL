<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>관리자 홈</title>
</head>

<body>
	<%@ include file="/WEB-INF/include/js-header.jsp"%>
	<h2 class="text-center">관리자 페이지입니다.</h2>

 <!-- Blog Section Begin -->
    <section class="blog-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-8 order-2 order-lg-1">
                    <div class="blog-sidebar">
                        <div class="blog-catagory">
                            <h4>관리자페이지</h4>
                            <ul>
                                <li><a href="<c:url value="/admin/userList" />">유저 관리</a></li>
                                <li><a href="<c:url value="/admin/notice" />">공지사항 및 이벤트 관리</a></li>
                                <li><a href="<c:url value="/admin/qnas" />">문의 및 신고 답변</a></li>
                                <li><a href="<c:url value="/admin/dest" />">여행지 및 상품 관리</a></li>
                            	<li><a href="<c:url value="/admin/requestList" />">상품 주문내역 관리</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

<%--                 <!--관리자페이지 table start-->
                <div class="col-lg-9 order-1 order-lg-2">
                    <div class="row">
                        <div class="col-lg-12 col-sm-12">
                            <div class="notice-table">
                                <table id="list-table">
									<tr class="first-list">
										<td>구분</td>
										<td>제목</td>
										<td>작성일</td>
										<td>조회수</td>
									</tr>
									<c:forEach items="${noticelist}" var="vo">
									<tr class="noticetable">
										<td>${vo.boardlistName}</td>
										<td><a href="${pageContext.request.contextPath}/notice/content_view?board_numbers=${vo.board_numbers}">${vo.title}</a></td>
										<td>${vo.dates}</td>
										<td>${vo.hit}</td>
									</tr>
									</c:forEach>
								</table>
                            </div>
                        </div>
                        
                    </div>
                </div>
                <!--관리자페이지 table end--> --%>
            </div>
        </div>
    </section>


<%@ include file="/WEB-INF/include/js-footer.jsp"%>

</body>
</html>