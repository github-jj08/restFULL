package bit.project.restfull.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import bit.project.restfull.vo.AttachmentVO;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.LikesVO;
import bit.project.restfull.vo.PagingVO;
import bit.project.restfull.vo.RequestVO;

/**
 * Handles requests for the application home page.
 */
public interface BoardMapper{
	//게시글 목록 출력
	List<BoardVO> getList(@Param("boardlist_numbers")int boardlist_numbers, @Param("searchWord") String searchWord);
	
	//메인 게시글 출력(content_view)
	BoardVO getBoardVO(int board_numbers);
	List<AttachmentVO> getBoardAttachmentVO(int board_numbers);

	//조회수
	void upHit(int board_numbers);
	
	//글작성(첨부파일 테이블+게시판 테이블)
	int insertBoardVO(BoardVO boardVO);
	void insertAttachmentVO(Map<String, Object> fileMap);

	//썸네일 이미지 업데이트
	void updateBoardThumbImg(@Param("board_numbers") int board_numbers, @Param("thumbnail")String thumbnail);

	//글수정(게시판 테이블)
	void updateBoardVO(BoardVO boardVO);
	
	//글삭제(게시판테이블)
	void deleteBoardVO(int board_numbers);
	
	//좋아요 기능 
	int likeCheck(LikesVO likesVO);
	void likeInsert(LikesVO likesVO);
	void likeDelete(LikesVO likesVO);
	int likeCount(int board_numbers);

	List<BoardVO> getOthers(@Param("board_numbers") int board_numbers, @Param("location") String location);

	@Select("select count(*) from board where member_id = #{member_id}")
	public int countBoard(String member_id);
	
	// 유저 >본인 게시글 확인(by윤환)
	@Select("Select * from (SELECT ROWNUM RN, A.* FROM "
			+ "(select * from board b where boardlist_numbers = '1' and member_id = #{member_id} order by board_numbers) A ) "
			+ "WHERE RN BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardList(@Param("member_id")String member_id, @Param("start")int start,  @Param("end")int end);

	// 유저 문의내역 확인
	@Select("select * from board where member_id = #{member_id} and boardlist_numbers = '4' order by board_numbers")
	public List<BoardVO> qnaList(String member_id);

	// 유저 신고내역 확인
	@Select("select * from board where member_id = #{member_id} and boardlist_numbers = '3' order by board_numbers")
	public List<BoardVO> askList(String member_id);

	//유저 > 결제내역 확인(by여진)
	@Select("select r.*, g.name as productName from request r, goods g where r.goods_numbers = g.goods_numbers and r.member_id = #{member_id} order by dates desc, merchant_uid desc")
	List<RequestVO> paymentList(String member_id);

	//유저 > 내가 좋아요 누른 글들을 확인(by여진)
	@Select("select b.* from likes l, board b where l.board_numbers = b.board_numbers and l.member_id = #{member_id}")
	List<BoardVO> getLikeList(String member_id);
		
}