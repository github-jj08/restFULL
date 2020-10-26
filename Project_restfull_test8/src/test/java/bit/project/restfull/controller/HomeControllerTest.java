package bit.project.restfull.controller;

import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import bit.project.restfull.mapper.BoardMapper;
import bit.project.restfull.service.AdminBoardService;
import bit.project.restfull.service.BoardService;
import bit.project.restfull.vo.AdminBoardVO;
import bit.project.restfull.vo.BoardVO;
import bit.project.restfull.vo.PagingVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)	
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context_custom.xml"})
@Log4j
public class HomeControllerTest {

	/** 
	 * 메인 > 검색기능 TEST
	 */
	@Autowired
	private BoardService boardService;
	
    @Test
    public void testABoardListView() throws Exception {
    	String searchWord = "t";
    	log.info("search : " + searchWord);
    	int totalCount = boardService.countBoard(searchWord);
    	int boardlist_numbers = 1;								//메인 게시글 그룹번호 = 1
    	
    	PagingVO pagingVO = new PagingVO(totalCount, 1,6);
		boardService.getList(pagingVO, boardlist_numbers, searchWord);
    }

	
}
