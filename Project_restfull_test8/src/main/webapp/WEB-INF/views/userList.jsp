<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table width="700" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>회원 아이디</td>
			<td>회원 비밀번호</td>
			<td>회원 이름</td>
			<td>생년월일</td>
			<td>성별</td>
			<td>연락처</td>
			<td>이메일</td>
		</tr>
		<c:forEach items="${list}" var="member">
		<tr>
			<td>${member.member_id}</td>
			<td>${member.pw}</td>
			<td>${member.name}</td>
			<td>${member.birth}</td>
			<td>${member.gender}</td>
			<td>${member.phone}</td>
			<td>${member.email}</td>
			<%-- <td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
				<c:forEach begin="1" end="12">-</c:forEach>
				<a href="content_view?board_count=${restful.board_count}">${restful.title}</a></td> --%>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="#">글작성</a> </td>
		</tr>
	</table>
	
</body>
</html>
