package camping.dto;

public class OrderDto {
	//결제번호 기본키(시퀀스, 자동생성)
	String o_number; //주문번호(랜덤생성)
	int amount; //결제금액
	String order_date; //결제시간
	//String order_item; //삭제 
	String paytype; //결제타입
	int userNumber; //유저번호, 외래키
	int r_id; //예약번호, 외래키
	
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
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	
}
