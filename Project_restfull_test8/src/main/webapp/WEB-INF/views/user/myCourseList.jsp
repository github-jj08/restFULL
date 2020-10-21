<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h2>내 여행코스 목록</h2>
   <hr/>
   <table style="border:1px">   
      <tr>
         <td>여행코스명</td>
         <td>작성일</td>
      </tr>
      <c:forEach items="${list}" var="vo">
      <tr>
         <td><a href="course_view?member_id=<sec:authentication property="principal.user.member_id"/>&serialNum=${vo.serialNum}">${vo.tcAlias}</a></td>
         <td>${vo.dates}</td>
      </tr>
      </c:forEach>
   </table>
   
   <h3>[<a href="userHome">돌아가기</a>]</h3>
   
</body>
</html>