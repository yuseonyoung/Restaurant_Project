package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DTO.SeatDTO;
import JDBCUtil.JdbcUtil;

public class FoodOrderDAO {

	private static FoodOrderDAO instance = null;

	private FoodOrderDAO() {
	}

	public static FoodOrderDAO getInstance() {
		if (instance == null)
			instance = new FoodOrderDAO();
		return instance;
	}

//---------------------------------------------------   
	JdbcUtil jdbc = JdbcUtil.getInstance();

	public List<Map<String, Object>> foodAllList() {
		String sql = "SELECT F_NO, F_NAME, F_PRICE FROM FOOD ORDER BY F_NO";
		return (List<Map<String, Object>>) jdbc.selectList(sql);
	}

	public int orderInsert(Object no, Object qty) throws Exception {
		// 주문을 처리하는 로직을 구현합니다.
		// 예를 들어, 주문번호와 수량을 받아서 데이터베이스에 삽입하는 코드를 작성합니다.
		// 주문번호와 수량을 받아오는 부분
		int orderNo = Integer.parseInt(no.toString());
		int quantity = Integer.parseInt(qty.toString());

		// 데이터베이스에 주문을 삽입하는 로직
		// 데이터베이스 연결 및 쿼리 실행
		// insert 쿼리문을 실행하여 주문 정보를 데이터베이스에 삽입합니다.
		// 삽입된 행의 수를 insertedRows 변수에 할당합니다.
		String sql = " INSERT INTO O_FOOD ( O_ID, F_ID, OF_QTY ) VALUES ( ORDER_SEQ.NEXTVAL, (SELECT F_ID FROM FOOD WHERE F_NO = ?), ? ) ";

		List<Object> param = new ArrayList<Object>();

		param.add(orderNo);
		param.add(quantity);

		return jdbc.insert(sql, param);
	}

	public List<Map<String, Object>> selectList() {
		String sql = " SELECT * FROM BILL_VIEW ";
		return (List<Map<String, Object>>) jdbc.selectList(sql);
	}

	public List<Map<String, Object>> selectSeat() {
		String sql = " SELECT * FROM SEAT ";
		return (List<Map<String, Object>>) jdbc.selectList(sql);
	}

	public int updateSeat(String s_whether, String s_no) {
		String sql = " update seat set s_whether = ? where s_no = ? ";
		List<Object> param = new ArrayList<Object>();

		param.add(s_whether);
		param.add(s_no);

		return jdbc.update(sql, param);
	}

}
