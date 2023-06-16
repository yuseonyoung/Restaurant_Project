package Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.OrderDAO;
import DTO.IngredientDTO;
import DTO.OrderDTO;
import JDBCUtil.ScanUtil;
import JDBCUtil.TotalView;

public class OrderService {

	Scanner sc = new Scanner(System.in);
	IngredientDTO od = new IngredientDTO();
	OrderDTO pc = new OrderDTO();

	private static OrderService instance = null;

	private OrderService() {
	}

	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}
//-------------------------------------------------------싱글톤 패턴

	OrderDAO dao = OrderDAO.getInstance();

	public void selectOrder() throws Exception {
		int count = 1;
		List<Map<String, Object>> list = dao.selectList();

		if (list != null) {
			System.out.println("───────────────────────────────────────");
			System.out.println("               재고가 얼마 남지 않은 식재료 목록 ");
			System.out.println();
			System.out.println("  순번     식재료코드          식재료명       재고        원산지");
			for (Map<String, Object> n : list) {
				System.out.printf("%3d  %-11s %3s      %5d개        %3s \n", (count++), n.get("I_ID"), n.get("I_NAME"),
						Integer.parseInt((String.valueOf(n.get("I_INVEN"))).trim()), n.get("I_ORIGIN"));
				System.out.println("───────────────────────────────────────");
			}
		} else {
			System.out.println("식재료가 충분한 상태입니다.");
		}
	}

//------------------------------------------------------재고수량이 적은 식재료 목록 보여주기	
	public void ingredientOrder() {
		TotalView tv = TotalView.getInstance();
		Loop1: while (true) {
			System.out.print("구입하실 식재료명을 입력 하세요 : ");
			String name = sc.nextLine();
			od.setI_name(name);

			System.out.print("구입하실 수량을 입력 하세요 : ");
			int num = ScanUtil.nextInt();
			od.setI_inven(num);
			try {
			dao.orderInsert(od.getI_name(), od.getI_inven());
			}catch(Exception e) {
				System.out.println("식재료명이 존재하지 않습니다. 다시 입력해주세요.");
				continue;
			}
			System.out.println("발주가 완료 되었습니다.");
			System.out.println();
			while (true) {
				System.out.println("0. 돌아가기");
				System.out.println("1. 식재료 추가 발주");
				System.out.println("2. 종료하기");
				System.out.print("원하시는 기능을 숫자로 입력 해주세요 : ");
				int input = ScanUtil.nextInt();
				if (input == 0) {
					tv.managerSelect();
				} else if (input == 1) {
					continue Loop1;
				} else if (input == 2) {
					System.out.println("관리 시스템을 종료합니다.");
					System.exit(0);
				} else {
					System.out.println("잘못된 값을 입력 하였습니다.");
					continue;
				}
			}
		}
	}

	public void OrderList() {
		int count = 1;
		TotalView tv = TotalView.getInstance();
		List<Map<String, Object>> list = dao.orderAllList();

		System.out.println("─────────────────────────────────────────────────────────");
		System.out.println("                                           식재료 목록 ");
		System.out.println();
		System.out.println("  순번     식재료코드          식재료명       재고        원산지        유통기한        보관");
		for (Map<String, Object> n : list) {
			System.out.printf("%3d  %-11s %3s      %5d개        %3s      %3s      %3s        \n", (count++),
					n.get("I_ID"), n.get("I_NAME"), Integer.parseInt((String.valueOf(n.get("I_INVEN"))).trim()),
					n.get("I_ORIGIN"), n.get("P_EXPDATE"), n.get("I_STOR"));
			System.out.println("──────────────────────────────────────────────────────────");
		}
		
		System.out.println();
		while(true) {
			System.out.println("0. 돌아가기");
			System.out.println("1. 종료하기");
			System.out.print("원하시는 기능을 숫자로 입력해주세요 : ");
			int num = ScanUtil.nextInt();
		
			if(num==0) {
				tv.managerSelect();
				break;
			}else if(num==1) {
				System.exit(0);
			} 
		}
	}

	public void OrderDelete() throws Exception {
		TotalView tv = TotalView.getInstance();
		int count = 1;
		List<Map<String, Object>> list = dao.deleteList();
		if (list != null) {
			System.out.println("─────────────────────────────────────────────────────────");
			System.out.println("유통기한이 지난 식재료 목록");
			System.out.println("  순번     발주서코드          식재료명     구매일자       유통기한     재고");
			for (Map<String, Object> n : list) {
				System.out.printf("%6d  %3s       %-11s   %10s      %10s    %10s  \n", (count++), n.get("P_ID"),
						n.get("I_NAME"), n.get("P_BDATE"), n.get("P_EXPDATE"), n.get("P_QTY"));
			}

			while (true) {
				
				System.out.println("0. 돌아가기");
				System.out.println("1. 유통기한이 지난 식재료를 선택하여 폐기");
				System.out.println("2. 유통기한이 지난 식재료를 전체 폐기");

				System.out.print("숫자 입력 : ");
				int num = ScanUtil.nextInt();

				if (num == 0) {
					tv.managerSelect();
				} else if (num == 1) {
					selectDelete();
					break;
				} else if (num == 2) {
					DeleteAll();
					break;
				} else {
					System.out.println("잘못된 값을 입력 하였습니다. 다시 입력하여주세요.");
					continue;
				}
			}
		} else {
			System.out.println("유통기한이 지난 식재료가 없습니다.");
			System.out.println("0. 돌아가기");
			System.out.println("1. 종료하기");
			
			while(true) {
				System.out.print("숫자 입력 : ");
				int num = ScanUtil.nextInt();
	
				if(num==0) {
					tv.managerSelect();
					break;
				}else if(num==1) {
					System.exit(0);
				}else {
					System.out.println("잘못된 값을 입력하였습니다.");
					System.out.println("다시 입력해주세요");
					continue;
				}
			}
		}
	}

	public void selectDelete() throws Exception {

		while (true) {
			if (dao.deleteList() != null) {
				System.out.print("삭제하실 발주서코드를 입력하세요 : ");
				pc.setP_id(ScanUtil.nextInt());
				System.out.print("삭제하실 식재료명을 입력하세요 : ");
				od.setI_name(ScanUtil.nextLine());

				dao.orderDelete(pc.getP_id(), od.getI_name());

				System.out.println("삭제 되었습니다 !");
				System.out.println();
				System.out.println("1. 돌아가기");
				System.out.println("2. 식재료 추가 삭제");
				System.out.println("3. 시스템종료");
				System.out.println();
				System.out.print("원하시는 기능을 숫자로 입력하여주세요 : ");
				int value = ScanUtil.nextInt();

				if (value == 1) {
					OrderDelete();
					break;
					
				} else if (value == 2) {
					continue;
				} else if (value == 3) {
					System.exit(0);
				} else {
					System.out.println("잘못된 값이 입력 되었습니다.");
					continue;
				}
			} else {
				System.out.println("유통기한이 지난 식재료가 없습니다.");
			}
		}
	}

	public void DeleteAll() throws Exception {
		dao.deleteAllValue();
		System.out.println("모든 데이터가 삭제되었습니다.");
		OrderDelete();
	}
}
