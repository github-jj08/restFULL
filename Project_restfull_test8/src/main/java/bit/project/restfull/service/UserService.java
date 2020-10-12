package bit.project.restfull.service;

import java.util.List;

import bit.project.restfull.vo.PagingVO;
import bit.project.restfull.vo.UserVO;

public interface UserService {
	
	public void addUser(UserVO userVO); 
	
	public void modifyUser(UserVO userVO);
		
	public void userDelete(UserVO userVO);
	
	public String getEncodePassword(String pw);
	
	public int getUser(String member_id);
	
	public void adminModifyUser(UserVO userVO);
	
	public int countMember();
	
	public List<UserVO> userList(PagingVO pagingVO);

	public UserVO getUserVO(String member_id);
	
}
