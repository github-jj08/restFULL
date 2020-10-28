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
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fawtmt0h7b&submodules=geocoder"></script>
</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>

<section class="blog-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2">
				<div class="blog-details-inner">
					<div class="blog-detail-title">
						<form action="modify" method="post" enctype="multipart/form-data" id="boardForm">
							<!-- hidden -->
							<sec:authentication var="principal" property="principal" />
							<input type="hidden" name="member_id" value="${modify_view.member_id}"/>
							<input type="hidden" name="boardlist_numbers" value="<c:out value='1'/>">
							<input type="hidden" name="filter_numbers" value="<c:out value='1'/>">
							<input type="hidden" name="board_numbers" value="${modify_view.board_numbers}"/>

							<!-- write Data -->
							사진 데이터 및 위치정보는 수정이 불가능합니다.
		 					<div id="photo-view">
								<div id="myCarousel" class="carousel slide" data-ride="carousel">
								     <ul class="carousel-indicators"> </ul>
								     
								     <div class="carousel-inner"> </div>
								     
								     <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
								       <span class="carousel-control-prev-icon"></span>
								     </a>
								     <a class="carousel-control-next" href="#myCarousel" data-slide="next">
								       <span class="carousel-control-next-icon"></span>
								     </a>
								</div>
							</div>
						
							<script>
								$(function(){  
									var filelist = new Array();
									
									<c:forEach items="${filelist}" var="file">
										var json = new Object();
										json.filedirectory = "${file.filedirectory}";
										filelist.push(json);
									</c:forEach>
									
									console.log("filelist : " + filelist);
									
							    	for(var i=0 ; i< filelist.length ; i++) {
								       $('<div class="carousel-item"><img src="'+filelist[i].filedirectory+'" style="height:450px; "></div>').appendTo('.carousel-inner');
								       $('<li data-target="#myCarousel" data-slide-to="'+i+'"></li>').appendTo('.carousel-indicators')
								     	}
								     $('.carousel-item').first().addClass('active');
								     $('.carousel-indicators>li').first().addClass('active');
								     $('#myCarousel').carousel();
								});
								
							</script>
		
							<hr/>
							
							<!-- 제목,내용 -->		
							<div class="group-input">
								<div class="writetitle">
			 						<label for="title">제목 </label>
									<input type="text" name="title" value="${modify_view.title}">
								</div>	
							</div>
							
							<div class="group-input">
								<label for="contents">내용 </label>
								<div class="writecontentx">
									<textarea rows="10" cols="50" name="contents" class="write_content" placeholder="최대 500자 까지 작성 가능합니다." maxlength="500" required>${modify_view.contents}</textarea>
								</div>
							</div>						
							
							<!-- 위치  지정 불가 -->
							<div class="group-input">
								<label for="contents">위치 </label>
								<input type="text" name="location" value="${modify_view.location}" readOnly>
							</div>
							<div class="notice_write">
								<button type="submit" id="submit">수정</button>
							</div>
						</form>
						
						<button type="button" onclick="history.go(-1);" class="gobackbtn">돌아가기</button>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
						

	
<%@ include file="/WEB-INF/include/js-footer.jsp"%>	
</body>
</html>