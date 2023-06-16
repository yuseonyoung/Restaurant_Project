package JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.SeatDTO;
import Service.FoodOrderService;

public class JdbcUtil {
	
	
	
	private static JdbcUtil instance = null;

	private JdbcUtil() {
	}

	public static JdbcUtil getInstance() {
		if (instance == null) {
			instance = new JdbcUtil();
		}
		return instance;
	}

// --------------------------------------------------------------------------------------- 뿬湲곌퉴吏   떛湲  넠
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";

	private String user = "fp";
	private String passwd = "java";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
//----------------------------------------------------------------------------------------db 뿰寃곗뿉  븘 슂 븳 蹂  닔 꽑 뼵

//--------------------------------------------------------------------------------------- 븳媛쒖쓽 媛믪쓣 諛쏆븘  븳 뻾 쓣 議고쉶 븯 뒗 硫붿냼 뱶
	/**
	 * 하나의 데이터 조회할 때 사용
	 * @param sql
	 * @param param
	 * @return
	 */
	public Map<String, Object> selectOne(String sql, List<Object> param) {
		// ex) sql = "SELECT * FROM TBL_MEMBER WHERE MEM_ID= 뀅0010 AND MEM_PASS=1234"

		Map<String, Object> row = null;

		try {

			conn = DriverManager.getConnection(url, user, passwd);

			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
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
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}
		return row;
	}

//-------------------------------------------------------------------------------------------------------------

	/**
	 * 목록 조회할때 사용
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectList(String sql, List<Object> param) {
		List<Map<String, Object>> list = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.size(); i++) {

					pstmt.setObject(i + 1, param.get(i));
				}
			}

			rs = pstmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				if (list == null)
					list = new ArrayList<>();
				Map<String, Object> row = new HashMap<>();

				for (int i = 0; i < columnCount; i++) {

					String key = rsmd.getColumnLabel(i + 1);

					Object value = rs.getObject(i + 1);
					row.put(key, value);
				}

				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			;
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			;
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
			;
		}

		return list;
	}

//-------------------------------------------------------------------------------------------------------------
	/**
	 * insert, update, delete 사용
	 * @param sql
	 * @param param
	 */
	public int cud(String sql, List<Object> param) {
		int result =0;

		try {

			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				
				pstmt.setObject(i + 1, param.get(i));
			}

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}
		return result;
	}
}
