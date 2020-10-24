package bit.project.restfull.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class BoardControllerTest {
	
	@Setter(onMethod_ = @Autowired)		
	private WebApplicationContext ctx;	
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();//mock�� ���� = mock���� webAppContext ��Ʈ�� ������
	}
	
	
	/*
	 * list�� �̷��� �Ǿ��ִٰ� �Ҷ�.
	 @GetMapping("/list")
	 public void list(Model model) {	
		 log.info("list");
		 model.addAttribute("list", service.getList());	service��ü�� ��� �о���̳ĸ�
		root-context.xml����. 
		<context:component-scan
		base-package="edu.bit.board.service"></context:component-scan>
	 }*/
	@Test
	public void testList() throws Exception {
		 mockMvc.perform(get("/list"))	//list�� ���ε� �� �ֳ�
		 .andExpect(status().isOk())	//���� ����(http �������ݿ� ���� ������ �ѷ���)
		 .andDo(print())
         .andExpect(forwardedUrl("/WEB-INF/views/list.jsp")); //list.jsp�� ������.
	}

//	������ �׽�Ʈ
//	@PostMapping("/write")
//	public String write(BoardVO boardVO) throws Exception {
//		log.info("write()");		
//		service.writeBoard(boardVO);				
//		return "redirect:list";
//	}
	
	@Test
	public void testWrite() throws Exception{
		 mockMvc.perform(post("/write"))//post���
		 .andExpect(status().isFound())	//���� ����(http �������ݿ� ���� ������ �ѷ���). isOK = �����ڵ� 200, redirectedURL�� ������ �ӽ��̵� �ڵ��� 302. isFound()!!!!
		 .andDo(print())
         .andExpect(redirectedUrl("list")); //��/WEB-INF/views/list.jsp�ƴϰ� �׳� list?
	}	
}
