package com.cyan.pot.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
    op数据库中order表对应的JavaBean类
 */
public class Order {
    private String number;
    private LocalDateTime dateTime;
    private BigDecimal sums;
    private Integer status;
    private Integer uid;

    public Order() {
    }
    public Order(String number, LocalDateTime dateTime, BigDecimal sums, Integer status, Integer uid) {
        this.number = number;
        this.dateTime = dateTime;
        this.sums = sums;
        this.status = status;
        this.uid = uid;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getSums() {
        return sums;
    }
    public void setSums(BigDecimal sums) {
        this.sums = sums;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number='" + number + '\'' +
                ", dateTime=" + dateTime +
                ", sums=" + sums +
                ", status=" + status +
                ", uid=" + uid +
                '}';
    }
}
