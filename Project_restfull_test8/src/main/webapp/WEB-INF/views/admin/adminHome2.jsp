<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>RestFuLL | 개인정보처리방침</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

	
</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>

   <hr>

 <!-- Blog Section Begin -->
    <section class="blog-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-8 order-1 order-lg-1">
                    <div class="blog-sidebar">
                        <div class="blog-catagory">
                            <h4>관리 메뉴</h4>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/admin/userList">유저 관리</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/notice">공지사항 및 이벤트 관리</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/qnas">문의 및 신고 답변</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/dest">여행지 및 상품 관리</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/requestList">상품 주문내역 관리</a></li>
                            </ul>
                        </div>
                       
                      
                    </div>
                </div>
                
                <!--개인정보처리방침 start-->
                <div class="col-lg-9 order-2 order-lg-2">
					관리자 정보 ?
                </div>
            </div>
        </div>
    </section>
   <!--개인정보처리방침 end-->



<%@ include file="/WEB-INF/include/js-footer.jsp"%>

</body>
</html>