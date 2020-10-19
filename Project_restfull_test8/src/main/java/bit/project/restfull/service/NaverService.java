package bit.project.restfull.service;

import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import bit.project.restfull.mapper.LoginMapper;
import bit.project.restfull.security.CustomUserDetailsService;
import bit.project.restfull.vo.UserVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class NaverService {
	
	@Autowired
	private LoginMapper loginmapper;
	
	private UserService userservice;
	private CustomUserDetailsService customuserdetailsService;
	
	@Inject
	private BCryptPasswordEncoder passEncoder;
	
	public void addsnsN( String id, String name) {
		UserVO loginUserInfo = userservice.getUserByIdandAutho(id,"Naver");
		
		Gson gson = new Gson();
		
		if(loginUserInfo == null) {
		UserVO socialRegisterUser = UserVO.builder()
    			.member_id(id)
    			.pw(id+"Naver")
    			.name(name)
    			.gender(" ")
    			.phone(" ")
    			.email(" ")
    			.enabled(1)
    			.grade_name("뚜벅이")
    			.authority_name("ROLE_USER")
    			.login_type("Naver")
    			//여권대조
    			.build();
		log.info("  여기까지 왔낭  	;" +gson.toJson(socialRegisterUser));
		userservice.addUser(socialRegisterUser);
		}
	}
	
	
	
	
	
	
	
	
	public int getUser(String sns_id) {
		return loginmapper.idChk(sns_id);
	}
	
	
	
	
	





}

