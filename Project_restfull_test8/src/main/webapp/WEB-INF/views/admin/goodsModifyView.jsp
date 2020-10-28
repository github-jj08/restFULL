<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>RestFuLL | 상품주문내역 관리</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
      <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fawtmt0h7b&submodules=geocoder"></script>
</head>
<body>
<%@ include file="/WEB-INF/include/js-header.jsp"%>
	<div class="text-center"><h2>상품주문내역 관리</h2></div>
	<hr/>

 <!-- Blog Section Begin -->
    <section class="blog-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-8 order-2 order-lg-1">
                    <div class="blog-sidebar">
                        <div class="blog-catagory">
                            <h4>관리자페이지</h4>
                            <ul>
                                <li><a href="<c:url value="/admin/userList" />">유저 관리</a></li>
                                <li><a href="<c:url value="/admin/notice" />">공지사항 및 이벤트 관리</a></li>
                                <li><a href="<c:url value="/admin/qnas" />">문의 및 신고 답변</a></li>
                                <li><a href="<c:url value="/admin/dest" />">여행지 및 상품 관리</a></li>
                            	<li><a href="<c:url value="/admin/requestList" />">상품 주문내역 관리</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

      
				  
                <!--관리자페이지 여행지등록table start-->
                <div class="col-lg-9 order-1 order-lg-2">
                    <div class="row">
                        <div class="admin col-lg-12 col-sm-12">
                        	<div class="notice_write">
					         	<div class="dest_info">      
							      <form action="modify" method="post">
							         <!-- hidden -->
							         <input type="hidden" name="goods_numbers" value="${modify_view.goods_numbers}"/>              
							         <input type="hidden" name="destination_numbers" id="destination_numbers" value="${modify_view.destination_numbers}"/>
							         <hr/>
							         <div class="goods_info">
								        <div class="board-contents">
											상품명 : <input type="text" name="name" value="${modify_view.name}"/>
											판매업자 : <input type="text" name="seller" value="${modify_view.seller}"/>
											가격 : <input type="text" name="price" value="${modify_view.price}"/>
											재고수량 : <input type="text" name="amount" value="${modify_view.amount}"/>
											판매상태<select name="status" id="status">
													<option value="1">판매중</option>
													<option value="0">판매중단</option>
												</select>
											판매 시작일
											<br/>
									        <input type="date" id="sellstart" name="sellstart" value="${modify_view.sellstart}"/>
											판매 종료일
											<br/>
											<input type="date" id="sellend" name="sellend" value="${modify_view.sellend}"/>
									     </div>
									  </div>       
									<button type="submit" id="submit">수정</button>
							      </form>
							      <button type="button" onclick="history.go(-1);" class="gobackbtn">돌아가기</button>
							   </div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
<%@ include file="/WEB-INF/include/js-footer.jsp"%>	
</body>
</html>