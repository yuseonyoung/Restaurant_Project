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

public class JdbcUtil {

	private static JdbcUtil instance = null;


	private JdbcUtil() {}


	public static JdbcUtil getInstance() {
		if (instance == null) {
			instance = new JdbcUtil();
		}
		return instance;
	}

// ---------------------------------------------------------------------------------------여기까지 싱글톤
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private String user = "ysy02";
	private String passwd = "7487";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
//----------------------------------------------------------------------------------------db연결에 필요한 변수선언

//---------------------------------------------------------------------------------------한개의 값을 받아 한행을 조회하는 메소드

	public Map<String, Object> selectOne(String sql, List<Object> param) {
		// ex) sql = "SELECT * FROM TBL_MEMBER WHERE MEM_ID=ㅁ0010 AND MEM_PASS=1234"

		Map<String, Object> row = null;

		try {

			conn = DriverManager.getConnection(url, user, passwd);
			
			pstmt = conn.prepareStatement(sql);


			for (int i = 0; i < param.size(); i++) {
				// 오라클이기 때문에 1번부터 시작해야됨 ?에 대응되는 숫자임
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


//-------------------------------------------------------------------------------------------------조건에 맞는 행을 조회하는 메소드
	public int insert(String sql, List<Object> param) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				// 오라클이기 때문에 1번부터 시작해야됨 ?에 대응되는 숫자임
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
			// metadata를 통해서 resultset에있는 column의 수를 얻어옴
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				if (list == null)
					list = new ArrayList<>();
				// 맵은 계속 넣어야하기문에 while문이 돌때마다 계속 생성해줘야함
				Map<String, Object> row = new HashMap<>();

				for (int i = 0; i < columnCount; i++) {

					String key = rsmd.getColumnLabel(i + 1);
					// value 값은 rs에서 가져와야함.
					Object value = rs.getObject(i + 1);
					row.put(key, value);
				}
				// list에 차례대로 Map에 저장된 전체의 값을 넣는다.
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
	         if(param != null) {
		         for (int i = 0; i < param.size(); i++) {
		            // 오라클이기 때문에 1번부터 시작해야됨 ?에 대응되는 숫자임
		            pstmt.setObject(i + 1, param.get(i));
		         }
	         }
	         // sql에 존재하는 ?에 대응되는 데이터 mapping =>
	         // pstmt.set타입명(?순번,데이터)
	         // prepareStatement 쿼리는 입력데이터가 들어와야 실행됨
	         rs = pstmt.executeQuery();
	         
	         // metadata를 통해서 resultset에있는 column의 수를 얻어옴
	         ResultSetMetaData rsmd = rs.getMetaData();
	         int columnCount = rsmd.getColumnCount();
	         while (rs.next()) {
	            if (list == null)
	               list = new ArrayList<>();
	            // 맵은 계속 넣어야하기  문에 while문이 돌때마다 계속 생성해줘야함
	            Map<String, Object> row = new HashMap<>();
	            
	            for (int i = 0; i < columnCount; i++) {
	               
	               String key = rsmd.getColumnLabel(i + 1);
	               // value 값은 rs에서 가져와야함.
	               Object value = rs.getObject(i + 1);
	               row.put(key, value);
	            }
	            // list에 차례대로 Map에 저장된 전체의 값을 넣는다.
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


//---------------------------------------------------------------
public Map<String, Object> selectChoice(String sql) {
	Map<String, Object> list = null;

	try {
		conn = DriverManager.getConnection(url, user, passwd);
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		// MetaData : 테이블의 제약사항,구성,컬럼명,컬럼갯수를 가지고있는 데이터
		// 결과집합이 들어오는 부분
		// 컬럼의수,컬럼명을 알기위해 사용한것이 MetaData임
		ResultSetMetaData rsmd = rs.getMetaData();
		// 컬럼의수
		int columnCount = rsmd.getColumnCount();
		// rs에 데이터가 1개만 들어갈거라 1개가져올것임
		while (rs.next()) {
			list = new HashMap<>();

			for (int i = 1; i < columnCount; i++) {
				// columnName -> 원본테이블의 컬럼명
				// ColumnLabel -> 컬럼의 별칭
				// 별칭이 없으면 원본 컬럼명
				//String key = rsmd.getColumnLabel(i);
				 String key = rsmd.getColumnName(i);
				// value 값은 rs에서 가져와야함.
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
	// 1개의 값을 출력할거니 list안만듬
	return list;
}




}




	


	

//-------------------------------------------------------------------------------------------------
	public Map<String, Object> deletetList(String sql, List<Object> param) {
		Map<String, Object> row = null;

		try {

			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < param.size(); i++) {
				// 오라클이기 때문에 1번부터 시작해야됨 ?에 대응되는 숫자임
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

