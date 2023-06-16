package JDBCUtil;

import java.util.Map;
import java.util.Scanner;

import DTO.SeatDTO;
import Service.CookService;
import Service.EmployeeService;
import Service.FoodOrderService;
import Service.LoginService;
import Service.OrderService;
import Service.SalesService;

public class TotalView {
	Scanner sc = new Scanner(System.in);
	Map<String, Object> result;
	OrderService os = OrderService.getInstance();
	FoodOrderService fos = FoodOrderService.getInstance();
	

	private static TotalView instance = null;

	private TotalView() {}

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
			System.out.println("│            레스토랑 관리 시스템 입니다                        	     │");
			System.out.println("│                                            │");
			System.out.println("│          원하시는 모드를 숫자로 선택해 주세요.   	     │");
			System.out.println("│                                            │");
			System.out.println("│                                            │");
			System.out.println("│   1.손님                                             2. 직원   	     │");
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
			System.out.println("•────•°•     안녕하세요 고객님               •°•────•");
			System.out.println("•────•°• 味 味 레스토랑에 오신걸 환영합니다 •°•────•");
			System.out.println();
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿");
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			System.out.println();
			System.out.println();
			System.out.println("원하시는 메뉴를 입력해주세요.");
			System.out.println();
			while (true) {
				try {
					fos.seatSelect();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("잘못된 값을 입력했습니다. 다시 입력해 주세요.");
					continue;
				}
				orderView();
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
					System.out.println("☹ ID와 Pw가 잘못되었습니다 다시 입력 해주세요.");
				}

			}
			break;
		default:
			System.out.println("☹ 잘못된 값을 입력 하였습니다.");
		}
	}

	public void managerSelect() {
		int num = 0;
		while (true) {
			System.out.println();
			System.out.println("╔═════════════╗");
			System.out.println("│    관 리 기 능        │");
			System.out.println("╚═════════════╝");
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
				System.out.println("  4. 직원 관리                                                           ");
				System.out.println("╚════════════╝");
			}

			try {
				System.out.println();
				System.out.print("✎______원하시는 관리기능을 숫자로 입력해 주세요 : ");
				num = Integer.valueOf(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("☹ 잘못된 값이 입력되었습니다.");
				continue;
			}
		}
		switch (num) {
		case 1:
			while (true) {
				System.out.println("╔════════════╗");
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
					System.out.println("╚════════════╝");
				}
				try {
					OrderService order = OrderService.getInstance();
					order.selectOrder();
					System.out.println();
					System.out.print("✎______원하시는 기능을 숫자로 입력해 주세요 : ");
					System.out.println();
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
						System.out.println("☹ 잘못된 값이 입력되었습니다.");
						break;
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("☹ 잘못된 값이 입력되었습니다.");
					continue;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case 2:
			while (true) {
				System.out.println("╔═════════════════════╗");
				System.out.println("│        요 리 관 리 	      │");   
				System.out.println("╚═════════════════════╝");
				System.out.println("  0. 돌아가기                                                            ");
				System.out.println("                                     ");
				System.out.println("  1. 요리레시피 조회  및 요리                                                    ");
				System.out.println("                                     ");
				System.out.println("  2. 요리별 원가 조회                                                        ");
				System.out.println("                                     ");
				System.out.println("  3. 요리별 순수익 조회                                                       ");
				System.out.println("╚═════════════════════╝");
				
				try {
					CookService cs = CookService.getInstance();
					//order.selectOrder();
					System.out.println();
					System.out.print("✎______원하시는 음식 관리 기능을 숫자로 입력해 주세요 : ");
					int value = Integer.valueOf(sc.nextLine());
					switch (value) {
					case 1:
						cs.getRecipe();
						break;
					case 2:
						cs.printCostAll();
						break;
					case 3:
						cs.printNetProfitAll();
						break;
					case 0:
						managerSelect();
						break;
					default:
						System.out.println("☹ 잘못된 값이 입력되었습니다.");
						break;
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("☹ 잘못된 값이 입력되었습니다.");
					continue;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
		case 3:
			while (true) {
			if (result.get("E_JOB").equals("점장") || result.get("E_JOB").equals("매니저")) {
				System.out.println("╔══════════════════╗");
				System.out.println("│    	매 출 관 리	   │");   
				System.out.println("╚══════════════════╝");
				System.out.println("  0. 돌아가기                                                            ");
				System.out.println("                                     ");
				System.out.println("  1. 일별 매출,지출조회                                                     ");
				System.out.println("                                     ");
				System.out.println("  2. 주간별 매출,지출조회                                                     ");
				System.out.println("                                     ");
				System.out.println("  3. 순이익 조회                                                    ");
				System.out.println("╚══════════════════╝");
			}else {
				System.out.println("접근 권한이 없습니다. 자동으로 돌아갑니다.");
			}
			try {
				SalesService ss = SalesService.getInstance();
				//order.selectOrder();
				System.out.println();
				System.out.print("✎______원하시는 매출 관리 기능을 숫자로 입력해 주세요 : ");
				System.out.println();
				int value = Integer.valueOf(sc.nextLine());
				switch (value) {
				case 1:
					ss.oneDay();
					break;
				case 2:
					ss.oneWeek();
					break;
				case 3:
					ss.netProfit();
					break;
				case 0:
					managerSelect();
					break;
				default:
					System.out.println("☹ 잘못된 값이 입력되었습니다.");
					break;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("☹ 잘못된 값이 입력되었습니다.");
				continue;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			break;
			
		case 4:
			while (true) {
				System.out.println("╔══════════════╗");
				System.out.println("│    직 원 관 리          │");   
				System.out.println("╚══════════════╝");
				System.out.println("  0. 돌아가기                                                            ");
				System.out.println("                                     ");
				System.out.println("  1. 직원 정보 조회                                                      ");
				System.out.println("                                     ");

				if (result.get("E_JOB").equals("점장") || result.get("E_JOB").equals("매니저")) {
					System.out.println("  2. 직원 정보 생성                                                        ");
					System.out.println("                                     ");
					System.out.println("  3. 직원 정보 변경                                                        ");
					System.out.println("                                     ");
					System.out.println("  4. 직원 정보 삭제                                                        ");
					System.out.println("                                     ");
					System.out.println("  5. 인센티브 관리                                                       ");
					System.out.println("                                     ");
					System.out.println("  6. 직원 평가 관리                                                      ");
					System.out.println("╚══════════════╝");
				}
				try {
					EmployeeService es = EmployeeService.getInstance();
					//order.selectOrder();
					System.out.println();
					System.out.print("✎______원하시는 직원 관리 기능을 숫자로 입력해 주세요 : ");
					System.out.println();
					int value = Integer.valueOf(sc.nextLine());
					switch (value) {
					case 1:
						es.EmpInfo();
						break;
					case 2:
						es.getAddEmp();
						break;
					case 3:
						es.getUpDateEmp();
						break;
					case 4:
						es.getKillEmp();
						break;
					case 5:
						es.empIncen();
						break;
					case 6:
						es.searchEmp();
						break;	
					case 0:
						managerSelect();
						break;
					default:
						System.out.println("☹ 잘못된 값이 입력되었습니다.");
						break;
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("☹ 잘못된 값이 입력되었습니다.");
					continue;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case 5 :
			init();
			break;
			
		default:
			System.out.println("☹ 잘못 입력하셨습니다.");
			System.out.print("✎______다시 입력해주세요.");
			break;
		}
		
	}
	
	public void orderView() {
		boolean flag = false;
		while (true) {
			System.out.println("╔═════════════╗");
			System.out.println("  1.	주문하기");
			System.out.println("  2.	주문내역");
			System.out.println("  3.	결제하기");
			System.out.println("  4.	나가기");
			System.out.println("╚═════════════╝");	
			System.out.println();
			System.out.print("✎______선택 : ");
		
			Scanner sc = new Scanner(System.in);
			int menu = sc.nextInt();
			System.out.println();

			switch (menu) {
			case 1:
				fos.FoodList();
				System.out.println();
				fos.FoodOrder();
				System.out.println();
				fos.OrderMain();
				break;

			case 2:
				// 주문내역
				fos.bill();
				continue;
	
			case 3:
				fos.bill();
				fos.payment();
				break;
			case 4:
				// 나가기
				System.out.println("•────•°• 안녕히가세요 고객님  •°•────•");
				System.out.println("•────•°•  味 味 레스토랑       •°•────•");
				flag = true; // 이용상태(중지)
				break;
			default:
				// 그외의 메뉴
				System.err.println("☹ 번호를 정확하게 입력해주세요!");
				break;
			}

			System.out.println();
			System.out.println();

			if (flag) {
				System.exit(0);
			}
		}
	}
}
