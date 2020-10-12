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
public class GoodsVO {
	private int goods_numbers; //GOODS_NUMBERS
	private String destination_name; //DESTINATION_NAME
	private String name; //NAME
	private int price; //PRICE
	private int amount; //AMOUNT
	private String status; //STATUS
	private String sellstart; //SELLSTART
	private String sellend; //SELLEND
	private String seller; //SELLER
}
