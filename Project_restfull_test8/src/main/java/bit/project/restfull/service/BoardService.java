package bit.project.restfull.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import bit.project.restfull.vo.AttachmentVO;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.CommentVO;
import bit.project.restfull.vo.LikesVO;

public interface BoardService {
	List<BoardVO> getList(int boardlist_numbers, String searchWord);
	
	//���� �Խñ� ���
	BoardVO getBoardVO(int board_numbers);
	List<AttachmentVO> getBoardAttachmentVO(int board_numbers);
	
	//���ۼ�
	int writeBoardVO(MultipartFile[] uploadfiles, BoardVO boardVO) throws IllegalStateException, IOException;
	
	//�ۼ���
	void modifyBoardVO(BoardVO boardVO);

	//�ۻ���
	void deleteBoardVO(int board_numbers);

	//���ƿ� ��� 
	int likeCheck(LikesVO likesVO);
	void likeUpdate(LikesVO likesVO);
	int likeCount(int board_numbers);

	//��� ���
	public void writeComment(CommentVO commentVO);
	public List<CommentVO> commentList(int board_numbers);
	public void delComment(int comments_numbers);

	//���� �Խñ� �̱�
	List<BoardVO> getOtherBoardVO(int board_numbers, String location);

	//�Ű��
	void writeBoardVO(BoardVO boardVO);

	List<BoardVO> boardList(String member_id);

	List<BoardVO> qnaList(String member_id);

	List<BoardVO> askList(String member_id);


}
