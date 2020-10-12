<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>회원 아이디</td>
			<td>회원 비밀번호</td>
			<td>회원 이름</td>
			<td>생년월일</td>
			<td>성별</td>
			<td>연락처</td>
			<td>이메일</td>
		</tr>
		<c:forEach items="${restful}" var="restful">
		<tr>
			<td>${restful.member_id}</td>
			<td>${restful.pw}</td>
			<td>${restful.name}</td>
			<td>${restful.birth}</td>
			<td>${restful.gender}</td>
			<td>${restful.phone}</td>
			<td>${restful.email}</td>
			<%-- <td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
				<c:forEach begin="1" end="12">-</c:forEach>
				<a href="content_view?board_count=${restful.board_count}">${restful.title}</a></td> --%>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="#">추가 기능</a> </td>
		</tr>
	</table>
	
</body>
</html>
