package DTO;

public class SalesDTO {
	private String s_daily;     	//메출 일별
	private String s_weekly; 		//매출 주간
	private String s_priceDaily;	//식재료 구입비용을 일별
	private String s_priceWeekend;	//식재료 구입비용을 주별
	private String s_gain;			//순수익
	
	
	public SalesDTO() {}


	public String getS_daily() {
		return s_daily;
	}
	public void setS_daily(String s_daily) {
		this.s_daily = s_daily;
	}
	public String getS_weekend() {
		return s_weekly;
	}
	public void setS_weekend(String s_weekend) {
		this.s_weekly = s_weekend;
	}
	public String getS_priceDaily() {
		return s_priceDaily;
	}
	public void setS_priceDaily(String s_priceDaily) {
		this.s_priceDaily = s_priceDaily;
	}
	public String getS_priceWeekend() {
		return s_priceWeekend;
	}
	public void setS_priceWeekend(String s_priceWeekend) {
		this.s_priceWeekend = s_priceWeekend;
	}
	public String getS_gain() {
		return s_gain;
	}
	public void setS_gain(String s_gain) {
		this.s_gain = s_gain;
	}
}	