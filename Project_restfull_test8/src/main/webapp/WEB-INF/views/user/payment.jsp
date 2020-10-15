<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 테스트</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</head>

	<body>
		
	<table id="list-table" width="800" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>판매업자</td>
			<td>상품명</td>
			<td>상품 가격</td>
			<td>상품 상태</td>
			<td>상품 판매 시작일</td>
			<td>상품 판매 종료일</td>
			<td>체크</td>
		</tr>
		<c:forEach items="${goods}" var="goods">
		<tr>
			<td>${goods.seller}</td>
			<td><a href="content_view?goods_numbers=${goods.goods_numbers}">${goods.name}</a></td>
			<td>${goods.amount}</td>
			<td>${goods.status}</td>
			<td>${goods.sellstart}</td>
			<td>${goods.sellend}</td>
			<td><input type="checkbox" id="amount_check" data-toggle="checkbox" value="${goods.amount}"></td>
		</tr>
		</c:forEach>
	</table>
		<p><button onclick="requestPay()" type="button">결제 모듈 테스트</button></p>
		
	</body>
	<script>
	
	function requestPay() {
				var IMP = window.IMP; // 생략가능
				IMP.init('iamport');
			// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
			// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
				IMP.request_pay({
				pg: 'inicis', // version 1.1.0부터 지원.
			/*
			'kakao':카카오페이,
			html5_inicis':이니시스(웹표준결제)
			'nice':나이스페이
			'jtnet':제이티넷
			'uplus':LG유플러스
			'danal':다날
			'payco':페이코
			'syrup':시럽페이
			'paypal':페이팔
			*/
			pay_method: 'card',
			/*
			'samsung':삼성페이,
			'card':신용카드,
			'trans':실시간계좌이체,
			'vbank':가상계좌,
			'phone':휴대폰소액결제
			*/
			merchant_uid: 'merchant_' + new Date().getTime(),
			/*
			merchant_uid에 경우
			https://docs.iamport.kr/implementation/payment
			위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
			참고하세요.
			나중에 포스팅 해볼게요.
			*/
			name: '${goods.name}',
			//결제창에서 보여질 이름
			amount: '100',
			//가격
			buyer_email: '${buyer.email}',
			buyer_name: '${buyer.member_id}',
			buyer_tel: '${buyer.phone}',
			m_redirect_url: 'http://localhost:8282/restfull/payments/complete'
			/*
			모바일 결제시,
			결제가 끝나고 랜딩되는 URL을 지정
			(카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
			*/
			}, function (rsp) {
			console.log(rsp);
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
				}
			alert(msg);
			});
		}
		
	</script>
</html>
