<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>RestFuLL | 메인페이지</title>
    <%@ include file="/WEB-INF/include/plugins.jspf"%>
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Section Begin -->
    <header class="header-section">
        <div class="header-top">
            <div class="container">          
                
             	<div class="ht-right">
		           	<sec:authorize access="isAnonymous()">
		                <button type="button" class="login-panel">
		                   	<a href="${pageContext.request.contextPath}/login">로그인</a>  
		                </button>
		            </sec:authorize>
	             	
	             	<sec:authorize access="isAuthenticated()">
	             		<sec:authentication var="principal" property="principal"/>
						<input type="hidden" name="member_id" value="<sec:authentication property="principal.user.member_id"/>"/>
						<div class="showloign">
							<strong><sec:authentication property="principal.user.member_id"></sec:authentication></strong>님 환영합니다.
			              	<a href="<c:url value="/user/userHome" />">🏠‍💁‍♀️‍🙋‍♀️💁‍♂️💒</a>
						</div>
		          
			          	<c:if test="${principal.user.authority_name == 'ROLE_ADMIN'}">
			            	[<a href="${pageContext.request.contextPath}/admin/adminHome">관리자 홈</a>]
			         	</c:if>
			         	
			         	<form action = "${pageContext.request.contextPath}/kakaologout" method="post">
							<button type="submit" name="submit" id="kakaosubmit">로그아웃</button>
						</form>
					</sec:authorize>
               	</div>  
            </div>
         </div>
         
 		<!-- 자동완성 자바스크립트 -->
        <script>
        $(function() {
        	
	        var searchSource = [
				"부산","부천","부경",
				"서울","서산","광주","전주","여수","여주","대전",
				"제주도","거제도","인천","양양","강릉"
	        ];
	        
	        $("#searchbox").autocomplete({
	        	source: searchSource,
	        	select : function(event, ui) {
	        		console.log(ui.item);
	        	},
	        	focus :function(event, ui) {
	        		return false;
	        	},
	        	minLength:1,
	        	autoFocus : true,
	        	classes : {
	        		"ui-autocomplete" : "highlight"
	        	},
	        	delay : 500,
	        	close : function(event) {
	        		console.log(event);
	        	}
	        	
	        });
  
   		 });
        </script>
         
        <div class="container">
                <div class="inner-header">
                    <div class="row">
                        <div class="col-lg-2 col-md-2">
                            <div class="logo">
                                <a href="./rs-mainpage.jsp">
                                    <img src="resources/img/LOGOsmall.png" alt="">
                                </a>
                            </div>
                        </div>
                         <div class="col-lg-6 col-md-6 offset-lg-1 search-top">
                           <form name="searchform" method="post" action="#">
                                <div class="advanced-search">
                                    <div class="input-group">
                                        <input type="text" id="searchbox" placeholder="어느 지역으로 여행을 가시나요?">
                                        <button type="submit" id="submit" value="검색"><i class="ti-search"></i></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
        </div>
  
        <div class="nav-item">
            <div class="container">
                <nav class="nav-menu mobile-menu">
                    <ul>
                        <li class="active"><a href="./rs-mainpage.jsp">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/write_view">글작성</a></li>
                        <li><a href="travelcourse">여행코스작성</a></li>
                        <li><a href="./rs-Servicecenter.jsp">공지사항</a></li>
                        <li><a href="#">이벤트</a></li>
                        <li><a href="./rs-Servicecenter_faq.jsp">자주하는질문</a></li>
                        <!--<li><a href="#">로그인</a></li>-->
                    </ul>
                </nav>
                <div id="mobile-menu-wrap"></div>
            </div>
        </div>
    </header>
</body>
</html>