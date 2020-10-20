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
			<h1>여행코스명 | ${alias}</h1>
			<p>작성일 | ${dates}</p>
			
			<c:forEach items="${list}" var="vo">
				${vo.tcContents}<br/>
			</c:forEach>
	
	
	<h3>[<a href="userHome">돌아가기</a>]</h3>
	
</body>
</html>
