package org.javatribe.pinker.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 不要管这个
 * @author kaiscript
 * 2015年10月14日 下午8:59:34
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("-------preHandle------");
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				Cookie cookie = cookies[i];
				System.out.println("interptor/cookie:"+cookie.getName());
				System.out.println("interptor/cookie:"+cookie.getValue());
				if("autoLogin".equals(cookie.getName())){
					String value = cookie.getValue();
					System.out.println("value:"+value);
					if(value.endsWith("AL1")){ //cookie包含确认自动登录信息AL1
						System.out.println("AL1:"+value);
						return true;
					}
					else if(value.endsWith("AL0")){
						System.out.println("AL0:"+value);
						break;
					}
				}
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/sys/login.jsp").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
