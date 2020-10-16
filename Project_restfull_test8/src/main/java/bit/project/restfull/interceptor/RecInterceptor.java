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
      
      //HttpServletRequest request : http 
      String member_id = "admin";
      String pw = "admin";
      
      log.info("preHandle");
      
      //session 
      HttpSession session = request.getSession();
      
      //login
      UserVO user = (UserVO) session.getAttribute("uservo");
      
      if(user==null) { //user 
         log.info("user is null");
         response.sendRedirect(request.getContextPath());
         return false; // 
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
      
      log.info("postHandle");
   }
   
}


