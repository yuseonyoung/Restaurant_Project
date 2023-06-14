package JDBCUtil;

import java.util.Map;
import java.util.Scanner;

import DTO.EmployeeDTO;
import Service.LoginService;
import Service.OrderService;

public class TotalView {
	Scanner sc = new Scanner(System.in);
	Map<String, Object> result;
	LoginService login = LoginService.getInstance();
	OrderService order = OrderService.getInstance();
	
	private static TotalView instance = null;
	private TotalView() {}
	
	public static TotalView getInstance() {
		if(instance == null) {
			instance = new TotalView();
		}
		return instance;
	}
	
	public void init() throws ClassNotFoundException {
		int num = 0;
		while (true) {
			System.out.println("��������������������������������������������������������������������������������������������");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��            ������� ���� �ý��� �Դϴ�                       ��");
			System.out.println("��                                            ��");
			System.out.println("��          ���Ͻô� ��带 ���ڷ� ������ �ּ���.   	 ��");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��   1.�մ�                                             2. ����        ��");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��������������������������������������������������������������������������������������������");

			try {
				num = Integer.valueOf(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
				continue;
			}

		}
		switch (num) {
		case 1:
		case 2:
			System.out.println("��������������������������������������������������������������������������������������������");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��       ������ ���̵�� �н����带 �Է����ּ���        	 ��");
			System.out.println("��                                            ��");
			System.out.println("��       ID :                         	     ��");
			System.out.println("��                                            ��");
			System.out.println("��       PW :                                 ��");
			System.out.println("��    	                                     ��");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��������������������������������������������������������������������������������������������");
			
		
			result = login.login();
			managerSelect();
			
			break;
	
		default:
			System.out.println("�߸��� ���� �Է� �Ͽ����ϴ�.");
		}
		
	}

	public void managerSelect() {
		int num = 0;
		while (true) {
			System.out.println("                                     ");
			System.out.println("  1. ����� ����                                          ");
			System.out.println("                                     ");
			System.out.println("  2. ���� ����                                             ");
			System.out.println("                                     ");
			
	
			if(result.get("E_JOB").equals("�Ŵ���")) {
				System.out.println("  3. ���� ����                                             ");
				System.out.println("                                     ");
			}
				
			else if(result.get("E_JOB").equals("����")) {
				System.out.println("  3. ���� ����                                              ");
				System.out.println("                                     ");
				System.out.println("  4. ���� ����                                          \n");
			}
			
			try {
				System.out.println("���Ͻô� ��������� ���ڷ� �Է��� �ּ��� : ");
				num = Integer.valueOf(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
				continue;
			}
		}
		switch (num) {
		case 1:
			
			while (true) {
				System.out.println("                                     ");
				System.out.println("  0. ���ư���                                              ");
				System.out.println("                                     ");
				System.out.println("  1. ����� ��ȸ                                           ");
				System.out.println("                                     ");
				
				
				if(result.get("E_JOB").equals("����") || result.get("E_JOB").equals("�Ŵ���")) {
					System.out.println("  2. ����� ����                                           ");
					System.out.println("                                     ");
					System.out.println("  3. ����� ���                                           ");
					System.out.println("                                     ");
				}
				
				order.selectOrder();
				System.out.println();
				try {
					System.out.print("���Ͻô� ����� ���ڷ� �Է��� �ּ��� : ");
					int value = Integer.valueOf(sc.nextLine());
					switch (value) {
					case 1:
						order.OrderList();
						break;
					case 2:
						order.ingredientOrder();
						System.out.println("���ְ� �Ϸ�Ǿ����ϴ�.");
						break;
					case 3:
						order.OrderDelete();
						break;
					case 0:
						managerSelect();
					default:
						System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
						break;
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
					continue;
				}
			}
			break;
		case 2:
			System.out.println("������������������������������������������������������������������������������");
			System.out.println("��                                     ��");
			System.out.println("��  1. ���� ��ȸ   ��� ����                               ��");
			System.out.println("��                                     ��");
			System.out.println("������������������������������������������������������������������������������");
			break;
		case 3:
			System.out.println("������������������������������������������������������������������������������");
			System.out.println("��                                     ��");
			System.out.println("��  1. ���� ��ȸ  ��� ����                                ��");
			System.out.println("��                                     ��");
			System.out.println("������������������������������������������������������������������������������");
			break;
		default:
			System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			break;
		}

	}


}
