package DTO;

public class OrderDTO {
	private String i_id;
	private String i_name; 
	private String i_origin;
	private String i_classification;
	private String i_expirationdate;
	private int i_inventory;
	private String i_storage;
	private String i_allergy;
	private String v_id;
	
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
	public String getI_classification() {
		return i_classification;
	}
	public void setI_classification(String i_classification) {
		this.i_classification = i_classification;
	}
	public String getI_expirationdate() {
		return i_expirationdate;
	}
	public void setI_expirationdate(String i_expirationdate) {
		this.i_expirationdate = i_expirationdate;
	}
	public int getI_inventory() {
		return i_inventory;
	}
	public void setI_inventory(int i_inventory) {
		this.i_inventory = i_inventory;
	}
	public String getI_storage() {
		return i_storage;
	}
	public void setI_storage(String i_storage) {
		this.i_storage = i_storage;
	}
	public String getI_allergy() {
		return i_allergy;
	}
	public void setI_allergy(String i_allergy) {
		this.i_allergy = i_allergy;
	}
	public String getV_id() {
		return v_id;
	}
	public void setV_id(String v_id) {
		this.v_id = v_id;
	}
}
