package JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {

	/*
	 * JDBC�� ����Ͽ� CRUD�� ȿ�������� ����� �� �ִ� �޼��带 ������ class ����� �̱��� ������ �����Ѵ�.
	 */
	private static JdbcUtil instance = null;

	private JdbcUtil() {
	}

	public static JdbcUtil getInstance() {
		if (instance == null) {
			instance = new JdbcUtil();
		}
		return instance;
	}

// ---------------------------------------------------------------------------------------������� �̱���
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "ysy02";
	private String passwd = "7487";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
//----------------------------------------------------------------------------------------db���ῡ �ʿ��� ��������

//---------------------------------------------------------------------------------------�Ѱ��� ���� �޾� ������ ��ȸ�ϴ� �޼ҵ�

	public Map<String, Object> selectOne(String sql, List<Object> param) {
		// ex) sql = "SELECT * FROM TBL_MEMBER WHERE MEM_ID=��0010 AND MEM_PASS=1234"

		Map<String, Object> row = null;

		try {

			conn = DriverManager.getConnection(url, user, passwd);
			
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				// ����Ŭ�̱� ������ 1������ �����ؾߵ� ?�� �����Ǵ� ������
				pstmt.setObject(i + 1, param.get(i));
			}

			rs = pstmt.executeQuery();

			if (rs != null) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();

				while (rs.next()) {
					row = new HashMap<>();
					for (int i = 0; i < columnCount; i++) {
						String key = rsmd.getColumnLabel(i + 1);
						Object value = rs.getObject(i + 1);
						row.put(key, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {}
		}
		return row;
	}

//-------------------------------------------------------------------------------------------------���ǿ� �´� ���� ��ȸ�ϴ� �޼ҵ�
	public int insert(String sql, List<Object> param) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				// ����Ŭ�̱� ������ 1������ �����ؾߵ� ?�� �����Ǵ� ������
				pstmt.setObject(i + 1, param.get(i));
			}
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {}
		}
		return result;
	}

//-------------------------------------------------------------------------------------------------------------
	public List<Map<String, Object>> selectList(String sql) {
		List<Map<String, Object>> list = null;

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// metadata�� ���ؼ� resultset���ִ� column�� ���� ����
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				if (list == null)
					list = new ArrayList<>();
				// ���� ��� �־���ϱ⋚���� while���� �������� ��� �����������
				Map<String, Object> row = new HashMap<>();

				for (int i = 0; i < columnCount; i++) {

					String key = rsmd.getColumnLabel(i + 1);
					// value ���� rs���� �����;���.
					Object value = rs.getObject(i + 1);
					row.put(key, value);
				}
				// list�� ���ʴ�� Map�� ����� ��ü�� ���� �ִ´�.
				list.add(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {}
		}

		return list;
	}

//-------------------------------------------------------------------------------------------------
	public Map<String, Object> deletetList(String sql, List<Object> param) {
		Map<String, Object> row = null;

		try {

			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				// ����Ŭ�̱� ������ 1������ �����ؾߵ� ?�� �����Ǵ� ������
				pstmt.setObject(i + 1, param.get(i));
			}

			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {}
		}
		return row;
	}

	public int deleteAllValue(String sql) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {}
		}
		return result;
	}

}
