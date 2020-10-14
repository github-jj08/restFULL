<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="boardList?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
</head>
<body>
<div id="outter">
	<div style="float: right;">
		<select id="cntPerPage" name="sel" onchange="selChange()">
			<option value="5"
				<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄 보기</option>
			<option value="10"
				<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄 보기</option>
			<option value="15"
				<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄 보기</option>
			<option value="20"
				<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄 보기</option>
		</select>
	</div> 
	<!-- 옵션선택 끝 -->
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>회원 아이디</td>
			<td>회원 이름</td>
			<td>생년월일</td>
		</tr>
		<c:forEach items="${restful_user}" var="restful_user">
		<tr>
			<td><a href="user_content_view?member_id=${restful_user.member_id}">${restful_user.member_id}</a></td>
			<td>${restful_user.name}</td>
			<td>${restful_user.birth}</td>
		</tr>
		</c:forEach>
	</table>
	
	<c:if test="${paging.startPage != 1 }">
			<a href="userList?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="userList?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="userList?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
</div>
	<h3><a href="adminHome">돌아가기</a></h3>
	
</body>
</html>
