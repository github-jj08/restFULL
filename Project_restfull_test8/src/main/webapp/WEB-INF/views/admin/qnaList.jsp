<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   
</head>
<body>
   <select name="boardlist_numbers" id="boardlist_numbers">
      <option value="3" selected>신고글</option>
      <option value="4">문의사항</option>
   </select>   
   
   <table id="list-table" width="500" cellpadding="0" cellspacing="0" border="1">
      <tr>
         <td>구분</td>
         <td>제목</td>
         <td>작성자</td>
         <td>작성일</td>
      </tr>
   </table>
   <script>
   $(function(){ 
         
      function getList(){
            
         var boardlist = $("#boardlist_numbers option:selected").val();
           
         $.ajax({
            url: "${pageContext.request.contextPath}/admin/qnas/" + boardlist,
               type: "GET",
               dataType:"json",
               success: function (result) {
                   console.log("성공");   
                     setTable(result);
               }
         
          });
      };

      function setTable(result){
         var htmls="";
          $("#list-table").html("");   
   
         $("<tr>" , {
            html : "<td>" + "구분" + "</td>"+  // 컬럼명들
                  "<td>" + "제목" + "</td>"+
                  "<td>" + "작성자" + "</td>"+
                  "<td>" + "작성일" + "</td>"      
         }).appendTo("#list-table") // 이것을 테이블에붙임
         
         if(result.length < 1){
            htmls += "등록된 글이 없습니다.";
         } else {
                   $(result).each(function(){                                                          
                       htmls += '<tr>';
                       htmls += '<td>'+ this.boardName + '</td>';
                       htmls += '<td><a href="${pageContext.request.contextPath}/admin/qnas/content_view?board_numbers=' + this.board_numbers + '">' + this.title + '</a></td>';
                      htmls += '<td>'+ this.member_id + '</td>';
                       htmls += '<td>'+ this.dates + '</td>';   
                       htmls += '</tr>';   //여기까지가 결과물 출력
                  });   //each end
   
         }
   
         $("#list-table").append(htmls);
         }
      
      getList();
      
      $('#boardlist_numbers').on('change', function(){
         getList();
      });
   });
   </script>
   <h3><button onclick="history.go(-1);">돌아가기</button></h3>
</body>
</html>