package bit.project.restfull.page;

import lombok.*;
import lombok.extern.log4j.Log4j;

@Getter
@Setter
@Log4j
public class Criteria {

	
	private int pageNum;
	// 페이지 번호
	private int amount;
	// 한 페이지당 데이터 갯수
	
	public Criteria() {
		this(1, 10);
		// 페이지당 10개
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	
}