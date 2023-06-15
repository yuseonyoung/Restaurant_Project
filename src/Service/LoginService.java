package Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import DAO.LoginDAO;
import DTO.EmployeeDTO;
import JDBCUtil.ScanUtil;
import JDBCUtil.TotalView;
//import dao.LoginDAO;

//�ܺηκ��� �޾Ƶ��ϼ��ִ°��� �Է¹޴� Ŭ����
public class LoginService {
	EmployeeDTO ed = new EmployeeDTO();

	Scanner sc = new Scanner(System.in);
	// �̱���
	private static LoginService instance;

	private LoginService() {
	}

	public static LoginService getInstance() {
		if (instance == null) {
			instance = new LoginService();
		}
		return instance;
	}

//----------------------------------------------------������� �̱��� ����

	private Map<String, String> sessionStorage = new HashMap<>();

	public Map<String, String> getSessionStorage() {
		return sessionStorage;
	}

	public void setSessionStorage(Map<String, String> sessionStorage) {
		this.sessionStorage = sessionStorage;
	}

	public static int loginCount = 0;
	LoginDAO dao = LoginDAO.getInstance();
	Map<String, Object> result;

	// �α��� �޼���
	public Map<String, Object> login() {
		System.out.println("������  �α��� ������");

		Loop1: while (true) {

			System.out.print("ID �Է� : ");
			ed.setE_id(sc.nextLine());

			System.out.print("PW �Է� : ");
			ed.setE_pw(sc.nextLine());

			sessionStorage.put(ed.getE_id(), ed.getE_pw());

			while (true) {
				System.out.println("�α��� �Ͻ÷��� Y, ���ư��÷��� N�� �����ּ���");
				String value = ScanUtil.nextLine();
				loginCount++;

				EmployeeDTO eld = new EmployeeDTO();
				try {
					if (value.equalsIgnoreCase("Y")) {

						if (loginCount == 3) {
							System.out.println("3ȸ�̻� ������ �߸� �Է��Ͽ����ϴ�.");
							System.out.println();
							System.out.println("10�ʵ��� �α����� ���� �˴ϴ�.");
							LoginRetry();
						}
						result = dao.login(ed.getE_id(), ed.getE_pw());
						String job = (String) result.get("E_JOB");
						eld.setE_job(job);
						if (result == null) {
							System.out.println("ID�� PW�� Ȯ�����ּ���.");
						} else {
							System.out.printf("%3s �������� �����Ͽ����ϴ�.", eld.getE_job());
							break Loop1;
						}

					} else if (value.equalsIgnoreCase("N")) {
						TotalView lv = TotalView.getInstance();
						lv.init();
					} else {
						System.out.println("�߸��Ȱ��� �Է��Ͽ����ϴ�. �ٽ� �Է����ּ���.");
						continue;
					}

				} catch (NumberFormatException e) {
					System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
					continue Loop1;
				} catch (Exception e) {
					System.out.println("ID�� PW�� Ȯ�����ּ���. ");
					continue Loop1;
				}

				System.out.println();
				continue Loop1;
			}
		}
		return result;
	}

	private void LoginRetry() {
		for (int i = 0; i < 10; i++) {
			System.out.print("��");
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			loginCount = 0;
		}
		System.out.println();
	}

//-----------------------------------------------------------------������� �α��� �޼ҵ�
	public Map<String, Object> isDuplicate(String id) {
		result = dao.select(id);
		return result;
	}
//-----------------------------------------------------------------�ߺ��� id �˻� �޼ҵ�	
}
