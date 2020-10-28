<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>RestFuLL | error</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>   
<article>
	<div class="container">
		<div class="row">
			<c:if test="${requestScope['javax.servlet.error.status_code'] == 400}">
				<p class="error-message">잘못 된 요청입니다.</p>    
			</c:if>	
			
			<c:if test="${requestScope['javax.servlet.error.status_code'] == 404}">
				<p class="error-message">요청하신 페이지를 찾을 수 없습니다.</p>    
			</c:if>
			
			<c:if test="${requestScope['javax.servlet.error.status_code'] == 405}">
				<p class="error-message">요청된 메소드가 허용되지 않습니다.</p>    
			</c:if>
			
			<c:if test="${requestScope['javax.servlet.error.status_code'] == 500}">
				<p class="error-message">서버에 오류가 발생하여 요청을 수행할 수 없습니다.</p>
			</c:if>
			
			<c:if test="${requestScope['javax.servlet.error.status_code'] == 503}">
				<p class="error-message">서비스를 사용할 수 없습니다.</p>
			</c:if>
			<a href="${pageContext.request.contextPath}/">HOME으로 돌아가기</a>
				<br/>
			<img src="resources/img/error.png" style="width:30%; margin:0 auto"/>
		</div>
	</div>
</article>
	
	
	
	<%@ include file="/WEB-INF/include/js-footer.jsp"%>
</body>
</html>