package com.cyan.pot.web;

import com.cyan.pot.domain.CartItem;
import com.cyan.pot.service.ShoppingCartService;
import com.cyan.pot.service.impl.ShoppingCartServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Web层的CartServlet，用于处理购物车相关的请求。(针对于普通用户)
 */
@WebServlet(urlPatterns = {"/shoppingCartServlet"})
public class ShoppingCartServlet extends BasicServlet {
    //Web层 调用 Service层
    private ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();

    //向购物车中添加数据
    public void addCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CartItem cartItem = new CartItem();

        //仍然使用Apache提供的工具类BeanUtils完成对数据的封装。
        try {
            BeanUtils.populate(cartItem, req.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //if-else判断
        /*
            若检测到用户未登录，(uid是在首页面通过EL表达式的sessionScope对象传入的)
            就通过请求重定向，跳转到用户登录页面。
         */
        if (cartItem.getUid() == null || cartItem.getUid().intValue() == 0) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/views/user/login.jsp");
        } else {
            /*
                如果用户已经登录，就添加该摆设(完成购物车的添加)，
                !!!
                此处仍然利用分页查询思想，要求用户在第几页添加的商品————添加商品后就返回到第几页。
                !!!
                注意,一定要区分用户在首页添加商品和在购物车内通过修改按钮添加商品所走的servlet路径。
             */
            shoppingCartService.addCartItem(cartItem, cartItem.getUid());

            String pageNumber = req.getParameter("pageNumber");
            String rows = req.getParameter("rows");

            //请求重定向的URL由浏览器解析，因此必须要带上WEB工程上下文
            resp.sendRedirect(req.getContextPath() +
                    "/presentToUsersServlet?action=pagingByName&pageNumber=" + pageNumber +
                    "&rows=" + rows);
        }
    }

    //展示购物车(数据来自op数据库中的shopping_cart表)
    public void showCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer uid = null;

        String id = req.getParameter("uid");
        if (!(id == null || "".equals(id))) {
            uid = Integer.valueOf(id);
        }

        //若用户未登录，请求重定向到用户登录页面
        if (uid == null || uid.intValue() == 0) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/views/user/login.jsp");
        } else {
            //若用户已经登录，请求重定向到该用户的购物车界面
            List<CartItem> cartItems = shoppingCartService.queryCartItemsByUid(uid);

            BigDecimal totalPrice = new BigDecimal(0);
            for (CartItem cartItem : cartItems) {
                //此处使用BigDecimal的add方法后，必须赋值回去。
                totalPrice = totalPrice.add(cartItem.getTotalPrice());
            }
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("cartItems", cartItems);
            httpSession.setAttribute("totalPrice", totalPrice);
            resp.sendRedirect(this.getServletContext().getContextPath() + "/views/user/cart.jsp");
        }
    }

    //修改购物车中的摆设
    public void alterCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer fid = Integer.valueOf(req.getParameter("fid"));
        Integer uid = Integer.valueOf(req.getParameter("uid"));
        //先按CartItem对象的形式，取出要修改的条目.
        CartItem cartItem = shoppingCartService.querySpecificCartItemByFidAndUid(fid, uid);

        //修改这个临时对象的cnt 和 totalPrice (!!!需要设法得到实时的cnt)
        Integer cnt = Integer.valueOf(req.getParameter("cnt"));
        cartItem.setCnt(cnt);
        cartItem.setTotalPrice(cartItem.getUnitPrice().multiply(BigDecimal.valueOf(cartItem.getCnt())));

        //此处调用updateCntAndTotalPrice方法,修改购物车表中相应条目的cnt 和 total_price.
        shoppingCartService.updateCntAndTotalPrice(cartItem, uid);

        //重新计算购物车中所有商品的总的totalPrice，并重新放入session域中。
        HttpSession httpSession = req.getSession();
        httpSession.removeAttribute("totalPrice");
        httpSession.removeAttribute("cartItems");
        BigDecimal totalPrice = new BigDecimal(0);

            //由于数据库中的数据已经更改完毕，因此此处取出的cartItems，已经是最新的cartItems.
        List<CartItem> cartItems = shoppingCartService.queryCartItemsByUid(uid);
        for (CartItem item : cartItems) {
            //一定要将增加后的值赋值给原来的totalPrice，否则加了个寂寞。
            totalPrice = totalPrice.add(item.getTotalPrice());
        }

            //将最新的购物车信息和最新计算的totalPrice重新放入到session域中。
        httpSession.setAttribute("cartItems", cartItems);
        httpSession.setAttribute("totalPrice", totalPrice);

        resp.sendRedirect(this.getServletContext().getContextPath() + "/views/user/cart.jsp");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer fid = Integer.valueOf(req.getParameter("fid"));
        Integer uid = Integer.valueOf(req.getParameter("uid"));

        shoppingCartService.deleteCartItem(fid, uid);

        //删除成功后，请求重定向到showCart方法
        resp.sendRedirect(this.getServletContext().getContextPath() + "/shoppingCartServlet?action=showCart&uid=" + uid);
    }

    public void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer uid = Integer.valueOf(req.getParameter("uid"));

        shoppingCartService.deleteAllCartItems(uid);

        //清空后，请求重定向到showCart方法
        resp.sendRedirect(this.getServletContext().getContextPath() + "/shoppingCartServlet?action=showCart&uid=" + uid);
    }
}
