package bit.project.restfull.vo;

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
public class SidoguVO {
	private int sidocode;
	private String sidoname;
	private int sigungu_code;
	private String sigunguname;
}
