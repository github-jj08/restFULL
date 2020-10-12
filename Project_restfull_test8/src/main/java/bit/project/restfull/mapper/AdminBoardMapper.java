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

import bit.project.restfull.vo.AdminBoardVO;
import bit.project.restfull.vo.AttachmentVO;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.DestinationVO;
import bit.project.restfull.vo.GoodsVO;
import bit.project.restfull.vo.LikesVO;
import bit.project.restfull.vo.SidoguVO;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
public interface AdminBoardMapper{
	//게시글 목록 출력
	List<AdminBoardVO> getList(@Param("boardlist_numbers")int boardlist_numbers);
	
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

}