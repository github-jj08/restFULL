package bit.project.restfull.controller;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;

import bit.project.restfull.service.PaymentService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.PaymentVO;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@NoArgsConstructor
@AllArgsConstructor
//@RequestMapping("/pay")
//아임포트 - 결제 기능 정의
public class PayController {

	private PaymentService paymentService;
	private UserService userService;
	

	/*
	 * 아임포트 REST API 결제완료된 정보, 결제취소, 상태별 결제목록 조회 등의 기능을 하는 REST API를 제공합니다.
	 * 비인증결제,정기 자동결제 등 부가기능을 위한 REST API를 제공합니다.
	 * 
	 *	== 즉, 결제완료된 이후에 결제 정보를 가져오고 저장하기 위해서는 아임포트 서버의 데이터를 가져오는 rest api를 사용해야 한다는 뜻
	 * 따라서 저장을 위한 데이터 생성 이전에 아임포트 토큰 생성 후 데이터를 가져오는 메소드를 호출 --> 가져온 데이터로 db에 저장
	 *
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
	
	private static String IMP_APIKEY = "imp_apikey";
	private static String IMP_APISECRET= "ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f";
	private String requestURL = "https://api.iamport.kr/users/getToken";

	
	private String getToken(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// 결제 상태 확인을 위해 아임포트 토큰생성 
		String imp_key 		=	URLEncoder.encode(IMP_APIKEY, "UTF-8");
		String imp_secret	=	URLEncoder.encode(IMP_APISECRET, "UTF-8");
		JsonObject json = new JsonObject();
		json.addProperty("imp_key", imp_key);
		json.addProperty("imp_secret", imp_secret);
		
		String token = paymentService.getToken(request, response, json, requestURL); 
		//돌려받은 토큰으로 필요한 메소드에서 사용하면 됨
		return token;
	}
	
	//크로스 도메인 발생할수도 있어서 동일한 도메인으로 처리하라는데 아직 이해 X
	//아마 travelcourse 페이지 뽑은거랑 똑같은 url 매핑을 해줘야되는게 아닐까 생각
	@ResponseBody
	@PostMapping("/payments/complete")
	public void paymentComplete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String token = getToken(request, response);
		log.info("imp_uid ? = " + request.getAttribute("imp_uid"));
		log.info(token);
	}
}

