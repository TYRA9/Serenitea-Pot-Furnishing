package com.cyan.pot.test;

import com.cyan.pot.domain.CartItem;
import com.cyan.pot.service.ShoppingCartService;
import com.cyan.pot.service.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class ShoppingCartServiceTest {
    private ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();

    @Test
    public void testQueryCartItemsByUid() {
        List<CartItem> cartItems = shoppingCartService.queryCartItemsByUid(3);
        for (CartItem cartItem : cartItems) {
            System.out.println("cartItem = " + cartItem);
        }
    }

    @Test
    public void testAddCartItem() {
        //CartItem cartItem = new CartItem(null, 9,"摆设3", new BigDecimal(4500), 2, new BigDecimal(9000), 3);
        CartItem cartItem = new CartItem(null, 10,"摆设4", new BigDecimal(4500), 2, new BigDecimal(9000), 3);

        int affectedRows = shoppingCartService.addCartItem(cartItem, cartItem.getUid());

        System.out.println("affectedRows = " + affectedRows);
    }

    @Test
    public void testQuerySpecificCartItemByIdAndUid() {
        CartItem cartItem1 = shoppingCartService.querySpecificCartItemByFidAndUid(Integer.valueOf(14), Integer.valueOf(3));
        System.out.println("cartItem1 = " + cartItem1);
    }

    @Test
    public void testUpdateCartItem() {
        CartItem cartItem = new CartItem(null, 14, "摆设1", new BigDecimal(1500), 10, new BigDecimal(15000), 3);
        int affectedRows = shoppingCartService.updateCartItem(cartItem, cartItem.getUid());

        System.out.println("affectedRows = " + affectedRows);
    }
}
