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
	@Insert("insert into MEMBER(member_id, pw, name, grade_name, authority_name, login_type) values(#{member_id}, #{pw}, #{name}, #{grade_name}, #{authority_name}, #{login_type})")
	public void insertID(UserVO userVO);
}