  
package bit.project.restfull.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import bit.project.restfull.vo.SnsVO;
import bit.project.restfull.vo.UserVO;


@Mapper
public interface SnsMapper {
	//nickname추가 (name)
	@Insert("insert into member(member_id, pw, name, grade_name, authority_name) values(#{member_id}, #{pw}, #{name}, #{grade_name}, #{authority_name})")
	public void insertID(UserVO userVO);

	@Insert("insert into SNS(member_id, sns_id, sns_type, sns_nickname) values(#{member_id}, #{sns_id}, #{sns_type}, #{sns_nickname})")
	public void insertSns(SnsVO snsVO);

	@Select("select * from member m, sns s where m.member_id = s.member_id and s.member_id = #{member_id}")
	public SnsVO readSns(String member_id);

	@Delete("delete from member where member_id = #{member_id}")
	public void delSns(String member_id);
}