package JDBCUtil;

import java.util.Scanner;

public class ScanUtil {
	// ��ĳ�ʸ� �ս��� ����� �� �ִ� static �޼��带 ����������
	static Scanner sc = new Scanner(System.in);
	public static String nextLine() {
		return sc.nextLine();
	}
	public static int nextInt() {
		while(true) {
			try {
				int result = Integer.valueOf(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("�߸��� �Է°��Դϴ�.");
			}
		}
	}
}
