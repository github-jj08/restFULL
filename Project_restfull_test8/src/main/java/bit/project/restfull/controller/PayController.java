package bit.project.restfull.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bit.project.restfull.service.PaymentService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.PaymentVO;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller()
@AllArgsConstructor
@RequestMapping("/pay")
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
	
	
	
	
	
	
	
}

