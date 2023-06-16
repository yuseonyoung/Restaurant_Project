package DAO;


import java.util.List;
import java.util.Map;

import JDBCUtil.JdbcUtil;

public class SalesDAO {
   private static SalesDAO instance=null;
   
   private SalesDAO() {}
   
   public static SalesDAO getInstance() {
      if (instance == null) {
         instance = new SalesDAO();
      }
      return instance;
   }
//----------------------------------------------------------             
   
   JdbcUtil jdbc = JdbcUtil.getInstance();
   
   // 매일매출
   public List<Map<String, Object>> selectDailySalesList(List<Object> param){
      String sql = " SELECT TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,8),'YYYYMMDD'),'YYYY-MM-DD') AS ONEDAY, SUM(A.OF_QTY*B.F_PRICE) AS GAIN "
               + " FROM O_FOOD A "
               + " INNER JOIN FOOD B "
               + " ON A.F_ID = B.F_ID "
                + " WHERE SUBSTR(A.O_ID,1,6) = ? "          
                + " GROUP BY SUBSTR(A.O_ID,1,8)"
                + " ORDER BY ONEDAY ASC ";
                
      return (List<Map<String, Object>>) jdbc.selectList(sql,param);
   }
   
   //  주간매출 
   public List<Map<String, Object>> selectWeeklySalesList(List<Object> param){
      String sql =  ""
            + "   SELECT                                                                      "   
            + "      MIN(SUBSTR(A.O_ID,1,8)) AS MIN_DATE                                      "   
            + "      , MAX(SUBSTR(A.O_ID,1,8)) AS MAX_DATE                                    "   
            + "       , TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,8),'YYYYMMDD'),'WW')||'주' AS WEEK   "   
            + "      , SUM(A.OF_QTY * B.F_PRICE) AS GAIN                                      "   
            + "   FROM O_FOOD A                                                               "   
            + "      INNER JOIN FOOD B                                                       "   
            + "         ON A.F_ID = B.F_ID                                                  "   
            + "   WHERE SUBSTR(A.O_ID,1,6) BETWEEN ? AND ?                  "   
            + "   GROUP BY TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,8),'YYYYMMDD'),'WW')               "
            + " ORDER BY MIN_DATE                                                          ";
      return (List<Map<String, Object>>) jdbc.selectList(sql,param);
   }
   
   
   // 매일지출
   public List<Map<String, Object>> selectDailyCostList(List<Object> param){
      String sql =  " SELECT B.I_ID AS I_ID" 
               + "    , B.I_NAME AS I_NAME" 
               + "      , TO_CHAR(A.P_BDATE,'YYYY-MM-DD') AS BDATE" 
               + "      , SUM(A.P_QTY*B.I_PRICE) AS BUY" 
               + " FROM PURCHASE A" 
               + "   INNER JOIN INGREDIENT B" 
               + "   ON A.I_ID = B.I_ID" 
               + " WHERE TO_CHAR(A.P_BDATE,'YYYYMM') = ?"  
               + " GROUP BY TO_CHAR(A.P_BDATE,'YYYY-MM-DD'), B.I_ID, B.I_NAME" 
               + " ORDER BY TO_CHAR(A.P_BDATE,'YYYY-MM-DD')";
      return (List<Map<String, Object>>) jdbc.selectList(sql,param);
   }
   
   //주간지출
   public List<Map<String, Object>> selectWeeklyCostList(List<Object> param){
      String sql =  ""
            + "   SELECT                                                                 "         
            + "      MIN(TO_CHAR(A.P_BDATE,'YYYYMMDD')) AS MIN_DATE                      "         
            + "      , MAX(TO_CHAR(A.P_BDATE,'YYYYMMDD')) AS MAX_DATE                    "         
            + "      , TO_CHAR(A.P_BDATE,'WW')||'주' AS WEEK                             "       
            + "      , SUM(A.P_QTY*B.I_PRICE) AS BUY                                   "         
            + "   FROM PURCHASE A                                                        "         
            + "   INNER JOIN INGREDIENT B                                                "     
            + "      ON A.I_ID = B.I_ID                                                 "     
            + "   WHERE TO_CHAR(A.P_BDATE,'YYYYMM') BETWEEN ? AND ?  "         
            + "   GROUP BY TO_CHAR(A.P_BDATE,'WW')                               "
            + " ORDER BY MIN_DATE                                      ";
      return (List<Map<String, Object>>) jdbc.selectList(sql,param);
   }
   
   
   //직원월급
   public Map<String ,Object> selectEmpSal(){
      String sql = "SELECT SUM(E_SAL) AS E_TOTALSAL FROM EMP ";
      return (Map<String ,Object>) jdbc.selectOne(sql, null);
   }
   
   //일간순이익
   public List<Map<String, Object>> selectDailyGainList(List<Object> param){
      String sql =  "SELECT TO_CHAR(TO_DATE(A.DAY1,'YYYYMMDD'),'YYYY-MM-DD') AS ONEDAY "
               +", (A.GAIN -( NVL(B.BUY,0))) AS GAIN "
             +" FROM "
             +"     (SELECT SUBSTR(A.O_ID, 1, 8) AS DAY1, SUM(A.OF_QTY * B.F_PRICE) AS GAIN "
             +"     FROM O_FOOD A "
             +"     LEFT OUTER JOIN FOOD B ON A.F_ID = B.F_ID "
             +"     GROUP BY SUBSTR(A.O_ID, 1, 8)) A "
             +" LEFT OUTER JOIN "
             +"     (SELECT TO_CHAR(A.P_BDATE, 'YYYYMMDD') AS DAY2, SUM(A.P_QTY * B.I_PRICE) AS BUY "
             +"     FROM PURCHASE A "
             +"     LEFT OUTER JOIN INGREDIENT B ON A.I_ID = B.I_ID "
             +"     GROUP BY TO_CHAR(A.P_BDATE, 'YYYYMMDD')) B "
             +" ON A.DAY1 = B.DAY2 "
             +" WHERE SUBSTR(A.DAY1,1,6) = ? "
             +" ORDER BY SUBSTR(A.DAY1,1,8) ASC ";
      //System.out.println(sql);
  return (List<Map<String, Object>>) jdbc.selectList(sql,param);
   }
   
   //  주간순이익
   public List<Map<String, Object>> selectWeeklyGainList(List<Object> param){
      String sql =   ""
            + " SELECT A.WEEK AS WEEK                                                                  "
            + "   , (A.GAIN-( NVL(B.GAIN,0))) AS GAIN                                                   "
            + "   , TO_CHAR(TO_DATE(A.MIN_DATE,'YYYYMMDD'),'YYYY-MM-DD') AS MIN_DATE                   "
            + "   , TO_CHAR(TO_DATE(A.MAX_DATE,'YYYYMMDD'),'YYYY-MM-DD') AS MAX_DATE                   "
            + " FROM                                                                                   "
            + "   (                                                                                    "
            + "      SELECT                                                                           "
            + "         MIN(SUBSTR(A.O_ID,1,8)) AS MIN_DATE                                           "
            + "         , MAX(SUBSTR(A.O_ID,1,8)) AS MAX_DATE                                         "
            + "          , TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,8),'YYYYMMDD'),'WW')||'주' AS WEEK        "
            + "         , SUM(A.OF_QTY * B.F_PRICE) AS GAIN                                           "
            + "      FROM O_FOOD A                                                                    "
            + "         INNER JOIN FOOD B                                                            "
            + "            ON A.F_ID = B.F_ID                                                       "
            + "      WHERE SUBSTR(A.O_ID,1,6) = ?                     "
            + "      GROUP BY TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,8),'YYYYMMDD'),'WW')                    "
            + "   )A                                                                                   "
            + "   LEFT OUTER JOIN                                                                      "
            + "   (                                                                                    "
            + "      SELECT                                                                           "
            + "         MIN(TO_CHAR(A.P_BDATE,'YYYYMMDD')) AS MIN_DATE                                "
            + "         , MAX(TO_CHAR(A.P_BDATE,'YYYYMMDD')) AS MAX_DATE                              "
            + "          , TO_CHAR(A.P_BDATE,'WW')||'주' AS WEEK                                     "
            + "         , SUM(A.P_QTY*B.I_PRICE) AS GAIN                                              "
            + "      FROM PURCHASE A                                                                  "
            + "         INNER JOIN INGREDIENT B                                                      "
            + "            ON A.I_ID = B.I_ID                                                       "
            + "      WHERE TO_CHAR(A.P_BDATE,'YYYYMM') = ?           "
            + "      GROUP BY TO_CHAR(A.P_BDATE,'WW')                                                 "
            + "   )B                                                                                   "
            + " ON A.WEEK = B.WEEK                                                                       "
            + " ORDER BY A.WEEK                                                                       ";
      return (List<Map<String, Object>>) jdbc.selectList(sql,param);
   }
   
   
   
   
   
}