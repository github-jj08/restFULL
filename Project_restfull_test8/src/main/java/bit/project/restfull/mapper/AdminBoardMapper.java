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
	//�Խñ� ��� ���
	List<AdminBoardVO> getList(@Param("boardlist_numbers")int boardlist_numbers);
	
	//���� �Խñ� ���(content_view)
	AdminBoardVO getBoardVO(int board_numbers);
	List<AttachmentVO> getBoardAttachmentVO(int board_numbers);

	//��ȸ��
	void upHit(int board_numbers);
	
	//���ۼ�(÷������ ���̺�+�Խ��� ���̺�)
	void insertBoardVO(AdminBoardVO boardVO);
	void insertAttachmentVO(Map<String, Object> fileMap);

	//�ۼ���(�Խ��� ���̺�)
	void updateBoardVO(AdminBoardVO boardVO);
	
	//�ۻ���(�Խ������̺�)
	void deleteBoardVO(int board_numbers);

	//������ ��� ���
	List<DestinationVO> getDestList();
	DestinationVO getDestVO(String destination_name);
	void insertDestVO(DestinationVO destinationVO);
	void updateDestVO(DestinationVO destinationVO);
	void deleteDestVO(String destination_name);
		
	//���� ��ȣ ��� ���
	List<SidoguVO> sigunguList(int sidocode);
	
	//��ǰ ��� ���
	List<GoodsVO> getGoodsList(String destination_name);
	GoodsVO getGoodsVO(int goods_numbers);
	void insertGoodsVO(GoodsVO goodsVO);
	void updateGoodsVO(GoodsVO goodsVO);
	void deleteGoodsVO(int goods_numbers);

	//�����ڽ�¥�����
	//������ ������(�ñ��� �ڵ带 �޾� �ش� �ñ��� �ڵ忡 �´� �������� �����. ���� getDestList�� ��� �������� �����.)
	List<DestinationVO> getDests(int sigungu_code);
	//���� ��ǰ���
	List<GoodsVO> getRGoods(String[] destinations);

	//����� �̹��� ������Ʈ
	void updateBoardThumbImg(@Param("board_numbers") int board_numbers, @Param("thumbnail")String thumbnail);

}