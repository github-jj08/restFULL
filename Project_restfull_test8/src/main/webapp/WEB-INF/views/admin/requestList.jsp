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

	<table>
		<tr>
			<td>결제 번호</td>
			<td>주문 번호</td>
			<td>주문자 id</td>
			<td>여행지명</td>
			<td>상품명</td>
			<td>상품가격</td>
			<td>결제 날짜</td>
			<td>취소 여부</td>
		</tr>
		<c:forEach items="${list}" var="vo">
			<tr>
				<td>${vo.imp_uid}</td>
				<td>${vo.request_numbers}</td>
				<td>${vo.member_id}</td>
				<td>${vo.destination_name}</td>
				<td><a href="${pageContext.request.contextPath}/user/goods_view?goods_numbers=${vo.goods_numbers}">${vo.productName}</a></td>
				<td>${vo.price}</td>
				<td>${vo.dates}</td>
				<td>
					<c:set var="cancelyn" value="${vo.cancel}" />
					<c:choose>
					    <c:when test="${cancelyn == 0}">
							공지사항
						</c:when>
					    <c:when test="${cancelyn == 1}">
					    	이벤트
					    </c:when>
					    <c:otherwise>
					    	못받아왓쟈냐
					    </c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<h3>[<a href="adminHome">돌아가기</a>]</h3>
</body>
</html>
