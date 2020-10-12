<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>회원가입</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	 
    <script type="text/javascript"></script>
    <script>
    var chkId = false;
    var regexp = /[0-9a-zA-Z]/;
    var text = $("#member_id").val();
    
    $(function() {
    	$("#cancel").on("click", function(){

			location.href = "/board/home";

		});
		if($("#pwForm").submit(function() {
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
			} else if(chkId == false) {
				alert("아이디 중복 체크를 진행해주세요.");
	    		console.log("id check is not done")
	    		chkId = false;
	    		return false;
			} else if(chkId == true){
				console.log("id check is done")
				chkId = true;
				return false;
			}
			
			
		}));
		 for(var i = 0; i < text.length; i++){
				if(text.charAt(i) != " " && regexp.text(text.charAt(i)) == false){
					alert("한글 및 특수문자는 입력이 불가능 합니다.");
					$('#member_id').val("");
					return;
				}
				if(text.length < 4){
					alert("ID는 4자 이상입니다.");
					$('#member_id').val("");
					return;
				}
			}
		
		
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

<h1>회원가입</h1>

<form:form id ="pwForm" name="JoinMember" action="addUser" method="POST">

    <div class="form-group">
        <label for="member_id">아이디</label>
        <p><input type="text"  id="member_id" name="member_id" placeholder="ID" required/>  
        <p><button class="btn btn-default" onclick="chkId()">중복확인</button></p>
    </div>
    
    <div class="form-group">
    <p></p>
        <label for="pw">비밀번호</label>
        <p><input type="password" id="pw" name="pw" placeholder="PASSWORD" required/>
    </div>
    
    <div class="form-group">
    <p></p>
        <label for="pw2">비밀번호 확인</label>
       <p> <input type="password" id="pw2" name="pw2" placeholder="PASSWORD" required/>
    </div>
    
	<div class="form-group">
	<p></p>
        <label for="name">이름</label>
      <p><input type="text" id="name" name="name" placeholder="NAME" required/>
    </div>
    
    <div class="form-group">
    <p></p>
        <label for="birth">생년월일</label>
        <p><input type="date" id="birth" name="birth" placeholder="BIRTH" required/>
    </div>
    
    <div class="form-group">
    <p></p>
        <label for="gender">성별</label>
        <p><select class="form-control" id="gender" name="gender">
        <option> 남성 </option>
        <option> 여성 </option>
        </select>
	</div>
    
	<div class="form-group">
	<p></p>
        <label for="phone">휴대전화 ('-' 없이 입력해주세요)</label>
        <p><input type="text" id="phone" name="phone" placeholder="PHONE" required onkeypress="onlyNumber()"/>
    </div>
    
	<div class="form-group">
	<p></p>
        <label for="email">이메일</label>
       <p><input type="email" class="form-control" id="email" placeholder="EMAIL" name="email">
       <p></p>
    </div>
    
    
    <button type="submit" class="btn" id="join_submit" >가입하기</button>
    <button class="cencle btn btn-danger" type="button" id="cancel">취소</button>
</form:form>

</body>

	<script>
	function chkId() {
		var id = $('#member_id').val();
		
		$.ajax({
			url : "idCheck",
			type : "GET",
			dataType : "text",
			contentType: 'text/plain; charset=utf-8',
			data : { id },
			
			success : function(data) {	//data : checkSignup에서 넘겨준 결과값
				var result = JSON.parse(data);
				var checkSuccess = result.statusCode === 200;
				
				if(checkSuccess){
					alert("사용 가능한 아이디 입니다.");
				} else {
					alert("사용 불가능한 아이디 입니다.");
					checkSuccess = 400;
				}
				
	         }, 
	         error : function(req, status, errThrown) {
			 alert("network error occur");
			}
		});
	}	
	</script>
</html>