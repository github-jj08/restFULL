package bit.project.restfull.interceptor;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import bit.project.restfull.vo.UserVO;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */

@Log4j

public class RecInterceptor extends HandlerInterceptorAdapter{
   
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      
      //HttpServletRequest request : http 프로토콜에 있는 모든 정보를 불러들이는 역할을 하는 객체 (세션, 아이피 등)
      String member_id = "admin";
      String pw = "admin";
      
      log.info("preHandle 실행");
      
      //session 객체를 가져옴
      HttpSession session = request.getSession();
      
      //login처리를 담당하는 사용자 정보를 담고있는 객체를 가져옴.
      UserVO user = (UserVO) session.getAttribute("uservo");
      
      if(user==null) { //user에 세션이 저장되어있지않다면 
         log.info("user가 null");
         //로그인이 안되있는 상태이므로 로그인 폼으로 다시 돌려보냄(redirect)
         response.sendRedirect(request.getContextPath());
         return false; // 더이상 컨트롤러 요청으로 가지않도록 false로 변환
      } else if((user.getMember_id().equals(member_id)) &&
    		  	(user.getPw().equals(pw))) {
           return true;
           } response.sendRedirect(request.getContextPath());
           return false;
   }
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
   throws Exception {
   
      super.postHandle(request, response, handler, modelAndView);
      
      log.info("postHandle 실행");
   }
   
}


