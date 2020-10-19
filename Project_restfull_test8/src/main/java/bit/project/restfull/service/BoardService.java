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
	
	//메인 게시글 출력
	BoardVO getBoardVO(int board_numbers);
	List<AttachmentVO> getBoardAttachmentVO(int board_numbers);
	
	//글작성
	int writeBoardVO(MultipartFile[] uploadfiles, BoardVO boardVO) throws IllegalStateException, IOException;
	
	//글수정
	void modifyBoardVO(BoardVO boardVO);

	//글삭제
	void deleteBoardVO(int board_numbers);

	//좋아요 기능 
	int likeCheck(LikesVO likesVO);
	void likeUpdate(LikesVO likesVO);
	int likeCount(int board_numbers);

	//댓글 기능
	public void writeComment(CommentVO commentVO);
	public List<CommentVO> commentList(int board_numbers);
	public void delComment(int comments_numbers);

	//관련 게시글 뽑기
	List<BoardVO> getOtherBoardVO(int board_numbers, String location);

	//신고글
	void writeBoardVO(BoardVO boardVO);

	List<BoardVO> boardList(String member_id);

	List<BoardVO> qnaList(String member_id);

	List<BoardVO> askList(String member_id);


}
