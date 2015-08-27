package org.javatribe.pinker.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaiscript
 * 2015年8月28日 上午12:01:41
 */
public class Question {
	public String firstQuestion="出生地?";
	public String secondQuestion="小学名字?";
	
	public Map<String,String> questionMap=new HashMap<String, String>();
	
	public Question(){
		questionMap.put("1stQuestion", firstQuestion);
		questionMap.put("2ndQuestion", secondQuestion);
	}
	
	public Map<String,String> getQuestions(){
		return questionMap;
	}
}
