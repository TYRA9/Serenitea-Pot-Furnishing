package com.cyan.pot.service.impl;

import com.cyan.pot.dao.FurnishingDAO;
import com.cyan.pot.dao.OrderDAO;
import com.cyan.pot.dao.OrderItemDAO;
import com.cyan.pot.dao.ShoppingCartDAO;
import com.cyan.pot.dao.impl.FurnishingDAOImpl;
import com.cyan.pot.dao.impl.OrderDAOImpl;
import com.cyan.pot.dao.impl.OrderItemDAOImpl;
import com.cyan.pot.dao.impl.ShoppingCartDAOImpl;
import com.cyan.pot.domain.CartItem;
import com.cyan.pot.domain.Furnishing;
import com.cyan.pot.domain.Order;
import com.cyan.pot.domain.OrderItem;
import com.cyan.pot.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
    Service层的OrderServiceImpl，
    之前在DAO层定义的两个DAO————都是直接为该service服务的。[该service共调用了四个dao]
 */
public class OrderServiceImpl implements OrderService {
    //Service层 调用 DAO层
        //分层思想的实际应用(同一个service中可以调用多个不同的dao，完成复杂的业务)
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private FurnishingDAO furnishingDAO = new FurnishingDAOImpl();
    private ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAOImpl();

    /**
     @param sums : 当前购物车中商品的总价
     @param cartItems : 购物车中所有的商品
     @param uid : 当前下单的用户的id
        OrderServiceImpl的saveOrder方法要完成四件事————
        1> 生成一张订单，并将该订单保存到order表中。
        2> 将生成订单的全部订单项，依次保存到order_item表中。
        3> 修改finishing表中对应的sales和stock.
        4> 清空当前用户购物车中的内容(需要操作到shopping_cart表)
     */
    @Override
    public String saveOrder(BigDecimal sums, List<CartItem> cartItems, Integer uid) {
        //获取唯一的订单编号
        String number = System.currentTimeMillis() + "" + uid;

        //保存订单
        Order order = new Order(number,LocalDateTime.now(),sums, 0, uid);
        orderDAO.saveOrder(order);

        //通过遍历购物车中的条目，完成保存orderItem和更新furnishing两件事
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getUnitPrice(),
                    cartItem.getCnt(), cartItem.getTotalPrice(), number);
            orderItemDAO.saveOrderItem(orderItem);

            Furnishing furnishing = furnishingDAO.queryFurnishingById(cartItem.getFid());
            furnishing.setSales(furnishing.getSales() + cartItem.getCnt());
            furnishing.setStock(furnishing.getStock() - cartItem.getCnt());
            furnishingDAO.updateFurnishing(furnishing);
        }
        //清空当前用户的购物车
        shoppingCartDAO.deleteAllCartItems(uid);

        return number;
    }

    /**
        当管理员在管理页面使用"Order Admin"功能时，返回查询到的所有用户的所有订单
     */
    @Override
    public List<Order> queryAllOrders() {
        return orderDAO.queryAllOrders();
    }

    /**
        当用户在首页使用"Order Admin"功能时，返回当前用户相关的所有订单
     */
    @Override
    public List<Order> queryOrdersByUid(Integer uid) {
        return orderDAO.queryOrdersByUid(uid);
    }

    /**
        根据订单编号，返回该订单中包含的所有订单项。
     */
    @Override
    public List<OrderItem> queryOrderItems(String orderNumber) {
        return orderItemDAO.queryOrderItems(orderNumber);
    }
}
