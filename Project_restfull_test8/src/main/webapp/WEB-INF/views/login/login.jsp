<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>RestFuLL | 로그인</title>
    <%@ include file="/WEB-INF/include/plugins.jspf"%>
</head>

<body>

<%@ include file="/WEB-INF/include/js-header.jsp"%>


 
 
    <!-- Login Section Begin -->
    <div class="register-login-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 offset-lg-4">
                    <div class="login-form">
                        <h2>Login</h2>
                        
                        <c:url value="/login" var="loginUrl" />
                       <%--  <form action="${pageContext.request.contextPath}/" method="post"> --%>
                        <form:form name="f" action="${loginUrl}" method="POST"> 
						    <c:if test="${param.error != null}">
						        <p>아이디와 비밀번호가 잘못되었습니다.</p>
						    </c:if> 
                            <div class="group-input">
                                <label for="username">아이디</label>
                                <input type="text" id="member_id" name="member_id">
                            </div>
                            <div class="group-input">
                                <label for="pass">비밀번호</label>
                                <input type="password" id="pw" name="pw">
                            </div>
                            <div class="group-input gi-check">
                                <div class="gi-more">
                                    <a href="#" class="forget-pass">ID/ PW 찾기</a>
                                </div>
                            </div>
                            <button type="submit" class="site-btn login-btn">로그인</a></button>
                        </form:form>
                        
                        
                        <div class="switch-login">
                            <a href="${pageContext.request.contextPath}/register" class="or-login">회원가입</a>
                        </div>
                        <!-- 소셜로그인 start -->
                        <div class="social_login">
                        	<!-- 카카오로그인 버튼 -->
                        	<div class="kakao_login">
		                        <a href="https://kauth.kakao.com/oauth/authorize?client_id=8c8d010971d8629ec3188f9ed097f73b&redirect_uri=http://localhost:8282/restfull/kakaologin&response_type=code">
		           				 	<img src="resources/img/kakao_login_medium_wide.png">
		       					</a>
	       					</div>
	       					<!-- 네이버 로그인버튼 -->
	       					
							<div id="naver_id_login" style="text-align: center">
								<a href="${url}"> <img width="223"
									src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" /></a>
									<br />
							
							</div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
                     </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Register Form Section End -->

<%@ include file="/WEB-INF/include/js-footer.jsp"%>


</body>

</html>