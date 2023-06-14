package Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.OrderDAO;
import DTO.OrderDTO;
import JDBCUtil.ScanUtil;

public class OrderService {
	
	Scanner sc = new Scanner(System.in);
	OrderDTO od = new OrderDTO();
	
	
	private static OrderService instance= null;
	private OrderService() {}
	
	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}
//-------------------------------------------------------싱글톤 패턴
	
	OrderDAO dao = OrderDAO.getInstance();

	public void selectOrder(){
		int count =1;
		List<Map<String, Object>> list = dao.selectList();
		
		System.out.println("───────────────────────────────────────");
		System.out.println("               재고가 얼마 남지 않은 식재료 목록 ");
		System.out.println();
		System.out.println("  순번     식재료코드          식재료명       재고        원산지");
		for(Map<String, Object> n : list) {
		System.out.printf("%3d  %-11s %3s      %5d개        %3s \n",(count++),n.get("I_ID"),n.get("I_NAME")
				,Integer.parseInt((String.valueOf(n.get("I_INVEN"))).trim()),n.get("I_ORIGIN"));
		System.out.println("───────────────────────────────────────");
		
		
		}
	}
//------------------------------------------------------재고수량이 적은 식재료 목록 보여주기	
	public void ingredientOrder() {
		System.out.print("구입하실 식재료명을 입력 하세요 : ");
		String name = sc.nextLine();
		od.setI_name(name);
		
		System.out.print("구입하실 수량을 입력 하세요 : ");
		int num = ScanUtil.nextInt();
		od.setI_inven(num);
		
		dao.orderInsert(od.getI_name(), od.getI_inven());
		
	}
	
	public void OrderList(){
		int count =1;
		List<Map<String, Object>> list = dao.orderAllList();
		
		System.out.println("─────────────────────────────────────────────────────────");
		System.out.println("                                           식재료 목록 ");
		System.out.println();
		System.out.println("  순번     식재료코드          식재료명       재고        원산지        유통기한        보관");
		for(Map<String, Object> n : list) {
		System.out.printf("%3d  %-11s %3s      %5d개        %3s      %3s      %3s        \n",(count++),n.get("I_ID"),n.get("I_NAME")
				,Integer.parseInt((String.valueOf(n.get("I_INVEN"))).trim()),n.get("I_ORIGIN")
				,n.get("P_EXPDATE"),n.get("I_STOR"));
		System.out.println("──────────────────────────────────────────────────────────");
		
		
		}
	}
	public void OrderDelete() {
		int count =1;
		System.out.println("─────────────────────────────────────────────────────────");
		System.out.println("유통기한이 지난 식재료 목록");
		System.out.println("  순번     식재료코드          식재료명     구매일자       유통기한     재고");
		List<Map<String, Object>> list = dao.deleteList();
		for(Map<String, Object> n : list) {
			System.out.printf("%3d  %3s       %-11s %3s      %5s    %3s   %3s \n",(count++),n.get("P_ID"),n.get("I_ID")
					,n.get("I_NAME"),n.get("P_BDATE"),n.get("P_EXPDATE"),n.get("P_QTY"));
		}
		
	}
}
