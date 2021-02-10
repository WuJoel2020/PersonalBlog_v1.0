package org.joel.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器，检查Session，如果没有登录就自动重定向到登录界面
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
            System.out.println("拦截器启用:" + request.getRequestURL());
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
