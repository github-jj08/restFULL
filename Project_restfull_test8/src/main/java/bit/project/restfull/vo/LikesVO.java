package bit.project.restfull.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@AllArgsConstructor
public class LikesVO {
	private int board_numbers;
	private String member_id;
}
