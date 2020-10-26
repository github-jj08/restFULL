package bit.project.restfull.controller;
import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import org.junit.runners.MethodSorters;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import bit.project.restfull.mapper.AdminBoardMapper;
import bit.project.restfull.mapper.BoardMapper;
import bit.project.restfull.service.BoardService;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.PagingVO;
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
public class BoardControllerTest {

	
	/**
	 * 게시판 CRUD TEST
	 * read(list) > create > read(vo) > update > delete 순서로 진행 
	 */

	@Autowired
    private BoardService boardService;

	public static int BOARD_NUMBERS = 0;

    /* 특정 회원이 작성한 여행후기 list 읽어온다 */
    @Test
    public void testABoardListView() throws Exception {
    	log.info("list");
    	String member_id = "testyj";
    	int totalCount = boardService.countBoard(member_id);
    	PagingVO pagingVO = new PagingVO(totalCount, 1,6);
		boardService.boardList(member_id, pagingVO );
    }

     /* 회원이 새로운 여행 후기를 작성한다. (회원 id는 임의로 지정) */
    @Test
    public void testBInsert() throws Exception {
    	log.info("insert");
    	
    	BoardVO boardVO = new BoardVO();
    	
    	boardVO.setMember_id("testyj");
    	boardVO.setTitle("Test title");
    	boardVO.setContents("Test Contents");
    	boardVO.setBoardlist_numbers(1);
    	boardVO.setFilter_numbers(1);
    	boardVO.setLocation("");
    	
    	boardService.writeBoardVO(boardVO);
    	System.out.println("boardVO... " + boardVO.getTitle());
		int boardNum = boardVO.getBoard_numbers();
		
		log.info("success insert : " + boardNum);

		BOARD_NUMBERS = boardNum;
    }
    
    /* 회원의 후기목록 중 하나의 여행후기를 읽어온다. */
    @Test
    public void testCContent_view_detail() throws Exception {
    	log.info("get : " + BOARD_NUMBERS);
    	
    	boardService.getBoardVO(BOARD_NUMBERS);
    }

    /* 여행 후기를 수정한다. */
    @Test
    public void testDModify() throws Exception {
    	log.info("modify");
    	
    	BoardVO boardVO = new BoardVO();
    	
    	boardVO.setMember_id("testyj");
    	boardVO.setTitle("Test title222");
    	boardVO.setContents("Test Contents222");
    	boardVO.setBoardlist_numbers(1);

    	boardService.modifyBoardVO(boardVO);
        
        log.info("success update");
    }

    /* 여행 후기를 삭제한다. */
    @Test
    public void testEDelete() throws Exception {
    	log.info("delete");
    	
    	boardService.deleteBoardVO(BOARD_NUMBERS);
        log.info("success delete");
    }

}
