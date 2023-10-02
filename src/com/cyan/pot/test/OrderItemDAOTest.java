package com.cyan.pot.test;

import com.cyan.pot.dao.OrderItemDAO;
import com.cyan.pot.dao.impl.OrderItemDAOImpl;
import com.cyan.pot.domain.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class OrderItemDAOTest {
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Test
    public void testSaveOrderItem() {
        OrderItem orderItem = new OrderItem(null, "摆设1",
                BigDecimal.valueOf(10000), Integer.valueOf(2),
                BigDecimal.valueOf(20000), "test01");

        int affectedRows = orderItemDAO.saveOrderItem(orderItem);

        System.out.println(affectedRows);
    }
}
