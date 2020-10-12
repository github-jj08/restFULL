<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>RestFull indexPage</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
      
      <!-- 제이쿼리  -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        
        <!-- 부트 스트랩  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fawtmt0h7b&submodules=geocoder"></script>
      <%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/ckeditor/ckeditor.js"></script>
       --%>
       <style>
      </style>
</head>
<body>
      
      <form action="modify" method="post">
         <!-- hidden -->
         <input type="hidden" name="goods_numbers" value="${modify_view.goods_numbers}"/>
         <hr/>               
            여행지명 : ${modify_view.destination_name}
            <input type="hidden" name="destination_name" id="destination_name" value="${modify_view.destination_name}"/>
            <hr/>
            상품명 : <input type="text" name="name" value="${modify_view.name}"/>
            판매업자 : <input type="text" name="seller" value="${modify_view.seller}"/>
            가격 : <input type="text" name="price" value="${modify_view.price}"/>
            재고수량 : <input type="text" name="amount" value="${modify_view.amount}"/>
            판매상태    <select name="status" id="status">
                     <option value="1">판매중</option>
                     <option value="0">판매중단</option>
                  </select>
            판매 시작일<br/>
            <input type="date" id="sellstart" name="sellstart" value="${modify_view.sellstart}"/>
            판매 종료일<br/>
            <input type="date" id="sellend" name="sellend" value="${modify_view.sellend}"/>
            
            <input type="submit" value="완료"/>
      </form>
</body>
</html>