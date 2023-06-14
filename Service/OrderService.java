package Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.OrderDAO;
import DTO.IngredientDTO;
import DTO.PurchaseDTO;
import JDBCUtil.ScanUtil;
import JDBCUtil.TotalView;

public class OrderService {

	Scanner sc = new Scanner(System.in);
	IngredientDTO od = new IngredientDTO();
	PurchaseDTO pc = new PurchaseDTO();

	private static OrderService instance = null;

	private OrderService() {
	}

	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}
//-------------------------------------------------------�̱��� ����

	OrderDAO dao = OrderDAO.getInstance();

	public void selectOrder() throws Exception {
		int count = 1;
		List<Map<String, Object>> list = dao.selectList();

		if (list != null) {
			System.out.println("������������������������������������������������������������������������������");
			System.out.println("               ��� �� ���� ���� ����� ��� ");
			System.out.println();
			System.out.println("  ����     ������ڵ�          ������       ���        ������");
			for (Map<String, Object> n : list) {
				System.out.printf("%3d  %-11s %3s      %5d��        %3s \n", (count++), n.get("I_ID"), n.get("I_NAME"),
						Integer.parseInt((String.valueOf(n.get("I_INVEN"))).trim()), n.get("I_ORIGIN"));
				System.out.println("������������������������������������������������������������������������������");
			}
		} else {
			System.out.println("����ᰡ ����� �����Դϴ�.");
		}
	}

//------------------------------------------------------�������� ���� ����� ��� �����ֱ�	
	public void ingredientOrder() {
		TotalView tv = TotalView.getInstance();
		Loop1: while (true) {
			System.out.print("�����Ͻ� �������� �Է� �ϼ��� : ");
			String name = sc.nextLine();
			od.setI_name(name);

			System.out.print("�����Ͻ� ������ �Է� �ϼ��� : ");
			int num = ScanUtil.nextInt();
			od.setI_inven(num);

			dao.orderInsert(od.getI_name(), od.getI_inven());

			System.out.println("���ְ� �Ϸ� �Ǿ����ϴ�.");
			System.out.println();
			while (true) {
				System.out.println("0. ���ư���");
				System.out.println("1. ����� �߰� ����");
				System.out.println("2. �����ϱ�");
				System.out.print("���Ͻô� ����� ���ڷ� �Է� ���ּ��� : ");
				int input = ScanUtil.nextInt();
				if (input == 0) {
					tv.managerSelect();
				} else if (input == 1) {
					continue Loop1;
				} else if (input == 2) {
					System.out.println("���� �ý����� �����մϴ�.");
					System.exit(0);
				} else {
					System.out.println("�߸��� ���� �Է� �Ͽ����ϴ�.");
					continue;
				}
			}
		}
	}

	public void OrderList() {
		int count = 1;
		TotalView tv = TotalView.getInstance();
		List<Map<String, Object>> list = dao.orderAllList();

		System.out.println("������������������������������������������������������������������������������������������������������������������");
		System.out.println("                                           ����� ��� ");
		System.out.println();
		System.out.println("  ����     ������ڵ�          ������       ���        ������        �������        ����");
		for (Map<String, Object> n : list) {
			System.out.printf("%3d  %-11s %3s      %5d��        %3s      %3s      %3s        \n", (count++),
					n.get("I_ID"), n.get("I_NAME"), Integer.parseInt((String.valueOf(n.get("I_INVEN"))).trim()),
					n.get("I_ORIGIN"), n.get("P_EXPDATE"), n.get("I_STOR"));
			System.out.println("��������������������������������������������������������������������������������������������������������������������");
		}
		
		System.out.println();
		while(true) {
			System.out.println("0. ���ư���");
			System.out.println("1. �����ϱ�");
			System.out.print("���Ͻô� ����� ���ڷ� �Է����ּ��� : ");
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
			System.out.println("������������������������������������������������������������������������������������������������������������������");
			System.out.println("��������� ���� ����� ���");
			System.out.println("  ����     ���ּ��ڵ�          ������     ��������       �������     ���");
			for (Map<String, Object> n : list) {
				System.out.printf("%6d  %3s       %-11s   %10s      %10s    %10s  \n", (count++), n.get("P_ID"),
						n.get("I_NAME"), n.get("P_BDATE"), n.get("P_EXPDATE"), n.get("P_QTY"));
			}

			while (true) {
				
				System.out.println("0. ���ư���");
				System.out.println("1. ��������� ���� ����Ḧ �����Ͽ� ���");
				System.out.println("2. ��������� ���� ����Ḧ ��ü ���");

				System.out.print("���� �Է� : ");
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
					System.out.println("�߸��� ���� �Է� �Ͽ����ϴ�. �ٽ� �Է��Ͽ��ּ���.");
					continue;
				}
			}
		} else {
			System.out.println("��������� ���� ����ᰡ �����ϴ�.");
			System.out.println("0. ���ư���");
			System.out.println("1. �����ϱ�");
			
			while(true) {
				System.out.print("���� �Է� : ");
				int num = ScanUtil.nextInt();
	
				if(num==0) {
					tv.managerSelect();
					break;
				}else if(num==1) {
					System.exit(0);
				}else {
					System.out.println("�߸��� ���� �Է��Ͽ����ϴ�.");
					System.out.println("�ٽ� �Է����ּ���");
					continue;
				}
			}
		}
	}

	public void selectDelete() throws Exception {

		while (true) {
			if (dao.deleteList() != null) {
				System.out.print("�����Ͻ� ���ּ��ڵ带 �Է��ϼ��� : ");
				pc.setP_id(ScanUtil.nextInt());
				System.out.print("�����Ͻ� �������� �Է��ϼ��� : ");
				od.setI_name(ScanUtil.nextLine());

				dao.orderDelete(pc.getP_id(), od.getI_name());

				System.out.println("���� �Ǿ����ϴ� !");
				System.out.println();
				System.out.println("1. ���ư���");
				System.out.println("2. ����� �߰� ����");
				System.out.println("3. �ý�������");
				System.out.println();
				System.out.print("���Ͻô� ����� ���ڷ� �Է��Ͽ��ּ��� : ");
				int value = ScanUtil.nextInt();

				if (value == 1) {
					OrderDelete();
					break;
					
				} else if (value == 2) {
					continue;
				} else if (value == 3) {
					System.exit(0);
				} else {
					System.out.println("�߸��� ���� �Է� �Ǿ����ϴ�.");
					continue;
				}
			} else {
				System.out.println("��������� ���� ����ᰡ �����ϴ�.");
			}
		}
	}

	public void DeleteAll() throws Exception {
		dao.deleteAllValue();
		System.out.println("��� �����Ͱ� �����Ǿ����ϴ�.");
		OrderDelete();
	}
}
