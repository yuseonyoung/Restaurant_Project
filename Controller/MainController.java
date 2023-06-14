package Controller;


import Service.CookService;
import Service.SalesService;
import JDBCUtil.TotalView;

public class MainController {
	
	public static void main(String[] args) {

		TotalView lv = TotalView.getInstance();

		
			lv.init();
	
		SalesService sa = SalesService.getInstance();
		//sa.empSal();
		
		//sa.dailyCost(); //기능 OK
		//sa.dailyGain(); //기능ok
		//sa.dailySales();//기능 ok
		//sa.weeklyCost(); 
		//sa.weeklyGain(); //기능ok
		sa.weeklySales();
		
		
		
		
		
  }
}