<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>메인페이지</title>
</head>

<body>

<h1>메인페이지</h1>


<sec:authorize access="isAuthenticated()">
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	    <input type="submit" value="로그아웃" />
	</form:form>
	
	<sec:authentication var = "principal" property = "principal"/>
	<p>${principal.member.name}님 환영합니다.</p> 
	
	
	<p><a href="<c:url value="/loginInfo" />">로그인 정보 확인</a></p>
</sec:authorize>

</body>
</html>
