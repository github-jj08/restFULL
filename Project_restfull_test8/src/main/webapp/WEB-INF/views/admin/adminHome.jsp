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

<h1>관리자 페이지 입니다.</h1>

<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	    <input type="submit" value="로그아웃" />
</form:form>

<h3>[<a href="<c:url value="/admin/userList" />">유저 관리</a>]</h3>
<h3>[<a href="<c:url value="/admin/notice" />">공지사항 및 이벤트 관리</a>]</h3>
<h3>[<a href="<c:url value="/admin/qnas" />">문의 및 신고 답변</a>]</h3>
<h3>[<a href="<c:url value="/admin/dest" />">여행지 및 상품 관리</a>]</h3>

</body>
</html>