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
    
    <script>
 		$(function(){
 			$("#find_Id_btn").click(function(){
 				location.href ='findID';
 			});
 			$("#find_Pw_btn").click(function(){
 				location.href ='findPW';
 			});
 			
 		})
 	</script>
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
                        <!-- 로그인 검사 -->
                        <c:url value="/login" var="loginUrl" />
                        <form:form name="f" action="${loginUrl}" method="POST"> 
                        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
                                <c:if test="${param.error != null}">
							        <p>아이디와 비밀번호가 잘못되었습니다.</p>
							    </c:if>
							    <c:if test="${param.logout != null}">
							        <p>로그아웃 하였습니다.</p>
							    </c:if>  
							<!-- id/pw 입력창 -->
                            <div class="group-input">
                            	<label for="id">아이디</label>
     							<input type="id" class="form-control" id="member_id" placeholder="아이디를 입력해주세요" name="member_id">
                            </div>
                            <div class="group-input">
                           		<label for="pwd">비밀번호 (Password):</label>
     					 		<input type="password" class="form-control" id="pw" placeholder="비밀번호를 입력해주세요" name="pw">
                            </div>
                            <div class="group-input gi-check">
                                <div class="gi-more">
                                    <a id="find_Id_btn"><button type="button">ID찾기</button></a> 
                                    <a id="find_Pw_btn"><button type="button">PW찾기</button></a>
                                </div>
                            </div>
                            
    					<div class="form-group">        
                  			<div class=" col-md-4 aaa align-self-auto">
                       			<button type="submit" class="site-btn login-btn">로그인</button>
                            </div>
                		</div>
                     </form:form> <!-- login form end -->
                     <div class="switch-login">
                        <a href="${pageContext.request.contextPath}/register" class="or-login">회원가입</a>
                     </div>
                        
                        <!-- 소셜로그인 start -->
                        <div class="social_login">
                        	<!-- 카카오로그인 버튼 -->
                        	<div class="kakaobtn">
	                            <a href="https://kauth.kakao.com/oauth/authorize?client_id=8c8d010971d8629ec3188f9ed097f73b&redirect_uri=http://localhost:8282/restfull/kakaologin&response_type=code" class="btn btn-warning btn-user btn-block">
	                              	<img width="240" height="45"
	                               	 	src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"/>
	                            </a>
                        	</div>
	       					<!-- 네이버 로그인버튼 -->
	       					<div id="naver_id_login" style="text-align: center">
								<a href="${naverLoginUrl}"> 
									<img width="223"
										src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" />
								</a>
							</div>
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