package com.jlj.util;

import java.util.ArrayList;
import java.util.List;

import com.jlj.vo.PharseVO;


public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * test greenroad getUsefulPhase
		 */
		/*String s = "0_1_0.5";
		String phaseString = "20,0,20";
		if(phaseString.substring(0, 1)!=null&&!phaseString.substring(0, 1).equals("0"))
		{
			int firstIndex = phaseString.indexOf(",0");
			if(firstIndex!=-1)
			{
				phaseString = phaseString.substring(0,firstIndex);
			}
			String[] usefulPhase = phaseString.split(",");
			for(int i=0;i<usefulPhase.length;i++)
			{
				System.out.println(usefulPhase[i]);
			}
		}*/
		
		
		//System.out.println(3689/3600);
		  /*
		   */
//		Object obj = null;
//		obj.equals(""); 
		
//		int a = 0;
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
//		String dateString1=sdf.format(new Date());
//		System.out.println(dateString1);
//		List<Usero> useros = new ArrayList<Usero>();
//		for (int i = 0; i < 300000; i++) {
//			Usero usero = new Usero();
//			usero.setId(i);
//			usero.setUsername("asd");
//			usero.setPassword("asd");
//			usero.setUlimit(1);
//			usero.setUip("192.168.1.21");
//			useros.add(usero);
//		}
//		for (int i = 0; i < useros.size(); i++) {
//			a  = a + useros.get(i).getId();
//		}
//		System.out.println(a);
//		String dateString2=sdf.format(new Date());
//		System.out.println(dateString2);
		String dates = "3_2_3:3,3_0_3:3,3_3_3:3,3_0_2:3,3_0_1:3,3_0_0:3,3_3_2:3,3_3_1:3,3_3_0:3,3_1_0:3,3_1_1:3,3_1_2:3,3_2_0:3,3_2_1:3,3_2_2:3,3_1_3:3,5_2_3:3,5_0_3:3,5_3_3:3,5_0_2:3,5_0_1:3,5_0_0:3,5_3_2:3,5_3_1:3,5_3_0:3,5_1_0:3,5_1_1:3,5_1_2:3,5_2_0:3,5_2_1:3,5_2_2:3,5_1_3:3,";
		String gtime = "3_10,5_10,";
		String ytime = "3_10,5_10,";
		String rtime = "3_10,5_10,";
		String[] ytimes = ytime.split(",");
		String[] rtimes = rtime.split(",");
		String[] gtimes = gtime.split(",");
		
		String[] dateses = dates.split(",");
		 
		List<PharseVO> pharseVOS= new ArrayList<PharseVO>();
		for (int j = 0; j < gtimes.length; j++) {
			PharseVO pharseVO = new PharseVO();
			String param = gtimes[j];
			int sid = Integer.parseInt(param.substring(0, param.indexOf("_")));
			int time = Integer.parseInt(param.substring(param.indexOf("_")+1, param.length()));
			pharseVO.setSid(sid);
			pharseVO.setGltime(time);
			pharseVOS.add(pharseVO);
		}
		
		for (int j = 0; j < ytimes.length; j++) {
			String param = ytimes[j];
			int time = Integer.parseInt(param.substring(param.indexOf("_")+1, param.length()));
			pharseVOS.get(j).setYltime(time);
		}
		
		for (int j = 0; j < rtimes.length; j++) {
			String param = rtimes[j];
			int time = Integer.parseInt(param.substring(param.indexOf("_")+1, param.length()));
			pharseVOS.get(j).setRltime(time);
		}
		
		
		
		  int splitSize = pharseVOS.size();//分割的块大小
		    
		  List<String> subAry = splitAry(dateses, splitSize);//分割后的子块数组
		
		  for (int j = 0; j < pharseVOS.size(); j++) {
			  pharseVOS.get(j).setDates(subAry.get(j));
			  
			  System.err.println(pharseVOS.get(j).getDates());
			}
		  
	}
	
    /*
     * 
     */  
    private static List<String> splitAry(String[] ary, int subSize) {  
          int count = 16;  
          List<String> subAryList = new ArrayList<String>();  
  
          for (int i = 0; i < subSize; i++) {
        	  
        	  String dates = "";
        	  
        	  for (int j = 0; j < count; j++) {
        		  	
        		  dates = dates + ary[j+i*count]+",";
        	  }
        	  subAryList.add(dates);
        	  
          }
            
          return subAryList;  
    }  

}
