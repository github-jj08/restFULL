package bit.project.restfull.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import bit.project.restfull.vo.AdminBoardVO;
import bit.project.restfull.vo.AttachmentVO;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.DestinationVO;
import bit.project.restfull.vo.GoodsVO;
import bit.project.restfull.vo.LikesVO;
import bit.project.restfull.vo.SidoguVO;

public interface AdminBoardService {

	List<AdminBoardVO> getList(int boardlist_numbers);
	
	//�����ڱ�
	AdminBoardVO getBoardVO(int board_numbers);
	List<AttachmentVO> getBoardAttachmentVO(int board_numbers);
	
	//�����ڱ� CRUD
	void writeBoardVO(MultipartFile[] uploadfiles, AdminBoardVO boardVO);
	void modifyBoardVO(AdminBoardVO boardVO);
	void deleteBoardVO(int board_numbers);

	//������ CRUD
	List<DestinationVO> getDestList();
	DestinationVO getDestVO(String destination_name);
	void writeDestVO(DestinationVO destinationVO);
	void modifyDestVO(DestinationVO destinationVO);
	void deleteDestVO(String destination_name);

	List<SidoguVO> getOptionList(int sicoCode);
	
	//��ǰ CRUD
	List<GoodsVO> getGoodsList(String destination_name);
	GoodsVO getGoodsVO(int goods_numbers);
	void writeGoodsVO(GoodsVO goodsVO);
	void modifyGoodsVO(GoodsVO goodsVO);
	void deleteGoodsVO(int goods_numbers);

	//������ Read - �����ڽ�¥�⿡�� �����
	List<DestinationVO> getDestList(int sigungu_code);
	//���� ��ǰ��� Read
	List<GoodsVO> getRGoods(String[] destinations);

	//���ͰԽ��ǻ̱�
	List<AdminBoardVO> getFilterList(int boardlist_numbers);
}
