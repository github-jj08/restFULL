package bit.project.restfull.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import bit.project.restfull.security.CustomUserDetailsService;
import bit.project.restfull.service.KakaoService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.KakaoProfile;
import bit.project.restfull.vo.OAuthToken;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@NoArgsConstructor
@AllArgsConstructor
public class KakaoController {
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	   
	@Autowired
	private UserService userService;
	   
	
	private static String KAKAO_LOGIN_URL = "http://localhost:8282/restfull/kakaologin";
	private static String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
	private static String KAKAO_USER_URL= "https://kapi.kakao.com/v2/user/me";
	
	// 카카오 소셜 로그인
	@GetMapping("/kakaologin")
	public String kakaologin(String code, HttpServletRequest request) throws Exception{

    	Gson gson = new Gson();
    	RestTemplate rt = new RestTemplate();
    	
    	HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    	
    	MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "authorization_code");
    	params.add("client_id", "8c8d010971d8629ec3188f9ed097f73b");
    	params.add("redirect_uri", KAKAO_LOGIN_URL);
    	params.add("code", code);
    	
    	HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
    	
    	ResponseEntity<String> response = rt.exchange(
    			KAKAO_TOKEN_URL,
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
    	
    	//HttpHeaders 
    	HttpHeaders headers2 = new HttpHeaders();
    	headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
    	// 토큰 받아오는 함수
	    headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    	
    	//HttpHeader HttpBody
    	HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
    	
    	//Http post response 
    	ResponseEntity<String> response2 = rt2.exchange(
    			KAKAO_USER_URL,
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
    	// 이제 로그인 타입까지 추가로 비교를 해야해 (카카오만의 아이디 검증을 해야하니)
    	UserVO loginUserInfo = userService.getUserByIdandAutho(socialUserId,"kakao");
    	
    	if(loginUserInfo == null) {
    		// 여기도 카카오 로그인 타입을 추가해야지
    		UserVO socialRegisterUser = UserVO.builder()
	    			.member_id(socialUserId) // 카카오 유저 아이디를 멤터테이블 멤버 아이디 칼럼에 '빌드'
	    			.pw(kakaoProfile.getId() + "kakao")
	    			.name(kakaoProfile.getProperties().getNickname())
	    			.birth("")
	    			.gender(" ")
	    			.phone(" ")
	    			.email(kakaoProfile.getProperties().getEmail())
	    			.enabled(1)
	    			.grade_name("뚜벅이")
	    			.authority_name("ROLE_USER")
	    			.login_type("kakao")
	    			.build();
    		log.info("  마무리 단계 	: " +gson.toJson(socialRegisterUser));
    		userService.addUser(socialRegisterUser);
    	}
    	
    	// 시큐리티 제공하는 유저 정보 조회 서비스를 통한 유저 정보 조회
    	UserDetails userDetails = customUserDetailsService.loadUserByUsername(socialUserId);
    	
    	log.info(" 로그인처리 직전 	:" +gson.toJson(loginUserInfo));
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
	
	@GetMapping("/kakaologout") 
	public String kakaologout(HttpSession session) {
		kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
	  
		session.removeAttribute("access_Token"); 
	  	session.removeAttribute("member_id");
		  
	  	session.invalidate();
	  	log.info("카카오 로그아웃");
		  
		  
	  	return "redirect:/"; 
	}
	 
}
