package bit.project.restfull.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Qualifier;

import bit.project.restfull.vo.SnsVO;
import bit.project.restfull.vo.UserVO;


@Mapper
public interface SnsMapper {
	//nickname추가 (name)
	@Insert("insert into member(member_id, pw, name, grade_name, authority_name) values(#{member_id}, #{pw}, #{name}, #{grade_name}, #{authority_name})")
	public void insertID(UserVO userVO);

	@Insert("insert into SNS(member_id, sns_id, sns_type, sns_nickname) values(#{member_id}, #{sns_id}, #{sns_type}, #{sns_nickname})")
	public void insertSns(SnsVO snsVO);

	@Select("select * from member where member_id = #{member_id}")
	public SnsVO readSns(String member_id);

	@Delete("delete from member where member_id = #{member_id}")
	public void delSns(String member_id);

	/*
	 * @Select("selects * from member order by member_id") public List<UserVO>
	 * userList(UserVO userVO);
	 */ 
	
	 // ���̵� �ߺ� üũ
		/*
		 * @Select("select count(*) from SNS where member_id = #{member_id}") 
		 * public int idChk(String member_id);
		 */

}
