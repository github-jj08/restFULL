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
public class RequestVO {
	private String request_numbers;
	private Timestamp dates;		
	private String imp_uid;		
	private String member_id;		
	private int goods_numbers;		
	private String destination_name;	
	private int cancel;		
	private int price;
	private String merchant_uid; 
	private String productName;
	private int amount;
}
