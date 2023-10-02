package com.cyan.pot.service.impl;

import com.cyan.pot.dao.ShoppingCartDAO;
import com.cyan.pot.dao.impl.ShoppingCartDAOImpl;
import com.cyan.pot.domain.CartItem;
import com.cyan.pot.service.ShoppingCartService;

import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class ShoppingCartServiceImpl implements ShoppingCartService {
    //Service层 调用 DAO层
    private ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAOImpl();

    /**
     * @return : 返回已加入到购物车中的所有摆设。
     */
    @Override
    public List<CartItem> queryCartItemsByUid(Integer uid) {
        return shoppingCartDAO.queryCartItemsByUid(uid);
    }

    /**
     * @return : 根据fid和uid查询购物车中指定的一条摆设。
     */
    @Override
    public CartItem querySpecificCartItemByFidAndUid(Integer fid, Integer uid) {
        return shoppingCartDAO.querySpecificCartItemByFidAndUid(fid, uid);
    }

    /**
     * @return : 向购物车中添加指定的摆设。
     */
    @Override
    public int addCartItem(CartItem cartItem, Integer uid) {
        int affectedRows = 0;

        //查看该摆设是否已经被添加到购物车中
        if (shoppingCartDAO.querySpecificCartItemByFidAndUid(cartItem.getFid(), uid) != null) {
            //如果之前就添加过了，调用相应方法修改购物车信息即可
            affectedRows = shoppingCartDAO.updateCartItem(cartItem, uid);
        } else {
            //如果之前没有添加该摆设到购物车中，则直接添加即可。
            affectedRows = shoppingCartDAO.addCartItem(cartItem, uid);
        }

        return affectedRows;
    }

    /**(该方法暂时没有勇武之地; Service层的addCartItem方法中已经直接调用了DAO层的addCartItem)
     * @return : 该方法服务于addCartItem方法，即满足首页面对同一摆设的重复添加操作。
     */
    @Override
    public int updateCartItem(CartItem cartItem, Integer uid) {
        int affectedRows = 0;
        affectedRows = shoppingCartDAO.updateCartItem(cartItem, uid);

        return affectedRows;
    }

    /**
     * @return : 该方法用于实现————在购物车页面中，通过修改某一条目的cnt，而引起的对应totalPrice的变化。
     */
    @Override
    public int updateCntAndTotalPrice(CartItem cartItem, Integer uid) {
        int affectedRows = 0;
        affectedRows = shoppingCartDAO.updateCntAndTotalPrice(cartItem, uid);

        return affectedRows;
    }

    /**
     * @return : 该方法用于————根据fid和uid，删除购物车中指定的摆设。返回表中受影响的行数。
     */
    @Override
    public int deleteCartItem(Integer fid, Integer uid) {
        int affectedRows = 0;
        affectedRows = shoppingCartDAO.deleteCartItem(fid, uid);

        return affectedRows;
    }

    /**
     * @return : 该方法根据用户的id(即传入的uid)，删除该用户购物车中的所有商品。
     */
    @Override
    public int deleteAllCartItems(Integer uid) {
        int affectedRows = 0;
        affectedRows = shoppingCartDAO.deleteAllCartItems(uid);

        return affectedRows;
    }
}
