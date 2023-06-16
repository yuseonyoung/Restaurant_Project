package JDBCUtil;

public class UndoUtil {
	TotalView tv = TotalView.getInstance();
	
	public void undo() {
		while (true) {
            System.out.println("0. 처음으로 돌아가기");
            System.out.println("1. 작업 계속하기");
            System.out.println("2. 종료하기");
            System.out.print("원하시는 기능을 숫자로 입력 해주세요 : ");
            int input = ScanUtil.nextInt();
            if (input == 0) {
               tv.managerSelect();
            }else if (input == 1){
            	break;
            }else if (input == 2){
               System.out.println("관리 시스템을 종료합니다.");
               System.exit(0);
            }else{
               System.out.println("잘못된 값을 입력 하였습니다.");
               continue;
            }
         }
	}
}
