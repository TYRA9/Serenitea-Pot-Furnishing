package com.cyan.pot.dao;

import com.cyan.pot.domain.Furnishing;

import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public interface FurnishingDAO {
    public abstract List<Furnishing> queryFurnishings();

    Furnishing queryFurnishingById(Integer id);

    int addFurnishing(Furnishing furnishing);

    int deleteFurnishing(Integer id);

    int updateFurnishing(Furnishing furnishing);

    //分页相关方法开始
        //前俩服务于管理员后台
    int getRecordSum();
    List<Furnishing> getPageItems(int start, int rows);

        //后俩服务于用户前台
    int getRecordSumByName(String name);
    List<Furnishing> getPageItemsByName(int start, int rows, String name);
    //分页相关方法结束
}
