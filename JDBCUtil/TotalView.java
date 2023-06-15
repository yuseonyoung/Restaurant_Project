package JDBCUtil;

import java.util.Map;
import java.util.Scanner;

import DTO.SeatDTO;
import Service.FoodOrderService;
import Service.LoginService;
import Service.OrderService;

public class TotalView {
	Scanner sc = new Scanner(System.in);
	Map<String, Object> result;
	OrderService os = OrderService.getInstance();
	FoodOrderService fos = FoodOrderService.getInstance();
	

	private static TotalView instance = null;

	private TotalView() {
	}

	public static TotalView getInstance() {
		if (instance == null) {
			instance = new TotalView();
		}
		return instance;
	}

	public void init() {
		int num = 0;
		while (true) {
			System.out.println("┌────────────────────────────────────────────┐");
			System.out.println("│                                            │");
			System.out.println("│                                            │");
			System.out.println("│                                            │");
			System.out.println("│            레스토랑 관리 시스템 입니다                                 │");
			System.out.println("│                                            │");
			System.out.println("│          원하시는 모드를 숫자로 선택해 주세요.   	     │");
			System.out.println("│                                            │");
			System.out.println("│                                            │");
			System.out.println("│   1.손님                                             2. 직원                        │");
			System.out.println("│                                            │");
			System.out.println("│                                            │");
			System.out.println("└────────────────────────────────────────────┘");

			try {
				num = ScanUtil.nextInt();
				break;
			} catch (NumberFormatException e) {
				System.out.println("잘못된 값이 입력되었습니다.");
				continue;
			}

		}
		switch (num) {
		case 1:
			System.out.println("        <味 味 레스토랑에 오신걸 환영합니다.>  ");
			System.out.println("⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣠⣤⣶⣶");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⢰⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣀⣀⣾⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿");

			System.out.println();
			System.out.println();
			System.out.println("이랏샤이메세");
				
			fos.seatSelect();
			
			System.out.println("■■■대덕요리개발원■■■");
			while (true) {
				boolean flag = false;

				System.out.println("■1. 주문하기");
				System.out.println("■2. 주문내역");
				System.out.println("■3. 결제하기");
				System.out.println("■4. 나가기");
				System.out.println();

				System.out.print("::: 선택 : ");

				Scanner sc = new Scanner(System.in);
				int menu = sc.nextInt();
				System.out.println();

				switch (menu) {
				case 1:

					// dbConnection();
					// 주문하기
					// order();
					fos.FoodList();
					fos.FoodOrder();
					break;

				case 2:
					// 주문내역
					// cart();
					break;

				case 3:
					// count();
					break;
				case 4:
					// 나가기
					System.out.println("■이용해주셔서 감사합니다!");
					System.out.println("■■■■대덕요리개발원■■■■");
					flag = true; // 이용상태(중지)
					break;
				default:
					// 그외의 메뉴
					System.err.println("■번호를 정확하게 입력해주세요!");
					break;
				}

				System.out.println();
				System.out.println();

				if (flag) {
					System.exit(0);
				}
			}

		case 2:
			while (true) {
				System.out.println("┌────────────────────────────────────────────┐");
				System.out.println("│                                            │");
				System.out.println("│                                            │");
				System.out.println("│                                            │");
				System.out.println("│       직원의 아이디와 패스워드를 입력해주세요        	     │");
				System.out.println("│                                            │");
				System.out.println("│       ID :                         	     │");
				System.out.println("│                                            │");
				System.out.println("│       PW :                                 │");
				System.out.println("│    	                                     │");
				System.out.println("│                                            │");
				System.out.println("│                                            │");
				System.out.println("└────────────────────────────────────────────┘");

				LoginService login = LoginService.getInstance();
				result = login.login();
				if (result != null) {
					managerSelect();
					break;
				} else {
					System.out.println("ID와 Pw가 잘못되었습니다 다시 입력 해주세요.");
				}

			}
			break;
		default:
			System.out.println("잘못된 값을 입력 하였습니다.");
		}
	}

	public void managerSelect() {
		int num = 0;
		while (true) {
			System.out.println("                                     ");
			System.out.println("  1. 식재료 관리                                                        ");
			System.out.println("                                     ");
			System.out.println("  2. 음식 관리                                                           ");
			System.out.println("                                     ");

			if (result.get("E_JOB").equals("매니저")) {
				System.out.println("  3. 매출 관리                                                           ");
				System.out.println("                                     ");
			}

			else if (result.get("E_JOB").equals("점장")) {
				System.out.println("  3. 매출 관리                                                           ");
				System.out.println("                                     ");
				System.out.println("  4. 직원 관리                                                           \n");
			}

			try {
				System.out.println("원하시는 관리기능을 숫자로 입력해 주세요 : ");
				num = Integer.valueOf(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("잘못된 값이 입력되었습니다.");
				continue;
			}
		}
		switch (num) {
		case 1:
			while (true) {
				System.out.println("                                     ");
				System.out.println("  0. 돌아가기                                                            ");
				System.out.println("                                     ");
				System.out.println("  1. 식재료 조회                                                        ");
				System.out.println("                                     ");

				if (result.get("E_JOB").equals("점장") || result.get("E_JOB").equals("매니저")) {
					System.out.println("  2. 식재료 발주                                                        ");
					System.out.println("                                     ");
					System.out.println("  3. 식재료 폐기                                                        ");
					System.out.println("                                     ");
				}
				try {
					OrderService order = OrderService.getInstance();
					order.selectOrder();
					System.out.println();
					System.out.print("원하시는 기능을 숫자로 입력해 주세요 : ");
					int value = Integer.valueOf(sc.nextLine());
					switch (value) {
					case 1:
						os.OrderList();
						break;
					case 2:
						os.ingredientOrder();
						break;
					case 3:
						os.OrderDelete();
						break;
					case 0:
						managerSelect();
						break;
					default:
						System.out.println("잘못된 값이 입력되었습니다.");
						break;
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("잘못된 값이 입력되었습니다.");
					continue;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case 2:
			System.out.println("┌─────────────────────────────────────┐");
			System.out.println("│                                     │");
			System.out.println("│  1. 예약 조회   기능 구현                                           │");
			System.out.println("│                                     │");
			System.out.println("└─────────────────────────────────────┘");
			break;
		case 3:
			System.out.println("┌─────────────────────────────────────┐");
			System.out.println("│                                     │");
			System.out.println("│  1. 직원 조회  기능 구현                                            │");
			System.out.println("│                                     │");
			System.out.println("└─────────────────────────────────────┘");
			break;
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			break;
		}

	}

}
