<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>RestFuLL | 상품정보</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
            
    <div class="container">
        <div class="row">
            <div class="col-lg-4 offset-lg-4">
               <input type="hidden" name="goods_numbers" value="${content_view.goods_numbers}">
                <div class="goods_info">
                    <h3>상품 정보</h3>
                    
                    <div class="board-name">
						여행지명 : ${content_view.destination_name}
                        <hr/>
                    </div>
                    
                     <div class="board-contents">
						판매업자 : <span>${content_view.seller}</span><br/>
						상품명 : <span>${content_view.name}</span><br/>
						상품 가격 : <span>${content_view.price}</span><br/>
						상품 재고수량: <span>${content_view.amount}</span><br/>
						상품 판매 상태 : <span>${content_view.status}</span><br/>
						상품 판매 시작일 : <span>${content_view.sellstart}</span><br/>
						상품 판매 종료일 : <span>${content_view.sellend}</span><br/>
                    </div>
                </div>
            </div>   
        </div>
    </div>
    
</body>
</html>