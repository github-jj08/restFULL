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

<h1>내정보 확인</h1>
<sec:authentication var="principal" property="principal"/>
<p>Principal : <sec:authentication property="principal.user"/></p>
<p>사용자 아이디 : <sec:authentication property="principal.user.member_id"/></p>
<p>사용자 비밀번호 : <sec:authentication property="principal.user.pw"/></p>
<p>사용자 이름 : <sec:authentication property="principal.user.name"/></p>
<p>사용자 성별 : <sec:authentication property="principal.user.gender"/></p>
<p>사용자 이메일 : <sec:authentication property="principal.user.email"/></p>
<p>사용자 연락처 : <sec:authentication property="principal.user.phone"/></p>

<h3>[<a href="<c:url value="/" />">홈으로 돌아가기.</a>]-->나중에는 지울것</h3>

<p>[<a href="<c:url value="userDeleteView" />">회원탈퇴</a>]</p>
<p>[<a href="<c:url value="userModify" />">개인정보 수정</a>]</p>

<hr/>
<h1>생성 완료</h1>
<hr/>
<h3>게시판</h3>
<p>[<a href="myList?member_id=<sec:authentication property="principal.user.member_id"/>">내 게시글 보기</a>]</p>
<p>[<a href="qnaList?member_id=<sec:authentication property="principal.user.member_id"/>">내 문의내역 보기</a>]</p>
<p>[<a href="reportList?member_id=<sec:authentication property="principal.user.member_id"/>">내 신고내역 보기</a>]</p>

<h1>생성 미완료-결제구현 이후 생성예정(빨리빨리해라)</h1>
<hr/>
<p>[<a href="qnaList?member_id=<sec:authentication property="principal.user.member_id"/>">내 결제내역 보기</a>]</p>
<p>[<a href="reportList?member_id=<sec:authentication property="principal.user.member_id"/>">좋아요 한 글 목록</a>]</p>
<p>[<a href="reportList?member_id=<sec:authentication property="principal.user.member_id"/>">내 여행코스 보기</a>]</p>




</body>
</html>