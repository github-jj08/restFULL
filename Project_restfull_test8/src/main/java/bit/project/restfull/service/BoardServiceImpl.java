package bit.project.restfull.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import bit.project.restfull.vo.RequestVO;
import bit.project.restfull.vo.TravelVO;
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
		log.info("boardServicImpl-getList(게시글 목록 호출): " + searchWord);
		return mapper.getList(boardlist_numbers, searchWord);
	}

	@Override
	public BoardVO getBoardVO(int board_numbers) {
		log.info("boardServicImpl-getBoardVO(게시글 호출): " + board_numbers);
		mapper.upHit(board_numbers);
		return mapper.getBoardVO(board_numbers);
	}
	
	@Override
	public List<AttachmentVO> getBoardAttachmentVO(int board_numbers) {
		log.info("boardServicImpl-getBoardVO(게시글 호출): " + board_numbers);
		return mapper.getBoardAttachmentVO(board_numbers);
	}

	@Override
	public int writeBoardVO(MultipartFile[] uploadfiles, BoardVO boardVO) throws IllegalStateException, IOException {
		//0.파일경로
		String root_path = "C:/Users/hoora/Desktop";
		String attach_path = "/resources/upload/";

		//1.글작성
		mapper.insertBoardVO(boardVO);
		int bNum = boardVO.getBoard_numbers();
		log.info("insertBoardVO() completed. 글번호는 " + bNum);
		
		 
		//2. 파일 업로드
		Map<String,Object>fileMap = new HashMap<String,Object>();
        
        for(int i = 0; i<uploadfiles.length; i++) {
            try {
            	//프로젝트 폴더 내에 저장. 사진 파일명 중복 방지를 위한 이름 재생성
            	UUID uuid = UUID.randomUUID();	//랜덤 문자열 생성
        		String fileName = uuid + "_" + uploadfiles[i].getOriginalFilename();
        		
                //첫번째 요소에 대해서만 썸네일을 생성함
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
        			//첫번째 요소가 아니라면 그냥 저장
	        		int fileSize = (int) uploadfiles[i].getSize();	
	                String fileDirectory = attach_path + fileName;
	                log.info("저장된 이름 :"+ fileName);
	                
	                File saveFile = new File(root_path+attach_path,fileName); // 실제로 저장할 폴더 이름 + 저장할 파일 이름으로 저장할 파일 객체를 생성함
	                //저장 경로를 DB에 등록
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


	//좋아요 기능 (1) 좋아요 상태 업데이트
	@Override
	public void likeUpdate(LikesVO likesVO){
		log.info("서비스단 조아요 기능 " );
		//좋아요 상태 체크 함수 호출
		int result = likeCheck(likesVO);
		
		if(result == 0){ // 좋아요를 누르지 않으면 0
			mapper.likeInsert(likesVO);
		}else{ 
			mapper.likeDelete(likesVO);
		}
	}
	
	//좋아요 기능 (2) 갯수 세기
	@Override
	public int likeCount(int board_numbers) {
		return mapper.likeCount(board_numbers);
	}

	//좋아요 기능 (3) 좋아요 상태 체크
	@Override
	public int likeCheck(LikesVO likesVO) {
		int result = mapper.likeCheck(likesVO);
		return result;
	}

	//댓글 기능
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

	//신고기능
	@Override
	public void writeBoardVO(BoardVO boardVO) {
		boardVO.setLocation("");
		mapper.insertBoardVO(boardVO);
	}

	//유저 > 자기가쓴 게시글 확인
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

	@Override
	public List<RequestVO> getPaymentList(String member_id) {
		return mapper.paymentList(member_id);
	}

	@Override
	public List<BoardVO> getLikeList(String member_id) {
		return mapper.getLikeList(member_id);
	}

	@Override
	public List<TravelVO> getMyCourseList(String member_id) {
		List<TravelVO> rawCourseList = mapper.getMyCourseList(member_id);
		List<TravelVO> processedList = new ArrayList<TravelVO>();
		
		log.info("processedList.size() " + processedList.size());
		
		if(rawCourseList.size() != 0) {
			
			processedList.add(rawCourseList.get(0));	//일단 첫번째 요소는 저장
	
			/* 여행 코스 목록을 구분짓는 data만 수집한다. */
			for(int i =0; i<rawCourseList.size(); i++) {
				for(int j =i+1;j<rawCourseList.size();j++) {
					String origin = rawCourseList.get(i).getSerialNum();
					String target = rawCourseList.get(j).getSerialNum();
					
					log.info("origin["+i+"] : " + origin);
					log.info("target["+j+"] : " + target);
					
					if(origin.equals(target) != true) {
						//원래 요소와 비교대상 요소의 여행코스 구별번호가 다르다면 타겟을 새로 저장시킨다.
						log.info("origin.equals(target) != true --> " + origin.equals(target));
						
						if(i < j ) {
							processedList.add(rawCourseList.get(j));
							i = j-1;
							log.info("i값 변경 : " + i);
						}
						break;
					} else {
						log.info("원래요소와 비교대상 요소가 같으므로 저장하지 않음" );
					}
					
				}
			}
			//확인 테스트용
			log.info("processedList.size() " + processedList.size());
			for(int i =0; i<processedList.size(); i++) {
				log.info("확인 " + processedList.get(i).getSerialNum());
			}
			return processedList;
		} else {
			return null;
		}
	}

	@Override
	public List<TravelVO> getMyCourse(String member_id, String serialNum) {
		return mapper.getMyCourse(member_id, serialNum);
	}
}
