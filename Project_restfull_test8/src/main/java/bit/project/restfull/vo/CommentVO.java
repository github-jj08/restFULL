package bit.project.restfull.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
	private int comments_numbers;
	private int board_numbers;
	private String contents;
	private Timestamp dates;
	private String member_id;
}
