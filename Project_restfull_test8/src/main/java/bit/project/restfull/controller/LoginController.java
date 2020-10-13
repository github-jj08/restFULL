package bit.project.restfull.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.CustomUser;
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
	
}


