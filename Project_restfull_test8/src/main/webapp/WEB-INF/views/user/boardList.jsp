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
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>글 번호</td>
			<td>게시글 제목</td>
			<td>게시 날짜</td>
		</tr>
		<c:forEach items="${userBoard}" var="userBoard">
		<tr>
			<td>${userBoard.board_numbers}</td>
			<td><a href="content_view?board_numbers=${userBoard.board_numbers}">${userBoard.title}</a></td>
			<td>${userBoard.dates}</td>
		</tr>
		</c:forEach>
	</table>
	
	<h3>[<a href="userHome">돌아가기</a>]</h3>
	
</body>
</html>
