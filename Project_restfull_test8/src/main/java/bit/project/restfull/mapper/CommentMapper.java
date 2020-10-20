package bit.project.restfull.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import bit.project.restfull.vo.CommentVO;

@Mapper
public interface CommentMapper {
	
	@Insert("insert into comments (comments_numbers, board_numbers, contents, dates, member_id) values (comments_seq.nextval, #{board_numbers}, #{contents}, sysdate, #{member_id})") 
	public void insertComment(CommentVO commentVO);

	@Select("select * from comments where board_numbers = #{board_numbers} order by comments_numbers desc")
	public List<CommentVO> commentList(int board_numbers);
	
	@Delete("delete from comments where comments_numbers = #{comments_numbers}")
	public void delComment(int comments_numbers);
	
}
