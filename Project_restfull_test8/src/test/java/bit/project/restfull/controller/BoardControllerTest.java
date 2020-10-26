package bit.project.restfull.controller;
import static org.junit.Assert.*;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import bit.project.restfull.mapper.AdminBoardMapper;
import bit.project.restfull.mapper.BoardMapper;
import bit.project.restfull.vo.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context_custom.xml"
})
@Log4j
public class BoardControllerTest {

	@Setter(onMethod_ = {@Autowired})	
	private WebApplicationContext ctx;	

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
		
		
	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(forwardedUrl("/WEB-INF/views/list.jsp"));
	}
	
//	@Test
//	public void testList() throws Exception {
//		mockMvc.perform(get("/list"))
//		.andExpect(status().isOk())//���� ����
//		.andDo(print()) //ģ���ϰ� MockHttpServletRequest�� handler�� ���� �˷���.
//		.andExpect(forwardedUrl("/WEB-INF/views/list.jsp"));
//	}
//		
//		
//	@Test
//	public void testWrite() throws Exception {
//		mockMvc.perform(post("/write"))
//		.andExpect(status().isFound())//���� ����
//		.andDo(print()) //ģ���ϰ� MockHttpServletRequest�� handler�� ���� �˷���.
//		.andExpect(redirectedUrl("list"));
//	}
	
	
	@Autowired
    private BoardMapper boardService;
	private AdminBoardMapper adminBoardMapper;

	private int board_numbers = 0;
    /**
     * 회원이 새로운 여행 후기를 작성.회원 id 임의로 지정
     * 글작성 시 두개의 메소드 호출 : 글작성 & 여행지 등록
     */
    @Test
    public void testInsertDiary() throws Exception {
    	BoardVO boardVO = new BoardVO();
    	
    	boardVO.setMember_id("testyj");
    	boardVO.setTitle("Test title");
    	boardVO.setContents("Test Contents");
    	boardVO.setBoardlist_numbers(1);
    	
    	boardService.insertBoardVO(boardVO);
        log.info("success insert");
    }

    /**
     * 특정 회원이 작성한 여행후기를 모두 읽어온다
     * @throws BusinessException
     */
    @Test
    public void testBoardListView() throws Exception {
    	String member_id = "testyj";
    	int start = 1;
    	int end = 6;
    	boardService.boardList(member_id, start, end);
    }

    /**
     * 회원의 후기목록 중 하나의 여행후기를 읽어온다.
     * @throws Exception
     */
    @Test
    public void testContent_view_detail() throws Exception {
    	boardService.getBoardVO(board_numbers);
    }

    /**
     * 여행 후기를 수정한다
     * @throws Exception
     */
    @Test
    public void testModify() throws Exception {
    	BoardVO boardVO = new BoardVO();
    	
    	boardVO.setMember_id("testID");
    	boardVO.setTitle("Test title");
    	boardVO.setContents("Test Contents");
    	boardVO.setBoardlist_numbers(1);

    	boardService.updateBoardVO(boardVO);
        
        log.info("success update");
    }

    /**
     * 여행 후기를 삭제한다
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
    	boardService.deleteBoardVO(board_numbers);
        log.info("success delete");
    }

}
