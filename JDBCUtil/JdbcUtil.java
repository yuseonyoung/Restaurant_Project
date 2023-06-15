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

// ---------------------------------------------------------------------------------------�뿬湲곌퉴吏� �떛湲��넠
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";

	private String user = "ysy02";
	private String passwd = "7487";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
//----------------------------------------------------------------------------------------db�뿰寃곗뿉 �븘�슂�븳 蹂��닔�꽑�뼵

//---------------------------------------------------------------------------------------�븳媛쒖쓽 媛믪쓣 諛쏆븘 �븳�뻾�쓣 議고쉶�븯�뒗 硫붿냼�뱶

	public Map<String, Object> selectOne(String sql, List<Object> param) {
		// ex) sql = "SELECT * FROM TBL_MEMBER WHERE MEM_ID=�뀅0010 AND MEM_PASS=1234"

		Map<String, Object> row = null;

		try {

			conn = DriverManager.getConnection(url, user, passwd);

			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				// �삤�씪�겢�씠湲� �븣臾몄뿉 1踰덈��꽣 �떆�옉�빐�빞�맖 ?�뿉 ���쓳�릺�뒗 �닽�옄�엫
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

//-------------------------------------------------------------------------------------------------議곌굔�뿉 留욌뒗 �뻾�쓣 議고쉶�븯�뒗 硫붿냼�뱶
	public int insert(String sql, List<Object> param) throws Exception {
		int result = 0;

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				pstmt.setObject(i + 1, param.get(i));
			}

			result = pstmt.executeUpdate();

		} catch (NullPointerException e) {

		} finally {
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
//-------------------------------------------------------------------------------------------------------------

	public List<Map<String, Object>> selectList(String sql) {
		List<Map<String, Object>> list = null;

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);
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

		} catch (Exception e) {
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
				// 留듭� 怨꾩냽 �꽔�뼱�빞�븯湲� 臾몄뿉 while臾몄씠 �룎�븣留덈떎 怨꾩냽 �깮�꽦�빐以섏빞�븿
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
	public Map<String, Object> selectChoice(String sql) {
		Map<String, Object> list = null;

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();

			while (rs.next()) {
				list = new HashMap<>();

				for (int i = 1; i < columnCount; i++) {
					String key = rsmd.getColumnName(i);
					Object value = rs.getObject(i);
					list.put(key, value);
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
		// 1媛쒖쓽 媛믪쓣 異쒕젰�븷嫄곕땲 list�븞留뚮벉
		return list;
	}

//-------------------------------------------------------------------------------------------------
	public Map<String, Object> deletetList(String sql, List<Object> param) {
		Map<String, Object> row = null;

		try {

			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				// �삤�씪�겢�씠湲� �븣臾몄뿉 1踰덈��꽣 �떆�옉�빐�빞�맖 ?�뿉 ���쓳�릺�뒗 �닽�옄�엫
				pstmt.setObject(i + 1, param.get(i));
			}

			rs = pstmt.executeQuery();

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

	public int update(String sql, List<Object> param) {
		int result = 0;

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
