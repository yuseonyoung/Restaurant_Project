package DTO;

public class FoodOrderDTO {
   private int f_no;
   private String f_name;
   private int f_price;
   private int of_qty;
   private String o_id;
   private String f_id;
   
   private static FoodOrderDTO instance = null;
   
   private FoodOrderDTO() {}
   
   public static FoodOrderDTO getInstance(){
	   if(instance ==null) instance = new FoodOrderDTO();
	   return instance;
   }
   
   public int getF_no() {
      return f_no;
   }

   public void setF_no(int f_no) {
      this.f_no = f_no;

   }

   public String getF_name() {
      return f_name;
   }

   public void setF_name(String f_name) {
      this.f_name = f_name;
   }

   public int getF_price() {
      return f_price;
   }

   public void setF_price(int f_price) {
      this.f_price = f_price;
   }

   public int getOF_qty() {
      return of_qty;
   }

   public void setOF_qty(int of_qty) {
      this.of_qty = of_qty;
   }



   public String getO_id() {
      return o_id;
   }

   public void setO_id(String o_id) {
      this.o_id = o_id;
   }
   
   public String getF_id() {
      return f_id;
   }

   public void setF_id(String f_id) {
      this.f_id = f_id;
   }


}