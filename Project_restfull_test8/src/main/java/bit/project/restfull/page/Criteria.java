package bit.project.restfull.page;

import lombok.*;
import lombok.extern.log4j.Log4j;

@Getter
@Setter
@Log4j
public class Criteria {

	
	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	
}