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
<%@ include file="/WEB-INF/include/js-header.jsp"%>



<!-- <글쓰기 부분> -->

    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="blog-details-inner">
                        <div class="blog-detail-title">
							<form action="<%=request.getContextPath() %>/write" method="post" enctype="multipart/form-data" class="writeform">
								<!-- hidden -->
								<sec:authentication var="principal" property="principal" />
								<input type="hidden" name="member_id" value="${principal.user.member_id}"/>
								<input type="hidden" name="boardlist_numbers" value="<c:out value='1'/>">
								<input type="hidden" name="filter_numbers" value="<c:out value='1'/>">
								
								<!-- write Data -->
								<div class="group-input">
									<label for="picturech">사진 선택 </label>
									<div class="writepic">
									    <input type="file" class="uploadfile" id="fileupload" name="file" multiple="multiple" /> 
                                    </div>
								</div>
								
								
								<div class="group-input">
									<label for="title">제목 </label>
									<div class="writetitle">
									    <input type="text" name="title" class="write_tilte">
                                    </div>
								</div>
								
								<div class="group-input">
									<label for="contents">내용 </label>
									<div class="writecontentx">
									    <textarea rows="10" cols="50" name="contents" class="write_content" placeholder="최대 500자 까지 작성 가능합니다." maxlength="500"></textarea>
									</div>
								</div>
								
								<div class="group-input">
									<label for="location">대표위치 검색 :</label>
									<input type="text" class="write_loc" name="location">
								</div>
								
								
								<input type="submit" class="site-btn write-btn" value="글 게시"/>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	
<%@ include file="/WEB-INF/include/js-footer.jsp"%>
</body>
</html>