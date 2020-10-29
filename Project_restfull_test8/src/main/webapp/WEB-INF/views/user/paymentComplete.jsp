<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RestFuLL | 구매완료</title>
</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>
    <!-- Register Section Begin -->
    <div class="register-login-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 offset-lg-4">
                    <div class="register-form">
                        <h2>구매해주셔서 <br/> 감사합니다.</h2>
                    	<div class="payComplete-btn">
							<button type="submit" class="site-btn" style="">
								<a type="button" class="do-btn" href="${pageContext.request.contextPath}/user/myCourseList?member_id=<sec:authentication property="principal.user.member_id"/>">여행코스<br/>보러가기</a>
							</button>
                    	</div>
                   </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Register Form Section End -->
    

 	<%@ include file="/WEB-INF/include/js-footer.jsp"%>
</body>
</html>