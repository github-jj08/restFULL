package bit.project.restfull.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Qualifier;

import bit.project.restfull.vo.UserVO;


@Mapper
@Qualifier("LoginMapper")
public interface LoginMapper {
	
	
	@Insert("insert into member(member_id, pw, name, birth, gender, phone, email, enabled, grade_name) values(#{member_id}, #{pw}, #{name}, #{birth}, #{gender}, #{phone}, #{email}, #{enabled}, #{grade_name})")
	public void insertUser(UserVO userVO);

	@Insert("insert into authority(name, member_id) values('ROLE_USER', #{member_id})")
	public void insertAuthority(UserVO userVO);
	
	@Select("select * from member where member_id = #{member_id}")
	public UserVO readUser(String member_id);
	
	@Delete("delete from member where member_id = #{member_id}")
	public void delMember(String member_id);
	
	@Select("select * from member order by member_id")
	public List<UserVO> userList(UserVO userVO);
	
	/* 아이디 중복 체크 */
	@Select("select count(*) from member where member_id = #{member_id}")
	public int idChk(String member_id);
	
	/* 관리자 권한 개인정보 수정 */
	@Update("update member set gender = #{gender}, email = #{email}, phone = #{phone}, grade_name = #{grade_name}, authority_name = #{authority_name} where member_id = #{member_id}")
	public void adminModifyUser(UserVO userVO);
	
	/* 유저 권한 개인정보 수정 */ 
	@Update("update member set pw = #{pw}, email = #{email}, phone = #{phone} where member_id = #{member_id}")
	public void modifyUser(UserVO userVO);
	
	
	@Select("select * from member where member_id = #{member_id}")
	public UserVO getUserVO(String member_id);
	
	@Select("select count(*) from member")
	public int countMember();
	
}
