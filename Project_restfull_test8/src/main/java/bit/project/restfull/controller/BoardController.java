package bit.project.restfull.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bit.project.restfull.service.BoardService;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.CommentVO;
import bit.project.restfull.vo.LikesVO;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@RequestMapping("/user")
//회원가입 및 로그인이 필요한 사용자 기능들
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	//write_view
	@GetMapping("/write_view")
	public String write_view() {
		log.info("write_view ");
		
		return "user/write_view";
	}
	
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam(value="file") MultipartFile[] uploadfiles, BoardVO boardVO) throws IllegalStateException, IOException {
		log.info("write");
		int board_numbers = boardService.writeBoardVO(uploadfiles, boardVO);
		log.info("service.uploadFile(uploadFiles);");
		return "redirect:/content_view?board_numbers="+board_numbers;
	}
	
	//좋아요 기능
	@ResponseBody
	@RequestMapping(value="/board/likeCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public int likeCheck(LikesVO likesVO) {
		log.info("likeCheck");
		int result = boardService.likeCheck(likesVO);
		return result;
	}
	
	//좋아요 수 업데이트
	@ResponseBody
	@RequestMapping(value="/board/likeUpdate", method = {RequestMethod.GET, RequestMethod.POST})
	public void likeUpdate(LikesVO likesVO) {
		log.info("likeUpdate");
		log.info("조아여 글 번호 : " + likesVO.getBoard_numbers());
		log.info("조아여 아이디 : " + likesVO.getMember_id());
		boardService.likeUpdate(likesVO);
	}
	

	//수정 기능 modify_view 페이지 호출
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(int board_numbers, Model model) {
		model.addAttribute("modify_view", boardService.getBoardVO(board_numbers));
		model.addAttribute("filelist", boardService.getBoardAttachmentVO(board_numbers));
		
		return "modify_view";
	}
	
	//수정 기능 수행
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
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
	@RequestMapping(value="/addComments", method= RequestMethod.POST)
	public void writeComment(CommentVO commentVO){
		boardService.writeComment(commentVO);
	}
		
	//댓글 삭제
	@ResponseBody
	@RequestMapping(value="/delComments", method= RequestMethod.POST)
	public void delComment(int comments_numbers){
		boardService.delComment(comments_numbers);
	}
		
	//${pageContext.request.contextPath}/report
	//신고기능
	@ResponseBody
	@RequestMapping(value="/report", method= RequestMethod.POST)
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
