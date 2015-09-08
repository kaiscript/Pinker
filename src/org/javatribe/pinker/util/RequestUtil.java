package org.javatribe.pinker.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {
	private static final Base64 base64=new Base64(true);
	public static final String LAST_PAGE="com.javatribe.pinker";
	public static final String REDIRECT_HOME = "/";
	
	/**
     * 获取当前Request对象.
     * @return 当前Request对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
	public static HttpServletRequest currentRequest() throws IllegalStateException{
		ServletRequestAttributes attrs=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }
        return attrs.getRequest();
	}
	
	/**
     * 获取当前session对象. 若当前线程不是web请求或当前尚未创建{@code session}则返回{@code null}.
     * @return 当前session对象或{@code null}.
     */
	public static HttpSession currentSession(){
		ServletRequestAttributes attrs=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(attrs==null){
			return null;
		}
		return attrs.getRequest().getSession(false);
	}
	
	/**
     * 保存当前请求
     */
	public static void saveRequest(){
		HttpServletRequest request=currentRequest();
		request.getSession().setAttribute(LAST_PAGE, hashRequestPage(request));
		
	}
	
	/**
     * 取出之前保存的请求
     * @return
     */
	public static String retrieveSavedRequest(){
		HttpSession session=currentSession();
		if(session ==null){
			return REDIRECT_HOME;
		}
		String hashLastPage=(String)session.getAttribute(LAST_PAGE);
		if(hashLastPage==null){
			return REDIRECT_HOME;
		}
		else{
			return retrieve(hashLastPage);
		}
	}
	
	/**
     * 加密请求页面
     * @param request
     * @return
     */
	public static String hashRequestPage(HttpServletRequest request){
		String requestUri=request.getRequestURI();
		String query=request.getQueryString();
		if(query!=null){
			requestUri+="?"+query;
		}
		String targetPage=null;
		try {
			targetPage=base64.encodeAsString(requestUri.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return targetPage;
	}
	
	/**
     * 解密请求的页面
     * @param targetPage
     * @return
     */
	public static String retrieve(String targetPage){
		byte[] decode=base64.decode(targetPage);
		
		String requestUri;
		try {
			requestUri = new String(decode,"UTF-8");
			int i=requestUri.indexOf("/", 1);
			return requestUri.substring(i);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
