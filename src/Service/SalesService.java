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
import JDBCUtil.UndoUtil;

public class SalesService extends UndoUtil {
//매출조회 : 주문시 발생하는 수익을 일별, 주간별로 조회가능
   Scanner sc = new Scanner(System.in);
   SalesDTO sd = new SalesDTO();
   // 싱글톤
   private static SalesService instance;

   private SalesService() {
   }

   public static SalesService getInstance() {
      if (instance == null) {
         instance = new SalesService();
      }
      return instance;
   }
//----------------------------------------------------여기까지 싱글톤 구현
   
   SalesDAO dao = SalesDAO.getInstance();
   DecimalFormat df = new DecimalFormat("#,###");
   
   //매일매출
   public void dailySales() {
	  while(true) {
		  
		  int count = 1;
		  System.out.print("✎______조회하실 연도를 입력하세요 (YYYY): ");
		  String year = sc.nextLine();
		  System.out.println();
		  System.out.print("✎______조회하실 월을 입력하세요 (MM): ");
		  String month = sc.nextLine();
		  System.out.println();
		  if(month.length() == 1) {
			  month = "0"+month;
		  }
		  //╔═════════════╗╚════════════╝
		  List<Object> param = new ArrayList<Object>();
		  
		  param.add(year+month);
		  List<Map<String, Object>> list = dao.selectDailySalesList(param);
		  
		  System.out.println("╔════════════════════════════════════════════════╗");
		  System.out.println("│		"+year+"년 "+month+"월 일간 매출 내역		 │");
		  System.out.println("╚════════════════════════════════════════════════╝");
		  System.out.println();
		  System.out.println("\t순번\t일자\t\t매출액");
		  System.out.println("══════════════════════════════════════════════════");
		  for(Map<String, Object> n : list) {
			  System.out.printf("\t%d\t%s\t%10s\n",(count++),n.get("ONEDAY"),df.format(n.get("GAIN")));
			  System.out.println("══════════════════════════════════════════════════");
		  }
		  undo();
	  }
   }
   
   //주간매출   
   public void weeklySales() {
	  while(true) {
		  
		  System.out.print("✎______조회하실 연도를 입력하세요 (YYYY): ");
		  String year = sc.nextLine();
		  System.out.println();
		  System.out.print("✎______조회하실 월을 입력하세요 (MM): ");
		  String month = sc.nextLine();
		  System.out.println();
		  if(month.length() == 1) {
			  month = "0"+month;
		  }
		  
		  List<Object> param = new ArrayList<Object>();
		  
		  param.add(year+month);
		  param.add(year+month);
		  
		  List<Map<String, Object>> list = dao.selectWeeklySalesList(param);
		  
		  System.out.println("╔════════════════════════════════════════════════╗");
		  System.out.println("│		"+year+"년 "+month+"월 주간 매출 내역		 │");
		  System.out.println("╚════════════════════════════════════════════════╝");
		  System.out.println();
		  System.out.println("   주차\t\t일자\t\t  매출액");
		  System.out.println("══════════════════════════════════════════════════");
		  for(Map<String, Object> n : list) {
			  System.out.printf(" %s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
					  ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
			  System.out.println("══════════════════════════════════════════════════");
			  System.out.println();
		  }
		 undo(); 
	  }
   }
   
   //매일지출 
   public void dailyCost() {
	  while(true) {
		  
		  int count = 1;
		  System.out.print("✎______조회하실 연도를 입력하세요 (YYYY): ");
		  String year = sc.nextLine();
		  System.out.println();
		  System.out.print("✎______조회하실 월을 입력하세요 (MM): ");
		  String month = sc.nextLine();
		  System.out.println();
		  if(month.length() == 1) {
			  month = "0"+month;
		  }
		  
		  List<Object> param = new ArrayList<Object>();
		  
		  param.add(year+month);
		  List<Map<String, Object>> list = dao.selectDailyCostList(param);
		  
		  System.out.println("╔════════════════════════════════════════════════╗");
		  System.out.println("│		"+year+"년 "+month+"월 일간 지출 내역		 │");
		  System.out.println("╚════════════════════════════════════════════════╝");
		  System.out.println();
		  System.out.println("\t순번\t일자\t\t식재료명\t  총 지출액");
		  System.out.println("══════════════════════════════════════════════════");
		  for(Map<String, Object> n : list) {
			  System.out.printf("\t%d\t%s\t%s\t%10s\n",(count++),n.get("BDATE"),n.get("I_NAME"),df.format(n.get("BUY")));
			  System.out.println("══════════════════════════════════════════════════");
		  }
		  undo();
	  }
   }   
   
   //주간 지출
   public void weeklyCost() {
      while(true) {
    	  
    	  System.out.print("✎______조회하실 연도를 입력하세요 (YYYY): ");
    	  String year = sc.nextLine();
    	  System.out.println();
    	  System.out.print("✎______조회하실 월을 입력하세요 (MM): ");
    	  String month = sc.nextLine();
    	  System.out.println();
    	  if(month.length() == 1) {
    		  month = "0"+month;
    	  }
    	  
    	  List<Object> param = new ArrayList<Object>();
    	  
    	  param.add(year+month);
    	  param.add(year+month);
    	  
    	  List<Map<String, Object>> list = dao.selectWeeklyCostList(param);
    	  
    	  System.out.println("╔════════════════════════════════════════════════╗");
    	  System.out.println("│		"+year+"년 "+month+"월 주간 지출 내역		 │");
    	  System.out.println("╚════════════════════════════════════════════════╝");
    	  System.out.println();
    	  System.out.println("\t주차\t\t일자\t\t   지출액");
    	  System.out.println("══════════════════════════════════════════════════");
    	  for(Map<String, Object> n : list) {
    		  System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
    				  ,n.get("MAX_DATE"),df.format(n.get("BUY")));
    		  System.out.println("══════════════════════════════════════════════════");
    	  }
    	  undo();
      }
   }
   
   //매일 순수익
   public void dailyGain() {
	  while(true) {
		  
		  int count = 1;
		  System.out.print("✎______조회하실 연도를 입력하세요 (YYYY): ");
		  String year = sc.nextLine();
		  System.out.println();
		  System.out.print("✎______조회하실 월을 입력하세요 (MM): ");
		  String month = sc.nextLine();
		  System.out.println();
		  if(month.length() == 1) {
			  month = "0"+month;
		  }
		  
		  List<Object> param = new ArrayList<Object>();
		  
		  param.add(year+month);
		  List<Map<String, Object>> list = dao.selectDailyGainList(param);
		  
		  System.out.println("╔════════════════════════════════════════════════╗");
		  System.out.println("│		"+year+"년 "+month+"월 일간 순이익 내역	 	│");
		  System.out.println("╚════════════════════════════════════════════════╝");
		  System.out.println();
		  System.out.println("\t순번\t일자\t\t순수익");
		  System.out.println("══════════════════════════════════════════════════");
		  for(Map<String, Object> n : list) {
			  System.out.printf("\t%d\t%s\t%10s\n",(count++),n.get("ONEDAY"),df.format(n.get("GAIN")));
			  System.out.println("══════════════════════════════════════════════════");
		  }
		  undo();
	  }
   }
   
   //월간 직원 총 임금
   public void empSal() {
      
      Map<String, Object> map = dao.selectEmpSal();
      System.out.printf("현재 레스토랑 총 직원의 임금은 %s원 입니다.",String.format("%,d",map.get("E_SAL")));
     // System.out.printf("현재 레스토랑 총 직원의 임금은 %원 입니다.",map.get("SUM(E_SAL)"));
   }
   
   //주간 순수익 + 막대그래프
   public void weeklyGain() {
	  while(true) {
		  
		  System.out.print("✎______조회하실 연도를 입력하세요 (YYYY): ");
		  String year = sc.nextLine();
		  System.out.print("✎______조회하실 월을 입력하세요 (MM): ");
		  String month = sc.nextLine();
		  System.out.println();
		  if(month.length() == 1) {
			  month = "0"+month;
		  }
		  
		  List<Object> param = new ArrayList<Object>();
		  
		  param.add(year+month);
		  param.add(year+month);
		  List<Map<String, Object>> list = dao.selectWeeklyGainList(param);
		  
		  System.out.println("╔════════════════════════════════════════════════╗");
		  System.out.println("│		"+year+"년 "+month+"월 주간 순이익 내역	  │");
		  System.out.println("╚════════════════════════════════════════════════╝");
		  System.out.println();
		  System.out.println("\t주차\t\t일자\t지출액");
		  System.out.println("══════════════════════════════════════════════════");
		  for(Map<String, Object> n : list) {
			  System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
					  ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
			  System.out.println("══════════════════════════════════════════════════");         
		  }
		  System.out.println();
		  System.out.println("═════════ "+year+"년 "+month+"월 주간 순수익내역 그래프 ═════════");
		  
		  //최대값 구하기
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
					  System.out.print("(단위 : 만원)");
				  }
				  
			  }//end j
			  System.out.println();
			  if(i==1||i==0) {
				  System.out.print("────────────────");
				  for(int j=0; j<list.size(); j++) {
					  System.out.print("────────");
				  }
				  System.out.println();
				  
			  }
		  }//end i
		  undo();
	  }
   }   
      
   //그래프 출력시 ■ 반환   
   public char getGraph(int standard, int gain) {
      return gain >= standard ? '■' : '　';        // 점수가 기준점수 이상이면 * 출력

   }

   public void oneDay() {
	   
	   while(true) {
		   System.out.println("일간 매출,지출 조회");
		   System.out.println(" 1. 일간 매출");
		   System.out.println(" 2. 일간 지출");
		   try {
				System.out.println();
				System.out.print("✎______조회하고 싶은 내역의 번호를 입력하세요 : ");
				int value = Integer.valueOf(sc.nextLine());
				System.out.println();
				switch (value) {
				case 1:
					dailySales();
					break;
				case 2:
					dailyCost();
					break;
				
				default:
					System.out.println("☹ 잘못된 값이 입력되었습니다.");
					break;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("☹ 잘못된 값이 입력되었습니다.");
				continue;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		   
   }
   
   public void oneWeek() {
	   
	   while(true) {
		   System.out.println("╔═════════════╗");
		   System.out.println("│ 주간 매출,지출 조회 │");
		   System.out.println("╚═════════════╝");
		   System.out.println("   1. 주간 매출");
		   System.out.println("   2. 주간 지출");
		   try {
				System.out.println();
				System.out.print("✎______조회하고 싶은 내역의 번호를 입력하세요 : ");
				System.out.println();
				int value = Integer.valueOf(sc.nextLine());
				switch (value) {
				case 1:
					weeklySales();
					break;
				case 2:
					weeklyCost();
					break;
				
				default:
					System.out.println("☹ 잘못된 값이 입력되었습니다.");
					break;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("☹ 잘못된 값이 입력되었습니다.");
				continue;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		   
   }
   
   public void netProfit() {
	   
	   while(true) {
		   System.out.println("╔═════════════╗");
		   System.out.println("│   순이익 조회         │");
		   System.out.println("╚═════════════╝");
		   System.out.println("  1. 일간 순이익");
		   System.out.println("  2. 주간 순이익");
		   try {
				System.out.println();
				System.out.print("✎______조회하고 싶은 내역의 번호를 입력하세요 : ");
				System.out.println();
				int value = Integer.valueOf(sc.nextLine());
				switch (value) {
				case 1:
					dailyGain();
					break;
				case 2:
					weeklyGain();
					break;
				
				default:
					System.out.println("☹ 잘못된 값이 입력되었습니다.");
					break;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("☹ 잘못된 값이 입력되었습니다.");
				continue;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		   
	   
   }
   
}
