package DTO;

public class EmployeeDTO {
	private String e_id;
	private String e_name;
	private String e_num;
	private String e_hiredate;
	private int e_sal;
	private String e_passwd;
	private String e_rank;
	public EmployeeDTO(){}
	
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getE_passwd() {
		return e_passwd;
	}
	public void setE_passwd(String e_passwd) {
		this.e_passwd = e_passwd;
	}
	public String getE_rank() {
		return e_rank;
	}
	public void setE_rank(String e_rank) {
		this.e_rank = e_rank;
	}
	
}
