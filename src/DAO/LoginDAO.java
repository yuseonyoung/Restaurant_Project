package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import JDBCUtil.JdbcUtil;

public class LoginDAO {


	private static LoginDAO instance = null;



	private LoginDAO() {}


	public static LoginDAO getInstance() {
		if (instance == null) {
			instance = new LoginDAO();
		}
		return instance;
	}


	JdbcUtil jdbc = JdbcUtil.getInstance();


	
	public Map<String, Object> login(String id, String pass) {
	String sql = " SELECT E_ID, E_PW, E_JOB FROM EMP WHERE E_ID = ?";
		sql = sql + " AND E_PW = ? ";

		System.out.println(id);
		System.out.println(pass);
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
	

		return jdbc.selectOne(sql, param);
	}
//---------------------------------------------------------------------- �뿬湲곌퉴吏� id��pw瑜� �엯�젰諛쏆븘 泥섎━�븷 �닔 �엳�뒗 荑쇰━臾몄쓣 留뚮뱾�뼱 jdbc�뿉 �꽆寃⑥＜�뒗 硫붿냼�뱶

	// id留� 媛�吏�怨� 寃��궗瑜� �븯�뒗 硫붿꽌�뱶
	public Map<String, Object> select(String id) {
		String sql = "SELECT * FROM EMP WHERE E_ID = ? ";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		// jdbc�쓽 selectone�쓣 �궗�슜�븯�젮怨� �쐞�뿉�엳�뒗 紐⑤뱺寃껋쓣 留뚮뱺寃껋엫.

		return jdbc.selectOne(sql, param);

	}
//-----------------------------------------------------------------------�뿬湲곌퉴吏� id瑜� �엯�젰諛쏆븘 泥섎━�븷 �닔 �엳�뒗 荑쇰━臾몄쓣 留뚮뱾�뼱 jdbc�뿉 �꽆寃⑥＜�뒗 硫붿냼�뱶 
}

