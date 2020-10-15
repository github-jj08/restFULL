package bit.project.restfull.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.project.restfull.service.AdminBoardService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.GoodsVO;
import bit.project.restfull.vo.PaymentVO;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller()
@AllArgsConstructor
public class PayController {
	
	private AdminBoardService adminboardService;
	private UserService userService;
	
	@GetMapping("payments/complete")
	public String paymentList(PaymentVO paymentVO, UserVO userVO, Model model) {
		
		/*
		 * log.info("user board list"); String member_id = userVO.getMember_id();
		 * log.info("user member_id : "+member_id); // name userVO =
		 * userService.getUserVO(member_id); log.info(userVO);
		 * 
		 * model.addAttribute("goodsPay", paymentService.getPayList2());
		 */
		/*
		 * model.addAttribute("goodsPayment",
		 * paymentMapper.getPayList(destination_name));
		 */
		
		return "user/payPage";
	}
	
	// 결제 페이지
		@GetMapping("user/pay_view")
		public String pay(HttpSession session, UserVO userVO, GoodsVO goodsVO, Model model) {
			
			log.info("user goods list");
			String member_id = userVO.getMember_id();
			log.info(session.getAttribute("member_id"));
			log.info("user member_id : "+member_id); // name
			
			String destination_name = "샘플 여행지1"; // 테스트용 에버랜드
			int goods_numbers = goodsVO.getGoods_numbers();
			log.info(destination_name);
			log.info(goods_numbers);
			
			
			model.addAttribute("buyer", userService.getUserVO(member_id));
			model.addAttribute("goods", adminboardService.getGoodsList(destination_name));
			model.addAttribute("goodsDetail", adminboardService.getGoodsVO(goods_numbers));
			return "user/payment";
		}
	
		@GetMapping("user/content_view")
		public String goodsContentView(HttpSession session, GoodsVO goodsVO, Model model) {
			
			log.info("user goods list");
			int goods_number = goodsVO.getGoods_numbers();
			log.info(session.getAttribute("member_id"));
			log.info("user member_id : "+goods_number); // name
			
			
			model.addAttribute("content_view", adminboardService.getGoodsVO(goods_number));
			return "admin/goods_content_view";
		}
	
	
		@PostMapping("/test_check")
		@ResponseBody
		public void testCheck(@RequestParam(value = "valueArrTest[]") List<String> amountArr) {
			log.info(amountArr);
		}


	
	
}

