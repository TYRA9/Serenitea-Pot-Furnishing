package com.cyan.pot.test;

import com.cyan.pot.dao.OrderDAO;
import com.cyan.pot.dao.impl.OrderDAOImpl;
import com.cyan.pot.domain.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class OrderDAOTest {
    OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void testSaveOrder() {
        Order test01 = new Order("test01", LocalDateTime.now(), BigDecimal.valueOf(100000), Integer.valueOf(0), Integer.valueOf(3));

        int affectedRows = orderDAO.saveOrder(test01);
        System.out.println(affectedRows);
    }
}
