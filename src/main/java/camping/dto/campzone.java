package camping.dto;

public class campzone {
	int idx;
	String cpname;
 	String cptel;
 	Double lat;
 	Double lng;
 	String addr;
 	String addr_detail;
	
 	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getCpname() {
		return cpname;
	}
	public void setCpname(String cpname) {
		this.cpname = cpname;
	}
	
	public String getCptel() {
		return cptel;
	}
	public void setcptel(String cptel) {
		this.cptel = cptel;
	}
	
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getAddr_detail() {
		return addr_detail;
	}
	public void setAddr_detail(String addr_detail) {
		this.addr_detail = addr_detail;
	}
	
	
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public double getLat() {
		return lat;
	}
	

}
