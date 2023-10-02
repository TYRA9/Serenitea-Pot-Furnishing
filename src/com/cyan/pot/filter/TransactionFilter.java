package com.cyan.pot.filter;

import com.cyan.pot.utils.JDBCUtilsDruid;
import jakarta.servlet.*;

import java.io.IOException;

/**
 * 用于进行事务控制的过滤器
 */
public class TransactionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
            //在doFilter方法的后置代码中进行事务的提交和回滚
            JDBCUtilsDruid.commit();
        } catch (Exception e) {
            JDBCUtilsDruid.rollback();
            //异常机制参与业务逻辑(将500错误抛给Tomcat)
            throw new RuntimeException(e);
        }
    }
}
