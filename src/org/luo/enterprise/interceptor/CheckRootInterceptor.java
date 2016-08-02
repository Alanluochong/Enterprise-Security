package org.luo.enterprise.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
public class CheckRootInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest req,HttpServletResponse res,Object object) throws Exception{
		String real_name = (String) req.getSession().getAttribute("real_name");
		if("ÂÞ³å".equals(real_name)){
			return true;
		}
		res.sendRedirect("/Enterprise-Security/login/nopower");
		return false;
	}
}
