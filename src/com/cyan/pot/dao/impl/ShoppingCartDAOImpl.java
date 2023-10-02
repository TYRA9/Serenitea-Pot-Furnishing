package com.cyan.pot.dao.impl;

import com.cyan.pot.dao.BasicDAO;
import com.cyan.pot.dao.ShoppingCartDAO;
import com.cyan.pot.domain.CartItem;

import java.util.List;

public class ShoppingCartDAOImpl extends BasicDAO<CartItem> implements ShoppingCartDAO {
    /**
     * 根据uid从购物车表中查找到该用户购物车中的所有摆设。
     */
    @Override
    public List<CartItem> queryCartItemsByUid(Integer uid) {
        String sql = "SELECT `id`,`fid`,`name`,`unit_price` AS `unitPrice`,`cnt`,`total_price` AS `totalPrice`,`uid` " +
                        "FROM `shopping_cart` " +
                        "WHERE `uid` = ?;";

        List<CartItem> cartItems = queryMultiply(sql, CartItem.class, uid);
        return cartItems;
    }

    /**
     * 根据fid和uid确定购物车表中的唯一摆设，返回该摆设。
     * (该方法返回的CartItem对象本质是一个临时对象，用于更新购物车条目)
     */
    @Override
    public CartItem querySpecificCartItemByFidAndUid(Integer fid, Integer uid) {
        String sql = "SELECT `id`,`fid`,`name`,`unit_price` AS `unitPrice`,`cnt`,`total_price` AS `totalPrice`,`uid` " +
                        "FROM `shopping_cart` " +
                        "WHERE `fid` = ? AND `uid` = ?";

        CartItem cartItem = querySingle(sql, CartItem.class, fid,uid);
        return cartItem;
    }

    /**
     * 向shopping_cart表中添加购物车条目
     */
    @Override
    public int addCartItem(CartItem cartItem, Integer uid) {
        int affectedRows = 0;
        String sql = "INSERT INTO `shopping_cart` " +
                        "VALUE " +
                        "(NULL, ?, ?, ?, ?, ?, ?);";

        affectedRows = update(sql, cartItem.getFid(),cartItem.getName(),cartItem.getUnitPrice(),cartItem.getCnt(),cartItem.getTotalPrice(),uid);
        return affectedRows;
    }

    /**
        用于“更新cartItem”的方法，实则是为Service层的addCartItem方法补充服务的(首页面逐个增一)。
     */
    @Override
    public int updateCartItem(CartItem cartItem, Integer uid) {
        int affectedRows = 0;
        String sql = "UPDATE `shopping_cart` " +
                        "SET `name` = ?,`unit_price` = ?,`cnt` = (cnt + 1),`total_price` = (`total_price` + ?) " +
                        "WHERE `fid` = ? AND `uid` = ?;";

        affectedRows = update(sql, cartItem.getName(),cartItem.getUnitPrice(),cartItem.getUnitPrice(),cartItem.getFid(),uid);
        return affectedRows;
    }

    /**
        该方法针对于购物车内部，修改cnt后引起了该条目的totalPrice的变化，该方法把这种变化应用于表中。
        再次强调，此处的CartItem是单独从shopping_cart表中取出的临时的对象,且cnt已经被修改过。
     */
    @Override
    public int updateCntAndTotalPrice(CartItem cartItem, Integer uid) {
        int affectedRows = 0;
        String sql = "UPDATE `shopping_cart` " +
                        "SET `cnt` = ?, `total_price` = ? " +
                        "WHERE `fid` = ? AND `uid` = ?;";

        affectedRows = update(sql, cartItem.getCnt(),cartItem.getTotalPrice(),cartItem.getFid(),uid);
        return affectedRows;
    }

    /**
        该方法用于删除购物车中指定的摆设
     */
    @Override
    public int deleteCartItem(Integer fid, Integer uid) {
        int affectedRows = 0;
        String sql = "DELETE FROM `shopping_cart` " +
                        "WHERE `fid` = ? AND `uid` = ?;";
        affectedRows = update(sql, fid,uid);
        return affectedRows;
    }

    /**
        该方法用于删除当前用户购物车中所有的摆设(清空购物车)
     */
    @Override
    public int deleteAllCartItems(Integer uid) {
        int affectedRows = 0;
        String sql = "DELETE FROM `shopping_cart` " +
                        "WHERE `uid` = ?;";
        affectedRows = update(sql, uid);
        return affectedRows;
    }
}
