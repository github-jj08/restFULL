<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
</head>
<body>
	<table id="list-table" width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>지역코드</td>
			<td>여행지명</td>
			<td>지번 주소</td>
			<td>도로명 주소</td>
			<td>관련 상품</td>
		</tr>
		<c:forEach items="${destlist}" var="vo">
		<tr>
			<td>${vo.sidoguVO.sidoName}</td>
			<td><a href="content_view?destination_numbers=${vo.destination_numbers}">${vo.destination_name}</td>
			<td>${vo.jibunaddress}</td>
			<td>${vo.doroaddress}</td>
			<td>0</td>
		</tr>
		</c:forEach>
		<a href="${pageContext.request.contextPath}/admin/dest/write_view">여행지 등록</a>
	</table>
	<h3><button onclick="history.go(-1);">돌아가기</button></h3>
	
</body>
</html>
