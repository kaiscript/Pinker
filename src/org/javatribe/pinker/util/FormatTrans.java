package org.javatribe.pinker.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * @author kaiscript
 * 2015年8月28日 上午12:02:49
 */
public class FormatTrans {
	/**
	 * 字符串转换为Date类型
	 * @param time
	 * @return
	 */
	public static Date stringToDate(String time){
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeDate=null;
		
		try {
			timeDate =sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeDate;
	}
	/**
	 * 
	 * @param commentTime 发布时间
	 * @return 现在距离 发布时间 的时间差.e.g 刚刚、1天前...
	 */
	public static String getHowLongTime(Date commentTime){
		long commentTimeL = commentTime.getTime();
		long subTime = (System.currentTimeMillis() - commentTimeL)/1000;
		//转换为分钟
		subTime /=60;
		
		if(subTime==0){
			return "刚刚";
		}else if(subTime<60){
			return subTime+"分钟前";
		}
		//转换为小时
		subTime /= 60;
		if(subTime<24){
			return subTime+"小时前";
		}
		
		//转换为天
		subTime /= 24;
		return subTime+"天前";
		
	}
	
	@Test
	public void test(){
		
//		System.out.println(getHowLongTime(1444557501955l));
	}
	
	@Test
	public void getNowTime(){
		System.out.println(System.currentTimeMillis());
	}
}
