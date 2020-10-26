package bit.project.restfull.controller;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import bit.project.restfull.service.UserService;
import bit.project.restfull.vo.UserVO;

import javax.servlet.Filter;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context_custom.xml"
})
@Log4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginControllerTest {

	@Setter(onMethod_ = {@Autowired})	
	private WebApplicationContext ctx;	

	private MockMvc mockMvc;

	@Autowired
	private Filter springSecurityFilterChain;
	
	UserVO userVO;
	
	@Autowired
	UserService userService;
	
	public static String loginUrl = "/login";
	
	private static String MY_ID = null;
	private static String MY_PW = null;
	
	@Test
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.dispatchOptions(true)
	            .addFilters(springSecurityFilterChain)
	            .build();
	}
	
	/* 회원가입 test */
	@Test
	public void AaddUserTest() throws Exception {
		log.info("============ sign up test ============");
		userVO = new UserVO();
	       
		//임시 아이디 생성을 위한 난수 생성
		int count = (int) (Math.random() * 10) + 1;
		
		String testId = "JunitTest_id" + count;
		log.info(testId);
		
		int str = userService.getUser(testId);
	       
		MY_ID = testId;
		MY_PW = testId;

		if(str == 0) {
			//중복 확인 결과가 참이면(중복되는 id가 없으면)
			userVO.setMember_id(testId);
			userVO.setPw(testId);
			userVO.setName("junit"+count);
			userVO.setBirth(null);

			userService.addUser(userVO);
			log.info("중복되지않는 id - 등록성공");
			
	    } else {
	    	fail("중복된 id - 등록실패");
	    }
	      
	}


}
