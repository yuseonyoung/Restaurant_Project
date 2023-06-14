package JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {
	
	/*
	 * JDBC�� ����Ͽ� CRUD�� ȿ�������� ����� �� �ִ� �޼��带 ������ class ����� �̱��� ������ �����Ѵ�.
	 */
	private static JdbcUtil instance = null;

	private JdbcUtil() {}

	public static JdbcUtil getInstance() {
		if (instance == null) {
			instance = new JdbcUtil();
		}
		return instance;
	}

// ---------------------------------------------------------------------------------------������� �̱���
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "fp";
	private String passwd = "java";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
//	private Statement stmt = null;
	private ResultSet rs = null;
//----------------------------------------------------------------------------------------db���ῡ �ʿ��� ��������
	public Map<String, Object> selectOne(String sql) {
		Map<String, Object> row = null;

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// MetaData : ���̺��� �������,����,�÷���,�÷������� �������ִ� ������
			// ��������� ������ �κ�
			// �÷��Ǽ�,�÷����� �˱����� ����Ѱ��� MetaData��
			ResultSetMetaData rsmd = rs.getMetaData();
			// �÷��Ǽ�
			int columnCount = rsmd.getColumnCount();
			// rs�� �����Ͱ� 1���� ���Ŷ� 1�������ð���
			while (rs.next()) {
				row = new HashMap<>();

				for (int i = 0; i < columnCount; i++) {
					// columnName -> �������̺��� �÷���
					// ColumnLabel -> �÷��� ��Ī
					// ��Ī�� ������ ���� �÷���
					String key = rsmd.getColumnLabel(i);
					// String key = rsmd.getColumnName(i);
					// value ���� rs���� �����;���.
					Object value = rs.getObject(i);
					row.put(key, value);
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
		// 1���� ���� ����ҰŴ� list�ȸ���
		return row;
	}
//---------------------------------------------------------------------------------------�Ѱ��� ���� �޾� ������ ��ȸ�ϴ� �޼ҵ�
	
	public Map<String, Object> selectOne(String sql, List<Object> param) {
		// ex) sql = "SELECT * FROM TBL_MEMBER WHERE MEM_ID=? AND MEM_PASS=?"

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
		return result;
	}
//-------------------------------------------------------------------------------------------------------------
	public int update(String sql ) {

		int result = 0;

		// �̰��� �������� ó���� �� �ִ� ������
		// ex) update tbl_member set mem_mileage =1000 where mem_id = 'a001'
		// ex) insert into tbl_member (mem_id,mem_pass,mem_name)
		// values('a003','789012','�̼���');
		// ex) delete from tbl_mamber where mem_id = 's001'
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
		return result;
	}
//---------------------------------------------------------------------------------------------------
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
		            // ����Ŭ�̱� ������ 1������ �����ؾߵ� ?�� �����Ǵ� ������
		            pstmt.setObject(i + 1, param.get(i));
		         }
	         }
	         // sql�� �����ϴ� ?�� �����Ǵ� ������ mapping =>
	         // pstmt.setŸ�Ը�(?����,������)
	         // prepareStatement ������ �Էµ����Ͱ� ���;� �����
	         rs = pstmt.executeQuery();
	         
	         // metadata�� ���ؼ� resultset���ִ� column�� ���� ����
	         ResultSetMetaData rsmd = rs.getMetaData();
	         int columnCount = rsmd.getColumnCount();
	         while (rs.next()) {
	            if (list == null)
	               list = new ArrayList<>();
	            // ���� ��� �־���ϱ�  ���� while���� �������� ��� �����������
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

		// MetaData : ���̺��� �������,����,�÷���,�÷������� �������ִ� ������
		// ��������� ������ �κ�
		// �÷��Ǽ�,�÷����� �˱����� ����Ѱ��� MetaData��
		ResultSetMetaData rsmd = rs.getMetaData();
		// �÷��Ǽ�
		int columnCount = rsmd.getColumnCount();
		// rs�� �����Ͱ� 1���� ���Ŷ� 1�������ð���
		while (rs.next()) {
			list = new HashMap<>();

			for (int i = 1; i < columnCount; i++) {
				// columnName -> �������̺��� �÷���
				// ColumnLabel -> �÷��� ��Ī
				// ��Ī�� ������ ���� �÷���
				//String key = rsmd.getColumnLabel(i);
				 String key = rsmd.getColumnName(i);
				// value ���� rs���� �����;���.
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
	// 1���� ���� ����ҰŴ� list�ȸ���
	return list;
}




}




	

