package bit.project.restfull.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;

import bit.project.restfull.mapper.PaymentMapper;
import bit.project.restfull.vo.PaymentVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class PaymentService {
	
	@Inject
	private PaymentMapper paymentMapper;
	
	public List<PaymentVO> getPayList(String destination_name){
		return paymentMapper.getPayList(destination_name);
		
	}
	public List<PaymentVO> getPayList2(){
		return paymentMapper.getPayList2();
		
	}
	
	
	

	/*
	 * 인증 API 
	 * POST /users/getToken 
	 * 
	 * 결제관련 기본 API 
	 * GET /payments/{imp_uid} 
	 * GET /payments/status/{payment_status} 
	 * POST /payments/cancel 
	 * POST /subscribe/payments/onetime 
	 * POST /subscribe/payments/again 
	 * POST /subscribe/payments/schedule 
	 * POST /subscribe/payments/unschedule
	 * 
	 */
	
	
	// /payments/complete
	IamportClient client;
		
	private IamportClient getNaverTestClient() {
		String test_api_key = "5978210787555892";
		String test_api_secret = "9e75ulp4f9Wwj0i8MSHlKFA9PCTcuMYE15Kvr9AHixeCxwKkpsFa7fkWSd9m0711dLxEV7leEAQc6Bxv";
			
		return new IamportClient(test_api_key, test_api_secret);
	}
	
	private IamportClient getBillingTestClient() {
		String test_api_key = "7544324362787472";
		String test_api_secret = "9frnPjLAQe3evvAaJl3xLOODfO3yBk7LAy9pRV0H93VEzwPjRSQDHFhWtku5EBRea1E1WEJ6IEKhbAA3";
	
		return new IamportClient(test_api_key, test_api_secret);
	}
		
	public void setup() {
		String test_api_key = "imp_apikey";
		String test_api_secret = "ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f";
		client = new IamportClient(test_api_key, test_api_secret);
	}
		
	public void testGetToken() {
		try {
			IamportResponse<AccessToken> auth_response = client.getAuth();
				
			assertNotNull(auth_response.getResponse());
			assertNotNull(auth_response.getResponse().getToken());
		} catch (IamportResponseException e) {
			System.out.println(e.getMessage());
				
			switch(e.getHttpStatusCode()) {
			case 401 :
				//TODO
				break;
			case 500 :
				//TODO
				break;
			}
		} catch (IOException e) {
			//서버 연결 실패
			e.printStackTrace();
		}
	}
		
		
	
	
	
}

