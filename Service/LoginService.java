package Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import DAO.LoginDAO;
import DTO.EmployeeDTO;
import DTO.IngredientDTO;
import JDBCUtil.TotalView;
//import dao.LoginDAO;

//�ܺηκ��� �޾Ƶ��ϼ��ִ°��� �Է¹޴� Ŭ����
public class LoginService {
	Scanner sc = new Scanner(System.in);
	// �̱���
	private static LoginService instance;

	public LoginService() {
	}

	public static LoginService getInstance() {
		if (instance == null) {
			instance = new LoginService();
		}
		return instance;
	}
//----------------------------------------------------------------������� �̱��� ����
	IngredientDTO idd = new IngredientDTO();
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
	public Map<String, Object> login()   {
		System.out.println("������  �α��� ������");

		Loop1: while (true) {

			System.out.print("ID �Է� : ");
			
			idd.setI_id(sc.nextLine());
			System.out.print("PW �Է� : ");
			idd.setI_pw(sc.nextLine());
			
			sessionStorage.put(idd.getI_id(), idd.getI_pw());
			
			
			while (true) {
				System.out.println("�α��� �Ͻ÷��� Y, ���ư��÷��� N�� �����ּ���");
				String value = sc.nextLine();
				loginCount++;

				EmployeeDTO eld = new EmployeeDTO();
				try {
					if (value.equalsIgnoreCase("Y")) {
												
						result = dao.login(idd.getI_id(), idd.getI_pw());
						
						String job=(String)result.get("E_JOB");
						eld.setE_rank(job);
						
					} else if (value.equalsIgnoreCase("N")) {
						TotalView lv = TotalView.getInstance();
						try {
							lv.init();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("�߸��Ȱ��� �Է��Ͽ����ϴ�. �ٽ� �Է����ּ���.");
						continue;
					}
					
					if (result == null) {
						System.out.println("ID�� PW�� Ȯ�����ּ���.");
						
					} else {
						System.out.printf("%s �������� �����Ͽ����ϴ�. \n", eld.getE_rank());
						break Loop1;
					}
					
					if (loginCount == 3) {
						System.out.println("3ȸ�̻� ������ �߸� �Է��Ͽ����ϴ�.");
						System.out.println("10�ʵ��� �α����� ���� �˴ϴ�.");
						for (int i = 0; i < 10; i++) {
							System.out.print("��");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						loginCount = 0;
					}
				} catch (NumberFormatException e) {
					System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
					continue Loop1;
				}

				System.out.println();
				continue Loop1;
			}
		}
		return result;
	}

//-----------------------------------------------------------------������� �α��� �޼ҵ�
	public Map<String, Object> isDuplicate(String id)   {
		result = dao.select(id);
		return result;
	}
//-----------------------------------------------------------------�ߺ��� id �˻� �޼ҵ�	
}
