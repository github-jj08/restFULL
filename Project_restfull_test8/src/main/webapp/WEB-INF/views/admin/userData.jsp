<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Content view</title>
         <style>
		table, td{
			text-align : center;
			margin : auto;
		}
	
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
    $(function() {
    	$("#cancel").on("click", function(){

			location.href = "/restFull/";

		});
		if($("#adminUserModify").submit(function() {
			alert("수정하시겠습니까?");
			if($("#pw").val() !== $("#pw2").val()){
				alert("비밀번호가 다릅니다.");
				$("#pw").val("").focus();
				$("#pw2").val("");
				return false;
			}else if ($("#pw").val().length < 8) {
				alert("비밀번호는 8자 이상으로 설정해야 합니다.");
				$("#pw").val("").focus();
				return false;
			}else if($.trim($("#pw").val()) !== $("#pw").val()) {
				alert("공백은 입력이 불가능합니다.");
				return false;
			}
			else if($("#member_id").val()==""){
				alert("아이디를 입력해주세요.");
				$("#member_id").focus();
				return false;
			}
			else if($("#pw").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#pw").focus();
				return false;
			}
			else if($("#name").val()==""){
				alert("성명을 입력해주세요.");
				$("#name").focus();
				return false;
			}
			else if($("#birth").val()==""){
				alert("생년월일을 입력해주세요.");
				$("#name").focus();
				return false;
			}
			else if($("#gender").val()==""){
				alert("성별을 입력해주세요.");
				$("#name").focus();
				return false;
			}
			else if($("#phone").val()==""){
				alert("연락처을 입력해주세요.");
				$("#name").focus();
				return false;
			}
			else if($("#email").val()==""){
				alert("이메일을 입력해주세요.");
				$("#name").focus();
				return false;
			}
			
			
		}));
		
	});
    </script>
    <script>
    function onlyNumber(){

        if((event.keyCode<48)||(event.keyCode>57))

           event.returnValue=false;

	}

    </script>
</head>
<body>
	<h4><a href="${pageContext.request.contextPath}/admin/userList">관리자 메인으로 돌아가기</a></h4>
	<table width="400" cellpadding="0" cellspacing="0" border="1">
		<form method="POST" action="adminUpdate">
			<tr>
				<td> 이름 </td>
				<td><input type="text" id="name" name="name" value="${userDetail.name}"></td>
			</tr>
			
			<tr>
				<td> 아이디 </td>
				<td><input type="text" id="member_id"name="member_id" value="${userDetail.member_id}"></td>
			</tr>
			<tr>
				<td> 생년월일 </td>
				<td><input type="text" id="birth" name="birth" value="${userDetail.birth}"></td>
			</tr>
			
			<tr>
				<td> 성별 </td>
				<td><input type="text" id="gender" name="gender" value="${userDetail.gender}"></td>
			</tr>
			
			<tr>
				<td> 연락처 </td>
				<td><input type="text" id="phone" name="phone" value="${userDetail.phone}"></td>
			</tr>
			
			<tr>
				<td> 이메일 </td>
				<td><input type="text" id="email" name="email" value="${userDetail.email}"></td>
			</tr>
			<tr>
				<td> 회원등급 </td>
				<td>
				<select type="text" id="grade_name" name="grade_name">
				<option> 뚜벅이 </option>
        		<option> 자전거 </option>
        		<option> 기차 </option>
        		<option> 비행기 </option>
				</select>
				</td>
			</tr>
			<script>
				var grade = "<c:out value='${userDetail.grade_name}'/>";
				$("#grade_name").val(grade).attr("selected", "selected");
			</script>
			<tr>
				<td> 회원 권한 </td>
				<td>
				<select type="text" id="authority_name" name="authority_name">
				<option> ROLE_USER </option>
        		<option> ROLE_ADMIN </option>
        		</select>
        		</td>
			</tr>
			<script>
				var authority = "<c:out value='${userDetail.authority_name}'/>";
				$("#authority_name").val(authority).attr("selected", "selected");
			</script>
			<tr>
				<td colspan="2"> 
				<input type="submit" value="수정"> &nbsp;&nbsp; 
				<a href="${pageContext.request.contextPath}/admin/userList">유저 목록보기</a> &nbsp;&nbsp; 
				<button class="btn btn-default" onclick="adminUserDelete()">회원 삭제</button> 
				</td>
			</tr>
		</form>	
	</table>
	<p> </p>
	
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>게시글 번호</td>
			<td>게시글 제목</td>
			<td>회원 아이디</td>
			<td>작성 날짜</td>
		</tr>
		<c:forEach items="${admin_board}" var="admin_board">
		<tr>
			<td>${admin_board.board_numbers}</td>
			<td><a href="${pageContext.request.contextPath}/admin/boardView?board_numbers=${admin_board.board_numbers}">${admin_board.title}</a></td>
			<td>${admin_board.member_id}</td>
			<td>${admin_board.dates}</td>
		</tr>
		</c:forEach>
	</table>
	
	
</body>
<script>

function adminUserDelete() {

	var yn = confirm("회원을 탈퇴시키시겠습니까?");
	
	if(yn){
		
			$.ajax({
				url : "/restfull/admin/userDeleteAdmin",
				async: true,
				type : "POST",
				dataType : "text json",
				contentType: "application/json",
				data : JSON.stringify({member_id: $('#member_id').val()}),
				
				success: function(data) {
					console.log(data);
					const isSuccess = data.statusCode === 200;
					if(isSuccess){
						alert("회원이 탈퇴되었습니다.");
						location.href = "/restfull/admin/userList";
					}else{
						alert("비밀번호를 다시 입력해 주세요");
					}
				},
				error: function(err) {
					alert("알수 없는 에러 발생");
				}
			})
	}else{
		alert("회원 탈퇴가 취소되었습니다.");
	}
}
	</script>
</html>