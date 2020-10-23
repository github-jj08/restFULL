<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <!-- 하트아이콘,,, -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
  <style>
  	#photo-view{
  		background-color: #8A93C0;
  		border : 1px;
  		width:100%;
  		height:50%;
  	}
  
  	#myCarousel {
  		background-color: black;
  		width: 100%;
  		height: 500px;
  		text-align:center;
  		justify-content:center;
  	}
	
  	.carousel-item,
  	.carousel-inner,
	.carousel-inner img {
		height: 100%;
		width: auto;
		text-align: center;
	}
	
	.carousel-inner {
		text-align: center;
	}
	
	.likebutton, 
	.btn dropdown-toggle {
		background-color:#8A93C0;
		color:white;
	}
	
  </style>
</head>
<body>

<%@ include file="/WEB-INF/include/js-header.jsp"%>


<!-- 메인 컨텐츠  -->
<section class="write-section spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-7 order-lg-1">
				<input type="hidden" name="board_numbers" value="${content_view.board_numbers}">
				<div class="leftbox">
					<div class="photo-view">
					
						<!-- 사진들 -->
						<div id="myCarousel" class="carousel slide" data-ride="carousel">
							<!-- Indicators -->
						    <ul class="carousel-indicators"> </ul>
						     
						    <!-- Wrapper for slides -->
						    <div class="carousel-inner"> </div>
						     
						    <!-- Controls -->
						    <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
						       <span class="carousel-control-prev-icon"></span>
						    </a>
						    <a class="carousel-control-next" href="#myCarousel" data-slide="next">
						       <span class="carousel-control-next-icon"></span>
						    </a>
						</div>
						
						 <!--------------------------
                        	 Carousel 스크립트 start
                         ----------------------------->
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
							       $('<div class="carousel-item" style="width:100%; text-align:center; margin:0px auto;"><img src="'+filelist[i].filedirectory+'" style="height:100%; line-height:auto; "></div>').appendTo('.carousel-inner');
							       $('<li data-target="#myCarousel" data-slide-to="'+i+'"></li>').appendTo('.carousel-indicators')
							    }
						    	
							    $('.carousel-item').first().addClass('active');
							    $('.carousel-indicators>li').first().addClass('active');
							     //$('#myCarousel').carousel();
							});
														
						</script>
						 <!--------------------------
                        	 Carousel 스크립트 end
                         ----------------------------->
						

						</div>
						<div class="write-view">
                            <div class="board-contents">
                                <div class="view-hit">조회수 - ${content_view.hit} 회</div>
                                <div class="view-dates">${content_view.dates} </div>
                                <br/>
                                <div class="view-id">${content_view.member_id}</div>
                                <div class="view-title">${content_view.title}</div>
                                <div class="view-contents">${content_view.contents}</div>
                            </div>
                        </div>
						  
						  
					</div>
					

					
					
					
	<%@ include file="/WEB-INF/include/js-footer.jsp"%>
	
</body>
</html>