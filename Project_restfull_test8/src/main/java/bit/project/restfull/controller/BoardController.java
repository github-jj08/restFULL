package bit.project.restfull.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import bit.project.restfull.service.AdminBoardService;
import bit.project.restfull.service.BoardService;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.CommentVO;
import bit.project.restfull.vo.DestinationVO;
import bit.project.restfull.vo.LikesVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/user")
//회원가입 및 로그인이 필요한 사용자 기능들
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private AdminBoardService adBoardService;
	
	
	//write_view
	@GetMapping("/write_view")
	public String write_view() {
		log.info("write_view ");
		
		return "user/write_view";
	}
	
	@ResponseBody
	@PostMapping("/writeMainPosting")
	public String writeMainPosting(@RequestParam(value="file") MultipartFile[] uploadfiles, BoardVO boardVO) throws IllegalStateException, IOException {
		log.info("writeMainPosting");
		int board_numbers = boardService.writeBoardVO(uploadfiles, boardVO);
		log.info("service.uploadFile(uploadFiles);" + board_numbers );
		String bNum = Integer.toString(board_numbers);
		return bNum;
	}

	@ResponseBody
	@PostMapping("/writeMainPosting_dest")
	public void dest_Write(@RequestBody DestinationVO destinationVO) {
		log.info("dest write");
		log.info("넘어온 여행지명 : " + destinationVO.getDestination_name());
		adBoardService.writeDestVO(destinationVO);
		log.info("writeDestVO;");
		//return destinationVO.getDestination_name();
	}
	
	@PostMapping("/write")
	public String write(@RequestParam(value="file") MultipartFile[] uploadfiles, BoardVO boardVO) throws IllegalStateException, IOException {
		log.info("write");
		int board_numbers = boardService.writeBoardVO(uploadfiles, boardVO);
		log.info("service.uploadFile(uploadFiles);");
		return "redirect:/content_view?board_numbers="+board_numbers;
	}
	
	//좋아요 기능
	@ResponseBody
	@GetMapping("/board/likeCheck")
	public int likeCheck(LikesVO likesVO) {
		log.info("likeCheck");
		int result = boardService.likeCheck(likesVO);
		return result;
	}
	
	//좋아요 수 업데이트
	@ResponseBody
	@PostMapping("/board/likeUpdate")
	public void likeUpdate(LikesVO likesVO) {
		log.info("likeUpdate");
		log.info("조아여 글 번호 : " + likesVO.getBoard_numbers());
		log.info("조아여 아이디 : " + likesVO.getMember_id());
		boardService.likeUpdate(likesVO);
	}
	

	//수정 기능 modify_view 페이지 호출
	@GetMapping("/modify")
	public String modify(int board_numbers, Model model) {
		model.addAttribute("modify_view", boardService.getBoardVO(board_numbers));
		model.addAttribute("filelist", boardService.getBoardAttachmentVO(board_numbers));
		
		return "modify_view";
	}
	
	//수정 기능 수행
	@PostMapping("/modify")
	public String modify(BoardVO boardVO) {
		log.info("modify()");
		boardService.modifyBoardVO(boardVO);
		log.info("글번호  - " + boardVO.getBoard_numbers());
		return "redirect:/content_view?board_numbers="+ boardVO.getBoard_numbers();
	}
	
	//delete?bId=${content_view.bId}
	@GetMapping("/delete")
	public String delete(BoardVO boardVO, Model model) {
		
		boardService.deleteBoardVO(boardVO.getBoard_numbers());
		log.info("삭제 성공");
		return "redirect:/";
	}
	

	//댓글 기능
	//댓글 작성
	@ResponseBody
	@PostMapping("/addComments")
	public void writeComment(CommentVO commentVO){
		boardService.writeComment(commentVO);
	}
		
	//댓글 삭제
	@ResponseBody
	@PostMapping("/delComments")
	public void delComment(int comments_numbers){
		boardService.delComment(comments_numbers);
	}
		
	//신고기능
	@ResponseBody
	@PostMapping("/report")
	public void report(BoardVO boardVO) {
		log.info(boardVO.getBoardlist_numbers());
		log.info(boardVO.getTitle());
		log.info(boardVO.getContents());
		log.info(boardVO.getFilter_numbers());
		log.info(boardVO.getMember_id());
			
		boardService.writeBoardVO(boardVO);
		log.info("신고 성공");
			
	}	 
	

}
