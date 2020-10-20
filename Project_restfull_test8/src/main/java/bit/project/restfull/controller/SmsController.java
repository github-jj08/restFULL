package bit.project.restfull.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@NoArgsConstructor
@AllArgsConstructor
public class SmsController {
	
	// 핸드폰 인증 컨트롤러 셋팅
	String authNum;

	@ResponseBody
	@PostMapping("/sendSms")
	public String sendSms(String receiver) {

		int rand = (int) (Math.random() * 899999) + 100000;
		authNum = "" + rand;
		log.info("수신자 번호: " + receiver);
		log.info("인증번호: " + rand);


		return receiver;	
	}

	@ResponseBody
	@RequestMapping("/smsCheck")
	public String smsCheck(String code) {
		String result = null;
		log.info("==============");
		log.info("인증번호 체크 실행");
		log.info("인증번호는 : " + authNum);
		log.info("입력받은 인증번호는:" + code);
		
		if (code.equals(authNum)) {
			result = "ok";
			return result;
		} else {
			result = "no";
			return result;
		}
	}
	
}

