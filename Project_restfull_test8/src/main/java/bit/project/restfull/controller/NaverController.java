package bit.project.restfull.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.Gson;

import bit.project.restfull.security.CustomUserDetailsService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@NoArgsConstructor
@AllArgsConstructor
public class NaverController {
   
	/* NaverLoginBO */
	@Autowired
	private NaverLoginBO naverLoginBO;
   
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
   
	@Autowired
	private UserService userService;
   
	private String apiResult = null;

	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback( HttpServletRequest request, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException {
       
		System.out.println("여기는 callback");
		Gson gson = new Gson();
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); // String형식의 json데이터
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
		System.out.println("id : "+id);
		String name = (String) response_obj.get("name");
		System.out.println("name : "+name);
		//4.파싱 닉네임 세션으로 저장
		System.out.println("apiResult : "+apiResult);
		
		//id+"@n" 아이디 만들어서 있는지 중복체크 
		String naverId = id+"@n";
		int dbid = userService.getUser(naverId);
		log.info("dbid= "+dbid);
		
		UserVO loginUserInfo = userService.getUserByIdandAutho(naverId,"naver");
		if(dbid==0) {
			//시큐리티 적용할 uservo
			
			if(loginUserInfo == null) {
				// 여기도 카카오 로그인 타입을 추가해야지
				UserVO socialRegisterUser = UserVO.builder()
						.member_id(naverId)
						.pw(naverId + "naver")
						.name(name)
						.birth("")
						.gender(" ")
						.phone(" ")
						.email("")
						.enabled(1)
						.grade_name("뚜벅이")
						.authority_name("ROLE_USER")
						.login_type("naver")
						//여권대조
						.build();
				log.info("  여기까지 왔낭     ;" +gson.toJson(socialRegisterUser));
				userService.addUser(socialRegisterUser);
			}	
		}else {
			log.info("id : "+id+" 는 이미 가입된 아이디 입니다.");
		}
       
		// 시큐리티 제공하는 유저 정보 조회 서비스를 통한 유저 정보 조회
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(naverId);
		
		// 여기서 로그인 처리
		// 유저정보 + 비밀번호(2번쨰 파라미터) 를 통한 로그인 권한정보 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, naverId + "naver", userDetails.getAuthorities());
        // 로그인 정보를 스프링 시큐리티 컨텍스트에 넣기 위해 컨텍스트 정보 가져오기
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // 스프링 시큐리티 권한정보에 위에서 만든 권한정보를 넣어준다.
        //4.세션 생성해서 파싱한 닉네임을 세션에 저장
        securityContext.setAuthentication(authentication);
        session = request.getSession(true);
        // 시큐리티 로그인 세션을 생성
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return "redirect:/";  // 여기서 홈으로 리다리엑트 하면 됨
	}
	
}