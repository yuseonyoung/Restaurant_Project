package DTO;

public class SeatDTO {

	private static SeatDTO instance =null;
	private SeatDTO() {}
	public static SeatDTO getInstance() {
		if(instance ==null)
			instance = new SeatDTO();
		return instance;
	}
	
	private String s_no;

	public String getS_no() {
		return s_no;
	}

	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
}
