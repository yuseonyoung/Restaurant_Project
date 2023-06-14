package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import JDBCUtil.JdbcUtil;
import Service.LoginService;

public class OrderDAO {
	
	LoginService login = LoginService.getInstance();
	
	private static OrderDAO instance=null;
	
	private OrderDAO() {}
	
	public static OrderDAO getInstance() {
		if (instance == null) {
			instance = new OrderDAO();
		}
		return instance;
	}
//-----------------------------------------------------------------------------------¿©±â ±îÁö ½Ì±¼Åæ
	
	JdbcUtil jdbc = JdbcUtil.getInstance();
	
	public List<Map<String, Object>> selectList(){
		String sql = "  SELECT I_ID, I_NAME, I_INVEN,  I_ORIGIN " + 
				" FROM INGREDIENT WHERE I_INVEN <= 10 ";
		return (List<Map<String, Object>>) jdbc.selectList(sql);
	}
	
	public List<Map<String, Object>> orderAllList(){
		String sql = " SELECT A.I_ID, A.I_NAME, A.I_INVEN, A.I_ORIGIN, TO_CHAR(B.P_EXPDATE, 'YYYY/MM/DD') AS P_EXPDATE " + 
				"FROM INGREDIENT A JOIN PURCHASE B ON A.I_ID = B.I_ID(+) ";
		return (List<Map<String, Object>>) jdbc.selectList(sql);
	}
	
	public Map<String ,Object> chooseOne(String id)  {
		String sql = " SELECT A.I_ID, A.I_NAME, B.P_INVEN,A.I_ORIGIN, TO_CHAR(B.P_EXPDATE,'YYYY/MM/DD')AS P_EXPDATE, A.I_INVEN "
		 		   + " FROM INGREDIENT A, PURCHASE B WHERE A.I_ID = B.I_ID AND A.I_NAME = ? ";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		return jdbc.selectOne(sql,param);
	}
	
	public int orderInsert(String name, Object qty){
		String key="";
		Map<String,String> info = login.getSessionStorage();
		System.out.println(info);
		for (Map.Entry<String, String> entry : info.entrySet()) {
		    	key = entry.getKey();	    
		}

		
		String sql = " INSERT INTO PURCHASE (P_ID, E_ID, I_ID, P_BDATE, P_EXPDATE, P_QTY) " + 
				" VALUES (LPAD(P_ID_SEQ.NEXTVAL, 2, '0'), ? , (select I_ID from INGREDIENT " + 
				" WHERE I_NAME = ?), SYSDATE, SYSDATE + 7 , ?) ";
		List<Object> param = new ArrayList<Object>();
		
		param.add(key);
		param.add(name);
		param.add(qty);
		
		return jdbc.insert(sql, param);
	}
	
	public List<Map<String, Object>> deleteList() {
		String sql = " select * from order_view where p_expdate < sysdate ";
		return (List<Map<String, Object>>) jdbc.selectList(sql);
	}
}
