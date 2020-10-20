package bit.project.restfull.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bit.project.restfull.vo.AdminBoardVO;
import bit.project.restfull.vo.AttachmentVO;
import bit.project.restfull.vo.DestinationVO;
import bit.project.restfull.vo.GoodsVO;
import bit.project.restfull.vo.RequestVO;
import bit.project.restfull.vo.SidoguVO;
import bit.project.restfull.vo.TravelVO;
public interface AdminBoardMapper{
	//게시글 목록 출력
	List<AdminBoardVO> getList(int boardlist_numbers);
	
	//메인 게시글 출력(content_view)
	AdminBoardVO getBoardVO(int board_numbers);
	List<AttachmentVO> getBoardAttachmentVO(int board_numbers);

	//조회수
	void upHit(int board_numbers);
	
	//글작성(첨부파일 테이블+게시판 테이블)
	void insertBoardVO(AdminBoardVO boardVO);
	void insertAttachmentVO(Map<String, Object> fileMap);

	//글수정(게시판 테이블)
	void updateBoardVO(AdminBoardVO boardVO);
	
	//글삭제(게시판테이블)
	void deleteBoardVO(int board_numbers);

	//여행지 목록 출력
	List<DestinationVO> getDestList();
	DestinationVO getDestVO(String destination_name);
	void insertDestVO(DestinationVO destinationVO);
	void updateDestVO(DestinationVO destinationVO);
	void deleteDestVO(String destination_name);
		
	//지역 번호 목록 출력
	List<SidoguVO> sigunguList(int sidocode);
	
	//상품 목록 출력
	List<GoodsVO> getGoodsList(String destination_name);
	GoodsVO getGoodsVO(int goods_numbers);
	void insertGoodsVO(GoodsVO goodsVO);
	void updateGoodsVO(GoodsVO goodsVO);
	void deleteGoodsVO(int goods_numbers);

	//여행코스짜기관련
	//여행지 목록출력(시군구 코드를 받아 해당 시군구 코드에 맞는 여행지만 출력함. 위의 getDestList는 모든 여행지를 출력함.)
	List<DestinationVO> getDests(int sigungu_code);
	//관련 상품출력
	List<GoodsVO> getRGoods(String[] destinations);

	//썸네일 이미지 업데이트
	void updateBoardThumbImg(@Param("board_numbers") int board_numbers, @Param("thumbnail")String thumbnail);

	//필터있는 게시판 리스트 뽑기
	List<AdminBoardVO> getFilterList(int boardlist_numbers);
	
	//특정 상품정보출력
	List<GoodsVO> myGoods(String[] goodsList);

	void insertRequest(List<RequestVO> requestList);

	//주문 정보를 등록하고, 주문 정보의 위변조 등을 비교하기 위해 주문 정보를 가져옴
	List<RequestVO> getRequests(String request_numbers);

	void updateRequests(@Param("imp_uid")String imp_uid,@Param("merchant_uid")String merchant_uid);

	//여행코스 등록
	void insertTravelCourse(List<TravelVO> myCourse);

	//관리자를 위한 기능 : 모든 주문 내역 출력함
	List<RequestVO> getRequestListForAdmin();

}