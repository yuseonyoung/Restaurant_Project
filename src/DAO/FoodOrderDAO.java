package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DTO.SeatDTO;
import JDBCUtil.JdbcUtil;

public class FoodOrderDAO {
	SeatDTO sd = SeatDTO.getInstance();
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
	//메뉴판 조회 dao
	public List<Map<String, Object>> foodAllList() {
		String sql = "SELECT F_NO, F_NAME, F_PRICE FROM FOOD ORDER BY F_NO";
		return (List<Map<String, Object>>) jdbc.selectList(sql, null);
	}
	//음식 구매 dao
	public int orderInsert(Object no, Object qty) throws Exception {
	
		int orderNo = Integer.parseInt(String.valueOf(no));
		int quantity = Integer.parseInt(String.valueOf(qty));


		String sql = " INSERT INTO O_FOOD ( O_ID, F_ID, OF_QTY ) "
				+ "VALUES (( SELECT o_id FROM ( SELECT ROWNUM AS rn, o_id FROM orders ORDER BY rn DESC ) where rownum = 1 ), (SELECT F_ID FROM FOOD WHERE F_NO = ?), ? ) ";
		

		List<Object> param = new ArrayList<Object>();

		param.add(orderNo);
		param.add(quantity);

		return jdbc.cud(sql, param);
	}
	//자동주문번호 생성 dao
	public int orderInsert() {
		String sql = " INSERT INTO ORDERS (O_ID, S_NO, O_PRICE) " + 
				" VALUES (TO_CHAR(SYSDATE, 'YYYYMMDD') || LPAD(order_seq.NEXTVAL, 2, '0'), ?, 0) ";

		List<Object> param = new ArrayList<Object>();
		param.add(sd.getS_no());
		
			return jdbc.cud(sql, param);		
	}
	//가장 최근 주문한 주문번호 조회 dao
	public  List<Map<String, Object>> selectId(){
		String sql = " SELECT o_id FROM ( SELECT ROWNUM AS rn, o_id "
				+ "FROM orders ORDER BY rn DESC ) where rownum = 1 ";
		
		return jdbc.selectList(sql, null);
	}
	//영수증 조회 dao
	public List<Map<String, Object>> selectList() {
		String sql = " SELECT * FROM BILL_VIEW ";
		
		return (List<Map<String, Object>>) jdbc.selectList(sql, null);
	}
	//전체좌석 조회 dao
	public List<Map<String, Object>> selectSeat() {
		String sql = " SELECT * FROM SEAT ";
		return (List<Map<String, Object>>) jdbc.selectList(sql, null);
	}
	//좌석 선택dao
	public int updateSeat(String s_whether, String s_no) {
		String sql = " update seat set s_whether = ? where s_no = ? ";
		List<Object> param = new ArrayList<Object>();

		param.add(s_whether);
		param.add(s_no);

		return jdbc.cud(sql, param);
	}
	//orders의 price값 update dao
	public int updatePrice(String oId){
		String sql = " update orders set o_price =( " + 
				" select sum(A.OF_QTY * B.F_PRICE) AS SUMPRICE " + 
				" from o_food a , food b " + 
				" where a.f_id = b.f_id and " + 
				" a.o_id = ? ) where o_id = ?";
		
		List<Object> param = new ArrayList<Object>();

		param.add(oId);
		param.add(oId);
				
		return jdbc.cud(sql, param);
	}
	
	public List<Map<String, Object>> totalPrice(String oId) {
		String sql = " SELECT O_PRICE FROM ORDERS WHERE O_ID = ?";
		
		List<Object> param = new ArrayList<Object>();
		param.add(oId);
		
		return jdbc.selectList(sql, param);
	}
	//주문취소
	public int orderCancel(String o_id) {
	       String sql = "DELETE FROM ORDERS WHERE O_ID = ?";
	       
	       List<Object> param = new ArrayList<Object>();
	       param.add(o_id);
	       
	       
	       return jdbc.cud(sql, param);
	   }

}
