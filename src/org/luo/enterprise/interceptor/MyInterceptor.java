package org.luo.enterprise.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
public class MyInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		Integer id = (Integer) req.getSession().getAttribute("id");
		if(id!=null){
			return true;
		}
		res.sendRedirect("/Enterprise-Security/login/toLogin");
		return false;
	}
}
