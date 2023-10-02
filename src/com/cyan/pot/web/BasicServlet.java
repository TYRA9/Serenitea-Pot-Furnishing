package com.cyan.pot.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
    Web层的BasicServlet作为抽象类，提供模版，并不单独作为servlet资源，所以不需要配置url.
 */
public abstract class BasicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //注意 : action是动态变化的，却决于提交的表单。
        //PS:在各个表单中添加了一个type=hidden的input标签
        String action = req.getParameter("action");

        /*
            联系手写Tomcat底层机制，线程通过Tomcat维护的容器得到反射创建的servlet实例，
            通过该实例来调用service方法，然后就是各种动态绑定。
         */
        try {
            //借助反射，实现了action的动态绑定！
            //此处的this即对应的servlet实例！
            Method dynAction =
                    this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            //通过invoke方法，实现反射调用
            dynAction.invoke(this, req,resp);
        } catch (Exception e) {
            //此处继续抛出异常，最终抛给了TransactionFilter
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
