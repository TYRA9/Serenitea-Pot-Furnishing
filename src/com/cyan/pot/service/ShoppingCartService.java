package com.cyan.pot.service;

import com.cyan.pot.domain.CartItem;

import java.util.List;

public interface ShoppingCartService {
    public abstract List<CartItem> queryCartItemsByUid(Integer uid);;

    CartItem querySpecificCartItemByFidAndUid(Integer fid, Integer uid);

    int addCartItem(CartItem cartItem, Integer uid);

    int updateCartItem(CartItem cartItem, Integer uid);

    int updateCntAndTotalPrice(CartItem cartItem, Integer uid);

    int deleteCartItem(Integer fid, Integer uid);

    int deleteAllCartItems(Integer uid);
}
