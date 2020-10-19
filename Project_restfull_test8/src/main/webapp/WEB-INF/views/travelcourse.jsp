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
  <script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <%@ include file="/WEB-INF/include/plugins.jspf"%>
   <style>
         .sortable {
            list-style-type: none;
            margin: 0;
            padding: 0;
            width: 60%;
            
         }
         
         .sortable li {
            margin: 10px 10px 10px 10px;
            padding: 0;
            padding-left: 1.5em;
            font-size: 1.4em;
            height: 18px;
            background-color: #8A93C0;
         }
         
         .sortable li span {
            position: relative;
         }
         
         input {
            border: none 0;
            margin: 0 auto;
            border: 1px;
            background-color: #8A93C2;
         }
         
         .choice-menu {
            width: 200px;
            height: 250px;
            display: inline-block;
            overflow: auto;
            cursor: pointer;
         }
         
         .sido-choice, .sigungu-choice, .trav-btn {
            
         }
         
         .course {
            text-align: center;
         }
         
         .course-1day {
            width: 350px;
            height: 300px;
            background-color: skyblue;
            border: 1px solid black;
            display: inline-block;
            cursor: pointer;
            margin: 5px;
            border-radius: .25em;
            text-align: center;
         }
         .delete-btn {
         background-color: red;
         }
         
         #myCourse {
            width: 100px;
            height: 325px;
            white-space: nowrap;
         }
         </style>
         <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
         <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
         <script>
            $(function() {
               $(".sortable").sortable();
               $(".sortable").disableSelection(); /* 드래그 해서 순서 변경 가능하게  */
            });
         </script>
      </head>
      <body>
         <%@ include file="/WEB-INF/include/js-header.jsp"%>
         <div class="container">
            <div class="row">
               <div class="col-xs-4 col-sm-2" style="margin-right: 15px;">
                  <div id="mybtns">
                     <h2>sido</h2>
                     <div id="sido" class="choice-menu">
                        <div class="sido-choice" value="11"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">서울/경기</div>
                        <div class="sido-choice" value="28"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">인천</div>
                        <div class="sido-choice" value="42"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">강원</div>
                        <div class="sido-choice" value="43"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">충북</div>
                        <div class="sido-choice" value="44"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">충남/대전</div>
                        <div class="sido-choice" value="47"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">경북/대구</div>
                        <div class="sido-choice" value="48"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">경남/부산/울산</div>
                        <div class="sido-choice" value="45"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">전북</div>
                        <div class="sido-choice" value="46"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">전남/광주</div>
                        <div class="sido-choice" value="50"
                           onMouseover="this.style.background='skyblue';"
                           onMouseout="this.style.background='white';">제주</div>
                     </div>
                  </div>
               </div>
               <div class="col-xs-4 col-sm-2" style="margin-right: 15px;">
                  <h2>sigungu</h2>
                  <div id="sigungu" class="choice-menu"></div>
      
               </div>
               <div class="col-sm-3" style="margin-right: 15px;">
                  <h2>여행지목록</h2>
                  <div id="travel-destinations" class="choice-menu"></div>
               </div>
               <div class="col-xs-4 col-sm-4" >
                  
                  
                  <h2>여행코스등록</h2>
                  <div id="myCourse">
                     <div class="course">
                        <div id="day1" class="course-1day" style="overflow: auto;">
                           <ol class="sortable">
   
                           </ol>
                        </div>
                     </div>
                  </div>
                  <button type="button" id="complete-btn" value="확인">확인</button>
                  <button type="button" id="blank-btn" class="travel">빈칸추가</button>
               </div>
            </div>
            <div class="row">
               <div class="col-sm-1"></div>
               <div class="col-sm-10">
                  <div id="goodsList"></div>
               </div>
            </div>
         </div>
      
      
      
      
      <!-------------------------------------------------------------------------------------------->
            <!-- 시군구 가져오는 곳 -->

				<script>
					$(function(){
						$(".sido-choice").click(function(){
						
						var sidoCode = $(this).attr("value");
												
						console.log("가져오는지 확인 sidoCode : " + sidoCode);			
						
						$.ajax({
							url: "${pageContext.request.contextPath}/travel/getSigunguCode/" + sidoCode,
					        type: "GET",
					        dataType:"json",
					        success: function (result) {
					         	//옵션 초기화 
					          	$('#sigungu').empty();
					            $(result).each(function(){		
					           		$('#sigungu').append('<div class="sigungu-choice" value="'+this.sigungu_code+'">'+this.sigunguname+'</div>');
					      		})
							}
						});	//ajax end
						
						});
						
					});
					
					$(document).on("click",".sigungu-choice",function(){  
							var sigungu_code = $(this).attr("value");
													
							console.log("가져오는지 확인 sigungu_code : " + sigungu_code);			
							
							$.ajax({
								url: "${pageContext.request.contextPath}/travel/getDest/"+sigungu_code,
						        type: "GET",
						        dataType:"json",
						        success: function (result) {
						         	//옵션 초기화
						          	$('#travel-destinations').empty();
						            $(result).each(function(){		
						           		$('#travel-destinations').append('<div class="trav-btn travel" value="'+this.destination_name+'">'+this.destination_name+'</option>');
						      		})
								}
							});	//ajax end
							
						});
				</script>
			
				<script>
	            	/* 여행코스 등록 기능 관련 스크립트 */
	            	//여행지 추가 count
	                var count = 0;
	
	                //여행지 추가 제한갯수(count 할 때 쓰임)
	            	var limit = 5;
	
	                //공백칸 추가 버튼을 눌러서 여행코스에 추가
	                $(function(){
		               	$("#blank-btn").click(function(){
		                        console.log("blank");
		                  		$(".sortable").append('<li class="ui-state-default"><div><input type="text" name="course" maxlength="300"/><span><input type="button" class="delete-btn" value="x"></span></div></li>');
		                 	});
	           		});
	
	                //추가한 여행지를 삭제함
		            $(document).on("click",".delete-btn",function(){ 
		               console.log("delete");
		               var li = $(this).parent().parent().parent();   //==li태그
		               li.remove();
		                    count--;
		                    console.log(count);
		            });
		            
	                //여행지 버튼을 눌러서 여행코스에 추가
		            $(document).on("click",".trav-btn",function(){  
		               var trav = $(this).text();
		               console.log(trav);
		               $(".sortable").append('<li class="ui-state-default"><div><input type="text" name="course" value="'+trav+'" readonly/><span><input type="button" class="delete-btn" value="x"></span></div></li>');
		                    
		            });
	
	                
	                //여행지 or 공백칸 버튼 눌렀을 때 여행지 갯수 count
		            $(document).on("click",".travel",function(){  
		                    count++;
		                    console.log(count);
		
		                    if(count > limit){
		                        alert("여행일정은 최대 " + limit + "개까지 추가가 가능합니다.");
		                        $(".sortable").children().last().remove();
		                        count--;
		                        console.log(count);
		                    }
		                    
		            });                
	                
	            
	            
		            /* 여행코스 등록 이후 상품 관련 스크립트 */
		            
		            //확인 버튼 누른 횟수 count
					var complete_cnt=0;
				
					//확인 버튼 눌렀을 때 작동 - 여행코스 목록을 가지고 관련 상품 get		            
	            	$(document).on("click","#complete-btn",function(){  
	                  console.log("complete");
	                  
	                  //여행코스 전체 목록의 길이를 가져옴
	                  var length= $('input[name="course"]').length;
	                  console.log(length);
	                  
	                  //ajax로 넘길 배열 생성
	                  var myCourse = new Array();
	                  
	                  //아무것도 등록하지 않았는데 확인을 눌렀을 경우 null값 들어가는 것 방지
	                  if(length==0){
	                	  myCourse.push('');
	                  }else{
		                  for(var i =0;i<length;i++){
		                     myCourse.push($('input[name="course"]').eq(i).val());
		                  }
	                  }
	                  
	                  console.log(myCourse);
	                  complete_cnt++;
	                  console.log("관련상품목록 한번만찍기" + complete_cnt);
 	                  if(complete_cnt>1){
	                	  console.log("reComplete");
	                  }else{ 
		                  $("#goodsList").append('<h2>관련 상품 목록</h2><hr/>'
		                  						+'<table style="100%">'
		                  						+'<thead> <tr>'
		                  						+'<td style="width:5%">선택</td>'
		                  						+'<td style="width:50px">여행지명</td>'
		    	                           		+'<td style="width:50px">상품명</td>'
		    	                           		+'<td style="width:50px">상품가격</td>'
		    	                           		+'<td style="width:50px">매수선택</td>'
		    	                           		+'<td style="width:50px">재고수량</td>'
		    	                           		+'</tr> </thead>'
		    	                           		+'<tbody id="goods-box"> </tbody>'
		                  						+'</table>');
	                  }
	                  
	                  //보기 편하게 하려고 htmls로 짬
	                  $.ajax
	                  ({
	                     url: "${pageContext.request.contextPath}/travel/getGoodsList",
	                        type: "POST",
	                        data: {
	                           "myCourse" : myCourse,
	                        },
	                        dataType:"json",
	                        success: function (result) {
	                            console.log("성공");   
	                       		var htmls="";
	                        if(result.length < 1){
	                            $("#goods-box").empty();
	                           	htmls += '<tr><td colspan="5" style="100%">관련상품이 없습니다.</td></tr>';
	                        } else {
	                           	$(result).each(function(){        
	                           		$("#goods-box").empty();
	                           		htmls += '<tr><td style="width:5%"><input type="checkbox" name="chk_goods" value="'+this.goods_numbers+'"></td>';
	                             	htmls += '<td style="width:40px">'+ this.destination_name + '<input type="hidden" name="destination_name" value="'+this.destination_name+'"/> </td>';
	                                htmls += '<td style="width:40px"><a href="${pageContext.request.contextPath}/travel/goods/content_view?goods_numbers=' + this.goods_numbers + '" target="_blank">' + this.name + '</a></td>';
	                                htmls += '<td style="width:40px">'+ this.price + '</td>';
	                                htmls += '<td style="width:40px">'
	                                		 + '<select name="count">'
	                                		 + '<c:forEach begin="1" end="10" var="i">'
	                                         + 		'<option value="${i}">${i}</option>'
											 + '</c:forEach></select>'
											 + '</td>';	
	                                htmls += '<td style="width:40px">'+ this.amount + '</td></tr>';	//상품 재고수량
	                                
	                            });   //each end
	                        }
	                        $("#goods-box").append(htmls);
							
	                        $("#goodsList").append('<span><h1>총 구매금액</h1></span> <span id="totalPrice"> 0 </span><span><h1> 원  </h1></span>');
	                        $("#goodsList").append('<input type="button" id="buy" name="buy" value="구매하기"/>');
	                     }//success end
	                   }); //ajax end
	            
	              	});//관련상품 불러오기 end
	
	              	/* 상품 금액 출력 관련 스크립트 */
	              	/* 중복코드 맞는데 정리하기 너무 귀찮ㅇㅁㄱㅈㄷ뷰ㅠㅠ*/
	              	$(document).on('change','input[name="chk_goods"], select[name="count"]',function(){ 
		                var myGoodsArray = new Array();
		                
		                var myLength = $('input[name="chk_goods"]:checked').length;
						console.log("체크된것갯수: " + myLength);
		                
						//아무것도 등록하지 않았는데 확인을 눌렀을 경우 null값 들어가는 것 방지
		                if(myLength==0){
		                	myGoodsArray.push('');
		                }else{
			                for(var i =0;i<myLength;i++){
			                	var myGoods = new Object();
			              		
			                	myGoods.goods_numbers=$('input[name="chk_goods"]:checked').eq(i).val();
			                    myGoods.count = $("select option:selected").eq(i).val();
			                    myGoodsArray.push(myGoods);
			                }
		                }
						
	              		var prices = 0;
	              		
	              		$.ajax({
                    		url: "${pageContext.request.contextPath}/payments/sumPrice", //cross-domain error가 발생하지 않도록 동일한 도메인으로 전송
                    		type: "POST",
	                        data: JSON.stringify(myGoodsArray),
	                        contentType: "application/json; charset=utf-8",
	                        dataType:"json",
	                        success: function (result) {
	                            console.log("성공"); 
	                            console.log(result); 
	    	              		//총금액 업데이트
	    	              		$('#totalPrice').text('');//선초기화
	    	              		$('#totalPrice').text(result);
		                    }//success end
	              		});//ajax end
	              		
	              	});
	              	
	              	
	              
		            /* 여행관련 상품 선택 및 구매 기능 스크립트 */
	                $(document).on("click","#buy",function(){  
		                  console.log("buy");
		                  
		                  //선택한 상품의 목록길이를 가져옴
		                  var totalLength= $('input[name="chk_goods"]').length;
		                  console.log("상품목록길이 : " + totalLength);
		                  
		                  //ajax로 넘길 json 배열 생성
		                  var goodsArray = new Array();
		                  
		                  
		                  var myLength = $('input[name="chk_goods"]:checked').length;
		                  console.log("체크된것갯수: " + myLength);
		                  //아무것도 등록하지 않았는데 확인을 눌렀을 경우 null값 들어가는 것 방지
		                  if(myLength==0){
		                	  goodsArray.push('');
		                  }else{
			                  for(var i =0;i<myLength;i++){
				                  var goods = new Object();
				                  goods.member_id = $('input[name="member_id"]').val();
				                  goods.goods_numbers=$('input[name="chk_goods"]:checked').eq(i).val();
				                  goods.count = $("select option:selected").eq(i).val();
				                  goods.destination_name = $('input[name="destination_name"]').eq(i).val();
			                      goodsArray.push(goods);
			                  }
			                  
			              }
			              
		                  //체크한 상품의 상품번호
			              console.log("myGoodsArray" + goodsArray);
			              console.log("myGoodsArray" + JSON.stringify(goodsArray));
			              
		                  var member_id = $('input[name="member_id"]').val();
		                  console.log(member_id);
		                  //먼저 주문 정보에 등록함
			              $.ajax({
			                     url: "${pageContext.request.contextPath}/travel/getgoods",
			                        type: "POST",
			                        data: JSON.stringify(goodsArray),
			                        contentType: "application/json; charset=utf-8",
			                        dataType:"json",
			                        success: function (result) {
			                            console.log("성공"); 
			                            console.log(result); 
			                            
			                            if(result.length < 1){
			                            	alert("상품을 선택하세요.");
			                            	console.log("상품번호에 부합하는 상품이 없었음")
				                        } else {
				                        	//주문 정보 생성
				                        	var totalPrice = 0,
				                        		productLength = 0;
				                        	
				                        	var buyer_name = $('input[name="name"]').val();
				                        	var buyer_email = $('input[name="email"]').val();
				                        	var buyer_tel = $('input[name="phone"]').val();
				                        	var merchant_id;
				                        	$(result).each(function (index, item) {
				                           		totalPrice += item.price;
					                        	console.log("주문번호는  " + item.merchant_uid);
				                           		merchant_id = item.merchant_uid;
				                           		
				                           		//반환된 0번째 상품을 메인 상품명으로 지정
				                           		if(index == 0){
				                           			productName = item.productName;
				                           		}else{
				                           			productLength++;
				                           		}
				                           		
				                           	});
				                           	
				                        	//상품명 생성
				                        	if(productLength > 1){
				                        		productName = productName + " 외 "+ productLength + "건";
				                        	}
				                        	
				                           	console.log("총 가격 = " + totalPrice);
				                           	
				                           	//결제 api 호출
				                           	var IMP = window.IMP;
				                            var code = "iamport";  // FIXME: 가맹점 식별코드
				                            IMP.init(code);
				                            
				                            // 결제요청
				                            IMP.request_pay({
				                                // name과 amount만 있어도 결제 진행가능
				                                pg : 'html5_inicis', // pg 사 선택
				                                pay_method : 'card',
				                                merchant_uid : merchant_id,
				                                name : productName,
				                                amount : totalPrice,
				                                buyer_email : buyer_email,
				                                buyer_name : buyer_name,
				                                buyer_tel : buyer_tel,
				                                m_redirect_url : 'http://localhost:8282/restfull/',
				                                // get 방식으로 짜야함(쿼리스트링으로 전달)
				                            }, function(rsp) {
				                                if ( rsp.success ) {
				                                	console.log("성공!!");
				                                	$.ajax({
				                                		url: "${pageContext.request.contextPath}/payments/complete", //cross-domain error가 발생하지 않도록 동일한 도메인으로 전송
				                                		type: 'POST',
				                                		dataType: 'json',
				                                		data: {
				                            	    		"imp_uid" : rsp.imp_uid,
				                            	    		"merchant_uid" : merchant_id,
				                            	    		"totalPrice" : totalPrice
				                                		}
				                                	}).done(function(data) {
				                                		//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
				                                		if ( data == 1 ) {
						                                    var msg = '결제가 완료되었습니다.';
						                                    msg += '매출전표 url : ' + rsp.receipt_url;
				                                			
						                                	$.ajax({
						                                		url: "${pageContext.request.contextPath}/payments/confirmation", //cross-domain error가 발생하지 않도록 동일한 도메인으로 전송
						                                		type: 'POST',
						                                		dataType: 'json',
						                                		data: {
						                            	    		"imp_uid" : rsp.imp_uid,
						                            	    		"merchant_uid" : merchant_id,
						                                		}
						                                	});
						                                    
						                                    alert(msg);
						                                    location.href="${pageContext.request.contextPath}/travel/comfirm";
				                                		} else {
				                                			alert("오류가 감지되어 결제가 되지 않았습니다.");
				                                		}
				                                	});
				                                } else {
				                                    var msg = '결제에 실패하였습니다.';
				                                    msg += '에러내용 : ' + rsp.error_msg;

				                                    alert(msg);
				                                }//if end
				                            });//function end
				                        }
			                        }//success end
			              	}); //ajax end     
			              
	               	});
	            
	         </script>
	   
	
			<%@ include file="/WEB-INF/include/js-footer.jsp"%>
	</body>
</html>
