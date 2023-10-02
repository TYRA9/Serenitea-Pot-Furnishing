package com.cyan.pot.domain;

import java.math.BigDecimal;

/**
    op数据库中furnishing表对应的JavaBean
 */
public class Furnishing {
    private Integer id;
    private String name;
    private String enterprise;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String imgPath = "resources/images/product-image/default.jpg";;

    public Furnishing() {
    }

    public Furnishing(Integer id, String name, String enterprise, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.enterprise = enterprise;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        if (!(null == imgPath || "".equals(imgPath))) {
            this.imgPath = imgPath;
        }
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

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Furnishing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
