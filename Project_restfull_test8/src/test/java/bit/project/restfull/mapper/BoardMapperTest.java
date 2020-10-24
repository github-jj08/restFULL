package bit.project.restfull.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import bit.project.restfull.vo.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	
@Log4j
public class BoardMapperTest {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Test
	public void testBoardMapper() {
		log.info(boardMapper);	
	}
	
	@Test
	public void testBoardMapperList() {
		List<BoardVO> list = boardMapper.getBoardList();
		
		for(BoardVO vo : list) {
			log.info(vo.getbName());
		}
	}

}
