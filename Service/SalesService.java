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
//������ȸ : �ֹ��� �߻��ϴ� ������ �Ϻ�, �ְ����� ��ȸ����
   Scanner sc = new Scanner(System.in);
   SalesDTO sd = new SalesDTO();
   // �̱���
   private static SalesService instance;

   private SalesService() {
   }

   public static SalesService getInstance() {
      if (instance == null) {
         instance = new SalesService();
      }
      return instance;
   }
//----------------------------------------------------������� �̱��� ����
   
   SalesDAO dao = SalesDAO.getInstance();
   DecimalFormat df = new DecimalFormat("#,###");
   
   //���ϸ���
   public void dailySales() {
      int count = 1;
      System.out.print("��ȸ�Ͻ� ������ �Է��ϼ��� (YYYY): ");
      String year = sc.nextLine();
      System.out.print("��ȸ�Ͻ� ���� �Է��ϼ��� (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectDailySalesList(param);
      
      System.out.println("������������������������������������������������������������������������������������������������������");
      System.out.println(year+"�� "+month+"�� �ϰ� ���� ����");
      System.out.println();
      System.out.println("\t����\t����\t\t�����");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%d\t%s\t%10s\n",(count++),n.get("ONEDAY"),df.format(n.get("GAIN")));
         System.out.println("������������������������������������������������������������������������������������������������������");
      
      }
   }   
   //�ְ�����   
   public void weeklySales() {
   
      System.out.print("��ȸ�Ͻ� ������ �Է��ϼ��� (YYYY): ");
      String year = sc.nextLine();
      System.out.print("��ȸ�Ͻ� ���� �Է��ϼ��� (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      // �Է¹��� ������ ���� ������� ������ �����ϰ� ������ ���
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month)- 1, 1); // month�� 0���� �����ϱ� ������ -1�� ���ݴϴ�.
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
      
      System.out.println("������������������������������������������������������������������������������������������������������");
      System.out.println(year+"�� "+month+"�� �ְ��� ���⳻��");
      System.out.println();
      System.out.println("\t����\t\t����\t�����");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
                      ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
         System.out.println("������������������������������������������������������������������������������������������������������");
      
      }      
   }
   
   //�������� 
   public void dailyCost() {
      int count = 1;
      System.out.print("��ȸ�Ͻ� ������ �Է��ϼ��� (YYYY): ");
      String year = sc.nextLine();
      System.out.print("��ȸ�Ͻ� ���� �Է��ϼ��� (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectDailyCostList(param);
      
      System.out.println("������������������������������������������������������������������������������������������������������");
      System.out.println(year+"�� "+month+"�� �ϰ� ���⳻��");
      System.out.println();
      System.out.println("\t����\t����\t\t������\t  �� �����");
      System.out.println("������������������������������������������������������������������������������������������������������");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%d\t%s\t%s\t%10s\n",(count++),n.get("BDATE"),n.get("I_NAME"),df.format(n.get("BUY")));
         System.out.println("������������������������������������������������������������������������������������������������������");
      }
   }   
   
   //�ְ� ����
   public void weeklyCost() {
      
      System.out.print("��ȸ�Ͻ� ������ �Է��ϼ��� (YYYY): ");
      String year = sc.nextLine();
      System.out.print("��ȸ�Ͻ� ���� �Է��ϼ��� (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      // �Է¹��� ������ ���� ������� ������ �����ϰ� ������ ���
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month)- 1, 1); // month�� 0���� �����ϱ� ������ -1�� ���ݴϴ�.
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
      
      System.out.println("������������������������������������������������������������������������������������������������������");
      System.out.println(year+"�� "+month+"�� �ְ��� ���⳻��");
      System.out.println();
      System.out.println("\t����\t\t����\t�����");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
                      ,n.get("MAX_DATE"),df.format(n.get("COST")));
         System.out.println("������������������������������������������������������������������������������������������������������");
      
      }      
   }
   //���� ������
   public void dailyGain() {
      int count = 1;
      System.out.print("��ȸ�Ͻ� ������ �Է��ϼ��� (YYYY): ");
      String year = sc.nextLine();
      System.out.print("��ȸ�Ͻ� ���� �Է��ϼ��� (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
   
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectDailyGainList(param);
      
      System.out.println("������������������������������������������������������������������������������������������������������");
      System.out.println(year+"�� "+month+"�� �ϰ� �����ͳ���");
      System.out.println("\t\t[������ : ���� - ����]");
      System.out.println();
      System.out.println("\t����\t����\t\t������");
      System.out.println("������������������������������������������������������������������������������������������������������");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%d\t%s\t%10s\n",(count++),n.get("ONEDAY"),df.format(n.get("GAIN")));
         System.out.println("������������������������������������������������������������������������������������������������������");
      }
   }
   
   //���� ���� �� �ӱ�
   public void empSal() {
      
      Map<String, Object> map = dao.selectEmpSal();
      System.out.printf("���� ������� �� ������ �ӱ��� %s�� �Դϴ�.",String.format("%,d",map.get("E_SAL")));
     // System.out.printf("���� ������� �� ������ �ӱ��� %�� �Դϴ�.",map.get("SUM(E_SAL)"));
   }
   
   //�ְ� ������ + ����׷���
   public void weeklyGain() {
      
      System.out.print("��ȸ�Ͻ� ������ �Է��ϼ��� (YYYY): ");
      String year = sc.nextLine();
      System.out.print("��ȸ�Ͻ� ���� �Է��ϼ��� (MM): ");
      String month = sc.nextLine();
      if(month.length() == 1) {
         month = "0"+month;
      }
      
      List<Object> param = new ArrayList<Object>();
      
      param.add(year+month);
      param.add(year+month);
      List<Map<String, Object>> list = dao.selectWeeklyGainList(param);
      
      System.out.println("������������������������������������������������������������������������������������������������������");
      System.out.println(year+"�� "+month+"�� �ְ� �����ͳ���");
      System.out.println("\t\t[������ : ���� - ����]");
      System.out.println();
      System.out.println("\t����\t\t����\t�����");
      for(Map<String, Object> n : list) {
         System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
                      ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
         System.out.println("������������������������������������������������������������������������������������������������������");
         
      }
      System.out.println(year+"�� "+month+"�� �ְ� �����ͳ��� �׷���");
      
      //�ִ밪 ���ϱ�
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
            		System.out.print("(���� : ����)");
            	}
                
            }//end j
            System.out.println();
            if(i==1||i==0) {
               System.out.print("��������������������������������");
               for(int j=0; j<list.size(); j++) {
            	   System.out.print("����������������");
               }
               System.out.println();
               
            }
        }//end i
      
   }   
      
   //�׷��� ��½� �� ��ȯ   
   public char getGraph(int standard, int gain) {
      return gain >= standard ? '��' : '��';        // ������ �������� �̻��̸� * ���

   }

}
//   public String format(String value) {
//      return df.format(Integer.parseInt(value));
//   }

