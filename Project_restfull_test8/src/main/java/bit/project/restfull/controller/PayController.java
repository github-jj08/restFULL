package bit.project.restfull.controller;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;

import bit.project.restfull.service.PaymentService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.PaymentVO;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller()
@AllArgsConstructor
//@RequestMapping("/pay")
//아임포트 - 결제 기능 정의
public class PayController {

	private PaymentService paymentService;
	private UserService userService;
	
	@GetMapping("/payPage")
	public String paymentList(PaymentVO paymentVO, UserVO userVO, Model model) {
		
		log.info("user board list");
		String member_id = userVO.getMember_id();
		log.info("user member_id : "+member_id); // name
		userVO = userService.getUserVO(member_id);
		log.info(userVO);
		
		model.addAttribute("goodsPay", paymentService.getPayList2());
		/*
		 * model.addAttribute("goodsPayment",
		 * paymentMapper.getPayList(destination_name));
		 */
		
		return "user/payPage";
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
	
	
	/payments/complete
	@GetMapping("/payPage")
	public String paymentList(PaymentVO paymentVO, UserVO userVO, Model model) {
		
		log.info("user board list");
		String member_id = userVO.getMember_id();
		log.info("user member_id : "+member_id); // name
		userVO = userService.getUserVO(member_id);
		log.info(userVO);
		
		model.addAttribute("goodsPay", paymentService.getPayList2());
		/*
		 * model.addAttribute("goodsPayment",
		 * paymentMapper.getPayList(destination_name));
		 */
		
		return "user/payPage";
	}
	
}

