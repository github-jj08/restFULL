package bit.project.restfull.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import bit.project.restfull.service.KakaoService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.CustomUser;
import bit.project.restfull.vo.ResponseVO;
import bit.project.restfull.vo.SnsVO;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller
@AllArgsConstructor
public class KakaoController {
	
	
	
	@Autowired
	private KakaoService kakaoService;
	

	@RequestMapping("/kakaologin")
	public String login(@RequestParam(value = "code", required = false) String code, HttpSession session) 
	throws Exception{
		
		log.info("code : " + code);
		
		String access_Token = kakaoService.getAccessToken(code);
		
		log.info("access_token:" + access_Token);
		
		
		HashMap<String, String> userInfo = kakaoService.getUserInfo(access_Token);
		log.info("Controller : " + userInfo);
		  
		
		// 세션 넣기
		session.setAttribute("member_id", userInfo.get("sns_id")+"@k");
		session.setAttribute("access_Token", access_Token);
		  	
		 
		log.info("kakao login");
		
		kakaoService.addSNS(userInfo);
		  
		return "redirect:/";
	}

	
	
	  @RequestMapping(value="/kakaologout") 
	  public String kakaologout(HttpSession session) {
		  kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
	  
		  session.removeAttribute("access_Token"); 
		  session.removeAttribute("member_id");
		  
		  session.invalidate();
		  log.info("카카오 로그아웃");
		  
		  
		  return "redirect:/"; 
	  }
	 
	 
}


