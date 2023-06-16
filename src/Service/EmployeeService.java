package Service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.EmployeeDAO;
import JDBCUtil.ScanUtil;
import JDBCUtil.TotalView;
import JDBCUtil.UndoUtil;

public class EmployeeService extends UndoUtil{
	Scanner sc = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("#,###");
    // 싱글톤
    private static EmployeeService instance = null;
    private EmployeeService () {}
    
    public static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }
  
    EmployeeDAO dao = EmployeeDAO.getInstance();
    
    public void getAddEmp() {
    	while (true) {

	    List<Object> param = new ArrayList<Object>();
      
    	System.out.println("직원등록");
    	
		System.out.print("직원 계정 아이디를 생성하세요.: ");
		String eid = sc.nextLine();
		param.add(eid);		
		
		System.out.print("직원 계정 비밀번호를 생성하세요.: ");
		String epw = sc.nextLine();
		param.add(epw);		
		
		System.out.print("직원 이름을 입력하세요: ");
		String ename = sc.nextLine();
		param.add(ename);		
		
		System.out.print("직원의 업무를 입력하세요: ");
		String ejob = sc.nextLine();
		param.add(ejob);	
		
		System.out.print("직원의 월급을 입력하세요: ");
		int esal = Integer.parseInt(sc.nextLine());
		
		System.out.print("직원의 전화번호를 입력하세요: ");
		String etel = sc.nextLine();
		param.add(etel);
		
		System.out.println("직원 등록중...");	
		for (int i=0; i<10; i++) {
			System.out.print("■");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			dao.getAddEmp(param, esal);
	         }catch(Exception e) {
	            System.out.println("입력이 잘못 되었습니다. 다시 입력해주세요.");
	            continue;
	         }
		System.out.println();
		System.out.println("직원 계정이 생성되었습니다.");
		
    	dao.getAddEmp(param, esal);
    	
    	undo();
      }
   }
    	
    public void getKillEmp() {
    	while (true) {
    	List<Object> param = new ArrayList<Object>();
    	
    	System.out.println("삭제할 직원의 이름을 입력하세요.");
		String ename = sc.nextLine();
		param.add(ename);
		
		System.out.println("사직서 작성중...");	
		for (int i=0; i<10; i++) {
			System.out.print("■");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			dao.getKillEmp(param);
	         }catch(Exception e) {
	            System.out.println("삭제할 직원이 존재하지 않습니다. 다시 입력해주세요.");
	            continue;
	         }
		System.out.println();
		System.out.println("직원이 삭제되었습니다.");
		
        dao.getKillEmp(param);
        
    	undo();
      }
   }
        
    public void getUpDateEmp() {
    	while (true) {
    	List<Object> param = new ArrayList<Object>();
    	
        System.out.print("정보를 변경할 직원의 ID를 입력하세요: ");
	    String eid = sc.nextLine();
	    
	    
	    System.out.print("직원의 직책을 변경하세요: ");
	    String ejob = sc.nextLine();
	    param.add(ejob);
	    
	    System.out.print("직원의 급여 변동분을 입력하세요: ");
	    int esal = Integer.parseInt(sc.nextLine());
	    //param.add(esal);
	    
	    System.out.print("평가점수의 변동분을 입력해주세요: ");
	    int eeval = Integer.parseInt(sc.nextLine());
	    //param.add(eeval);
	    
	    System.out.print("직원의 상태를 입력해주세요: ");
	    String estat = sc.nextLine();
	    param.add(estat);
	    param.add(eid);
	    System.out.println("직원 정보 변경 중...");	
		for (int i=0; i<10; i++) {
			System.out.print("■");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			dao.getUpDateEmp(param, esal, eeval); 
	         }catch(Exception e) {
	            System.out.println("입력형식이 올바르지 않습니다. 다시 입력해주세요.");
	            continue;
	         }
		System.out.println();
		System.out.println("직원 정보가 변경 되었습니다.");
	    	    
    	dao.getUpDateEmp(param, esal, eeval);
    	
    	undo();
      }
    }
   
    //평가점수 10점 성과금 출력 
	public void empIncen() {
		while(true) {
			System.out.println();
			System.out.println("인센티브를 받을 직원을 조회합니다.");
			System.out.println();
			System.out.print("조회중  ");
			for (int i=0; i<3; i++) {
				System.out.print("¸.•*¨*•");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("🐇");
			
			List<Object> param = new ArrayList<Object>();
			param.add(10);
			List<Map<String, Object>> list = dao.giveIncen(param);
			
			System.out.println("─────────────────────────────────");
			System.out.println("직원이름\t평가점수\t성과금");
			System.out.println("─────────────────────────────────");
			for(Map<String,Object> n : list) {
			   System.out.println(n.get("NAME")+"\t"+n.get("EVAL")+"\t"+df.format(n.get("INCEN")));
			  
			}
			undo();
		}   
	}
      
    //평가점수가 (-)면  해고라는 글씨가 E_STAT에 생성
	public void searchEmp() {
		while(true) {
    		 
			List<Map<String, Object>> list = dao.searchEmp();
			
			System.out.println();
			System.out.println("\t직원 정보를 조회합니다.");
			System.out.println();
			
			System.out.print("조회중  ");
			for (int i=0; i<3; i++) {
				 System.out.print("¸.•*¨*•");
				 try {
					 Thread.sleep(500);
				 } catch (InterruptedException e) {
					 e.printStackTrace();
				 }
			}
			System.out.println("🐇");
			System.out.println();
			System.out.println("─────────────────────────────────");
			System.out.println("직원ID\t직원이름\t평가점수\t비고");
			System.out.println("─────────────────────────────────");
			
			for(Map<String,Object> n : list) {
				 System.out.println(n.get("ID")+"\t"+n.get("NAME")+"\t"
						 +n.get("EVAL")+"\t"+n.get("STAT"));
			}
			System.out.println("─────────────────────────────────");
			System.out.println("직원 평가가 낮은 직원을 해고 하시려면, ");
			System.out.println("\t해고하시려는 직원 이름을 입력하세요.");
			System.out.print("✎______입력  : ");
			String name = sc.nextLine();
			
			List<Object> param = new ArrayList<>();
			param.add(name);
			int result = dao.layOffEmp(param);
			
			if(result>0) {
				System.out.println();
				System.out.println("변경된 직원 정보를 조회 합니다.");
				List<Map<String, Object>> list2 = dao.searchEmp();
				
				System.out.println("─────────────────────────────────");
				System.out.println("직원ID\t직원이름\t평가점수\t비고");
				System.out.println("─────────────────────────────────");
				
				for(Map<String,Object> n : list2) {
					System.out.println(n.get("ID")+"\t"+n.get("NAME")+"\t"
							+n.get("EVAL")+"\t"+n.get("STAT"));
				}
			}else {
				System.out.println("퇴사 처리에 실패했습니다.");
			}
			undo();
    	}
	}

	//직원 정보 조회
	public void EmpInfo() {
		while(true) {
			List<Map<String, Object>> list = dao.searchEmp();
			
			System.out.println();
			System.out.println("\t직원 정보를 조회합니다.");
			System.out.println("─────────────────────────────────");
			System.out.println("직원ID	직원이름	직무	임금	고용일	전화번호	평가점수	상태");
			System.out.println("─────────────────────────────────");
			
			for(Map<String,Object> n : list) {
				System.out.println(n.get("ID")+"\t"+n.get("NAME")+"\t"+n.get("JOB")+"\t"+n.get("SAL")
				+"\t"+n.get("HDATE")+"\t"+n.get("TEL")+"\t"+n.get("EVAL")+"\t"+n.get("STAT"));
			}
			System.out.println();
			undo();
		}
	}
}