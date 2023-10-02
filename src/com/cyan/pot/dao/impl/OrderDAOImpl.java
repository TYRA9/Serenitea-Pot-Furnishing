package com.cyan.pot.dao.impl;

import com.cyan.pot.dao.BasicDAO;
import com.cyan.pot.dao.OrderDAO;
import com.cyan.pot.domain.Order;

import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {
    /**
        @function : 该方法保存订单记录到order表中。
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO `order` " +
                        "VALUES " +
                        "(?,?,?,?,?);";

        return update(sql, order.getNumber(),order.getDateTime(), order.getSums(),
                order.getStatus(), order.getUid());
    }

    /**
     @function : 该方法用于返回order表中所有用户的所有订单信息(管理员)
     */
    @Override
    public List<Order> queryAllOrders() {
        String sql = "SELECT `number`,`date_time` AS `dateTime`,`sums`,`status`,`uid` FROM `order`;";

        return queryMultiply(sql, Order.class);
    }

    /**
     @function : 该方法用于返回order表中指定用户的所有订单信息(用户)
     */
    @Override
    public List<Order> queryOrdersByUid(Integer uid) {
        String sql = "SELECT `number`,`date_time` AS `dateTime`,`sums`,`status`,`uid` FROM `order` " +
                        "WHERE `uid` = ?";

        return queryMultiply(sql, Order.class, uid);
    }
}
