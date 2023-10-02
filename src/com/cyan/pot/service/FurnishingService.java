package com.cyan.pot.service;

import com.cyan.pot.domain.Furnishing;
import com.cyan.pot.domain.Page;

import java.util.List;

public interface FurnishingService {
    public abstract List<Furnishing> queryFurnishings();

    Furnishing queryFurnishingById(Integer id);

    int addFurnishing(Furnishing furnishing);

    int deleteFurnishing(Integer id);

    int updateFurnishing(Furnishing furnishing);

    //分页相关方法开始
    Page<Furnishing> getPage(int pageNumber, int rows);

    Page<Furnishing> getPageByName(int pageNumber, int rows, String name);
    //分页相关方法结束
}
