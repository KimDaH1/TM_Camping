package camping.dto;

public class OrderDto {
	int o_id;//결제번호 기본키(시퀀스, 자동생성)
	String o_number; //주문번호(랜덤생성)
	int amount; //결제금액
	String order_date; //결제시간
	//String order_item; //삭제 
	String paytype; //결제타입
	int userNumber; //유저번호, 외래키
	int r_number; //예약번호, 외래키
	String o_state; //결제 상태
	
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public String getO_number() {
		return o_number;
	}
	public void setO_number(String o_number) {
		this.o_number = o_number;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
//	public String getOrder_item() {
//		return order_item;
//	}
//	public void setOrder_item(String order_item) {
//		this.order_item = order_item;
//	}
	public int getUserNumber() {
		return userNumber;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getR_number() {
		return r_number;
	}
	public void setR_number(int r_number) {
		this.r_number = r_number;
	}
	public String getO_state() {
		return o_state;
	}
	public void setO_state(String o_state) {
		this.o_state = o_state;
	}
	
}
