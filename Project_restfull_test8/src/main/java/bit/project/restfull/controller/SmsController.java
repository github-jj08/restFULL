//package bit.project.restfull.controller;
//
//import java.io.IOException;
//
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.github.scribejava.core.model.OAuth2AccessToken;
//import com.google.gson.Gson;
//
//import edu.bit.restFull.service.BoardService;
//import edu.bit.restFull.service.UserService;
//import edu.bit.restFull.vo.BoardVO;
//import edu.bit.restFull.vo.CustomUser;
//import edu.bit.restFull.vo.PagingVO;
//import edu.bit.restFull.vo.ResponseVO;
//import edu.bit.restFull.vo.UserVO;
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j;
//
///**
// * Handles requests for the application home page.
// */
//@Log4j
//@Controller()
//public class SmsController {
//	
//	// 핸드폰 인증 컨트롤러 셋팅
//		String authNum;
//
//		@ResponseBody
//		@PostMapping("/sendSms")
//		public String sendSms(String receiver) {
//
//			int rand = (int) (Math.random() * 899999) + 100000;
//			authNum = "" + rand;
//			log.info("수신자 번호: " + receiver);
//			log.info("인증번호: " + rand);
//
//
//			return receiver;
//		}
//
//		@ResponseBody
//		@RequestMapping("/smsCheck")
//		public String smsCheck(String code) {
//			String result = null;
//			log.info("==============");
//			log.info("인증번호 체크 실행");
//			log.info("인증번호는 : " + authNum);
//			log.info("입력받은 인증번호는:" + code);
//
//			if (code.equals(authNum)) {
//				return result = "ok";
//			} else {
//				return result = "no";
//			}
//		}
//}
//
