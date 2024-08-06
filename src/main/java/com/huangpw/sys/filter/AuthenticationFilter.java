package com.huangpw.sys.filter;

import com.huangpw.sys.utils.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 认证的过滤器：拦截所有的请求
 * 1. 判断当前是否是登录状态
 * 2. 请求的资源是否可以匿名访问
 * 3. 都不满足就跳转到登录页面
 */
@WebFilter(filterName = "authFilter", urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 对封装请求和响应的对象做向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取拦截的请求的访问地址
        String requestURI = request.getRequestURI();
        if(checkAccssible(requestURI)) {
            // 在没有登录的情况下可以访问的资源 登录页面 处理登录逻辑的Servlet 还有各种 js css img
            // 直接放过
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            // 访问的是需要登录后才能访问的资源
            HttpSession session = request.getSession();
            Object loginUser = session.getAttribute(Constant.LOGIN_USER);
            if(loginUser != null) {
                // 是登录的状态
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                // 说明不是登录的状态
                request.setAttribute("msg","请先登录!!!");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }
    }
    
    private boolean checkAccssible(String requestURI){
        List<String > urls =
                Arrays.asList("login.jsp","loginServlet",".css","/js/",".png",".jpg", ".gif", "/img/");
        for (String url : urls) {
            if(requestURI.contains(url)){
                return true;
            }
        }
        return false;
    }
}
