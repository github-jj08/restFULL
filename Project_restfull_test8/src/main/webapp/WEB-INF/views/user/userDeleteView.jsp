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
			location.href = "${pageContext.request.contextPath}/user/userHome";
		});

		$("#delete").on("click", function() {
			if($("#pw").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#pw").focus();
				return false;
			}
			
			$.ajax({
				url : "${pageContext.request.contextPath}/user/userDelete",
				async: true,
				type : "POST",
				dataType : "json",
				contentType: "application/json",
				data : JSON.stringify({pw: $('#pw').val()}),
				success: function(data) {
					console.log(data);
					const isSuccess = data.statusCode === 200;
					if(isSuccess){
						alert("회원 탈퇴 성공");
						location.href = "${pageContext.request.contextPath}/";
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
		<%@ include file="/WEB-INF/include/js-header.jsp"%>
		<h2 class="text-center">회원탈퇴 페이지 입니다.</h2>
		<hr>
		    <section class="blog-section spad">
		        <div class="container">
		            <div class="row">
		                <div class="col-sm-3 order-1">
		                    <div class="blog-sidebar">
								<div class="blog-catagory menu-background">
							        <h4>마이페이지</h4>
							        <ul>
							            <li><p>[<a href="<c:url value="userModify" />">개인정보 수정</a>]</p></li>
							            <li><p>[<a href="myList?member_id=<sec:authentication property="principal.user.member_id"/>">내 게시글 보기</a>]</p></li>
							            <li><p>[<a href="myLikeList?member_id=<sec:authentication property="principal.user.member_id"/>">좋아요 한 글 목록</a>]</p></li>
							            <li><p>[<a href="myCourseList?member_id=<sec:authentication property="principal.user.member_id"/>">내 여행코스 보기</a>]</p></li>
							            <li><p>[<a href="paymentList?member_id=<sec:authentication property="principal.user.member_id"/>">내 결제내역 보기</a>]</p></li>
							            <li><p>[<a href="reportList?member_id=<sec:authentication property="principal.user.member_id"/>">내 신고내역 보기</a>]</p></li>
							            <li><p>[<a href="qnaList?member_id=<sec:authentication property="principal.user.member_id"/>">내 문의내역 보기</a>]</p></li>
							            <li style="background-color: #8a93c0;"><p>[<a href="<c:url value="userDeleteView" />">회원탈퇴</a>]</p></li>
							        </ul>
						        </div>
					        </div>
				        </div>
				        <div class="col-sm-9 order-2">
							<section id="container">
								<div class="form-group has-feedback">
								<sec:authentication var="principal" property="principal"/>
									<label class="control-label" for="userId">아이디</label>
									<input class="form-control" type="text" name="member_id" value="<sec:authentication property="principal.user.member_id"/>"/>
								</div>
								<div class="form-group has-feedback">
									<label class="control-label" for="userPass">비밀번호</label>
									<input class="form-control" type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요"/>
								</div>
								<div class="form-group has-feedback">
									<label class="control-label" for="userName">성명</label>
									<input class="form-control" type="text" id="name" value="<sec:authentication property="principal.user.name"/>" readonly="readonly"/>
								</div>
								<div class="form-group has-feedback">
									<button class="btn btn-success" type="delete" id="delete">회원탈퇴</button>
									<button class="cencle btn btn-danger" type="button" id="cancel">취소</button>
								</div>
							</section>
					    </div>
	                </div>
	            </div>
			</section>
			<%@ include file="/WEB-INF/include/js-footer.jsp"%>
	</body>
</html>