package com.cyan.pot.filter;

import com.cyan.pot.domain.PotUser;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
    用于拦截普通用户非法访问————与用户功能相关的页面————的过滤器
 */
public class PotUserFilter implements Filter {
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

        if (!excludedURL.equals(url)) {
            HttpSession httpSession = req.getSession();
            PotUser potUser = (PotUser) httpSession.getAttribute("potUser");

            if (potUser == null) {
                //若检测到用户未登录，则请求转发到用户登录页面
                req.getRequestDispatcher("/views/user/login.jsp").forward(servletRequest, servletResponse);
                return;
            }
        }

        //若访问后台管理页面时，管理员已经登录，则放行。
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
