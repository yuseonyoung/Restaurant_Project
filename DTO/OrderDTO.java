package DTO;

public class OrderDTO {
	private String i_id;
	private String i_name; 
	private String i_origin;
	private String i_expdate;
	private int i_inven;
	private String i_stor;
	
	public OrderDTO(){}


	public String getI_id() {
		return i_id;
	}
	public void setI_id(String i_id) {
		this.i_id = i_id;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
	public String getI_origin() {
		return i_origin;
	}
	public void setI_origin(String i_origin) {
		this.i_origin = i_origin;
	}
	public String getI_expdate() {
		return i_expdate;
	}
	public void setI_expdate(String i_expdate) {
		this.i_expdate = i_expdate;
	}
	public int getI_inven() {
		return i_inven;
	}
	public void setI_inven(int i_inven) {
		this.i_inven = i_inven;
	}
	public String getI_stor() {
		return i_stor;
	}
	public void setI_stor(String i_stor) {
		this.i_stor = i_stor;
	}
	
}
