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
			<td>여행코스명</td>
			<td>작성일</td>
		</tr>
		<c:forEach items="${list}" var="vo">
		<tr>
			<td><a href="course_view?member_id=${vo.member_id}&serialNum=${vo.serialNum}">${vo.tcAlias}</a></td>
			<td>${vo.dates}</td>
		</tr>
		</c:forEach>
		
	</table>
	
	<h3>[<a href="userHome">돌아가기</a>]</h3>
	
</body>
</html>
