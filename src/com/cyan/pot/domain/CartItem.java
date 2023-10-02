package com.cyan.pot.domain;

import java.math.BigDecimal;

/**
    op数据库中shopping_cart表对应的JavaBean
 */
public class CartItem {
    private Integer id;             //条目在购物车中的id(自增长主键)
    private Integer fid;            //摆设本身在furnishing表中的id
    private String name;            //条目(摆设)的名字
    private BigDecimal unitPrice;   //条目(摆设)的单价
    private Integer cnt;            //条目(摆设)的数量
    private BigDecimal totalPrice;  //条目(摆设)的总价
    private Integer uid;            //条目(摆设)的总价(总价 = 单价 * 数量)

    public CartItem() {
    }
    public CartItem(Integer id, Integer fid, String name, BigDecimal unitPrice, Integer cnt, BigDecimal totalPrice, Integer uid) {
        this.id = id;
        this.fid = fid;
        this.name = name;
        this.unitPrice = unitPrice;
        this.cnt = cnt;
        this.totalPrice = totalPrice;
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getCnt() {
        return cnt;
    }
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", fid=" + fid +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", cnt=" + cnt +
                ", totalPrice=" + totalPrice +
                ", uid=" + uid +
                '}';
    }
}
