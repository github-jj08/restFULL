package bit.project.restfull.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import bit.project.restfull.security.CustomUserDetailsService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.CustomUser;
import bit.project.restfull.vo.KakaoProfile;
import bit.project.restfull.vo.OAuthToken;
import bit.project.restfull.vo.ResponseVO;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller
@AllArgsConstructor
public class LoginController {
	
	private UserService userservice;
	@Inject
    private BCryptPasswordEncoder passEncoder;
	private CustomUserDetailsService customuserdetailsService;
	
	//로그인
	@GetMapping(value = "/login")
	public String loginForm() {
		
		log.info("ToLogin");
		return "login/login";
	}
	
	//로그아웃.
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception{
		
		log.info("/member/logout");
		//세션 객체는  HttpServletRequest (login안에있음.) 여기서 가져옴
		session.invalidate();// 메모리 날리는 대상 (가비지 콜렉터 )에게
	
		return "redirect:/";
	}
	
	// 회원가입
	@GetMapping("/join") // 회원가입 창 이동
	public String user() {
		
		log.info("join");
		
		return "member/join";
	}

	
	@PostMapping("/addUser") // 회원가입 데이터베이스 자료 올리기
	public String adduser(UserVO userVO) {
		log.info("post resister");
		
		userservice.addUser(userVO);
				
		return "redirect:/login";
		
	}
	
	@GetMapping("/login/accessDenied")
	public String accessDenied() {
		log.info("accessDenied...");
		return "login/accessDenied";
	}

	
	@GetMapping("/user/userHome")
	public void userHome() {
		log.info("user welcome");
	}
	
	@GetMapping("/admin/adminHome")
	public void adminHome() {
		
		log.info("admin welcome");
		
	}
	
	@GetMapping("/admin/userModify")
	public String modify() {
		log.info("modify personal information");
		return "user/userModify";
	}
	
	
	@ResponseBody
	@GetMapping("/idCheck")
	public String idCheck(@RequestParam("id") String id) {
		log.info("Check ID");
		Gson gson = new Gson();
		log.info(id);
		
		int str = userservice.getUser(id);
		
		boolean validId = str == 0; // 참이면
	    	if (validId) {
	    		
	    		log.info("사용 가능한 아이디 : "+id);
	            return gson.toJson(new ResponseVO<>(200, "success"));
	        }  
	    	
	    	else {
	    		log.info("사용 불가능한 아이디 : "+id);
	    		return gson.toJson(new ResponseVO<>(400, "fail"));
	    	}
	
	}
	
	@GetMapping("/findID")
	public String findID() {
		System.out.println("access to find id page");
		return "login/findID";
	}
	
	@GetMapping("/findPW")
	public String findPW() {
		System.out.println("access to find pw page");
		return "login/findPW";
	}
	
	@PostMapping("doFind_ID")
	public String find_id(HttpServletResponse response, @RequestParam("email") String email, Model md) throws Exception{
		md.addAttribute("id", userservice.findID(response, email));
		return "login/findIdResult";
	}
	
	@PostMapping("doFind_PW")
	public void find_PW(@ModelAttribute UserVO userVO, HttpServletResponse response) throws Exception{
		System.out.println(userVO.getMember_id());
		userservice.findPW(response, userVO);
	}

	// 입국심사
    @GetMapping("/kakaologin")
    public String kakaoCallback(String code, HttpServletRequest request) throws Exception { //@ResponseBody data�� �������ִ� ��Ʈ�ѷ� �Լ�
    	
    	Gson gson = new Gson();
    	RestTemplate rt = new RestTemplate();
    	
    	HttpHeaders headers = new HttpHeaders();
	    	headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    	
    	MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
	    	params.add("grant_type", "authorization_code");
    	params.add("client_id", "8c8d010971d8629ec3188f9ed097f73b");
    	params.add("redirect_uri", "http://localhost:8282/restfull/kakaologin");
    	params.add("code", code);
    	
    	HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
    	
    	ResponseEntity<String> response = rt.exchange(
    			"https://kauth.kakao.com/oauth/token",
    			HttpMethod.POST,
    			kakaoTokenRequest,
    			String.class	
    			);
    	
    	ObjectMapper objectMapper = new ObjectMapper();	    	
    	OAuthToken oauthToken = null;    	
    	try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonParseException e) {			
			e.printStackTrace();
		} catch (JsonMappingException e) {		
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
    		    	
    	RestTemplate rt2 = new RestTemplate();
    	
    	//HttpHeaders ������Ʈ ����
    	HttpHeaders headers2 = new HttpHeaders();
    	headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
	    	headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    	
    	//HttpHeader�� HttpBody�� �ϳ��� ������Ʈ�� ���
    	HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
    	
    	//Http ��û�ϱ� - post ������� - �׸��� response ������ ���� ����.
    	ResponseEntity<String> response2 = rt2.exchange(
    			"https://kapi.kakao.com/v2/user/me",
    			HttpMethod.POST,
    			kakaoProfileRequest2,
    			String.class	
    			);
	
    	
    	System.out.println(response2.getBody());
    	
    	ObjectMapper objectMapper2 = new ObjectMapper();	    	
    	KakaoProfile kakaoProfile = null;    	
    	try {
    		// 카카오 로그인 정보 받은 곳
    		kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
    		log.info(gson.toJson(kakaoProfile));
		} catch (JsonParseException e) {			
			e.printStackTrace();
		} catch (JsonMappingException e) {		
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
    	
    	// 카카오톡 고유 아이디값
    	String socialUserId = kakaoProfile.getId().toString();
    	// 우리서비스 회원가입 여부 판단
    	// 이제 로그인 타입까지 추가로 비교를 해야해 (카카오만의 아이디 검증을 해야하니(
    	UserVO loginUserInfo = userservice.getUserByIdandAutho(socialUserId,"kakao");
    	
    	log.info("테스트");
    	log.info(loginUserInfo);
    	
    	if(loginUserInfo == null) {
    		// 여기도 카카오 로그인 타입을 추가해야지
    		UserVO socialRegisterUser = UserVO.builder()
	    			.member_id(socialUserId)
	    			.pw(kakaoProfile.getId() + "kakao")
	    			.name(kakaoProfile.getProperties().getNickname())
	    			.gender(" ")
	    			.phone(" ")
	    			.email(kakaoProfile.getProperties().getEmail())
	    			.enabled(1)
	    			.grade_name("뚜벅이")
	    			.authority_name("ROLE_USER")
	    			.login_type("kakao")
	    			//여권대조
	    			.build();
    		log.info("  여기까지 왔낭  	;" +gson.toJson(socialRegisterUser));
    		userservice.addUser(socialRegisterUser);
    	}
    	
    	// 시큐리티 제공하는 유저 정보 조회 서비스를 통한 유저 정보 조회
    	UserDetails userDetails = customuserdetailsService.loadUserByUsername(socialUserId);
    	
    	log.info(" 로그인처리 직전 	;" +gson.toJson(loginUserInfo));
    	// 여기서 로그인 처리
    
    	// 유저정보 + 비밀번호(2번쨰 파라미터) 를 통한 로그인 권한정보 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, socialUserId + "kakao", userDetails.getAuthorities());
		// 로그인 정보를 스프링 시큐리티 컨텍스트에 넣기 위해 컨텍스트 정보 가져오기
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // 스프링 시큐리티 권한정보에 위에서 만든 권한정보를 넣어준다.
        securityContext.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        // 시큐리티 로그인 세션을 생성
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return "redirect:/";  // 여기서 홈으로 리다리엑트 하면 됨
    }
	
	
}
