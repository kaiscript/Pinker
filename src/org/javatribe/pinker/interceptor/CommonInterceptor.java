package org.javatribe.pinker.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatribe.pinker.entity.Admin;
import org.javatribe.pinker.util.RequestUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//记住我 功能
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	
	/** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     *     
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("---preHandle---");
		if("GET".equalsIgnoreCase(request.getMethod())){
			RequestUtil.saveRequest();
		}
		String requestUri=request.getRequestURI();
		String contextPath=request.getContextPath();
		String requestUrl=requestUri.substring(contextPath.length());
		Admin admin=(Admin) request.getSession().getAttribute("user");
		String autoLogin = (String) request.getSession().getAttribute("autoLogin");
		System.out.println("----preHandle-----"+admin);
		System.out.println(autoLogin);
		if(admin==null){
			request.getSession().setAttribute("autoLogin", "00"); //第一位代表是否正确验证了信息
			request.getRequestDispatcher("/WEB-INF/sys/login.jsp").forward(request, response);
			System.out.println("a");
			return false;
		}
		else if(admin!= null && autoLogin.equals("10")){
			System.out.println("b");
			return true;
		}
		else if(admin!= null && autoLogin.equals("11")){
			System.out.println("c");
			return true;
		}
		return true;
	}
	

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println("---postHandle---");
		if(modelAndView != null){  //加入当前时间  
            modelAndView.addObject("var", "测试postHandle");  
        }
	}

	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("---afterCompletion---");
	}
	
}
