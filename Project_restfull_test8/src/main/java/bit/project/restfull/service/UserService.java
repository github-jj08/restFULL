package bit.project.restfull.service;

import java.util.List;

import bit.project.restfull.vo.UserVO;

public interface UserService {
	
	public List<UserVO> userList(UserVO userVO);
	public void addUser(UserVO userVO); 
	
	public void modifyUser(UserVO userVO);
		
	public void userDelete(UserVO userVO);
	
	public String getEncodePassword(String pw);
	
	public int getUser(String member_id);
	
}
