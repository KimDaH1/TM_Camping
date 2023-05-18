package camping.dto;

public class ReservationDto {

	int r_number;
	String s_date;
	String e_date;
	String amount;
	String userid;
	int c_id;
	String r_state;
	
	public ReservationDto() {
	}
	
	public ReservationDto(int r_number, String s_date, String e_date, String amount, String userid, int c_id, String r_state) {
	
		this.r_number = r_number;
		this.s_date = s_date;
		this.e_date = e_date;
		this.amount = amount;
		this.userid = userid;
		this.c_id = c_id;
		this.r_state = r_state;
	}
	
	public int getR_number() {
		return r_number;
	}
	public void setR_number(int r_number) {
		this.r_number = r_number;
	}
	public String getS_date() {
		return s_date;
	}
	public void setS_date(String s_date) {
		this.s_date = s_date;
	}
	public String getE_date() {
		return e_date;
	}
	public void setE_date(String e_date) {
		this.e_date = e_date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getR_state() {
		return r_state;
	}
	public void setR_state(String r_state) {
		this.r_state = r_state;
	}		
}