<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>신고 모달창</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style>
  .sortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
  .sortable li { margin:10px 10px 10px 10px ; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 18px;  background-color:#8A93C0;}
  .sortable li span { position: absolute; margin-left: -1.3em; }
  input { border: none 0; margin : 0 auto; border:1px; background-color:#8A93C2;}
  
  .choice-menu {
  		width: 200px;
        height: 250px;
        display: inline-block;
        overflow: auto; 
        cursor:pointer;
  }
  .sido-choice, .sigungu-choice, .trav-btn {
      	
  }
  
 	.course-1day{
 		width:200px;
 		height:300px;
 		background-color:green;
 		border:1px solid black;
 		display:inline-block;
 		position: relative; 
 		margin-top: 5px;
 		margin-left: 1px;
  	}
 	
  </style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( ".sortable" ).sortable();
    $( ".sortable" ).disableSelection(); /* 드래그 해서 순서 변경 가능하게  */
  });
  </script>
</head>
	<body>
	<%@ include file="/WEB-INF/include/js-header.jsp"%>
		<div class="container">
			<div class="row">
				<div class="col-xs-4 col-sm-2"></div>
				<div class="col-xs-4 col-sm-2">
					<div id ="mybtns">
						<h2>sido</h2>
						<div id="sido" class="choice-menu">
							<div class="sido-choice" value="11" 
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">서울/경기</div>
							<div class="sido-choice" value="28"
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">인천</div>
							<div class="sido-choice" value="42"
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">강원</div>
							<div class="sido-choice" value="43"
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">충북</div>
							<div class="sido-choice" value="44"
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">충남/대전</div>
							<div class="sido-choice" value="47"
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">경북/대구</div>
							<div class="sido-choice" value="48"
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">경남/부산/울산</div>
							<div class="sido-choice" value="45"
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">전북</div>
							<div class="sido-choice" value="46"
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">전남/광주</div>
							<div class="sido-choice" value="50"
		                         onMouseover="this.style.background='skyblue';" onMouseout="this.style.background='white';">제주</div>
						</div>
					</div>
				</div> 
				<div class="col-sm-1"></div>
				<div class="col-xs-4 col-sm-2">
					<h2>sigungu</h2>
					<div id="sigungu" class="choice-menu">
					
					</div>
					
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-3">
					<h2>여행지목록</h2>
					<div id="travel-destinations"  class="choice-menu">
					
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-2"></div>
				<div class="col-sm-10">
						<hr/>
						<h2>여행코스등록</h2>
						<button type="button" id="blank-btn" value="공백칸 추가">공백칸 추가</button>
						<button type="button" id="dayplus-btn" value="날짜 추가">날짜 추가</button>
						
						
						<div id="myCourse" >
							<div class="course-1day"  style="position: relative; overflow: auto;">
								<ol class="sortable">
								
								</ol>
							</div>
						</div>
							<button type="button" id="complete-btn" value="확인">확인</button>
							<hr/>
							<div id="goodsList">goodsList</div>
				</div>
			</div>
		</div>
		
		
		
		
<!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> -->
<!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> -->
<!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> --><!-- <!--  --> -->
			
			
			
			
			
			
			
			<!-- 시군구 가져오는 곳 -->
			<script>
				$(function(){
					$(".sido-choice").click(function(){
					
					var sidoCode = $(this).attr("value");
											
					console.log("가져오는지 확인 sidoCode : " + sidoCode);			
					
					$.ajax({
						url: "${pageContext.request.contextPath}/getSigunguCode/" + sidoCode,
				        type: "GET",
				        dataType:"json",
				        success: function (result) {
				         	//옵션 초기화 
				          	$('#sigungu').empty();
				            $(result).each(function(){		
				           		$('#sigungu').append('<div class="sigungu-choice"  value="'+this.sigungu_code+'"  onMouseover="this.style.background='+'\'skyblue\''+';" onMouseout="this.style.background='+'\'white\''+';">'+this.sigunguname+'</div>');
				      		})
						}
					});	//ajax end
					
					});
					
				});
				/* 시군구 가져오는 곳  끝 */
				
				/* 여행지 목록 가져오는 곳 */
				$(document).on("click",".sigungu-choice",function(){  
						var sigungu_code = $(this).attr("value");
												
						console.log("가져오는지 확인 sigungu_code : " + sigungu_code);			
						
						$.ajax({
							url: "${pageContext.request.contextPath}/getDest/"+sigungu_code,
					        type: "GET",
					        dataType:"json",
					        success: function (result) {
					         	//옵션 초기화
					          	$('#travel-destinations').empty();           /* 기존 내용을 비우고 */
					            $(result).each(function(){		
					           		$('#travel-destinations').append('<div class="trav-btn" value="'+this.destination_name+'"  onMouseover="this.style.background='+'\'skyblue\''+';" onMouseout="this.style.background='+'\'white\''+';">'+this.destination_name+'</div>');
					      		})
							}
						});	//ajax end
						
					});
					/* 여행지 목록 가져오는 곳 끝 */
			</script>
		
		
		
		
		
		
			<script>
				
				/*공백추가 버튼 누르는 곳!!! */
				$(function(){
					$("#blank-btn").click(function(){
						console.log("blank");
						$(".sortable").append('<li class="ui-state-default"><div><input type="text" name="course" maxlength="300"/><span><input type="button" class="delete-btn" value="x"></span></div></li>'); 
						
			  		});
				
					
				/* 날짜 추가버튼 누르는 곳!!!!!!! */
					$("#dayplus-btn").click(function(){
						console.log("dayplus-btn");
						var idx = $(this).index();
						$('.course-1day').eq(idx);
						$("#myCourse").append('<div class="course-1day" style="position: relative; overflow: auto;"><ol class="sortable"></ol></div>');
					});
				});
				
				
				
				/* 확인버튼 누르는 곳!!!! */
				$(document).on("click","#complete-btn",function(){  
						console.log("complete");
						var length= $('input[name="course"]').length;
						console.log("length="+length);
						var myCourse = new Array();
						
						for(var i =0;i<length;i++){
							myCourse.push($('input[name="course"]').eq(i).val());
						}
						console.log("myCourse="+myCourse);
						var clickCount=0;
						if(clickCount==0){
							clickCount++;
							console.log("goodsList 실행")
							$("#goodsList").append('<h2>관련 상품 목록</h2>');
						}else{
							console.log("goodsList")
						}
						
						//보기 편하게 하려고 htmls로 짬
						$.ajax
						({
							url: "${pageContext.request.contextPath}/myTravelCourse",
				            type: "POST",
				            data: {
				            	"myCourse" : myCourse,
				            },
				            dataType:"json",
				            success: function (result) {
				             	console.log("성공");	
								var htmls="";
								if(result.length < 1){
									htmls += "관련상품이 없습니다.";
								} else {
						            $(result).each(function(){			                    			                    
						            	htmls += '여행지명 - <span>'+ this.destination_name + '</span>  |  ';
						                htmls += '상품명 - <span><a href="${pageContext.request.contextPath}/goods/content_view?goods_numbers=' + this.goods_numbers + '">' + this.name + '</a></span>  |  ';
							            htmls += '상품 가격 - <span>'+ this.price + '</span>  |  ';
							            htmls += '재고수량 - <span>'+ this.amount + '</span>';
						            	});	//each end
								}
								$("#goodsList").append(htmls);
						   }//success end
					    }); //ajax end
				
						
						//상품 뽑는 쿼리문 : 
						//select * from goods where destination_name in ('용인 에버랜드','샘플 여행지1');
			  	});
				
				
				/* 추가된 칸 지우는 곳!!! */
				$(document).on("click",".delete-btn",function(){  
					console.log("delete");
					var li = $(this).parent().parent().parent();	//==li태그
					li.remove();
				});
				
				
				
				$(document).on("click",".trav-btn",function(){  
					var trav = $(this).text();
					console.log(trav);
					
					$(".sortable").append('<li class="ui-state-default"><div><input type="text" name="course" value="'+trav+'" readonly/><span><input type="button" class="delete-btn" value="x"></span></div></li>');
			
				});
					
			</script>
		<%@ include file="/WEB-INF/include/js-footer.jsp"%>
	</body>
</html>
