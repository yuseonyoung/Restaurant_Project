package Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.SalesDAO;
import DTO.SalesDTO;

public class SalesService {
//¸ÅÃâÁ¶È¸ : ÁÖ¹®½Ã ¹ß»ýÇÏ´Â ¼öÀÍÀ» ÀÏº°, ÁÖ°£º°·Î Á¶È¸°¡´É
   Scanner sc = new Scanner(System.in);
   SalesDTO sd = new SalesDTO();
   // ½Ì±ÛÅæ
   private static SalesService instance;

   private SalesService() {
   }

   public static SalesService getInstance() {
      if (instance == null) {
         instance = new SalesService();
      }
      return instance;
   }
//----------------------------------------------------¿©±â±îÁö ½Ì±ÛÅæ ±¸Çö
   
   SalesDAO dao = SalesDAO.getInstance();
   DecimalFormat df = new DecimalFormat("#,###");
   
   //¸ÅÀÏ¸ÅÃâ
   public void dailySales() {
      int count = 1;
      System.out.print("Á¶È¸ÇÏ½Ç ¿¬µµ¸¦ ÀÔ·ÂÇÏ¼¼¿ä (YYYY): ");
      String year = sc.nextLine();
      System.out.print("Á¶È¸ÇÏ½Ç ¿ùÀ» ÀÔ·ÂÇÏ¼¼¿ä (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectDailySalesList(param);
      
      System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      System.out.println(year+"³â "+month+"¿ù ÀÏ°£ ¸ÅÃâ ³»¿ª");
      System.out.println();
      System.out.println("\t¼ø¹ø\tÀÏÀÚ\t\t¸ÅÃâ¾×");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%d\t%s\t%10s\n",(count++),n.get("ONEDAY"),df.format(n.get("GAIN")));
         System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      
      }
   }   
   //ÁÖ°£¸ÅÃâ   
   public void weeklySales() {
   
      System.out.print("Á¶È¸ÇÏ½Ç ¿¬µµ¸¦ ÀÔ·ÂÇÏ¼¼¿ä (YYYY): ");
      String year = sc.nextLine();
      System.out.print("Á¶È¸ÇÏ½Ç ¿ùÀ» ÀÔ·ÂÇÏ¼¼¿ä (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      // ÀÔ·Â¹ÞÀº ¿¬µµ¿Í ¿ùÀ» ±â¹ÝÀ¸·Î ÁÖÂ÷ÀÇ ½ÃÀÛÀÏ°ú Á¾·áÀÏ °è»ê
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month)- 1, 1); // month´Â 0ºÎÅÍ ½ÃÀÛÇÏ±â ¶§¹®¿¡ -1À» ÇØÁÝ´Ï´Ù.
        int startWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.WEEK_OF_MONTH, startWeek);
        String startDate = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int endWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, endWeek);
        String endDate = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(startDate);
      param.add(endDate);
      
      List<Map<String, Object>> list = dao.selectDailySalesList(param);
      
      System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      System.out.println(year+"³â "+month+"¿ù ÁÖ°£º° ¸ÅÃâ³»¿ª");
      System.out.println();
      System.out.println("\tÁÖÂ÷\t\tÀÏÀÚ\t¸ÅÃâ¾×");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
                      ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
         System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      
      }      
   }
   
   //¸ÅÀÏÁöÃâ 
   public void dailyCost() {
      int count = 1;
      System.out.print("Á¶È¸ÇÏ½Ç ¿¬µµ¸¦ ÀÔ·ÂÇÏ¼¼¿ä (YYYY): ");
      String year = sc.nextLine();
      System.out.print("Á¶È¸ÇÏ½Ç ¿ùÀ» ÀÔ·ÂÇÏ¼¼¿ä (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectDailyCostList(param);
      
      System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      System.out.println(year+"³â "+month+"¿ù ÀÏ°£ ÁöÃâ³»¿ª");
      System.out.println();
      System.out.println("\t¼ø¹ø\tÀÏÀÚ\t\t½ÄÀç·á¸í\t  ÃÑ ÁöÃâ¾×");
      System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%d\t%s\t%s\t%10s\n",(count++),n.get("BDATE"),n.get("I_NAME"),df.format(n.get("BUY")));
         System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      }
   }   
   
   //ÁÖ°£ ÁöÃâ
   public void weeklyCost() {
      
      System.out.print("Á¶È¸ÇÏ½Ç ¿¬µµ¸¦ ÀÔ·ÂÇÏ¼¼¿ä (YYYY): ");
      String year = sc.nextLine();
      System.out.print("Á¶È¸ÇÏ½Ç ¿ùÀ» ÀÔ·ÂÇÏ¼¼¿ä (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      // ÀÔ·Â¹ÞÀº ¿¬µµ¿Í ¿ùÀ» ±â¹ÝÀ¸·Î ÁÖÂ÷ÀÇ ½ÃÀÛÀÏ°ú Á¾·áÀÏ °è»ê
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month)- 1, 1); // month´Â 0ºÎÅÍ ½ÃÀÛÇÏ±â ¶§¹®¿¡ -1À» ÇØÁÝ´Ï´Ù.
        int startWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.WEEK_OF_MONTH, startWeek);
        String startDate = new SimpleDateFormat("yyMMdd").format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int endWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, endWeek);
        String endDate = new SimpleDateFormat("yyMMdd").format(calendar.getTime());
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(startDate);
      param.add(endDate);
      
      List<Map<String, Object>> list = dao.selectWeeklyCostList(param);
      
      System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      System.out.println(year+"³â "+month+"¿ù ÁÖ°£º° ÁöÃâ³»¿ª");
      System.out.println();
      System.out.println("\tÁÖÂ÷\t\tÀÏÀÚ\tÁöÃâ¾×");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
                      ,n.get("MAX_DATE"),df.format(n.get("COST")));
         System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      
      }      
   }
   //¸ÅÀÏ ¼ø¼öÀÍ
   public void dailyGain() {
      int count = 1;
      System.out.print("Á¶È¸ÇÏ½Ç ¿¬µµ¸¦ ÀÔ·ÂÇÏ¼¼¿ä (YYYY): ");
      String year = sc.nextLine();
      System.out.print("Á¶È¸ÇÏ½Ç ¿ùÀ» ÀÔ·ÂÇÏ¼¼¿ä (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
   
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectDailyGainList(param);
      
      System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      System.out.println(year+"³â "+month+"¿ù ÀÏ°£ ¼ø¼öÀÍ³»¿ª");
      System.out.println("\t\t[¼ø¼öÀÍ : ¸ÅÃâ - ÁöÃâ]");
      System.out.println();
      System.out.println("\t¼ø¹ø\tÀÏÀÚ\t\t¼ø¼öÀÍ");
      System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%d\t%s\t%10s\n",(count++),n.get("ONEDAY"),df.format(n.get("GAIN")));
         System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      }
   }
   
   //¿ù°£ Á÷¿ø ÃÑ ÀÓ±Ý
   public void empSal() {
      
      Map<String, Object> map = dao.selectEmpSal();
      System.out.printf("ÇöÀç ·¹½ºÅä¶û ÃÑ Á÷¿øÀÇ ÀÓ±ÝÀº %s¿ø ÀÔ´Ï´Ù.",String.format("%,d",map.get("E_SAL")));
     // System.out.printf("ÇöÀç ·¹½ºÅä¶û ÃÑ Á÷¿øÀÇ ÀÓ±ÝÀº %¿ø ÀÔ´Ï´Ù.",map.get("SUM(E_SAL)"));
   }
   
   //ÁÖ°£ ¼ø¼öÀÍ + ¸·´ë±×·¡ÇÁ
   public void weeklyGain() {
      
      System.out.print("Á¶È¸ÇÏ½Ç ¿¬µµ¸¦ ÀÔ·ÂÇÏ¼¼¿ä (YYYY): ");
      String year = sc.nextLine();
      System.out.print("Á¶È¸ÇÏ½Ç ¿ùÀ» ÀÔ·ÂÇÏ¼¼¿ä (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(year+month);
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectWeeklyGainList(param);
      
      System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
      System.out.println(year+"³â "+month+"¿ù ÁÖ°£ ¼ø¼öÀÍ³»¿ª");
      System.out.println("\t\t[¼ø¼öÀÍ : ¸ÅÃâ - ÁöÃâ]");
      System.out.println();
      System.out.println("\tÁÖÂ÷\t\tÀÏÀÚ\tÁöÃâ¾×");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
                      ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
         System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
         
      }
      System.out.println(year+"³â "+month+"¿ù ÁÖ°£ ¼ø¼öÀÍ³»¿ª ±×·¡ÇÁ");
      
      //ÃÖ´ë°ª ±¸ÇÏ±â
      int max = 0;
      for(Map<String, Object> n : list) {
    	  int gain = Integer.parseInt(n.get("GAIN").toString())/10000;
    	  if(max < gain) {
    		  max = gain;
    	  }
      }
      for(int i=max/50; i>=-1; i--) {
            if(i>0) {
                System.out.printf("%3d\t", i*50);
            }else {
                System.out.print("\t\t");
            }    
            for(int j=0; j<list.size(); j++) {
            	
            	int gain = Integer.parseInt(list.get(j).get("GAIN").toString())/10000;
                if(i>0) {
                    System.out.printf("\t%c", getGraph(i*50, gain));
                }else if(i==0) {
                    System.out.printf("%s\t", list.get(j).get("WEEK"));
                }else if(i<0) {
                   System.out.print(gain+"\t");
                }
            	if(j == list.size()-1 && i == -1) {
            		System.out.print("(´ÜÀ§ : ¸¸¿ø)");
            	}
                
            }//end j
            System.out.println();
            if(i==1||i==0) {
               System.out.print("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
               for(int j=0; j<list.size(); j++) {
            	   System.out.print("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
               }
               System.out.println();
               
            }
        }//end i
      
   }   
      
   //±×·¡ÇÁ Ãâ·Â½Ã ¡á ¹ÝÈ¯   
   public char getGraph(int standard, int gain) {
      return gain >= standard ? '¡á' : '¡¡';        // Á¡¼ö°¡ ±âÁØÁ¡¼ö ÀÌ»óÀÌ¸é * Ãâ·Â

   }

}
//   public String format(String value) {
//      return df.format(Integer.parseInt(value));
//   }

