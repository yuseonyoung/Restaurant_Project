package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import JDBCUtil.JdbcUtil;

public class EmployeeDAO {
		// 싱글톤
		private static EmployeeDAO instance = null;

		private EmployeeDAO() {}

		public static EmployeeDAO getInstance() {
			if (instance == null) {
				instance = new EmployeeDAO();
			}
			return instance;
		}
		Scanner sc = new Scanner(System.in);
		JdbcUtil jdbc = JdbcUtil.getInstance();		
		 
	    // 직원의 고용관리는 직급이 점주에 해당하는 직원만 사용가능, 직원 추가 기능 구현 : insert 
		public int getAddEmp(List<Object> param, int esal) {
			String sql = " INSERT INTO EMP ( E_ID, E_PW, E_NAME, E_JOB, E_HDATE, E_SAL, E_TEL, E_EVAL ) "
					   + " VALUES ( ?, ?, ?, ?, TRUNC(SYSDATE), "+esal+", ?, 5 ) ";
	            
	        return jdbc.cud(sql, param);
	     	       
	    }
		// 직원 수정은 직원의 수정해야 할 정보를 수정 가능, 직급이 점주에 해당하는 직원은 각 직원에게 평가점수를 부여  : update
		//public int getUpDateEmp(List<Object> param, int esal, int eeval) {
		public int getUpDateEmp(List<Object> param, int esal, int eeval) {
	      	String sql = " UPDATE EMP SET E_JOB = ? , E_SAL = (E_SAL + "+esal+") , E_EVAL = ( E_EVAL + "+eeval+") , E_STAT = ? WHERE E_ID = ? ";
	        
	        return jdbc.cud(sql, param);
	     	       
	    }
		// 직원 삭제 : delete
		public int getKillEmp(List<Object> param) {
			String sql = " DELETE FROM EMP WHERE E_NAME = ? ";
			
			return jdbc.cud(sql, param);
	     	       
	    }
	    
		  
	    //// 직원의 평가점수가 10의 배수로 쌓일 경우엔 성과급(월급 * 0.2)을 지급 
	      public List<Map<String, Object>> giveIncen (List<Object> param){
	         
	         String sql = ""
	                +" SELECT                           "
	                +"    E_NAME AS NAME                  "
	                +"    ,E_EVAL AS EVAL                 "
	                +"    ,ROUND((E_SAL*0.2),-5) AS INCEN "
	                +" FROM EMP                         "
	                +" WHERE E_EVAL = ?                 ";
	        return (List<Map<String, Object>>) jdbc.selectList(sql,param);
	      
	      }
	      
	      //직원정보조회
	      public List<Map<String, Object>> searchEmp(){
	      
	         String sql = ""
	        		 + "   SELECT                                                 "
	        		 + "		 E_NAME AS NAME                                      "
	        		 + "		 ,NVL(E_EVAL,' ') AS EVAL                            "
	        		 + "		 ,E_ID AS ID                                         "
	        		 + "		 ,E_JOB AS JOB                                       "
	        		 + "		 ,NVL(E_SAL,'0') AS SAL                              "
	        		 + "		 ,NVL(TO_CHAR(E_HDATE,'YYYY-MM-DD'),'0000-00-00') AS HDATE    "
	        		 + "		 ,E_TEL AS TEL                                       "
	        		 + "		 ,NVL(E_STAT,' ') AS STAT                            "
	        		 + "	 FROM EMP                                                ";
	         return (List<Map<String, Object>>) jdbc.selectList(sql, null);
	      
	}
	    //직원상태창 퇴사로 변경
	      public int layOffEmp(List<Object> param) {
	         String sql = "UPDATE EMP SET E_STAT = '퇴사' WHERE E_NAME = ?";
	         return jdbc.cud(sql, param);
	}

		

}
