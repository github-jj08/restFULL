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
			<td>구분</td>
			<td>제목</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:forEach items="${noticelist}" var="vo">
		<tr>
			<td>${vo.boardlistName}</td>
			<td><a href="${pageContext.request.contextPath}/notice/content_view?board_numbers=${vo.board_numbers}">${vo.title}</a></td>
			<td>${vo.dates}</td>
			<td>${vo.hit}</td>
		</tr>
		</c:forEach>
	</table>
	<h3><button onclick="history.go(-1);">돌아가기</button></h3>
</body>
</html>
