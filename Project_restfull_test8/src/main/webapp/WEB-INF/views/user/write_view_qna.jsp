<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>RestFull indexPage</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- 제이쿼리  -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        
        <!-- 부트 스트랩  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fawtmt0h7b&submodules=geocoder"></script>
		<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/ckeditor/ckeditor.js"></script>
		 --%>
		 <style>
		</style>
</head>
<body>
<!-- 문의글 작성 -->
<form action="<%=request.getContextPath() %>/user/ask_write" method="post" enctype="multipart/form-data">
	<!-- hidden -->
	<sec:authentication var="principal" property="principal" />
	<input type="hidden" name="member_id" value="${principal.user.member_id}"/>
	<input type="hidden" name="boardlist_numbers" value="<c:out value='4'/>">
	<input type="hidden" name="filter_numbers" value="<c:out value='0'/>">
	
	<!-- write Data -->
	<input type="file" id="fileupload" name="file" multiple="multiple" /> 
	<hr>
	
	제목 : <input type="text" name="title">
	<hr/>
	내용 : <textarea rows="10" cols="50" name="contents" placeholder="최대 500자 까지 작성 가능합니다." maxlength="500">
	</textarea>
	<hr/>
	<input type="submit" id="submit" value="완료" style="position:absolute;"/>
</form>
</body>
</html>