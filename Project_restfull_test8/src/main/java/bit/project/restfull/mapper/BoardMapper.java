package bit.project.restfull.mapper;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import bit.project.restfull.vo.AttachmentVO;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.LikesVO;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
public interface BoardMapper{
	//�Խñ� ��� ���
	List<BoardVO> getList(@Param("boardlist_numbers")int boardlist_numbers, @Param("searchWord") String searchWord);
	
	//���� �Խñ� ���(content_view)
	BoardVO getBoardVO(int board_numbers);
	List<AttachmentVO> getBoardAttachmentVO(int board_numbers);

	//��ȸ��
	void upHit(int board_numbers);
	
	//���ۼ�(÷������ ���̺�+�Խ��� ���̺�)
	int insertBoardVO(BoardVO boardVO);
	void insertAttachmentVO(Map<String, Object> fileMap);

	//����� �̹��� ������Ʈ
	void updateBoardThumbImg(@Param("board_numbers") int board_numbers, @Param("thumbnail")String thumbnail);

	//�ۼ���(�Խ��� ���̺�)
	void updateBoardVO(BoardVO boardVO);
	
	//�ۻ���(�Խ������̺�)
	void deleteBoardVO(int board_numbers);
	
	//���ƿ� ��� 
	int likeCheck(LikesVO likesVO);
	void likeInsert(LikesVO likesVO);
	void likeDelete(LikesVO likesVO);
	int likeCount(int board_numbers);

	List<BoardVO> getOthers(@Param("board_numbers") int board_numbers, @Param("location") String location);

	// ���� >���� �Խñ� Ȯ��(by��ȯ)
	@Select("select * from board where member_id = #{member_id} and boardlist_numbers = '1' order by board_numbers")
	public List<BoardVO> boardList(String member_id);

	// ���� ���ǳ��� Ȯ��
	@Select("select * from board where member_id = #{member_id} and boardlist_numbers = '4' order by board_numbers")
	public List<BoardVO> qnaList(String member_id);

	// ���� �Ű��� Ȯ��
	@Select("select * from board where member_id = #{member_id} and boardlist_numbers = '3' order by board_numbers")
	public List<BoardVO> askList(String member_id);
		 
}