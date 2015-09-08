package org.javatribe.pinker.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
