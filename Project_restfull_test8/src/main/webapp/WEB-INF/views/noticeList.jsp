<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>RestFuLL | 공지사항</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

	
</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>

  <!-- Blog Section Begin -->
    <section class="blog-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-8 order-1 order-lg-1">
                    <div class="blog-sidebar">
                        <div class="blog-catagory">
                            <h4>고객센터</h4>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/notice/<c:out value='2'/>">공지사항</a></li>
                                <li><a href="${pageContext.request.contextPath}/notice/<c:out value='5'/>">이벤트</a></li>
                                <li><a href="${pageContext.request.contextPath}/FAQ">자주하는질문</a></li>
                                <li><a href="${pageContext.request.contextPath}/personal">약관 및 정책</a></li>

                            </ul>
                        </div>
                       
                      
                    </div>
                </div>
                
                
                <!--ajax로 받아야하는 부분-마이페이지 start-->
                <div class="col-lg-9 order-2 order-lg-2">
                    <div class="row">
                        <div class="col-lg-12 col-sm-12">
                            <div class="blog-item">
                                <table id="list-table" width="500" cellpadding="0" cellspacing="0" border="1">
									<tr>
										<td>구분</td>
										<td>제목</td>
										<td>작성일</td>
										<td>조회수</td>
									</tr>
									<c:forEach items="${noticelist}" var="vo">
									<tr>
										<td>${vo.boardlistName}</td>
										<td><a href="${pageContext.request.contextPath}/notice/content_view?board_numbers=${vo.board_numbers}">${vo.title}</a></td>
										<td>${vo.dates}</td>
										<td>${vo.hit}</td>
									</tr>
									</c:forEach>
								</table>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </section>
   <!--ajax로 받아야하는 부분-마이페이지 start-->




<!--                         <div class="col-lg-12 col-sm-12">
                            <div class="blog-item">
                                <div class="bi-pic">
                                    <img src="img/blog/blog-2.jpg" alt="">
                                </div>
                                <div class="bi-text">
                                    <a href="./blog-details.html">
                                        <h4>This was one of our first days in Hawaii last week.</h4>
                                    </a>
                                    <p>Fashion <span>- May 19, 2019</span></p>
                                </div>
                            </div>
                        </div>
 -->


<!-- 	<h3><button onclick="history.go(-1);">돌아가기</button></h3> -->
	
<%@ include file="/WEB-INF/include/js-footer.jsp"%>
</body>
</html>
