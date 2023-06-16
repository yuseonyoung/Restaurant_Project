package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import DAO.CookDAO;
import DAO.OrderDAO;
import DTO.CookDTO;
import JDBCUtil.ScanUtil;
import JDBCUtil.TotalView;
import JDBCUtil.UndoUtil;

public class CookService extends UndoUtil{
	Scanner sc = new Scanner(System.in);
	// 싱글톤
	private static CookService instance = null;
	private CookService () {}

	public static CookService getInstance() {
		if (instance == null) {
			instance = new CookService();
		}
		return instance;
	}

	CookDAO dao = CookDAO.getInstance();


	// 각 음식에 들어가는 각 식재료 정보(수량, 단위) 조회
	public void getRecipe() {
		Loop1: while (true) {
			TotalView tv = TotalView.getInstance();
			System.out.print("조회하실 요리이름을 입력하세요: ");
			String foodName = sc.nextLine();
			List<Object> param = new ArrayList<Object>();
			param.add(foodName);

			try {
				dao.getRecipe(param);
			}catch(Exception e) {
				System.out.println("입력이 잘못 되었습니다. 다시 입력해주세요.");
				continue;
			}

			List<Map<String, Object>> list = dao.getRecipe(param);

			System.out.println("레시피 조회중...");   
			for (int i=0; i<10; i++) {
				System.out.print("■");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}


			System.out.println("──────────────────────────────");
			System.out.println("               요리 레시피 ");
			System.out.println();
			System.out.println("   요리         식재료        소모량    단위");
			for(Map<String, Object> n : list) {
				System.out.printf("%3s\t%5s\t%4s\t%3s\n",n.get("F_NAME"),n.get("I_NAME"),n.get("C_QTY"), n.get("I_UNIT"));
				System.out.println("─────────────────────────────");}

			while (true) {
				System.out.println("0. 돌아가기");
				System.out.println("1. 레시피 추가 조회");
				System.out.println("2. 요리하기");
				System.out.println("3. 종료하기");
				System.out.print("원하시는 기능을 숫자로 입력 해주세요 : ");
				int input = ScanUtil.nextInt();
				if (input == 0) {
					tv.managerSelect();
				} else if (input == 1) {
					continue Loop1;
				} else if (input == 2) {
					Cooking();
				} else if (input == 3) {
					System.out.println("관리 시스템을 종료합니다.");
					System.exit(0);
				} else {
					System.out.println("잘못된 값을 입력 하였습니다.");
					continue;
				}
			}
		}
	}

	// 음식마다 소모되는 식재료 원가 계산
	public void printCostAll() {
		TotalView tv = TotalView.getInstance();
		List<Map<String, Object>> list = dao.getCostAll();

		System.out.println("원가 조회중...");   
		for (int i=0; i<10; i++) {
			System.out.print("■");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.print("            요리 별 원가 조회: \n");
		System.out.println("─────────────────────────────");
		System.out.println("      요리                         원가");
		System.out.println("─────────────────────────────");
		for(Map<String, Object> n : list) {
			System.out.printf("%8s   \t    %-8s  \n",n.get("F_NAME"),n.get("TOTAL_COST"));
			System.out.println("────────────────────────────");}

		while (true) {
			System.out.println("0. 돌아가기");
			System.out.println("1. 종료하기");
			System.out.print("원하시는 기능을 숫자로 입력 해주세요 : ");
			int input = ScanUtil.nextInt();
			if (input == 0) {
				tv.managerSelect();
			} else if (input == 1) {
				System.out.println("관리 시스템을 종료합니다.");
				System.exit(0);
			} else {
				System.out.println("잘못된 값을 입력 하였습니다.");
				continue;
			}
		}
	}

	// 음식 1개당 예상되는 순 수익 계산
	public void printNetProfitAll() {
		TotalView tv = TotalView.getInstance();
		List<Map<String, Object>> list = dao.getNetProfitAll();

		System.out.println("순수익 조회중...");   
		for (int i=0; i<10; i++) {
			System.out.print("■");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.print("          요리 별 순수익 조회: \n");
		System.out.println("─────────────────────────────");
		System.out.println("   요리                           순수익");
		System.out.println("─────────────────────────────");
		for(Map<String, Object> n : list) {
			System.out.printf("%8s   \t   %-8s  \n",n.get("F_NAME"),n.get("NET_PROFIT"));
			System.out.println("─────────────────────────────");}

		while (true) {
			System.out.println("0. 돌아가기");
			System.out.println("1. 종료하기");
			System.out.print("원하시는 기능을 숫자로 입력 해주세요 : ");
			int input = ScanUtil.nextInt();
			if (input == 0) {
				tv.managerSelect();
			} else if (input == 1) {
				System.out.println("관리 시스템을 종료합니다.");
				System.exit(0);
			} else {
				System.out.println("잘못된 값을 입력 하였습니다.");
				continue;
			}
		}
	}


	public void serchIngredient() {
		TotalView tv = TotalView.getInstance();
		tv.managerSelect();

	}

	// 음식 주문 시 식재료 재고 감소
	public void Cooking() {
		List<Map<String, Object>> a=dao.serchIngredient();
		if(a!=null) {
			System.out.println();
			System.out.println("식재료가 부족합니다.");
			System.out.println();
			System.out.println("관리메뉴로 돌아갑니다.");
			System.out.println();
			System.out.println("발주메뉴로 이동중...");   
			for (int i=0; i<10; i++) {
				System.out.print("■");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			serchIngredient();
		} else {

			List<Object> param = new ArrayList<Object>();
			TotalView tv = TotalView.getInstance();
			System.out.println("요리를 시작합니다. ~~♨~~");

			System.out.print("음식명 입력 : ");
			String fName = sc.nextLine();
			param.add(fName);
			param.add(fName);


			System.out.println();
			System.out.println("몇번 반복하시겠습니까? :");
			int qty = sc.nextInt();

			try {
				for(int i = 1 ; i<qty; i++) {
					dao.Cooking(param);
				}
			}catch(Exception e) {
				System.out.println("오류가 생겼습니다. 다시 입력해주세요.");
			}

			dao.Cooking(param);

			System.out.println();
			System.out.print("요리중...");   
			for (int i=0; i<10; i++) {
				System.out.print("♨");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
			System.out.println("\n¸¸♬·¯·♩¸¸♪·¯·♫¸¸¸♬·¯·♩¸¸♪·¯·♫¸¸");
			System.out.println("\n        요리 완료!!!");
			System.out.println("\n¸¸♬·¯·♩¸¸♪·¯·♫¸¸¸♬·¯·♩¸¸♪·¯·♫¸¸");
			System.out.println();
			System.out.print("식재료 재고가 감소되었습니다: ");
			System.out.println();



			tv.managerSelect();    
		}
	}
}
