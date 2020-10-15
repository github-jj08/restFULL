package bit.project.restfull.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import bit.project.restfull.service.AdminBoardService;
import bit.project.restfull.service.BoardService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.AdminBoardVO;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.DestinationVO;
import bit.project.restfull.vo.GoodsVO;
import bit.project.restfull.vo.PagingVO;
import bit.project.restfull.vo.ResponseVO;
import bit.project.restfull.vo.SidoguVO;
import bit.project.restfull.vo.UserVO;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@RequestMapping("/admin/board")
public class AdminBoardController {


   @Autowired
   private BoardService boardService;

   @Autowired
   private UserService userService;

   @Autowired
   private AdminBoardService adboardService;

   
   
   //////회원 관리///////
   @PostMapping("/adminUpdate") // to modify user information by admin
	public String adminUpdate(UserVO userVO, HttpSession session) {
		log.info("to Modify user information");
		
		log.info(userVO.getMember_id());  
		log.info(userVO.getEmail()); 
		log.info(userVO.getPw());
		log.info(userVO.getPhone()); 
		log.info(userVO.getGrade_name());
		
		userService.adminModifyUser(userVO);
		session.invalidate();
		
		return "admin/adminHome";
	}
   
   @ResponseBody
	@PostMapping("admin/userDeleteAdmin") // delete user account by admin
	public String userDeleteAdmin(@RequestBody UserVO userVO, Authentication authentication, HttpServletRequest request) throws Exception{
		Gson gson = new Gson();
		
		userService.userDelete(userVO);
           
           request.getSession().invalidate();  
           return gson.toJson(new ResponseVO<>(200, "success"));
   }
	
	@GetMapping("/userList") // to see user list with paging
	public String userlist(PagingVO pagingVO, Model model
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		int total = userService.countMember();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", pagingVO);
		model.addAttribute("restful_user", userService.userList(pagingVO));
		return "admin/userList";
	}
	
	@GetMapping("/user_content_view") // to see user detail information after list
	public String user_content_view(UserVO userVO, BoardVO boardVO, Model model) {
		
		//유저 리스트 추출 함수
		log.info("content_view");
		String member_id = userVO.getMember_id();
		log.info(member_id); // name
		userVO = userService.getUserVO(member_id);
		log.info(userVO);
		
		model.addAttribute("userDetail", userVO);
		model.addAttribute("admin_board", boardService.boardList(member_id));
		return "user_content_view";
	}
	
	
	@PostMapping("admin/boardDeleteAdmin") // 관리자 권한 게시글 삭제
	public String boardDeleteAdmin(BoardVO boardVO, HttpSession session) throws Exception{
		
		//기존에 있던 delete와 똑같아서 파라미터 수정
		boardService.deleteBoardVO(boardVO.getBoard_numbers());
		
		session.invalidate(); 
		return "admin/adminHome";
	}
	
   
   ////////웹사이트 관리////////////
   //공지사항 및 이벤트 관리 : 기본 url = /notice
   @RequestMapping(value = "/notice", method = RequestMethod.GET)
   public String noticeList() {
	   return "admin/noticeList";
   }
   
   @ResponseBody
   @GetMapping("/notice/{boardlist_numbers}")
   public List<AdminBoardVO> ajaxNoticeList(@PathVariable(value="boardlist_numbers") int boardlist_numbers, Model model) {
      log.info("boardlist_numbers : " + boardlist_numbers);
      //boardlist_numbers에 해당하는 게시글들을 불러옴
      List<AdminBoardVO> noticelist = adboardService.getList(boardlist_numbers);
      model.addAttribute("noticelist", noticelist);
      return noticelist;
   }
   
	/*
	 * //content_view
	 * 
	 * @GetMapping("/notice/content_view") public String content_view(String
	 * board_numbers, Model model) { log.info("content_view"); int board_no =
	 * Integer.parseInt(board_numbers); log.info("게시글 view 호출; 게시글 번호 = " +
	 * board_no);
	 * model.addAttribute("content_view",adboardService.getBoardVO(board_no));
	 * model.addAttribute("filelist",
	 * adboardService.getBoardAttachmentVO(board_no)); return
	 * "admin/notice_content_view"; }
	 * 
	 * 
	 * //write_view
	 * 
	 * @GetMapping("/notice/write_view") public String write_view() {
	 * log.info("write_view ");
	 * 
	 * return "admin/notice_write_view"; }
	 * 
	 * @RequestMapping(value="/notice/write", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public String write(HttpServletRequest
	 * request, @RequestParam(value="file") MultipartFile[] uploadfiles,
	 * AdminBoardVO boardVO) { log.info("write");
	 * adboardService.writeBoardVO(uploadfiles, boardVO);
	 * log.info("service.uploadFile(uploadFiles);"); return
	 * "redirect:/admin/board/notice"; }
	 * 
	 * //수정 기능 modify_view 페이지 호출
	 * 
	 * @RequestMapping(value = "/notice/modify", method = RequestMethod.GET) public
	 * String modify(int board_numbers, Model model) {
	 * model.addAttribute("modify_view", adboardService.getBoardVO(board_numbers));
	 * model.addAttribute("filelist",
	 * adboardService.getBoardAttachmentVO(board_numbers));
	 * 
	 * return "admin/notice_modify_view"; }
	 * 
	 * //수정 기능 수행
	 * 
	 * @RequestMapping(value = "/notice/modify", method = RequestMethod.POST) public
	 * String modify(AdminBoardVO boardVO) { log.info("modify()");
	 * adboardService.modifyBoardVO(boardVO); log.info("글번호 - " +
	 * boardVO.getBoard_numbers()); return
	 * "redirect:/admin/board/notice/content_view?board_numbers="+
	 * boardVO.getBoard_numbers(); }
	 * 
	 * //delete?bId=${content_view.bId}
	 * 
	 * @GetMapping("/notice/delete") public String delete(AdminBoardVO boardVO,
	 * Model model) { adboardService.deleteBoardVO(boardVO.getBoard_numbers());
	 * log.info("삭제 성공"); return "redirect:/admin/board/notice"; }
	 */
   
   
   //////////////////////////////////////
   //여행지 및 상품 관리 기본 url = /dest    ////
   @RequestMapping(value = "/dest", method = RequestMethod.GET)
   public String destList(Model model) {
      List<DestinationVO> destlist = adboardService.getDestList(); //select * from destination
      model.addAttribute("destlist", destlist);
      return "admin/destList";
   }
   //content_view
   @GetMapping("/dest/content_view")
   public String destContent_view(String destination_name, Model model) {
      log.info("content_view");
      model.addAttribute("content_view",adboardService.getDestVO(destination_name));
      model.addAttribute("goodsList",adboardService.getGoodsList(destination_name));
      return "admin/dest_content_view";
   }
   

   //write_view
   @GetMapping("/dest/write_view")
   public String destWrite_view() {
      log.info("dest_write_view");
      
      return "admin/dest_write_view";
   }
   
   @RequestMapping(value="/dest/write", method = {RequestMethod.GET, RequestMethod.POST})
   public String destWrite(DestinationVO destinationVO) {
      log.info("write");
      adboardService.writeDestVO(destinationVO);
      log.info("writeDestVO;");
      return "redirect:/admin/board/dest/";
   }
   
   @ResponseBody
   @GetMapping("/dest/getSigunguCode/{sidoCode}")
   public List<SidoguVO> getSigunguCode(@PathVariable(value="sidoCode") int sidoCode) {
      log.info("sidoCode : " + sidoCode);
      //sidoNum에 해당하는 지역코드들을 불러옴
      List<SidoguVO> optionList = adboardService.getOptionList(sidoCode);
      return optionList;
   }
   
   //수정 기능 modify_view 페이지 호출
   @RequestMapping(value = "/dest/modify", method = RequestMethod.GET)
   public String destModify(String destination_name, Model model) {
      model.addAttribute("modify_view", adboardService.getDestVO(destination_name));
      
      return "admin/dest_modify_view";
   }
   
   //수정 기능 수행
   @RequestMapping(value = "/dest/modify", method = RequestMethod.POST)
   public String destModify(DestinationVO destinationVO) throws UnsupportedEncodingException {
      log.info("modify()");
      adboardService.modifyDestVO(destinationVO);
      log.info("글번호 - " + destinationVO.getDestination_name());
      String destination_name = destinationVO.getDestination_name();
      String encodedParam = URLEncoder.encode(destination_name, "UTF-8");
      return "redirect:/admin/board/dest/content_view?destination_name="+ encodedParam;
   }
   
   //delete?bId=${content_view.bId}
   @GetMapping("/dest/delete")
   public String destDelete(DestinationVO destinationVO, Model model) {
      adboardService.deleteDestVO(destinationVO.getDestination_name());
      log.info("삭제 성공");
      return "redirect:/admin/board/dest/";
   }
   
   //////////////////////////////////////
   //상품 관리 기본 url = /dest/goods/    ////
   //content_view
   @GetMapping("/dest/goods/content_view")
   public String goodsContent_view(String goods_numbers, Model model) {
      log.info("content_view");
      int goodsNum = Integer.parseInt(goods_numbers);
      model.addAttribute("content_view",adboardService.getGoodsVO(goodsNum));
      return "admin/goods_content_view";
   }

   //write_view
   @GetMapping("/dest/{destination_name}/goods/write_view")
   public String goodsWrite_view(@PathVariable(name="destination_name") String destination_name, Model model) {
      log.info("dest_write_view");
      log.info(destination_name);
      model.addAttribute("destination_name", destination_name);
      return "admin/goods_write_view";
   }
   
   @RequestMapping(value="/dest/goods/write", method = {RequestMethod.POST})
   public String goodsWrite(GoodsVO goodsVO) throws UnsupportedEncodingException {
      log.info("write : " + goodsVO.getName());
      adboardService.writeGoodsVO(goodsVO);
      log.info("getDestination_name = " + goodsVO.getDestination_name());
      String destination_name = goodsVO.getDestination_name();
      String encodedParam = URLEncoder.encode(destination_name, "UTF-8");
      return "redirect:/admin/board/dest/content_view?destination_name="+ encodedParam;
   }             
   
   //수정 기능 modify_view 페이지 호출
   @RequestMapping(value = "/dest/goods/modify", method = RequestMethod.GET)
   public String goodsModify(int goods_numbers, Model model) {
      model.addAttribute("modify_view", adboardService.getGoodsVO(goods_numbers));
      
      return "admin/goods_modify_view";
   }
   
   //수정 기능 수행
   @RequestMapping(value = "/dest/goods/modify", method = RequestMethod.POST)
   public String goodsModify(GoodsVO goodsVO) {
      log.info("modify()");
      adboardService.modifyGoodsVO(goodsVO);
      log.info("글번호 - " + goodsVO.getDestination_name());
      return "redirect:/admin/board/dest/goods/content_view?goods_numbers="+ goodsVO.getGoods_numbers();
   }         
   
   //delete?bId=${content_view.bId}
   @GetMapping("/dest/{destination_name}/goods/delete")
   public String goodsDelete(@PathVariable(name="destination_name") String destination_name, int goods_numbers, Model model) throws UnsupportedEncodingException {
      adboardService.deleteGoodsVO(goods_numbers);
      log.info("삭제 성공");
      String encodedParam = URLEncoder.encode(destination_name, "UTF-8");
      return "redirect:/admin/board/dest/content_view?destination_name=" + encodedParam;
   }
   
   //회원의 문의 및 신고에 대한 답변 작성 : admin/board/qnas
   @GetMapping("/qnas")
   public String qnas() {
      log.info("qnas");
      return "admin/qnaList";
   }
   
   @ResponseBody
   @GetMapping("/qnas/{boardlist_numbers}")
   public List<AdminBoardVO> ajaxQnAsList(@PathVariable(value="boardlist_numbers") int boardlist_numbers, Model model) {
      log.info("boardlist_numbers : " + boardlist_numbers);
      //boardlist_numbers에 해당하는 게시글들을 불러옴
      List<AdminBoardVO> list = adboardService.getFilterList(boardlist_numbers);
      model.addAttribute("list", list);
      return list;
   }
   
   
   //content_view
   @GetMapping("/notice/content_view")
   public String content_view(String board_numbers, Model model) {
      log.info("content_view");
      int board_no = Integer.parseInt(board_numbers);
      log.info("게시글 view 호출; 게시글 번호 = " + board_no);
      model.addAttribute("content_view",adboardService.getBoardVO(board_no));
      model.addAttribute("filelist", adboardService.getBoardAttachmentVO(board_no));
      return "admin/notice_content_view";
   }
   

   //write_view
   @GetMapping("/notice/write_view")
   public String write_view() {
      log.info("write_view ");
      
      return "admin/notice_write_view";
   }
   
   @RequestMapping(value="/notice/write", method = {RequestMethod.GET, RequestMethod.POST})
   public String write(HttpServletRequest request, @RequestParam(value="file") MultipartFile[] uploadfiles, AdminBoardVO boardVO) {
      log.info("write");
      adboardService.writeBoardVO(uploadfiles, boardVO);
      log.info("service.uploadFile(uploadFiles);");
      return "redirect:/admin/board/notice";
   }

   //수정 기능 modify_view 페이지 호출
   @RequestMapping(value = "/notice/modify", method = RequestMethod.GET)
   public String modify(int board_numbers, Model model) {
      model.addAttribute("modify_view", adboardService.getBoardVO(board_numbers));
      model.addAttribute("filelist", adboardService.getBoardAttachmentVO(board_numbers));
      
      return "admin/notice_modify_view";
   }
   
   //수정 기능 수행
   @RequestMapping(value = "/notice/modify", method = RequestMethod.POST)
   public String modify(AdminBoardVO boardVO) {
      log.info("modify()");
      adboardService.modifyBoardVO(boardVO);
      log.info("글번호 - " + boardVO.getBoard_numbers());
      return "redirect:/admin/board/notice/content_view?board_numbers="+ boardVO.getBoard_numbers();
   }
   
   //delete?bId=${content_view.bId}
   @GetMapping("/notice/delete")
   public String delete(AdminBoardVO boardVO, Model model) {
      adboardService.deleteBoardVO(boardVO.getBoard_numbers());
      log.info("삭제 성공");
      return "redirect:/admin/board/notice";
   }
   
   
}