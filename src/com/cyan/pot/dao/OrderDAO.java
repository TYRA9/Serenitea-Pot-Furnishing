package com.cyan.pot.dao;

import com.cyan.pot.domain.Order;

import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public interface OrderDAO {
    public abstract int saveOrder(Order order);
    List<Order> queryAllOrders();
    List<Order> queryOrdersByUid(Integer uid);
}
