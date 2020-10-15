package bit.project.restfull.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Qualifier;

import bit.project.restfull.vo.SnsVO;


@Mapper
public interface SnsMapper {
	
	@Insert("insert into member(member_id) values(#{member_id})")
	public void insertID(String member_id);

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
	
	 // 아이디 중복 체크
		/*
		 * @Select("select count(*) from SNS where member_id = #{member_id}") 
		 * public int idChk(String member_id);
		 */

}
