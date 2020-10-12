<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 전용 태그 -->
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>메인페이지</title>
</head>

<body>

<h1>메인페이지</h1>

<sec:authorize access="isAnonymous()">
	[<a href="login" type="button">로그인</a>]
	[<a href="join" type="button">회원가입</a>]
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<sec:authentication var="principal" property="principal"/>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	    <input type="submit" value="로그아웃" />
	</form:form>
	
	<a class="btn btn-dark">환영합니다. ${principal.user.name}  <%-- <sec:authentication property="principal.name"/> 님!--%></a> 
	
	

	<h3>[<a href="<c:url value="/user/userHome" />">유저 마이페이지</a>]</h3>
	
	<c:if test="${principal.user.name == 'ADMIN'}">
	<h3>[<a href="<c:url value="/admin/adminHome" />">관리자 홈</a>]</h3>
	</c:if>
	
</sec:authorize>


</body>
</html>
