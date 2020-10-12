package bit.project.restfull.vo;

import org.springframework.web.multipart.MultipartFile;

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
public class AttachmentVO {
	private int board_numbers;
	private String filename;
	private String filedirectory;
	private int filesize;
}
