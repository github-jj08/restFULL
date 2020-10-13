package bit.project.restfull.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminBoardVO {
	private int board_numbers;
	private String title;	
	private String contents;
	private String hit;
	private Timestamp dates;
	private String member_id;
	private int boardlist_numbers;
	private String boardlistName;
	private AttachmentVO attachmentVO;	
	private String thumbnail;
	
}