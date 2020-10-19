<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<html>
	<head>
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	 	
	 	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<title>회원탈퇴</title>
    <script>
    $(document).ready(function() {
		// 취소
		$("#cancel").on("click", function(){

			location.href = "/restfull/";

		});

		$("#submit").on("click", function(){
			if($("#pw").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#pw").focus();
				return false;
			}
			
			$.ajax({
				url : "user/userDelete",
				async: true,
				type : "POST",
				dataType : "text json",
				contentType: "application/json",
				data : JSON.stringify({pw: $('#pw').val()}),
				
				success: function(data) {
					console.log(data);
					const isSuccess = data.statusCode === 200;
					if(isSuccess){
						alert("회원 탈퇴 성공");
						location.href = "/restfull/";
					}else{
						alert("비밀번호를 다시 입력해 주세요");
					}
				},
				error: function(err) {
					alert("알수 없는 에러 발생");
				}
			})

		});


	})
	</script>
		
	</head>
	<body>
	
	<h4>[<a href="<c:url value="/" />">홈으로 돌아가기</a>]</h4>
	<section id="container">
				<div class="form-group has-feedback">
				<sec:authentication var="principal" property="principal"/>
					<label class="control-label" for="userId">아이디</label>
					<input class="form-control" type="text" name="member_id" value="<sec:authentication property="principal.user.member_id"/>" readonly/>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userPass">비밀번호</label>
					<input class="form-control" type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요"/>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userName">성명</label>
					<input class="form-control" type="text" id="name" value="<sec:authentication property="principal.user.name"/>" readonly/>
				</div>
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="submit" id="submit">회원탈퇴</button>
					<button class="cencle btn btn-danger" type="button" id="cancel">취소</button>
				</div>
	</section>
	</body>
	
</html>