package bit.project.restfull.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context_custom.xml"
})
@Log4j
public class LoginControllerTest {


	/**
	 * Spring Security > Login Test
	 * */
	
	@Setter(onMethod_ = {@Autowired})	
	private WebApplicationContext ctx;	

	private MockMvc mockMvc;

	@Test
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()).build();
	}

	@Test
	public void testLogin() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		
		if (a == null) {
            log.info("null");
        } else {
        	log.info((UserDetails) a.getPrincipal());
        }
	}
//
//	@Test
//	public void testAdduser() {
//		fail("Not yet implemented");
//	}

}
