package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import JDBCUtil.JdbcUtil;

public class LoginDAO {

	// �̱���
	private static LoginDAO instance = null;

	private LoginDAO() {
	}

	public static LoginDAO getInstance() {
		if (instance == null) {
			instance = new LoginDAO();
		}
		return instance;
	}
//---------------------------------------------------------------------������� �̱���

	JdbcUtil jdbc = JdbcUtil.getInstance();

	// id�� pw���� �˻��ϴ� �޼���
	public Map<String, Object> login(String id, String pass) {
		String sql = " SELECT E_JOB FROM EMP WHERE E_ID = ? " + " AND E_PW = ? ";

		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		// Dao���� util�� ȣ����
		return jdbc.selectOne(sql, param);
	}
//---------------------------------------------------------------------- ������� id��pw�� �Է¹޾� ó���� �� �ִ� �������� ����� jdbc�� �Ѱ��ִ� �޼ҵ�

	// id�� ������ �˻縦 �ϴ� �޼���
	public Map<String, Object> select(String id) {
		String sql = "SELECT * FROM EMP WHERE E_ID = ? ";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		// jdbc�� selectone�� ����Ϸ��� �����ִ� ������ �������.
		return jdbc.selectOne(sql, param);
	}
//-----------------------------------------------------------------------������� id�� �Է¹޾� ó���� �� �ִ� �������� ����� jdbc�� �Ѱ��ִ� �޼ҵ� 
}
