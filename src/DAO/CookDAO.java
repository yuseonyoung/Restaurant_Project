package DAO;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import JDBCUtil.JdbcUtil;

public class CookDAO {

   // 싱글톤
   private static CookDAO instance = null;

   private CookDAO() {}

   public static CookDAO getInstance() {
      if (instance == null) {
         instance = new CookDAO();
      }
      return instance;
   }
   
   JdbcUtil jdbc = JdbcUtil.getInstance();

    // 각 음식에 들어가는 각 식재료 정보(수량, 단위) 조회
   public List<Map<String, Object>> getRecipe(List<Object> param) {
      String sql = " SELECT F.F_NAME, I.I_NAME, C.C_QTY , I.I_UNIT " +
                " FROM COOK C" + 
                " JOIN FOOD F ON C.F_ID = F.F_ID " +
                " JOIN INGREDIENT I ON C.I_ID = I.I_ID " +
                " WHERE F.F_NAME = ? ";
        
        return jdbc.selectList(sql, param);
    }

    // 음식마다 소모되는 식재료 원가 계산
   public List<Map<String, Object>> getCostAll() {
      String sql = " SELECT F.F_NAME, SUM(C.C_QTY * I.I_PRICE) AS TOTAL_COST " +
                " FROM COOK C" + 
                " JOIN FOOD F ON C.F_ID = F.F_ID " +
                " JOIN INGREDIENT I ON C.I_ID = I.I_ID " +
                " GROUP BY F.F_NAME " +
                " ORDER BY TOTAL_COST DESC ";
        return (List<Map<String, Object>>) jdbc.selectList(sql, null);
    }

    // 음식 1개당 예상되는 순 수익 계산
   public List<Map<String, Object>> getNetProfitAll() {
      String sql = " SELECT F.F_NAME, F.F_PRICE - COALESCE(SUM(C.C_QTY * I.I_PRICE), 0) AS NET_PROFIT " +
                " FROM COOK C" + 
                " JOIN FOOD F ON C.F_ID = F.F_ID " +
                " JOIN INGREDIENT I ON C.I_ID = I.I_ID " +
                " GROUP BY F.F_NAME, F.F_PRICE " +
                " ORDER BY NET_PROFIT DESC ";
      
        return (List<Map<String, Object>>) jdbc.selectList(sql, null);
    }
   
   // INGREDIENT 테이블과 COOK 테이블을 조인하여 재고 부족한 항목 검색
   public List<Map<String, Object>> serchIngredient() {
      String sql = " SELECT I.I_ID, I.I_INVEN, C.C_QTY " +
                " FROM INGREDIENT I " +
                " JOIN COOK C ON I.I_ID = C.I_ID " +
                " WHERE I.I_INVEN < C.C_QTY ";
      return (List<Map<String, Object>>) jdbc.selectList(sql, null);
   }

      
    // 주문시 식재료 재고 감소           
   public int Cooking(List<Object> param) {
      String sql = " UPDATE INGREDIENT " +
                " SET I_INVEN = I_INVEN - ( " +
                "  SELECT COOK.C_QTY " +
                "  FROM COOK " +
                "  WHERE COOK.F_ID IN ( " +
                "    SELECT F_ID " +
                "    FROM FOOD " +
                "    WHERE F_NAME = ? ) " +
                "    AND COOK.I_ID = INGREDIENT.I_ID ) " +
                " WHERE I_ID IN ( " +
                "  SELECT INGREDIENT.I_ID " +
                "  FROM COOK " +
                "  WHERE COOK.F_ID IN ( " +
                "    SELECT F_ID " +
                "    FROM FOOD " +
                "    WHERE F_NAME = ? ) " +
                "    AND COOK.I_ID = INGREDIENT.I_ID ) ";         
        return jdbc.cud(sql, param);
    }
}