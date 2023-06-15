
package Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.FoodOrderDAO;
import DTO.FoodOrderDTO;
import DTO.SeatDTO;
import JDBCUtil.ScanUtil;
import JDBCUtil.TotalView;

public class FoodOrderService {

	SeatDTO st = new SeatDTO();
	FoodOrderDTO fod = new FoodOrderDTO();

	Scanner sc = new Scanner(System.in);

	private static FoodOrderService instance;

	private FoodOrderService() {
	}

	public static FoodOrderService getInstance() {
		if (instance == null) {
			instance = new FoodOrderService();
		}
		return instance;
	}
//-------------------------------------------------------------------------

	FoodOrderDAO dao = FoodOrderDAO.getInstance();

	public void FoodList() {

		List<Map<String, Object>> list = dao.foodAllList();

		System.out.println("─────────────────────────────────────────────────────────");
		System.out.println("               메뉴목록 ");
		System.out.println();
		System.out.println("  번호      메뉴명                가격    ");
		System.out.println();

		for (Map<String, Object> a : list) {
			int fNo = Integer.parseInt((String.valueOf(a.get("F_NO"))).trim()); // F_NO를 int로 형변환
			int fPrice = Integer.parseInt((String.valueOf(a.get("F_PRICE"))).trim()); // F_PRICE를 숫자형으로 형변환 후 int로 변환
			System.out.printf("%3d  %-11s \t %3d\n", fNo, a.get("F_NAME"), fPrice);
			System.out.println();
		}

		System.out.println();

	}

	public void FoodOrder() {
		TotalView tv = TotalView.getInstance();
		Loop1: while (true) {
			System.out.print("주문하실 음식번호를 입력 하세요 : ");
			int no = sc.nextInt();
			fod.setF_no(no);

			System.out.print("주문하실 수량을 입력 하세요 : ");
			int qty = sc.nextInt();
			fod.setOF_qty(qty);

			try {
				dao.orderInsert(fod.getF_no(), fod.getOF_qty());
			} catch (Exception e) {
				System.out.println("잘못된 값이 입력되었습니다. 다시 입력해 주세요.");
				continue;
			}

			System.out.println("주문이 완료 되었습니다.");

			System.out.println();
			while (true) {
				System.out.println("0. 돌아가기");
				System.out.println("1. 주문 취소");
				System.out.println("2. 종료하기");
				System.out.print("원하시는 기능을 숫자로 입력 해주세요 : ");
				int input = ScanUtil.nextInt();
				if (input == 0) {
					tv.managerSelect();
				} else if (input == 1) {
					continue Loop1;
				} else if (input == 2) {
					System.out.println("주문을 종료합니다.");
					System.exit(0);
				} else {
					System.out.println("잘못된 값을 입력 하였습니다.");
					continue;
				}
			}
		}
	}

	public void bill() {

		List<Map<String, Object>> list = dao.selectList();

		if (list != null) {
			System.out.println("───────────────────────────────────────");
			System.out.println("               영수증 ");
			System.out.println();
			System.out.println("  순번\t 주문번호 \t 요리명\t 수량 \t 가격");
			for (Map<String, Object> n : list) {
				System.out.printf("%d \t %8s \t %-7s  %10d개  %10s원 \n",
						Integer.parseInt(String.valueOf(n.get("RNUM")).trim()), n.get("O_ID"), n.get("F_NAME"),
						Integer.parseInt(String.valueOf(n.get("OF_QTY")).trim()), n.get("PRICE"));
				System.out.println("───────────────────────────────────────");
			}
		} else {
			System.out.println("아직 주문이 들어오지 않았습니다.");
		}
	}

	public void seatSelect() {
		Map<String, Object> map = new HashMap<>();

		List<Map<String, Object>> list = dao.selectSeat();
		int cnt = 0;
		for (Map<String, Object> m : list) {
			map.put((String) m.get("S_NO"),m.get("S_WHETHER"));
			
			if (cnt % 3 == 0)
				System.out.println();
			if (m.get("S_WHETHER").equals("N")) {
				System.out.print("□ ");
			} else {
				System.out.print("■ ");
			}
			cnt++;
		}
		System.out.print("\n-------------");
		cnt = 0;
		for (Map<String, Object> m : list) {
			if (cnt % 3 == 0)
				System.out.println();
			System.out.print(m.get("S_NO") + " ");

			cnt++;
		}
		while(true) {	
			System.out.println("\n\n좌석을 선택하여주세요 : ");
			st.setS_no(ScanUtil.nextLine());
			
			//map이 내가 입력한 값을 포함하면
			//맵에서 key값(좌석번호)를 가져와서 Y과 같으면
			if(map.containsKey(st.getS_no())) {
				if(map.get(st.getS_no()).equals("Y")) {
					System.out.println("이미 선택된 좌석입니다. 다시 입력해 주세요.");
					continue;
				}else {
					dao.updateSeat("Y",st.getS_no());
					
					System.out.println("좌석 선택을 완료했습니다.");
					break;
				}
			}else {
				System.out.println("잘못 입력하였습니다. 다시 입력해 주세요.");
				continue;
			}
		}
		
		
		
	}
}
