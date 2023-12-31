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
//衙轎褻�� : 輿僥衛 嫦儅ж朝 熱櫛擊 橾滌, 輿除滌煎 褻�萼●�
   Scanner sc = new Scanner(System.in);
   SalesDTO sd = new SalesDTO();
   // 諒旋驛
   private static SalesService instance;

   private SalesService() {
   }

   public static SalesService getInstance() {
      if (instance == null) {
         instance = new SalesService();
      }
      return instance;
   }
//----------------------------------------------------罹晦梱雖 諒旋驛 掘⑷
   
   SalesDAO dao = SalesDAO.getInstance();
   DecimalFormat df = new DecimalFormat("#,###");
   
   //衙橾衙轎
   public void dailySales() {
      int count = 1;
      System.out.print("褻�裔牮� 翱紫蒂 殮溘ж撮蹂 (YYYY): ");
      String year = sc.nextLine();
      System.out.print("褻�裔牮� 錯擊 殮溘ж撮蹂 (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectDailySalesList(param);
      
      System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      System.out.println(year+"喇 "+month+"錯 橾除 衙轎 頂羲");
      System.out.println();
      System.out.println("\t牖廓\t橾濠\t\t衙轎擋");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%d\t%s\t%10s\n",(count++),n.get("ONEDAY"),df.format(n.get("GAIN")));
         System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      
      }
   }   
   //輿除衙轎   
   public void weeklySales() {
   
      System.out.print("褻�裔牮� 翱紫蒂 殮溘ж撮蹂 (YYYY): ");
      String year = sc.nextLine();
      System.out.print("褻�裔牮� 錯擊 殮溘ж撮蹂 (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      // 殮溘嫡擎 翱紫諦 錯擊 晦奩戲煎 輿離曖 衛濛橾婁 謙猿橾 啗骯
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month)- 1, 1); // month朝 0睡攪 衛濛ж晦 陽僥縑 -1擊 п鄹棲棻.
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
      
      System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      System.out.println(year+"喇 "+month+"錯 輿除滌 衙轎頂羲");
      System.out.println();
      System.out.println("\t輿離\t\t橾濠\t衙轎擋");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
                      ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
         System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      
      }      
   }
   
   //衙橾雖轎 
   public void dailyCost() {
      int count = 1;
      System.out.print("褻�裔牮� 翱紫蒂 殮溘ж撮蹂 (YYYY): ");
      String year = sc.nextLine();
      System.out.print("褻�裔牮� 錯擊 殮溘ж撮蹂 (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectDailyCostList(param);
      
      System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      System.out.println(year+"喇 "+month+"錯 橾除 雖轎頂羲");
      System.out.println();
      System.out.println("\t牖廓\t橾濠\t\t衝營猿貲\t  識 雖轎擋");
      System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%d\t%s\t%s\t%10s\n",(count++),n.get("BDATE"),n.get("I_NAME"),df.format(n.get("BUY")));
         System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      }
   }   
   
   //輿除 雖轎
   public void weeklyCost() {
      
      System.out.print("褻�裔牮� 翱紫蒂 殮溘ж撮蹂 (YYYY): ");
      String year = sc.nextLine();
      System.out.print("褻�裔牮� 錯擊 殮溘ж撮蹂 (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      // 殮溘嫡擎 翱紫諦 錯擊 晦奩戲煎 輿離曖 衛濛橾婁 謙猿橾 啗骯
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month)- 1, 1); // month朝 0睡攪 衛濛ж晦 陽僥縑 -1擊 п鄹棲棻.
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
      
      System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      System.out.println(year+"喇 "+month+"錯 輿除滌 雖轎頂羲");
      System.out.println();
      System.out.println("\t輿離\t\t橾濠\t雖轎擋");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
                      ,n.get("MAX_DATE"),df.format(n.get("COST")));
         System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      
      }      
   }
   //衙橾 牖熱櫛
   public void dailyGain() {
      int count = 1;
      System.out.print("褻�裔牮� 翱紫蒂 殮溘ж撮蹂 (YYYY): ");
      String year = sc.nextLine();
      System.out.print("褻�裔牮� 錯擊 殮溘ж撮蹂 (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
   
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectDailyGainList(param);
      
      System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      System.out.println(year+"喇 "+month+"錯 橾除 牖熱櫛頂羲");
      System.out.println("\t\t[牖熱櫛 : 衙轎 - 雖轎]");
      System.out.println();
      System.out.println("\t牖廓\t橾濠\t\t牖熱櫛");
      System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%d\t%s\t%10s\n",(count++),n.get("ONEDAY"),df.format(n.get("GAIN")));
         System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      }
   }
   
   //錯除 霜錳 識 歜旎
   public void empSal() {
      
      Map<String, Object> map = dao.selectEmpSal();
      System.out.printf("⑷營 溯蝶饜嫌 識 霜錳曖 歜旎擎 %s錳 殮棲棻.",String.format("%,d",map.get("E_SAL")));
     // System.out.printf("⑷營 溯蝶饜嫌 識 霜錳曖 歜旎擎 %錳 殮棲棻.",map.get("SUM(E_SAL)"));
   }
   
   //輿除 牖熱櫛 + 虞渠斜楚Щ
   public void weeklyGain() {
      
      System.out.print("褻�裔牮� 翱紫蒂 殮溘ж撮蹂 (YYYY): ");
      String year = sc.nextLine();
      System.out.print("褻�裔牮� 錯擊 殮溘ж撮蹂 (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(year+month);
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectWeeklyGainList(param);
      
      System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
      System.out.println(year+"喇 "+month+"錯 輿除 牖熱櫛頂羲");
      System.out.println("\t\t[牖熱櫛 : 衙轎 - 雖轎]");
      System.out.println();
      System.out.println("\t輿離\t\t橾濠\t雖轎擋");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
                      ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
         System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
         
      }
      System.out.println(year+"喇 "+month+"錯 輿除 牖熱櫛頂羲 斜楚Щ");
      
      //譆渠高 掘ж晦
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
            		System.out.print("(欽嬪 : 虜錳)");
            	}
                
            }//end j
            System.out.println();
            if(i==1||i==0) {
               System.out.print("式式式式式式式式式式式式式式式式");
               for(int j=0; j<list.size(); j++) {
            	   System.out.print("式式式式式式式式");
               }
               System.out.println();
               
            }
        }//end i
      
   }   
      
   //斜楚Щ 轎溘衛 ﹥ 奩��   
   public char getGraph(int standard, int gain) {
      return gain >= standard ? '﹥' : '﹛';        // 薄熱陛 晦遽薄熱 檜鼻檜賊 * 轎溘

   }

}
//   public String format(String value) {
//      return df.format(Integer.parseInt(value));
//   }

