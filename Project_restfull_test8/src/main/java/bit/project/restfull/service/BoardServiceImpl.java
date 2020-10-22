package bit.project.restfull.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import bit.project.restfull.mapper.BoardMapper;
import bit.project.restfull.mapper.CommentMapper;
import bit.project.restfull.vo.AttachmentVO;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.CommentVO;
import bit.project.restfull.vo.LikesVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.io.FileUtils;

@Log4j
@Service("boardService")
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{ 
	
	@Autowired
	private BoardMapper mapper;	
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public List<BoardVO> getList(int boardlist_numbers, String searchWord) {
		log.info("boardServicImpl-getList(�Խñ� ��� ȣ��): " + searchWord);
		return mapper.getList(boardlist_numbers, searchWord);
	}

	@Override
	public BoardVO getBoardVO(int board_numbers) {
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
	public int writeBoardVO(MultipartFile[] uploadfiles, BoardVO boardVO) throws IllegalStateException, IOException {
		//0.���ϰ��
		String root_path = "C:/Users/ssell";
		String attach_path = "/resources/upload/";
		
		//1.���ۼ�
		mapper.insertBoardVO(boardVO);
		int bNum = boardVO.getBoard_numbers();
		log.info("insertBoardVO() completed. �۹�ȣ�� " + bNum);
		
		 
		//2. ���� ���ε�
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
            } catch(Exception e){
                log.error("Error while uploading", e);
            }
            
	    }
        return bNum;
	}
	
	@Override
	public void modifyBoardVO(BoardVO boardVO) {
		mapper.updateBoardVO(boardVO);
	}

	@Override
	public void deleteBoardVO(int board_numbers) {
		mapper.deleteBoardVO(board_numbers);
	}


	//���ƿ� ��� (1) ���ƿ� ���� ������Ʈ
	@Override
	public void likeUpdate(LikesVO likesVO){
		log.info("���񽺴� ���ƿ� ��� " );
		//���ƿ� ���� üũ �Լ� ȣ��
		int result = likeCheck(likesVO);
		
		if(result == 0){ // ���ƿ並 ������ ������ 0
			mapper.likeInsert(likesVO);
		}else{ 
			mapper.likeDelete(likesVO);
		}
	}
	
	//���ƿ� ��� (2) ���� ����
	@Override
	public int likeCount(int board_numbers) {
		return mapper.likeCount(board_numbers);
	}

	//���ƿ� ��� (3) ���ƿ� ���� üũ
	@Override
	public int likeCheck(LikesVO likesVO) {
		int result = mapper.likeCheck(likesVO);
		return result;
	}

	//��� ���
	@Override
	public void writeComment(CommentVO commentVO) {
		String cts = commentVO.getContents();
		log.info(cts);
		commentMapper.insertComment(commentVO);
	}

	@Override
	public List<CommentVO> commentList(int board_numbers) {
		return commentMapper.commentList(board_numbers);
	}

	@Override
	public void delComment(int comments_numbers) {
		commentMapper.delComment(comments_numbers);
	}

	@Override
	public List<BoardVO> getOtherBoardVO(int board_numbers, String location) {
		return mapper.getOthers(board_numbers, location);
	}

	//�Ű���
	@Override
	public void writeBoardVO(BoardVO boardVO) {
		boardVO.setLocation("");
		mapper.insertBoardVO(boardVO);
	}

	//���� > �ڱⰡ�� �Խñ� Ȯ��
	@Override
	public List<BoardVO> boardList(String member_id){
		return mapper.boardList(member_id);
	}

	@Override
	public List<BoardVO> qnaList(String member_id){
		return mapper.qnaList(member_id);
	}
	@Override
	public List<BoardVO> askList(String member_id){
		return mapper.askList(member_id);
	}
}
