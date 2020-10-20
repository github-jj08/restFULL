package bit.project.restfull.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import bit.project.restfull.mapper.SnsMapper;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class NaverService {
	
	@Autowired
	private SnsMapper snsMapper;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	public void addsnsN( String id, String name) {
			UserVO uservo= new UserVO();
			
			log.info("id- "+id+"@n");
			log.info("name- "+name);
			
			String pw = "12341234"; 
			
			String encode = passEncoder.encode(pw);
			  
			uservo.setMember_id(id+"@n");
			uservo.setPw(encode);
			uservo.setName(name);
			uservo.setGrade_name("뚜벅이");
			uservo.setAuthority_name("ROLE_USER");
			uservo.setLogin_type("naver");
			log.info("uservo 셋팅완료");
			snsMapper.insertID(uservo);
			log.info("uservo 삽입완료");
	}
}