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
			<!-- 메인 컨텐츠  -->
			<div class="contentswrapper">
				<input type="hidden" name="board_numbers" value="${content_view.board_numbers}">
				<div id="leftbox" style="float:left; width:50%;">
					<div id="photo-view">
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
							       $('<div class="carousel-item"><img src="'+filelist[i].filedirectory+'" width=\"460\" height=\"345\""></div>').appendTo('.carousel-inner');
							       $('<li data-target="#myCarousel" data-slide-to="'+i+'"></li>').appendTo('.carousel-indicators')
							     	}
							     $('.carousel-item').first().addClass('active');
							     $('.carousel-indicators>li').first().addClass('active');
							     $('#myCarousel').carousel();
							});
						</script>

						    <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
						     
						    </button>
						    <div class="dropdown-menu">
						      <a class="dropdown-item" href="modify?board_numbers=${content_view.board_numbers}">수정</a>
						      <a class="dropdown-item active" href="delete?board_numbers=${content_view.board_numbers}">삭제</a>
						      <a class="dropdown-item disabled" href="#">신고</a>
						    </div>
						  </div>
					</div>
					
					<div>
						<h2><p>${content_view.title}</p></h2>
						<p>${content_view.member_id}</p>
						<p>${content_view.dates}</p>
						<p>${content_view.hit}</p>
						<p>${content_view.contents}</p>
					</div>
	
</body>
</html>