package com.cyan.pot.dao.impl;

import com.cyan.pot.dao.BasicDAO;
import com.cyan.pot.dao.OrderItemDAO;
import com.cyan.pot.domain.OrderItem;

import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {
    /**
        为OrderService的saveOrder方法提供服务，用于将购物车中的信息保存在订单项中。
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO `order_item` " +
                        "VALUES " +
                        "(NULL, ?,?,?,?,?);";

        return update(sql, orderItem.getName(),orderItem.getUnitPrice(),orderItem.getCnt(),
                orderItem.getTotalPrice(), orderItem.getOrderNumber());
    }

    /**
        查询指定订单的所有订单项，并以ArrayList的形式返回。
     */
    @Override
    public List<OrderItem> queryOrderItems(String orderNumber) {
        String sql = "SELECT `id`,`name`,`unit_price` AS `unitPrice`,`cnt`," +
                        "`total_price` AS `totalPrice`,`order_number` AS `orderNumber` " +
                        "FROM `order_item` WHERE `order_number` = ?;";

        return queryMultiply(sql, OrderItem.class, orderNumber);
    }
}
