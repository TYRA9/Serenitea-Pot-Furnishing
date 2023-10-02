package com.cyan.pot.dao;

import com.cyan.pot.domain.OrderItem;

import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public interface OrderItemDAO {
    public abstract int saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItems(String orderNumber);
}
