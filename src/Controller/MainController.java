package Controller;

import Service.CookService;
import Service.EmployeeService;
import Service.FoodOrderService;
import Service.SalesService;
import JDBCUtil.TotalView;

public class MainController {
	
	public static void main(String[] args) {

		TotalView lv = TotalView.getInstance();
		lv.init();
		//lv.managerSelect();
		
		
		
		FoodOrderService fs = FoodOrderService.getInstance();
		//fs .FoodList();
		//fs.FoodOrder();
		
		EmployeeService es = EmployeeService.getInstance();
		//es.EmpInfo();
		//es.empIncen();
		//es.getUpDateEmp();
		//es.searchEmp();
		
		SalesService ss = SalesService.getInstance();
		//ss.netProfit();
		ss.oneDay();
		//ss.weeklyGain();
		//ss.dailySales();
		//ss.weeklySales();
		
		
  }
}