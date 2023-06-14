package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import JDBCUtil.JdbcUtil;

 kby
=======

 main
public class LoginDAO {

	// 싱글톤
	private static LoginDAO instance = null;



	private LoginDAO() {}


	public static LoginDAO getInstance() {
		if (instance == null) {
			instance = new LoginDAO();
		}
		return instance;
	}
//---------------------------------------------------------------------여기까지 싱글톤

	JdbcUtil jdbc = JdbcUtil.getInstance();


	// id와 pw까지 검사하는 메서드
	public Map<String, Object> login(String id, String pass) {
	String sql = " SELECT E_ID, E_PW, E_JOB FROM EMP WHERE E_ID = ?";
		sql = sql + " AND E_PW = ? ";



		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		// Dao에서 util을 호출함

		return jdbc.selectOne(sql, param);
	}
//---------------------------------------------------------------------- 여기까지 id와pw를 입력받아 처리할 수 있는 쿼리문을 만들어 jdbc에 넘겨주는 메소드

	// id만 가지고 검사를 하는 메서드
	public Map<String, Object> select(String id) {
		String sql = "SELECT * FROM EMP WHERE E_ID = ? ";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		// jdbc의 selectone을 사용하려고 위에있는 모든것을 만든것임.

		return jdbc.selectOne(sql, param);

	}
//-----------------------------------------------------------------------여기까지 id를 입력받아 처리할 수 있는 쿼리문을 만들어 jdbc에 넘겨주는 메소드 
}

