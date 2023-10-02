package com.cyan.pot.web;

import com.cyan.pot.domain.*;
import com.cyan.pot.service.OrderService;
import com.cyan.pot.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Web层下的OrderServlet，
 */
@WebServlet(urlPatterns={"/orderServlet"})
public class OrderServlet extends BasicServlet{
    //Web层调用Servlet层
    private OrderService orderService = new OrderServiceImpl();

    //生成具体订单的方法
    public void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取session域中的数据
        HttpSession httpSession = req.getSession();

        BigDecimal totalPrice = (BigDecimal) httpSession.getAttribute("totalPrice");
        List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute("cartItems");
        PotUser potUser = (PotUser) httpSession.getAttribute("potUser");
        Integer uid = potUser.getId();

        //后端————数据校验, 如果不满足就返回首页(前端已经对totalPrice和cartItems进行了校验)
        if (totalPrice.equals(0) || totalPrice.intValue() == 0) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/views/user/index.jsp");
        }
        if (cartItems == null || cartItems.size() == 0) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/views/user/index.jsp");
        }
        if (potUser == null) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/views/user/index.jsp");
        }

        //调用orderService中的方法，完成四件事
        String orderNumber = orderService.saveOrder(totalPrice, cartItems, uid);

        //将生成的订单号存入session域中，并请求重定向到checkout.jsp页面
        httpSession.setAttribute("orderNumber", orderNumber);
        resp.sendRedirect(this.getServletContext().getContextPath() + "/views/user/checkout.jsp");
    }

    //该方法用于————管理员查看所有订单
    public void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer mgrId = Integer.valueOf(req.getParameter("mgrId"));
        List<Order> orders = orderService.queryAllOrders();

        req.setAttribute("orders", orders);
        req.setAttribute("mgrId", mgrId);
        req.getRequestDispatcher("/views/user/order.jsp").forward(req,resp);
    }

    //该方法用于————普通用户查看自己对应的所有订单
    public void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //在Web层的servlet中获取session域中的数据。
        HttpSession httpSession = req.getSession();
        PotUser potUser = (PotUser) httpSession.getAttribute("potUser");
        Integer uid = potUser.getId();

        List<Order> myOrders = orderService.queryOrdersByUid(uid);

        //httpSession.setAttribute("myOrders", myOrders);
        //resp.sendRedirect(this.getServletContext().getContextPath() + "/views/user/order.jsp");
        req.setAttribute("myOrders", myOrders);

        /*
            未涉及到数据库数据的变化，只是查询，考虑使用request域 + 请求转发。
            注意———如果使用请求重定向，就不能将数据放在request域中，因为请求重定向是两次请求。
         */
        req.getRequestDispatcher("/views/user/order.jsp").forward(req,resp);
    }

    //管理员和普通用户共用————查看指定订单的具体订单条目/订单项
    public void showOrderItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderNumber = req.getParameter("orderNumber");
        String mgrId = req.getParameter("mgrId");

        BigDecimal orderItemsTotalPrice = new BigDecimal(0);
        List<OrderItem> orderItems = orderService.queryOrderItems(orderNumber);
        for (OrderItem orderItem : orderItems) {
            orderItemsTotalPrice = orderItemsTotalPrice.add(orderItem.getTotalPrice());
        }

        /*
            将获取到的封装有订单项的集合放入request域中，并请求转发到相应页面.
            如果这是管理员发出的请求，mgrId不为空；如果这时普通用户发出的请求，mgrId为空。
         */
        req.setAttribute("orderNumber", orderNumber);
        req.setAttribute("orderItems", orderItems);
        req.setAttribute("orderItemsTotalPrice", orderItemsTotalPrice);
        req.setAttribute("mgrId", mgrId);
        req.getRequestDispatcher("/views/user/order_detail.jsp").forward(req,resp);
    }
}
