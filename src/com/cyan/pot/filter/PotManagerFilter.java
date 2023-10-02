package com.cyan.pot.filter;

import com.cyan.pot.domain.PotManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 用于拦截普通用户非法访问————与后台管理相关页面————的过滤器
 */
public class PotManagerFilter implements Filter {
    private String excludedURL = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedURL = filterConfig.getInitParameter("excludedURL");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //Returns the part of this request's URL that calls the servlet
        //getServletPath方法可以得到URL中具体请求的资源路径
        String url = req.getServletPath();

        //test
/*        StringBuffer requestURL = req.getRequestURL();
        String requestURI = req.getRequestURI();
        System.out.println("url = " + url);
        System.out.println("requestURL = " + requestURL);
        System.out.println("requestURI = " + requestURI);*/

        if (!excludedURL.equals(url)) {
            HttpSession httpSession = req.getSession();
            PotManager potManager = (PotManager) httpSession.getAttribute("potManager");

            if (potManager == null) {
                //请求转发到管理员登录页面
                req.getRequestDispatcher("/views/manager/manage_login.jsp").forward(servletRequest, servletResponse);
                return;
            }
        }

        //若访问后台管理页面时，管理员已经登录，则放行。
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
