<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>사용자 홈</title>
</head>

<body>

<h1>사용자 페이지 입니다.</h1>

<h3>[<a href="<c:url value="/home" />">홈으로 돌아가기.</a>]</h3>

<p>[<a href="<c:url value="/userDeleteView" />">회원탈퇴</a>]</p>
<p>[<a href="<c:url value="/userModify" />">개인정보 수정</a>]</p>

<sec:authentication var="principal" property="principal"/>
<p>Principal : <sec:authentication property="principal.user"/></p>
<p>사용자 아이디 : <sec:authentication property="principal.user.member_id"/></p>
<p>사용자 비밀번호 : <sec:authentication property="principal.user.pw"/></p>
<p>사용자 이름 : <sec:authentication property="principal.user.name"/></p>
<p>사용자 성별 : <sec:authentication property="principal.user.gender"/></p>
<p>사용자 이메일 : <sec:authentication property="principal.user.email"/></p>
<p>사용자 연락처 : <sec:authentication property="principal.user.phone"/></p>



</body>
</html>