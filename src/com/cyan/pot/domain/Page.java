package com.cyan.pot.domain;

import java.util.List;

/**
 *  这是一个特殊的JavaBean类（一个抽象的数据模型）

    Page————用于实现分页的数据模型，包含了分页相关的各种信息。
    Page本质是一个JavaBean类.
    因为不确定具体分页显示的对象，因此给Page类一个泛型T
 */
public class Page<T> {
    //定义静态常量
    private static final Integer PAGESIZE_FINAL = 4;


    //recordSum表示总共的记录条数
    private Integer recordSum;
    //pageSize表示每页分页显示内容的条数(默认为4)
    private Integer pageSize = PAGESIZE_FINAL;
    //pageAmount表示分页的总数量 (pageAmount = recordSum / pageSize;)
    private Integer pageAmount;
    //pageNumber表示当前显示的页码
    private Integer pageNumber;
    //具体要显示的对象
    private List<T> items;
    //分页导航的字符串
    private String url;

    public Integer getRecordSum() {
        return recordSum;
    }

    public void setRecordSum(Integer recordSum) {
        this.recordSum = recordSum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(Integer pageAmount) {
        this.pageAmount = pageAmount;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
