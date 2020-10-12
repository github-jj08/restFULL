package bit.project.restfull.service;



import java.util.List;



import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import bit.project.restfull.mapper.LoginMapper;
import bit.project.restfull.vo.UserVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	@Inject
	private BCryptPasswordEncoder passEncoder; 
	@Inject
	private LoginMapper loginMapper;

	public List<UserVO> userList(UserVO userVO){
		return loginMapper.userList(userVO);
	}
	
	public void addUser(UserVO userVO){ 
		
		String pw = userVO.getPw(); 
		
		String encode = passEncoder.encode(pw);
		  
		userVO.setPw(encode);
		loginMapper.insertUser(userVO); 
		loginMapper.insertAuthority(userVO);
	} 
	
	public void modifyUser(UserVO userVO) {
		
		String pw = userVO.getPw(); 
		log.info(pw);
		String encode = passEncoder.encode(pw);
		  
		userVO.setPw(encode);
		
		loginMapper.modifyUser(userVO);; 
		
		log.info(userVO);
		
	}
	 
	
	public void userDelete(UserVO userVO) {
		
		String member_id = userVO.getMember_id();
		log.info(member_id);
		loginMapper.delMember(member_id);
		
	}
	
	
	public String getEncodePassword(String pw) {
		log.info("pw"+pw);
		return passEncoder.encode(pw);
	}
	
	public int getUser(String member_id) {
		return loginMapper.idChk(member_id);
	}
		
	
}
