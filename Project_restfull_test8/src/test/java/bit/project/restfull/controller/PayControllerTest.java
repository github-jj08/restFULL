package bit.project.restfull.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.*;

import lombok.extern.log4j.Log4j;

import org.junit.Before;
import org.junit.Test;

import com.siot.IamportRestClient.exception.IamportResponseException;

@Log4j
public class PayControllerTest {
	
	IamportClient client;
	
	public static final String KEY = "imp_apikey"; 
	public static final String SECRET
			= "ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f";
	
	
	@Before
	public void setup() {
		client = new IamportClient(KEY, SECRET);
	}
	
	@Test
	public void testGetToken() {
		try {
			IamportResponse<AccessToken> auth_response = client.getAuth();
			
			log.info("auth_response 정보 : " + auth_response.getMessage());
			
			assertNotNull(auth_response.getResponse());
			assertNotNull(auth_response.getResponse().getToken());
		} catch (IamportResponseException e) {
			log.info("토큰 get 실패 : " +e.getMessage());
			
			switch(e.getHttpStatusCode()) {
			case 401 :
				log.info("401 ERROR");
				break;
			case 500 :
				log.info("500 ERROR");
				break;
			}
		} catch (IOException e) {
			//서버 연결 실패
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPaymentByImpUid() {
		//test_imp_uid --> 임의 setting
		String test_imp_uid = "imp_448280090638";
		try {
			IamportResponse<Payment> payment_response = client.paymentByImpUid(test_imp_uid);
			
			assertNotNull(payment_response.getResponse());
			assertEquals(test_imp_uid, payment_response.getResponse().getImpUid());
		} catch (IamportResponseException e) {
			log.info(e.getMessage());
			
			switch(e.getHttpStatusCode()) {
			case 401 :
				log.info("401 ERROR");
				break;
			case 500 :
				log.info("500 ERROR");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String test_imp_uid_cancelled = "imp_138841716839";
		try {
			IamportResponse<Payment> cancelled_response = client.paymentByImpUid(test_imp_uid_cancelled);
			
			Payment cancelled = cancelled_response.getResponse();
			PaymentCancelDetail[] cancelDetail = cancelled.getCancelHistory();
			
			assertEquals(cancelDetail.length, 1);
			assertNotNull(cancelDetail[0].getPgTid());
		} catch (IamportResponseException e) {
			
			switch(e.getHttpStatusCode()) {
			case 401 :
				log.info("401 ERROR");
				break;
			case 500 :
				log.info("500 ERROR");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

