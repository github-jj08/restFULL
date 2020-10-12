package bit.project.restfull.service;



import java.util.List;



import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import bit.project.restfull.mapper.LoginMapper;
import bit.project.restfull.vo.PagingVO;
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

	public void addUser(UserVO userVO){ 
		
		String pw = userVO.getPw(); 
		
		String encode = passEncoder.encode(pw);
		  
		userVO.setPw(encode);
		loginMapper.insertUser(userVO); 
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

	@Override
	public void adminModifyUser(UserVO userVO) {
		String pw = userVO.getPw(); 
		log.info(pw);
		String encode = passEncoder.encode(pw);
		
		userVO.setPw(encode);
		loginMapper.adminModifyUser(userVO);; 
	}

	@Override
	public int countMember() {
		return loginMapper.countMember();
	}

	@Override
	public List<UserVO> userList(PagingVO pagingVO){
		return loginMapper.userList(pagingVO);
	}

	@Override
	public UserVO getUserVO(String member_id) {
		return loginMapper.getUserVO(member_id);
	}
	
}
