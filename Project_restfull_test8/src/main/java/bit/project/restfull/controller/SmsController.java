package bit.project.restfull.controller;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Log4j
@Controller
public class SmsController {

	String authNum;

	@GetMapping("/send")
	public String sendSMS() {
		log.info("sendSMS 페이지 실행");
		return "sendSMS";

	}

	@ResponseBody
	@PostMapping("/sendSms")
	public String sendSms(String receiver) {

		int rand = (int) (Math.random() * 899999) + 100000;
		authNum = "" + rand;
		log.info("수신자 번호: " + receiver);
		log.info("인증번호: " + rand);

		String api_key = "NCSL80OSP7JG6GVX";
	    String api_secret = "02OMFQXTITOAER254FNJX64ILXOLJC9X";
		Message coolsms = new Message(api_key, api_secret);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", receiver); // 수신번호
		params.put("from", "01083460306"); // 발신번호
		params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
		params.put("text", "인증번호는 " + rand + "입니다."); // 문자내용
		params.put("app_version", "JAVA SDK v1.2"); // application name and version

		params.put("mode", "test"); // 'test' 모드. 실제로 발송되지 않으며 전송내역에 60 오류코드로 뜹니다. 차감된 캐쉬는 다음날 새벽에 충전 됩니다.
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			log.info("obj.toString()" + obj.toString());
		} catch (CoolsmsException e) {
			log.info("e.getMessage()" + e.getMessage());
			log.info("e.getCode()" + e.getCode());
		}
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
			return result = "ok";
		} else {
			return result = "no";
		}
	}

}