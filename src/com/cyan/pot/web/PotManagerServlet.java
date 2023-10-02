package com.cyan.pot.web;

import com.cyan.pot.domain.PotManager;
import com.cyan.pot.service.PotManagerService;
import com.cyan.pot.service.impl.PotManagerServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
    Web层的PotManagerServlet———
    该servlet专用于处理与 “管理员” 相关的请求, eg : Administrator Login
 */
@WebServlet(urlPatterns = {"/potManagerServlet"})
public class PotManagerServlet extends BasicServlet{
    //Web层 调用 Service层
    private PotManagerService potManagerService = new PotManagerServiceImpl();

    //用于完成用户登录的方法
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取登录表单的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PotManager manager = new PotManager(null, username, password);
        PotManager potManager = potManagerService.login(manager);

        //2.根据“以表单数据构建的PotUser对象”，来验证用户是否登录成功
        if (potManager != null) {
            //若管理员成功登录，则请求转发到后台管理页面manage_menu.jsp
                //potManager对象放入session域中，以在各个管理页面显示管理员的username
            req.getSession().setAttribute("potManager", potManager);
                //单独在request域中放入一个mgrId属性，为在共用的order页面与普通用户作区分。
            req.setAttribute("mgrId", potManager.getId());

            RequestDispatcher requestDispatcher =
                    req.getRequestDispatcher("/views/manager/manage_menu.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            //说明该用户并不存在于pot_user表中
            //将错误的登录信息回显给浏览器(借助EL表达式)
            req.setAttribute("hint", "用户名或密码错误！");
            req.setAttribute("username", username);

            RequestDispatcher requestDispatcher =
                    req.getRequestDispatcher("/views/manager/manage_login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    //由管理员视角下发起(eg: order.jsp,furn_manage.jsp)，借助该方法转发到manage_menu.jsp页面
    public void transmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer mgrId = Integer.valueOf(req.getParameter("mgrId"));
        req.setAttribute("mgrId", mgrId);

        req.getRequestDispatcher("/views/manager/manage_menu.jsp").forward(req,resp);
    }

    //由管理员视角下的order.jsp页面发起，设法去order_detail页面
    public void transmitToDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer mgrId = Integer.valueOf(req.getParameter("mgrId"));
        String orderNumber = req.getParameter("orderNumber");

        req.getRequestDispatcher("/orderServlet?action=showOrderItems&orderNumber" + orderNumber + "&mgrId=" + mgrId).forward(req,resp);
    }
}
