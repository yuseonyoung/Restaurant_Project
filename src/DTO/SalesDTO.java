package DTO;

public class SalesDTO {
	private String s_daily;     	//���� �Ϻ�
	private String s_weekly; 		//���� �ְ�
	private String s_priceDaily;	//����� ���Ժ���� �Ϻ�
	private String s_priceWeekend;	//����� ���Ժ���� �ֺ�
	private String s_gain;			//������
	
	
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