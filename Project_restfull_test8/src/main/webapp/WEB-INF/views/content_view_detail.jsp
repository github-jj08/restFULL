<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!-- view 부분이다 -->
<!DOCTYPE html>


<html>
   <head>
      <meta charset="UTF-8">
      <title>Board view</title>
         <style>
		table, td{
			text-align : center;
			margin : auto;
		}
	
	</style>
</head>
<body>

<form action="boardDeleteUser" id="modifyForm" method="post">
	<table width="400" cellpadding="0" cellspacing="0" border="1">
	
			<input type="hidden" name="name" value="${userBoardDetail.board_numbers}">
			
			<tr>
				<td> 글 번호 </td>
				<td><input id="board_numbers" name="board_numbers" value="${userBoardDetail.board_numbers}" readonly/></td>
			</tr>
			
			<tr>
				<td> 글 제목 </td>
				<td><input type="text" id="title"name="title" value="${userBoardDetail.title}"></td>
			</tr>
			<tr>
				<td> 글 내용 </td>
				<td><input type="text" id="contents" name="contents" value="${userBoardDetail.contents}"></td>
			</tr>
			<tr>
				<td> 조회수 </td>
				<td><input type="text" id="hit" name="hit" value="${userBoardDetail.hit}" readOnly></td>
			</tr>
			
			<tr>
				<td> 작성 날짜 </td>
				<td><input type="text" id="dates" name="dates" value="${userBoardDetail.dates}" readOnly></td>
			</tr>
			
			<tr>
				<td> 위치 </td>
				<td><input type="text" id="location" name="location" value="${userBoardDetail.location}"></td>
			</tr>
			
			<tr>
				<td> 작성자 </td>
				<td><input type="text" id="member_id" name="member_id" value="${userBoardDetail.member_id}" readOnly></td>
			</tr>
			
			<tr>
				<a href="boardList?member_id=${userBoardDetail.member_id}">게시글 확인</a> &nbsp;&nbsp;
				<button class="btn btn-default" type="submit">게시글 삭제</button>
			</tr>
		
	</table>
	</form>
	
</body>

</html>