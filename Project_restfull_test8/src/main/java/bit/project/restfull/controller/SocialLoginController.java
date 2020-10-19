//package bit.project.restfull.controller;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.scribejava.core.model.OAuth2AccessToken;
//import com.google.gson.Gson;
//import com.google.gson.JsonParseException;
//
//import bit.project.restfull.security.CustomUserDetailsService;
//import bit.project.restfull.service.SocialLoginService;
//import bit.project.restfull.service.UserService;
//import bit.project.restfull.vo.CustomUser;
//import bit.project.restfull.vo.ResponseVO;
//import bit.project.restfull.vo.UserVO;
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j;
//
///**
// * Handles requests for the application home page.
// */
//@Log4j
//@Controller
//@AllArgsConstructor
////로그인 관련 기능
//public class SocialLoginController {
//	
//	
//	@Autowired
//	private SocialLoginService snsLoginService;
//	
//	private static String KAKAO_LOGIN_URL = "http://localhost:8282/restfull/kakaologin";
//	private static String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
//	private static String KAKAO_USER_URL= "https://kapi.kakao.com/v2/user/me";
//	
//	@RequestMapping("/kakaologin")
//	public String login(@RequestParam(value = "code", required = false) String code, HttpSession session) throws Exception{
//		log.info("code : " + code);
//		String access_Token = snsLoginService.getAccessToken(code);
//		
//		log.info("access_token:" + access_Token);
//		HashMap<String, String> userInfo = snsLoginService.getUserInfo(access_Token);
//		log.info("Controller : " + userInfo);
//		
//		// 세션 넣기
//		session.setAttribute("member_id", userInfo.get("sns_id")+"@k");
//		session.setAttribute("access_Token", access_Token);
//		 
//		log.info("kakao login");
//		snsLoginService.addSNS(userInfo);
//		  
//		return "redirect:/";
//	}
//	
//	  @RequestMapping(value="/kakaologout") 
//	  public String kakaologout(HttpSession session) {
//		  snsLoginService.kakaoLogout((String)session.getAttribute("access_Token"));
//	  
//		  session.removeAttribute("access_Token"); 
//		  session.removeAttribute("member_id");
//		  
//		  session.invalidate();
//		  log.info("카카오 로그아웃");
//		  
//		  return "redirect:/"; 
//	  }
//	 
//	// 입국심사
//	    @GetMapping("/kakaologin")
//	    public String kakaoCallback(String code, HttpServletRequest request) throws Exception { //@ResponseBody data
//	    	
//	    	Gson gson = new Gson();
//	    	RestTemplate rt = new RestTemplate();
//	    	
//	    	HttpHeaders headers = new HttpHeaders();
//		    	headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//	    	
//	    	MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
//		    params.add("grant_type", "authorization_code");
//	    	params.add("client_id", "8c8d010971d8629ec3188f9ed097f73b");
//	    	params.add("redirect_uri", KAKAO_LOGIN_URL);
//	    	params.add("code", code);
//	    	
//	    	HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
//	    	
//	    	ResponseEntity<String> response = rt.exchange(
//	    			KAKAO_TOKEN_URL,
//	    			HttpMethod.POST,
//	    			kakaoTokenRequest,
//	    			String.class	
//	    			);
//	    	
//	    	ObjectMapper objectMapper = new ObjectMapper();	    	
//	    	OAuthToken oauthToken = null;    	
//	    	try {
//				oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
//			} catch (JsonParseException e) {			
//				e.printStackTrace();
//			} catch (JsonMappingException e) {		
//				e.printStackTrace();
//			} catch (IOException e) {	
//				e.printStackTrace();
//			}
//	    		    	
//	    	RestTemplate rt2 = new RestTemplate();
//	    	
//	    	//HttpHeaders 
//	    	HttpHeaders headers2 = new HttpHeaders();
//	    	headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
//		    headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//	    	
//	    	//HttpHeader HttpBody
//	    	HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
//	    	
//	    	//Http post response 
//	    	ResponseEntity<String> response2 = rt2.exchange(
//	    			KAKAO_USER_URL,
//	    			HttpMethod.POST,
//	    			kakaoProfileRequest2,
//	    			String.class	
//	    			);
//		
//	    	
//	    	System.out.println(response2.getBody());
//	    	
//	    	ObjectMapper objectMapper2 = new ObjectMapper();	    	
//	    	KakaoProfile kakaoProfile = null;    	
//	    	try {
//	    		// 카카오 로그인 정보 받은 곳
//	    		kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
//	    		log.info(gson.toJson(kakaoProfile));
//			} catch (JsonParseException e) {			
//				e.printStackTrace();
//			} catch (JsonMappingException e) {		
//				e.printStackTrace();
//			} catch (IOException e) {	
//				e.printStackTrace();
//			}
//	    	
//	    	// 카카오톡 고유 아이디값
//	    	String socialUserId = kakaoProfile.getId().toString();
//	    	// 우리서비스 회원가입 여부 판단
//	    	// 이제 로그인 타입까지 추가로 비교를 해야해 (카카오만의 아이디 검증을 해야하니(
//	    	UserVO loginUserInfo = userservice.getUserByIdandAutho(socialUserId,"kakao");
//	    	
//	    	log.info("테스트");
//	    	log.info(loginUserInfo);
//	    	
//	    	if(loginUserInfo == null) {
//	    		// 여기도 카카오 로그인 타입을 추가해야지
//	    		UserVO socialRegisterUser = UserVO.builder()
//		    			.member_id(socialUserId)
//		    			.pw(kakaoProfile.getId() + "kakao")
//		    			.name(kakaoProfile.getProperties().getNickname())
//		    			.gender(" ")
//		    			.phone(" ")
//		    			.email(kakaoProfile.getProperties().getEmail())
//		    			.enabled(1)
//		    			.grade_name("뚜벅이")
//		    			.authority_name("ROLE_USER")
//		    			.login_type("kakao")
//		    			//여권대조
//		    			.build();
//	    		log.info("  여기까지 왔낭  	;" +gson.toJson(socialRegisterUser));
//	    		userservice.addUser(socialRegisterUser);
//	    	}
//	    	
//	    	// 시큐리티 제공하는 유저 정보 조회 서비스를 통한 유저 정보 조회
//	    	UserDetails userDetails = customuserdetailsService.loadUserByUsername(socialUserId);
//	    	
//	    	log.info(" 로그인처리 직전 	;" +gson.toJson(loginUserInfo));
//	    	// 여기서 로그인 처리
//	    
//	    	// 유저정보 + 비밀번호(2번쨰 파라미터) 를 통한 로그인 권한정보 생성
//	        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, socialUserId + "kakao", userDetails.getAuthorities());
//			// 로그인 정보를 스프링 시큐리티 컨텍스트에 넣기 위해 컨텍스트 정보 가져오기
//	        SecurityContext securityContext = SecurityContextHolder.getContext();
//	        // 스프링 시큐리티 권한정보에 위에서 만든 권한정보를 넣어준다.
//	        securityContext.setAuthentication(authentication);
//	        HttpSession session = request.getSession(true);
//	        // 시큐리티 로그인 세션을 생성
//	        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//	        return "redirect:/";  // 여기서 홈으로 리다리엑트 하면 됨
//	    }
//		
//		
//	    
//
//	   /* NaverLoginBO */
//	   @Inject
//	   private NaverLoginBO naverLoginBO;
//	   
//	   @Inject
//	   private CustomUserDetailsService customuserdetailsService;
//	   
//	   @Inject
//	   private UserService userservice;
//	   
//	   private String apiResult = null;
//	   
//	   //로그인 첫 화면 요청 메소드
//	   @GetMapping(value = "/login")
//	   public String login(Model model, HttpSession session) {
//	      log.info("session : " + session);
//	      /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
//	      String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
//	      //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
//	      //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
//	      log.info("네이버:" + naverAuthUrl);
//	      //네이버
//	      model.addAttribute("url", naverAuthUrl);
//	      
//	      return "login/login";
//	   }
//
//	   //네이버 로그인 성공시 callback호출 메소드
//	   @RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
//	   public String callback( HttpServletRequest request, @RequestParam String code, @RequestParam String state, HttpSession session)
//	         throws IOException, ParseException {
//	       
//	      System.out.println("여기는 callback");
//	      Gson gson = new Gson();
//	      OAuth2AccessToken oauthToken;
//	      oauthToken = naverLoginBO.getAccessToken(session, code, state);
//	      
//	      //1. 로그인 사용자 정보를 읽어온다.
//	      apiResult = naverLoginBO.getUserProfile(oauthToken); // String형식의 json데이터
//	      /**
//	       * apiResult json 구조 {"resultcode":"00", "message":"success",
//	       * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
//	       **/
//	      //2. String형식인 apiResult를 json형태로 바꿈
//	      JSONParser parser = new JSONParser();
//	      Object obj = parser.parse(apiResult);
//	      JSONObject jsonObj = (JSONObject) obj;
//	      //3. 데이터 파싱
//	      //Top레벨 단계 _response 파싱
//	      JSONObject response_obj = (JSONObject) jsonObj.get("response");
//	      //response의 nickname값 파싱
//	      String id = (String) response_obj.get("id");
//	      System.out.println("id : "+id);
//	      String name = (String) response_obj.get("name");
//	      System.out.println("name : "+name);
//	      //4.파싱 닉네임 세션으로 저장
////	      session.setAttribute("sess", session);
//	      System.out.println("apiResult : "+apiResult);
//	      
//	      //id+"@n" 아이디 만들어서 있는지 중복체크 
//	      String naverId = id+"@n";
//	      int dbid = userservice.getUser(naverId);
//	      log.info("dbid= "+dbid);
//	         
//	      UserVO loginUserInfo = userservice.getUserByIdandAutho(naverId,"naver");
//	      if(dbid==0) {
////	         naverService.addsnsN(id, name);
//	         
//	         //시큐리티 적용할 uservo
//	         
//	          if(loginUserInfo == null) {
//	             // 여기도 카카오 로그인 타입을 추가해야지
//	             UserVO socialRegisterUser = UserVO.builder()
//	                   .member_id(naverId)
//	                   .pw(naverId + "kakao")
//	                   .name(name)
//	                   .gender(" ")
//	                   .phone(" ")
//	                   .email("")
//	                   .enabled(1)
//	                   .grade_name("뚜벅이")
//	                   .authority_name("ROLE_USER")
//	                   .login_type("naver")
//	                   //여권대조
//	                   .build();
//	             log.info("  여기까지 왔낭     ;" +gson.toJson(socialRegisterUser));
//	             userservice.addUser(socialRegisterUser);
//	          }
//	      }else {
//	         log.info("id : "+id+" 는 이미 가입된 아이디 입니다.");
//	      }
//	      
//	      
//	       
//	       // 시큐리티 제공하는 유저 정보 조회 서비스를 통한 유저 정보 조회
//	       UserDetails userDetails = customuserdetailsService.loadUserByUsername(naverId);
//	       
//	       log.info(" 로그인처리 직전    ;" +gson.toJson(loginUserInfo));
//	       // 여기서 로그인 처리
//	       // 유저정보 + 비밀번호(2번쨰 파라미터) 를 통한 로그인 권한정보 생성
//	        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, naverId + "naver", userDetails.getAuthorities());
//	      // 로그인 정보를 스프링 시큐리티 컨텍스트에 넣기 위해 컨텍스트 정보 가져오기
//	        SecurityContext securityContext = SecurityContextHolder.getContext();
//	        // 스프링 시큐리티 권한정보에 위에서 만든 권한정보를 넣어준다.
//	        //4.세션 생성해서 파싱한 닉네임을 세션에 저장
//	        securityContext.setAuthentication(authentication);
//	        session = request.getSession(true);
//	        // 시큐리티 로그인 세션을 생성
//	        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//	        return "redirect:/";  // 여기서 홈으로 리다리엑트 하면 됨
//	   }
//	   
//}
