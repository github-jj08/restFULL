<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fawtmt0h7b"></script>
	<script type="text/javascript" src="resources/js/MarkerClustering.js"></script>

</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>
	<!-- 게시글 목록 -->
	<div class="container contents">
		<div class="row">
		<!-- 게시물 list -->
			<div style="width:100%; height:500px; float:left">
				<h2>리스트 뽑기</h2>
					<c:forEach items="${boardlist}" var="vo">
						<div class="col-xs-6 col-md-3 main-postings" style="border:solid 1px">
						    <a href="${pageContext.request.contextPath}/content_view?board_numbers=${vo.board_numbers}" class="thumbnail">
						      <img src="${vo.thumbnail }"/>
						      <div class="caption">
					                <h3>${vo.title}</h3>
					                <p>${vo.location}</p>
					                <p>${vo.destinationVO.jibunaddress}</p>
					          </div>
						    </a>
						</div>
					</c:forEach>
			</div>
		</div>
	</div>

<hr/>
<%@ include file="/WEB-INF/include/js-footer.jsp"%>

</body>
</html>