package bit.project.restfull.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TravelVO {
	private int travel_numbers;	//TRAVEL_NUMBERS	NUMBER(10,0)
	private String member_id;	//MEMBER_ID	VARCHAR2(100 BYTE)
	private String contents;	//CONTENTS	VARCHAR2(1000 BYTE)
	private String tcAlias;		//TCALIAS	VARCHAR2(100 BYTE)
	private Timestamp dates;	//DATES	DATE
	private String tcUiqueNum;	//TCUNIQUENUM	VARCHAR2(100 BYTE)
	public int getTravel_numbers() {
		return travel_numbers;
	}
	public void setTravel_numbers(int travel_numbers) {
		this.travel_numbers = travel_numbers;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTcAlias() {
		return tcAlias;
	}
	public void setTcAlias(String tcAlias) {
		this.tcAlias = tcAlias;
	}
	public Timestamp getDates() {
		return dates;
	}
	public void setDates(Timestamp dates) {
		this.dates = dates;
	}
	public String getTcUiqueNum() {
		return tcUiqueNum;
	}
	public void setTcUiqueNum(String tcUiqueNum) {
		this.tcUiqueNum = tcUiqueNum;
	}
	
	
}
