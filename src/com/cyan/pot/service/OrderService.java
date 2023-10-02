package com.cyan.pot.service;

import com.cyan.pot.domain.CartItem;
import com.cyan.pot.domain.Order;
import com.cyan.pot.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public interface OrderService {
    //最终要返回保存成功的订单的订单号
    public abstract String saveOrder(BigDecimal sums, List<CartItem> cartItems, Integer uid);

    List<Order> queryAllOrders();

    List<Order> queryOrdersByUid(Integer uid);

    List<OrderItem> queryOrderItems(String orderNumber);
}
