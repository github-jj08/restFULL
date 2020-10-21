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
//회원가입 하지 않아도 사용가능한 사용자 기능들
public class HomeController {

	@Autowired
	private BoardService boardService;
	
	//인덱스 페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		log.info("index");
		return "rs-mainpage";
	}
	
	//검색 결과 페이지
	@GetMapping("/search")
	public String search(@RequestParam(value="boardlist_numbers") int boardlist_numbers, @RequestParam(value="searchWord") String searchWord, Model model) {
		log.info("searchWord : " + searchWord);
		//검색어에 해당하는 게시글들을 불러옴
		List<BoardVO> boardlist = boardService.getList(boardlist_numbers, searchWord);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("boardlist", boardlist);
		
		log.info(searchWord + " 에 대한 검색결과 return 수 : " + boardlist.size());
		return "searchResult";
	}
	
	@ResponseBody
	@GetMapping("/search/{searchWord}")
	public List<BoardVO> ajaxSearch(@PathVariable(value="boardlist_numbers") int boardlist_numbers, @PathVariable(value="searchWord") String searchWord) throws UnsupportedEncodingException {
		log.info("searchWord : " + searchWord);
		//검색어를 url에 포함시켰으니 ASCII로 한글이 인코딩되서 넘어오므로, 검색어를 다시 디코딩해줌
		searchWord = URLDecoder.decode(searchWord, "UTF-8");
		
		//검색어에 해당하는 게시글들을 불러옴
		List<BoardVO> boardlist = boardService.getList(boardlist_numbers, searchWord);
		log.info(searchWord + " 에 대한 검색결과 return 수 : " + boardlist.size());
		return boardlist;
	}
	
	//content_view
	@GetMapping("/content_view")
	public String content_view(String board_numbers, Model model) {
		log.info("content_view");
		int board_no = Integer.parseInt(board_numbers);
		log.info("게시글 view 호출; 게시글 번호 = " + board_no);
		BoardVO vo = boardService.getBoardVO(board_no);
		String location = vo.getLocation();
		//해당 여행지에 대한 다른 관련 게시글들을 뽑기 위해 location정보를 넘겨야함
		model.addAttribute("content_view",vo);
		model.addAttribute("filelist", boardService.getBoardAttachmentVO(board_no));
		model.addAttribute("others", boardService.getOtherBoardVO(board_no, location));
		
		return "content_view";
	}
	
	
	//좋아요 수 검색
	@ResponseBody
	@RequestMapping(value="/board/likeCount", method = {RequestMethod.GET, RequestMethod.POST})
	public int likeCount(int board_numbers) {
		int like_count = boardService.likeCount(board_numbers);
		return like_count;
	}


	//댓글 기능
	//댓글 목록
	@ResponseBody
	@RequestMapping(value="/getComments/{board_numbers}", method= RequestMethod.POST)
	public List<CommentVO> getComments(@PathVariable int board_numbers){
		List<CommentVO> commentlist = boardService.commentList(board_numbers);
		return commentlist;
	}
	
	//자주하는질문 추가하기
	@RequestMapping(value = "/FAQ", method = RequestMethod.GET)
	public String FAQ() {
		log.info("index");
		return "FAQ";
	}
	
	
	//자주하는질문 추가하기
	
	
	
		
}
