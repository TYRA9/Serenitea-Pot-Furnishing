package com.cyan.pot.domain;

import java.math.BigDecimal;

/**
    op数据库中order_item表对应的JavaBean类
 */
public class OrderItem {
    private Integer id;
    private String name;
    private BigDecimal unitPrice;
    private Integer cnt;
    private BigDecimal totalPrice;
    private String orderNumber;

    public OrderItem() {
    }
    public OrderItem(Integer id, String name, BigDecimal unitPrice, Integer cnt, BigDecimal totalPrice, String orderNumber) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.cnt = cnt;
        this.totalPrice = totalPrice;
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", cnt=" + cnt +
                ", totalPrice=" + totalPrice +
                ", orderNumber='" + orderNumber + '\'' +
                '}';
    }
}
