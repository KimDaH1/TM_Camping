package camping.dto;

public class ReservationDto {

	int r_id;
	String sDate;
	String eDate;
	
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	@Override
	public String toString() {
		return "ReservationDto [r_id=" + r_id + ", sDate=" + sDate + ", eDate=" + eDate + "]";
	}
	
}
