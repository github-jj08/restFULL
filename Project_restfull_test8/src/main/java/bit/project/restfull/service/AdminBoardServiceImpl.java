package bit.project.restfull.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bit.project.restfull.mapper.AdminBoardMapper;
import bit.project.restfull.mapper.BoardMapper;
import bit.project.restfull.service.BoardService;
import bit.project.restfull.vo.AdminBoardVO;
import bit.project.restfull.vo.AttachmentVO;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.DestinationVO;
import bit.project.restfull.vo.GoodsVO;
import bit.project.restfull.vo.LikesVO;
import bit.project.restfull.vo.SidoguVO;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
public class AdminBoardServiceImpl implements AdminBoardService {

	@Autowired
	private AdminBoardMapper mapper;	
	
	@Override
	public List<AdminBoardVO> getList(int boardlist_numbers) {
		return mapper.getList(boardlist_numbers);
	}

	@Override
	public AdminBoardVO getBoardVO(int board_numbers) {
		log.info("boardServicImpl-getBoardVO(�Խñ� ȣ��): " + board_numbers);
		mapper.upHit(board_numbers);
		return mapper.getBoardVO(board_numbers);
	}
	
	@Override
	public List<AttachmentVO> getBoardAttachmentVO(int board_numbers) {
		log.info("boardServicImpl-getBoardVO(�Խñ� ȣ��): " + board_numbers);
		return mapper.getBoardAttachmentVO(board_numbers);
	}

	@Override
	public void writeBoardVO(MultipartFile[] uploadfiles, AdminBoardVO boardVO) {
        //1.���ۼ�
		mapper.insertBoardVO(boardVO);
		int bNum = boardVO.getBoard_numbers();
		log.info("insertBoardVO() completed");
		
		//2. ���� ���ε�
		String root_path = "C:/Users/bit/Desktop";
		String attach_path = "/resources/upload/";

		Map<String,Object>fileMap = new HashMap<String,Object>();
        
		for(int i = 0; i<uploadfiles.length; i++) {
	        	 try {
             	//������Ʈ ���� ���� ����. ���� ���ϸ� �ߺ� ������ ���� �̸� �����
             	UUID uuid = UUID.randomUUID();	//���� ���ڿ� ����
         		String fileName = uuid + "_" + uploadfiles[i].getOriginalFilename();
         		
                 //ù��° ��ҿ� ���ؼ��� ������� ������
         		if(i == 0) {
         		
 	        		File target = new File(root_path+attach_path, fileName);
 	       		  	FileCopyUtils.copy(uploadfiles[i].getBytes(), target);
 	       		  	
 	       		  	String thumbFileName = "THUMB_" + fileName;
 	       		    File image = new File(root_path+attach_path,fileName);
 	
 	       		    String thumbDir = File.separator + "thumbnails" + File.separator + thumbFileName;
 	       		    File thumbnail = new File(root_path + attach_path + thumbDir);
 	
 	       		    log.info("thumbDir = " + thumbDir);
 	       		    
 	       		    if (image.exists()) {
 		       		    thumbnail.getParentFile().mkdirs();
 		       		    
 		       		    Thumbnails.of(image).crop(Positions.CENTER).size(200, 200).keepAspectRatio(true).toFile(thumbnail);
 	       		    }
 	       		    
 	       		    int fileSize = (int) uploadfiles[i].getSize();	
 	                String fileDirectory = attach_path + fileName;
 	                
 	       		    fileMap.put("fileDirectory", fileDirectory);
 	                fileMap.put("fileName", fileName);
 	                fileMap.put("fileSize", fileSize);
 	                System.out.println("fileMap :"+fileMap);
 	                uploadfiles[i].transferTo(image);
 	                
 	                mapper.insertAttachmentVO(fileMap);
 	       		    mapper.updateBoardThumbImg(bNum, attach_path + thumbDir);
 	                
         		} else {
         			//ù��° ��Ұ� �ƴ϶�� �׳� ����
 	        		int fileSize = (int) uploadfiles[i].getSize();	
 	                String fileDirectory = attach_path + fileName;
 	                log.info("����� �̸� :"+ fileName);
 	                
 	                File saveFile = new File(root_path+attach_path,fileName); // ������ ������ ���� �̸� + ������ ���� �̸����� ������ ���� ��ü�� ������
 	                //���� ��θ� DB�� ���
 	                fileMap.put("fileDirectory", fileDirectory);
 	                fileMap.put("fileName", fileName);
 	                fileMap.put("fileSize", fileSize);
 	                System.out.println("fileMap :"+fileMap);
 	                uploadfiles[i].transferTo(saveFile);
 	                mapper.insertAttachmentVO(fileMap);
 	            }
            }
            catch(Exception e){
                log.error("Error while uploading", e);
            }
	    }

	}

	@Override
	public void modifyBoardVO(AdminBoardVO boardVO) {
		mapper.updateBoardVO(boardVO);
	}

	@Override
	public void deleteBoardVO(int board_numbers) {
		mapper.deleteBoardVO(board_numbers);
	}

	
	// ������ ����� ���� ��Ӵٿ� �ɼ� ����
	@Override
	public List<SidoguVO> getOptionList(int sidoCode) {
		return mapper.sigunguList(sidoCode);
	}
	
	// ������ ���
	@Override
	public List<DestinationVO> getDestList() {
		return mapper.getDestList();
	}

	@Override
	public DestinationVO getDestVO(String destination_name) {
		return mapper.getDestVO(destination_name);
	}

	@Override
	public void writeDestVO(DestinationVO destinationVO) {
		mapper.insertDestVO(destinationVO);
	}

	@Override
	public void modifyDestVO(DestinationVO destinationVO) {
		mapper.updateDestVO(destinationVO);
	}

	@Override
	public void deleteDestVO(String destination_name) {
		mapper.deleteDestVO(destination_name);
	}
	
	//������ ���� ��ǰ ���
	@Override
	public List<GoodsVO> getGoodsList(String destination_name) {
		return mapper.getGoodsList(destination_name);
	}
	
	//��ǰ CRUD
	@Override
	public GoodsVO getGoodsVO(int goods_numbers) {
		return mapper.getGoodsVO(goods_numbers);
	}

	@Override
	public void writeGoodsVO(GoodsVO goodsVO) {
		mapper.insertGoodsVO(goodsVO);
	}

	@Override
	public void modifyGoodsVO(GoodsVO goodsVO) {
		mapper.updateGoodsVO(goodsVO);
	}

	@Override
	public void deleteGoodsVO(int goods_numbers) {
		mapper.deleteGoodsVO(goods_numbers);
	}

	@Override
	public List<DestinationVO> getDestList(int sigungu_code) {
		return mapper.getDests(sigungu_code);
	}
	
	public List<GoodsVO> getRGoods(String[] destinations) {
		return mapper.getRGoods(destinations);
	}

	@Override
	public List<AdminBoardVO> getFilterList(int boardlist_numbers) {
		return mapper.getFilterList(boardlist_numbers);
	}
}