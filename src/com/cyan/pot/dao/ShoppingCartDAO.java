package com.cyan.pot.dao;

import com.cyan.pot.domain.CartItem;

import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public interface ShoppingCartDAO {
    public abstract List<CartItem> queryCartItemsByUid(Integer uid);

    /*
        id指当前cartItem在shopping_cart表中的唯一id;
        uid指添加该cartItem的用户的唯一id。
     */
    CartItem querySpecificCartItemByFidAndUid(Integer fid, Integer uid);

    int addCartItem(CartItem cartItem, Integer uid);

    int updateCartItem(CartItem cartItem, Integer uid);

    int updateCntAndTotalPrice(CartItem cartItem, Integer uid);

    int deleteCartItem(Integer fid, Integer uid);

    int deleteAllCartItems(Integer uid);
}
