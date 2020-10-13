
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

<form action="admin/boardDeleteAdmin" id="modifyForm" method="post">
			<input type="hidden" name="board_numbers" value="${boardDetail.board_numbers}">
	<table width="400" cellpadding="0" cellspacing="0" border="1" readOnly>
	
			
			<tr>
				<td> 글 번호 </td>
				<td><input id="board_numbers" name="board_numbers" value="${boardDetail.board_numbers}" readonly/></td>
			</tr>
			
			<tr>
				<td> 글 제목 </td>
				<td><input type="text" id="title"name="title" value="${boardDetail.title}"></td>
			</tr>
			<tr>
				<td> 글 내용 </td>
				<td><input type="text" id="contents" name="contents" value="${boardDetail.contents}"></td>
			</tr>
			<tr>
				<td> 조회수 </td>
				<td><input type="text" id="hit" name="hit" value="${boardDetail.hit}"></td>
			</tr>
			
			<tr>
				<td> 작성 날짜 </td>
				<td><input type="text" id="dates" name="dates" value="${boardDetail.dates}"></td>
			</tr>
			
			<tr>
				<td> 위치 </td>
				<td><input type="text" id="location" name="location" value="${boardDetail.location}"></td>
			</tr>
			
			<tr>
				<td> 진행도 </td>
				<td><input type="text" id="process" name="process" value="${boardDetail.process}"></td>
			</tr>
			
			<tr>
				<td> 작성자 </td>
				<td><input type="text" id="member_id" name="member_id" value="${boardDetail.member_id}"></td>
			</tr>
			<tr>
				<td> 리스트 번호 </td>
				<td><input type="text" id="boardlist_numbers" name="boardlist_numbers" value="${boardDetail.boardlist_numbers}"></td>
			</tr>
			<tr>
				<td> 필터 번호 </td>
				<td><input type="text" id="filter_numbers" name="filter_numbers" value="${boardDetail.filter_numbers}"></td>
			</tr>
			
			<tr>
				<a href="content_view?member_id=${boardDetail.member_id}">목록보기</a> &nbsp;&nbsp;
				<button class="btn btn-default" type="submit">게시글 삭제</button>
			</tr>
		
	</table>
	</form>
	
</body>


</html>