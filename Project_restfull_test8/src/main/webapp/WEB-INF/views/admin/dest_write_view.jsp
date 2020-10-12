<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/ckeditor/ckeditor.js"></script>
		<style>
			.contents-left{
				width:45%;
				height:600px;
				float:left;
				
			}
			
			.image-editor{
				height:85%;
				border:1px;
			}
		</style>
</head>
<body>

<form method="post" action="write">
	<div class="container" style="width:100%; height:700px">  
		여행지 이름 : <input type="text" name="destination_name" id="destination_name"/>
		<hr/>
		위치 검색 : <div class="search" style="">
					<input id="address" type="text" >
		    		<input id="submit" type="button" value="주소 검색">
		    	</div>
				<div id="map" style="width: 100%; height: 350px; position: relative; overflow: hidden;">
		                <script>
		                    var map = new naver.maps.Map("map", {
		                    center: new naver.maps.LatLng(37.3595316, 127.1052133),
		                    zoom: 15,
		                    mapTypeControl: true
		                    });
		
		                    var infoWindow = new naver.maps.InfoWindow({
		                    anchorSkew: true
		                    });
		
		                    map.setCursor('pointer');
		                   
		                    function searchCoordinateToAddress(latlng) {
		
		                    infoWindow.close();
		
		                    naver.maps.Service.reverseGeocode({
		                        coords: latlng,
		                        orders: [
			                        naver.maps.Service.OrderType.ADDR,
			                        naver.maps.Service.OrderType.ROAD_ADDR
		                        ].join(',')
		                    }, function(status, response) {
		                        if (status === naver.maps.Service.Status.ERROR) {
		                        if (!latlng) {
		                            return alert('ReverseGeocode Error, Please check latlng');
		                        }
		                        if (latlng.toString) {
		                            return alert('ReverseGeocode Error, latlng:' + latlng.toString());
		                        }
		                        if (latlng.x && latlng.y) {
		                            return alert('ReverseGeocode Error, x:' + latlng.x + ', y:' + latlng.y);
		                        }
		                        return alert('ReverseGeocode Error, Please check latlng');
		                        }
		
		                        var address = response.v2.address,
		                            htmlAddresses = [];
		
		
		                        if (address.jibunAddress !== '') {
		                            htmlAddresses.push('[지번 주소] ' + address.jibunAddress);
		                        }
		
		                        if (address.roadAddress !== '') {
		                            htmlAddresses.push('[도로명 주소] ' + address.roadAddress);
		                        }
		
		                        infoWindow.setContent([
		                        '<div style="padding:10px;min-width:200px;line-height:150%;">',
		                        '<h4 style="margin-top:5px;">검색 좌표</h4>',
		                        '<button type="button" onclick="setAddressIntoBox()">입력</button><br />',
		                        htmlAddresses.join('<br />'),
		        	          	'<input id="x" type="hidden" value="'+ latlng.x + '"> ',
		        	          	'<input id="y" type="hidden" value="'+ latlng.y + '"> ',
		        	          	'<input id="jibunAddress" type="hidden" value="'+ address.jibunAddress + '"> ',
		        	          	'<input id="roadAddress" type="hidden" value="'+ address.roadAddress + '"> ',
		                        '</div>'
		                        ].join('\n'));
		                        
		                        console.log(" 좌표 검색  latlngs " + latlng.x + ", " + latlng.y);
		                        
		                        infoWindow.open(map, latlng);
		                    });
		                    }
		
		                    function searchAddressToCoordinate(address) {
		                    naver.maps.Service.geocode({
		                        query: address
		                    }, function(status, response) {
		                        if (status === naver.maps.Service.Status.ERROR) {
		                        if (!address) {
		                            return alert('Geocode Error, Please check address');
		                        }
		                        return alert('Geocode Error, address:' + address);
		                        }
		
		                        if (response.v2.meta.totalCount === 0) {
		                        return alert('No result.');
		                        }
		
		                        var htmlAddresses = [],
		                        item = response.v2.addresses[0],
		                        point = new naver.maps.Point(item.x, item.y);
		
		                        if (item.roadAddress) {
		                        htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
		                        }
		
		                        if (item.jibunAddress) {
		                        htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
		                        }
		
		                        if (item.englishAddress) {
		                        htmlAddresses.push('[영문명 주소] ' + item.englishAddress);
		                        }
								
		                        infoWindow.setContent([
		                        '<div style="padding:10px;min-width:200px;line-height:150%;">',
		                        '<h4 style="margin-top:5px;">검색 주소 : '+ address +'</h4>',
		                        '<button type="button" onclick="setAddressIntoBox()">입력</button><br />',
		                        htmlAddresses.join('<br />'),
		        	          	'<input id="x" type="hidden" value="'+ item.x + '"> ',
		        	          	'<input id="y" type="hidden" value="'+ item.y + '"> ',
		        	          	'<input id="jibunAddress" type="hidden" value="'+ item.jibunAddress + '"> ',
		        	          	'<input id="roadAddress" type="hidden" value="'+ item.roadAddress + '"> ',
		                        '</div>'
		                        ].join('\n'));
									                        
		                        map.setCenter(point);
		                        infoWindow.open(map, point);
		                    });
		                    }
		
		
							function setAddressIntoBox() {
								
								var jibunAddr = $("#jibunAddress").val(); 
							    var doroAddr = $("#roadAddress").val(); 
								var x= $("#x").val();
								var y= $("#y").val();
									  	
								document.getElementById("jibunAddr").value = jibunAddr;
								document.getElementById("doroAddr").value = doroAddr;
								document.getElementById("gps-x").value = x;
								document.getElementById("gps-y").value = y;
		                    };
		
		                    function initGeocoder() {
		                    if (!map.isStyleMapReady) {
		                        return;
		                    }
		
		                    map.addListener('click', function(e) {
		                        searchCoordinateToAddress(e.coord);
		                    });
		
		                    $('#address').on('keydown', function(e) {
		                        var keyCode = e.which;
		
		                        if (keyCode === 13) { // Enter Key
		                        searchAddressToCoordinate($('#address').val());
		                        }
		                    });
		
		                    $('#submit').on('click', function(e) {
		                        e.preventDefault();
		
		                        searchAddressToCoordinate($('#address').val());
		                    });
		
		                    
		                    }
		
		                    naver.maps.onJSContentLoaded = initGeocoder;
		                    naver.maps.Event.once(map, 'init_stylemap', initGeocoder);
		                    </script>
		          	</div><!-- naverMap End -->
		          
					<!-- 입력할 주소 출력 -->
					<div>
							지번 주소 : <input id="jibunAddr" name="jibunaddress" type="text" style="width:50%" ><br/>
							도로명 주소 : <input id="doroAddr" name="doroaddress" type="text" style="width:50%" ><br/>
			          		추가 주소 : <input id="detailAddr" name="details" type="text" style="width:50%"><br/>
			          		x 좌표 : <input id="gps-x" name="lat" type="text" style="width:50%"><br/>
			          		y 좌표 : <input id="gps-y" name="lng" type="text" style="width:50%"><br/>
			          		
			        </div>
					
		        </div>
	<!-- 드롭다운으로 선택 / selected로 보내기-->
				<div>
					<!-- 일단 여기는 하드코딩 나중에 시간 나면 ajax로 가져오도록 바꾸기 -->
					<select name="sido" id="sido">
						<option value="11" selected>서울/경기</option>
						<option value="28">인천</option>
						<option value="42">강원</option>
						<option value="43">충북</option>
						<option value="44">충남/대전</option>
						<option value="47">경북/대구</option>
						<option value="48">경남/부산/울산</option>
						<option value="45">전북</option>
						<option value="46">전남/광주</option>
						<option value="50">제주</option>
					</select>	
					
					<select name="sigungu_code" id="sigungu_code">
					</select>	
						<script>
							$(function(){
								function getOptionList(){
										
									var sidoCode = $("#sido option:selected").val();
									
									console.log("가져오는지 확인 sidoCode : " + sidoCode);								    
									$.ajax({
										url: "${pageContext.request.contextPath}/admin/board/dest/getSigunguCode/" + sidoCode,
								        type: "GET",
								        dataType:"json",
								        success: function (result) {
							            	//옵션 초기화
							            	$('#sigungu_code').empty();
								            $(result).each(function(){		
							                    $('#sigungu_code').append('<option value="'+this.sigungu_code+'">'+this.sigunguname+'</option>');
							                })
								        }
										
									});
								};
								getOptionList();
							
								//변경이 생길 때마다 list 다시 가져오기
								$('#sido').on('change', function(){
									getOptionList();
								});
							});
						
						</script>
					
				</div>	
	<input type="submit" value="완료" style="position:absolute;"/>
</form>
</body>
</html>