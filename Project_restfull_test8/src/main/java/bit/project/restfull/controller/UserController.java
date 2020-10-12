//package bit.project.restfull.controller;
//
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.google.gson.Gson;
//
//import edu.bit.restFull.mapper.PaymentMapper;
//import edu.bit.restFull.service.BoardService;
//import edu.bit.restFull.service.UserService;
//import edu.bit.restFull.vo.BoardVO;
//import edu.bit.restFull.vo.PaymentVO;
//import edu.bit.restFull.vo.CustomUser;
//import edu.bit.restFull.vo.ResponseVO;
//import edu.bit.restFull.vo.UserVO;
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j;
//
///**
// * Handles requests for the application home page.
// */
//@Log4j
//@Controller()
//@AllArgsConstructor
//public class UserController {
//	
//	private UserService userservice;
//	private BoardService boardService;
//	private PaymentMapper commentMapper;
//	@Inject
//    private BCryptPasswordEncoder passEncoder;
//	
//	
//
//}
//
//
