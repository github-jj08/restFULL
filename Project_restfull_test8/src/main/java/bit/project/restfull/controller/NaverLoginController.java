package bit.project.restfull.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

import bit.project.restfull.security.CustomUserDetailsService;
import bit.project.restfull.service.NaverService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class NaverLoginController {
	
	/* NaverLoginBO */
	@Inject
	private NaverLoginBO naverLoginBO;
	private CustomUserDetailsService customuserdetailsService;
	
	private String apiResult = null;
	
	@Inject
	private NaverService naverService;
	

	//로그인 첫 화면 요청 메소드
	@GetMapping(value = "/login")
	public String login(Model model, HttpSession session) {
		log.info("session : " + session);
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		//네이버
		model.addAttribute("url", naverAuthUrl);
		return "login/login";
	}

	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session
			, HttpServletRequest request)
			throws IOException, ParseException {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		//1. 로그인 사용자 정보를 읽어온다.
		System.out.println("전apiResult : "+ apiResult);
		apiResult = naverLoginBO.getUserProfile(oauthToken); // String형식의 json데이터
		System.out.println("apiResult : "+ apiResult);
		/**
		 * apiResult json 구조 {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		//response의 nickname값 파싱
		String id = (String) response_obj.get("id");
		System.out.println("id"+id);
		String name = (String) response_obj.get("name");
		System.out.println("name"+name);
		//4.파싱 닉네임 세션으로 저장
		session.setAttribute("sessionId", id); // 세션 생성
		model.addAttribute("result", apiResult);
		int dbid = naverService.getUser(id+"@n");
			log.info("dbid= "+dbid);
		if(dbid==0) {
			naverService.addsnsN(id, name);
		}else {
			log.info("id : "+id+" 는 이미 가입된 아이디 입니다.");
		}
		
		UserDetails userDetails = customuserdetailsService.loadUserByUsername(id);
		
		// 유저정보 + 비밀번호(2번쨰 파라미터) 를 통한 로그인 권한정보 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, id + "Naver", userDetails.getAuthorities());
		// 로그인 정보를 스프링 시큐리티 컨텍스트에 넣기 위해 컨텍스트 정보 가져오기
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // 스프링 시큐리티 권한정보에 위에서 만든 권한정보를 넣어준다.
        securityContext.setAuthentication(authentication);
        session = request.getSession(true);
        // 시큐리티 로그인 세션을 생성
        session.setAttribute("SPRING_SECURITY_CONTEXT2", securityContext);
        
		return "rs-mainpage";
	}
	
	
//	@RequestMapping(value = "/naverlogin", method = { RequestMethod.GET, RequestMethod.POST })
//	public String naverlogin(Model model, HttpSession session) {
//		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
//		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
//		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
//		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
//		System.out.println("네이버:" + naverAuthUrl);
//		//네이버
//		model.addAttribute("url", naverAuthUrl);
//		return "naverlogin";
//	}
//
//	
//	
//	
//	
//	
//	
//	//로그아웃
//	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
//	public String logout(HttpSession session) throws IOException {
//		System.out.println("여기는 logout");
//		session.invalidate();
//		return "redirect:/";
//	}
}
