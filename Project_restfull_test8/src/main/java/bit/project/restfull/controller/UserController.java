package bit.project.restfull.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import bit.project.restfull.mapper.PaymentMapper;
import bit.project.restfull.service.BoardService;
import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.CustomUser;
import bit.project.restfull.vo.ResponseVO;
import bit.project.restfull.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller()
@AllArgsConstructor
public class UserController {
	
	
	@Inject
    private UserService userService;
	
	@Inject
    private BoardService boardService;
	
	@Inject
    private PaymentMapper commentMapper;
	
	@Inject
    private BCryptPasswordEncoder passEncoder;
	
	
	@GetMapping("/userDeleteView") // 회원 탈퇴 페이지
	public String userDeleteView() {
		log.info("welcome userDeleteView!");
		return "user/UserDeleteView";
	}
	
	@ResponseBody
	@PostMapping("user/userDelete") // 회원 탈퇴 실행 함수
	public String userDelete(@RequestBody UserVO userVO, Authentication authentication, HttpServletRequest request) throws Exception{
		Gson gson = new Gson();
		CustomUser loginInfo = (CustomUser) authentication.getPrincipal();
		boolean isValidPassword = passEncoder.matches(userVO.getPw(), loginInfo.getUser().getPw());
		
		log.info("isValidPassword:  "+isValidPassword);
    	log.info("passEncoder:  "+passEncoder); 
    	log.info(" userVo.getPw():  "+userVO.getPw());	
    	log.info("loginInfo.getUser().getPw():  "+loginInfo.getUser().getPw()); 
    	log.info(isValidPassword+"= passEncoder= . matches("+userVO.getPw()+","+ loginInfo.getUser().getPw()+")");
    	
    	if (isValidPassword) {
            log.info(" : isValidPassword :  "+isValidPassword);
            userVO.setMember_id(loginInfo.getUser().getMember_id());
            userVO.setPw(loginInfo.getUser().getPw());
            
            log.info("loginInfo.getUser()  :  "+loginInfo.getUser()); 
            log.info("loginInfo.getUser().getId()  :  "+loginInfo.getUser().getMember_id()); 
            log.info("userVo  : "+userVO.getPw()); 
            userService.userDelete(userVO);
            
            request.getSession().invalidate();  
            log.info("request.getSession().invalidate()");
            return gson.toJson(new ResponseVO<>(200, "success"));
        }

        log.info("notValidPassword");
        return gson.toJson(new ResponseVO<>(400, "fail"));
		
	}
	
	
	@PostMapping("user/boardDeleteUser") // 마이페이지 - 유저 본인 게시글 삭제
	public String boardDeleteuser(BoardVO boardVO, HttpSession session) throws Exception{
		
		boardService.deleteBoardVO(boardVO.getBoard_numbers());
		
		session.invalidate(); 
		return "user/userHome";
	}
	
	@GetMapping("user/boardList") // 마이페이지 - 내 게시글 list
	public String boardListView(BoardVO boardVO, UserVO userVO, Model model) {
		
		log.info("user board list");
		String member_id = userVO.getMember_id();
		log.info("user member_id : "+member_id); // name
		userVO = userService.getUserVO(member_id);
		log.info(userVO);
		
		model.addAttribute("userBoard", boardService.boardList(member_id));
		
		return "user/boardList";
	}
	
	@GetMapping("user/content_view2") // 내 게시글 list - > 자세히 보기
	public String content_view_detail(UserVO userVO, BoardVO boardVO, Model model) {
		log.info("board_view");
		int board_numbers = boardVO.getBoard_numbers();
		log.info(board_numbers); // name
		boardVO = boardService.getBoardVO(board_numbers);
		log.info(boardVO);
		
		model.addAttribute("userBoardDetail", boardVO);
		return "content_view_detail";
	}

	@GetMapping("user/qnaList") // 마이페이지 - 문의하기 리스트
	public String qnaListView(BoardVO boardVO, UserVO userVO, Model model) {
		
		log.info("user board list");
		String member_id = userVO.getMember_id();
		log.info("user member_id : "+member_id); // name
		userVO = userService.getUserVO(member_id);
		log.info(userVO);
		
		model.addAttribute("userQna", boardService.qnaList(member_id));
		
		return "user/qnaList";
	}
	//수정예정
	@GetMapping("user/ask") // 마이페이지 - 문의글 작성 페이지
	public String userQnAWrite_view() {
		return "user/write_view_qna";
	}
	
	@PostMapping("user/ask_write") // 마이페이지 - 문의글 작성
	public String userQnaWrite(UserVO userVO, @RequestParam(value="file") MultipartFile[] uploadfiles, BoardVO boardVO) throws IllegalStateException, IOException {
		String member_id = userVO.getMember_id();
		log.info("user member_id : "+member_id); // name
		boardService.writeBoardVO(uploadfiles, boardVO);
		log.info("문의글 작성");
		return "redirect:qnaList";
	}
	
	@GetMapping("user/content_view_qna") // 문의하기 -> 자세히 보기
	public String content_view_qna(UserVO userVO,BoardVO boardVO, Model model) {
		log.info("board_view");
		int board_numbers = boardVO.getBoard_numbers();
		log.info(board_numbers); // name
		boardVO = boardService.getBoardVO(board_numbers);
		log.info(boardVO);
		
		model.addAttribute("content_view", boardVO);
		return "content_view_qna";
	}
	
	@GetMapping("user/reportList") // 마이페이지 - 신고리스트 페이지
	public String reportListView(HttpSession session, UserVO userVO, BoardVO boardVO, Model model) {
		log.info("user board list");
		String member_id = userVO.getMember_id();
		log.info(session.getAttribute("member_id"));
		log.info("user member_id : "+member_id); // name
		userVO = userService.getUserVO(member_id);
		log.info(userVO);
		model.addAttribute("userReports", boardService.askList(member_id));
		return "user/reportList";
	}
	
	@GetMapping("user/content_view_rep") // 신고내역 -> 자세히 보기
	public String content_view_ask(UserVO userVO,BoardVO boardVO, Model model) {
		log.info("board_view");
		int board_numbers = boardVO.getBoard_numbers();
		log.info(board_numbers); // name
		boardVO = boardService.getBoardVO(board_numbers);
		log.info(boardVO);
		
		model.addAttribute("content_view", boardVO);
		return "content_view_ask";
	}
	
	@GetMapping("/userModify") // to modifypage
	public String user_modify() {
		log.info("modify personal information");
		return "user/userModify";
	}
	
	@PostMapping("/update") // to modify user information by user
	public String userModify(UserVO userVO, HttpSession session) {
		log.info("to Modify user information");
		
		log.info(userVO.getMember_id());  
		log.info(userVO.getEmail()); 
		log.info(userVO.getPw());
		log.info(userVO.getPhone()); 
		log.info(userVO.getGrade_name());
		
		userService.modifyUser(userVO);
		session.invalidate();
		
		return "redirect:/";
		
	}
	
	
	
	
	
	
	

}


