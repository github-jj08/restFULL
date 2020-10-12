<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<a href="${pageContext.request.contextPath}/admin/board/dest/">여행지 목록으로 돌아가기</a>

            <div class="container">
            <a class="dropdown-item" href="modify?destination_name=${content_view.destination_name}">수정</a>
            <a class="dropdown-item active" href="delete?destination_name=${content_view.destination_name}">삭제</a>
               <h4>여행지 정보</h4><hr/>
               <div class="board-contents">
                  여행지명 : ${content_view.destination_name}<hr/>
                  ------위치정보----------------------------------------<br/>
                  지역 구분 : <span>${content_view.sidoguVO.sidoname}</span><br/>
                  지번 주소 : <span>${content_view.jibunaddress}</span><br/>
                  도로명 주소 : <span>${content_view.doroaddress}</span><br/>
                  GPS-X : <span>${content_view.lat}</span><br/>
                  GPS-Y : <span>${content_view.lng}</span><br/>
               </div>
               <hr/>
               <h4>관련 상품</h4>
               <div>
                  <a href="${content_view.destination_name}/goods/write_view">상품등록</a>
                  <table border="1px">
                     <tr>
                        <td>여행지명</td>
                        <td>상품명</td>
                        <td>판매업자</td>
                        <td>가격</td>
                        <td>재고수량</td>
                        <td>판매상태</td>
                        <td>판매 시작일</td>
                        <td>판매 종료일</td>
                     </tr>
                     
                     <c:forEach items="${goodsList}" var="vo">
                     <tr>
                        <td>${vo.destination_name}</td>
                        <td><a href="goods/content_view?goods_numbers=${vo.goods_numbers}">${vo.name}</td>
                        <td>${vo.seller}</td>
                        <td>${vo.price}</td>
                        <td>${vo.amount}</td>
                        <td>${vo.status}</td>
                        <td>${vo.sellstart}</td>
                        <td>${vo.sellend}</td>
                     </tr>
                     </c:forEach>
                  </table>
                  
               </div>
               
            </div>
   
</body>
</html>