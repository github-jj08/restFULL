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
public class BoardController {


	
	@Autowired
	private BoardService boardService;
	
	//�ε��� ������
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		log.info("index");
		return "rs-mainpage";
	}
	
	//�˻� ��� ������
	@GetMapping("/search")
	public String search(@RequestParam(value="boardlist_numbers") int boardlist_numbers, @RequestParam(value="searchWord") String searchWord, Model model) {
		log.info("searchWord : " + searchWord);
		//�˻�� �ش��ϴ� �Խñ۵��� �ҷ���
		List<BoardVO> boardlist = boardService.getList(boardlist_numbers, searchWord);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("boardlist", boardlist);
		
		log.info(searchWord + " �� ���� �˻���� return �� : " + boardlist.size());
		return "searchResult";
	}
	
	@ResponseBody
	@GetMapping("/search/{searchWord}")
	public List<BoardVO> ajaxSearch(@PathVariable(value="boardlist_numbers") int boardlist_numbers, @PathVariable(value="searchWord") String searchWord) throws UnsupportedEncodingException {
		log.info("searchWord : " + searchWord);
		//�˻�� url�� ���Խ������� ASCII�� �ѱ��� ���ڵ��Ǽ� �Ѿ���Ƿ�, �˻�� �ٽ� ���ڵ�����
		searchWord = URLDecoder.decode(searchWord, "UTF-8");
		
		//�˻�� �ش��ϴ� �Խñ۵��� �ҷ���
		List<BoardVO> boardlist = boardService.getList(boardlist_numbers, searchWord);
		log.info(searchWord + " �� ���� �˻���� return �� : " + boardlist.size());
		return boardlist;
	}
	
	//content_view
	@GetMapping("/content_view")
	public String content_view(String board_numbers, Model model) {
		log.info("content_view");
		int board_no = Integer.parseInt(board_numbers);
		log.info("�Խñ� view ȣ��; �Խñ� ��ȣ = " + board_no);
		BoardVO vo = boardService.getBoardVO(board_no);
		String location = vo.getLocation();
		//�ش� �������� ���� �ٸ� ���� �Խñ۵��� �̱� ���� location������ �Ѱܾ���
		model.addAttribute("content_view",vo);
		model.addAttribute("filelist", boardService.getBoardAttachmentVO(board_no));
		model.addAttribute("others", boardService.getOtherBoardVO(board_no, location));
		
		return "content_view";
	}
	

	//write_view
	@GetMapping("/write_view")
	public String write_view() {
		log.info("write_view ");
		
		return "write_view";
	}
	
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam(value="file") MultipartFile[] uploadfiles, BoardVO boardVO) throws IllegalStateException, IOException {
		log.info("write");
		boardService.writeBoardVO(uploadfiles, boardVO);
		log.info("service.uploadFile(uploadFiles);");
		return "redirect:/";
	}
	
	//���ƿ� ���
	@ResponseBody
	@RequestMapping(value="/board/likeCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public int likeCheck(LikesVO likesVO) {
		log.info("likeCheck");
		int result = boardService.likeCheck(likesVO);
		return result;
	}
	
	//���ƿ� �� ������Ʈ
	@ResponseBody
	@RequestMapping(value="/board/likeUpdate", method = {RequestMethod.GET, RequestMethod.POST})
	public void likeUpdate(LikesVO likesVO) {
		log.info("likeUpdate");
		log.info("���ƿ� �� ��ȣ : " + likesVO.getBoard_numbers());
		log.info("���ƿ� ���̵� : " + likesVO.getMember_id());
		boardService.likeUpdate(likesVO);
	}
	
	//���ƿ� �� �˻�
	@ResponseBody
	@RequestMapping(value="/board/likeCount", method = {RequestMethod.GET, RequestMethod.POST})
	public int likeCount(int board_numbers) {
		int like_count = boardService.likeCount(board_numbers);
		return like_count;
	}

	//���� ��� modify_view ������ ȣ��
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(int board_numbers, Model model) {
		model.addAttribute("modify_view", boardService.getBoardVO(board_numbers));
		model.addAttribute("filelist", boardService.getBoardAttachmentVO(board_numbers));
		
		return "modify_view";
	}
	
	//���� ��� ����
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO boardVO) {
		log.info("modify()");
		boardService.modifyBoardVO(boardVO);
		log.info("�۹�ȣ - " + boardVO.getBoard_numbers());
		return "redirect:/content_view?board_numbers="+ boardVO.getBoard_numbers();
	}
	
	//delete?bId=${content_view.bId}
	@GetMapping("/delete")
	public String delete(BoardVO boardVO, Model model) {
		
		boardService.deleteBoardVO(boardVO.getBoard_numbers());
		log.info("���� ����");
		return "redirect:/";
	}
	

	//��� ���
	//��� ���
	@ResponseBody
	@RequestMapping(value="/getComments/{board_numbers}", method= RequestMethod.POST)
	public List<CommentVO> getComments(@PathVariable int board_numbers){
		List<CommentVO> commentlist = boardService.commentList(board_numbers);
		return commentlist;
	}
	
	//��� �ۼ�
	@ResponseBody
	@RequestMapping(value="/addComments", method= RequestMethod.POST)
	public void writeComment(CommentVO commentVO){
		boardService.writeComment(commentVO);
	}
	
	//��� ����
	@ResponseBody
	@RequestMapping(value="/delComments", method= RequestMethod.POST)
	public void delComment(int comments_numbers){
		boardService.delComment(comments_numbers);
	}
	
	//${pageContext.request.contextPath}/report
	//�Ű���
	@ResponseBody
	@RequestMapping(value="/report", method= RequestMethod.POST)
	public void report(BoardVO boardVO) {
		log.info(boardVO.getBoardlist_numbers());
		log.info(boardVO.getTitle());
		log.info(boardVO.getContents());
		log.info(boardVO.getFilter_numbers());
		log.info(boardVO.getMember_id());
		
		boardService.writeBoardVO(boardVO);
		log.info("�Ű� ����");
		
	}	 
	
}
