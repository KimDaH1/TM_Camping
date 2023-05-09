package camping.dto;

public class OrderDto {

	String o_number;
	int amount;
	String order_date;
	String order_item;
	int userNumber;
	
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
	public String getOrder_item() {
		return order_item;
	}
	public void setOrder_item(String order_item) {
		this.order_item = order_item;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	
}
