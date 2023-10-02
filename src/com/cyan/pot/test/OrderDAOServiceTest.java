package com.cyan.pot.test;

import com.cyan.pot.domain.CartItem;
import com.cyan.pot.service.OrderService;
import com.cyan.pot.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class OrderDAOServiceTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void testSaveOrder() {
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem(null,Integer.valueOf(11),"摆设5",
                BigDecimal.valueOf(10000),Integer.valueOf(1),BigDecimal.valueOf(10000),
                Integer.valueOf(3)));

        String orderNumber = orderService.saveOrder(BigDecimal.valueOf(10000), cartItemList, Integer.valueOf(3));
        System.out.println("orderNumber = " + orderNumber);
    }
}
