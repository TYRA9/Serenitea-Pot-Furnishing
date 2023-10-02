package com.cyan.pot.service.impl;

import com.cyan.pot.dao.FurnishingDAO;
import com.cyan.pot.dao.impl.FurnishingDAOImpl;
import com.cyan.pot.domain.Furnishing;
import com.cyan.pot.domain.Page;
import com.cyan.pot.service.FurnishingService;

import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class FurnishingServiceImpl implements FurnishingService {
    //Service层 调用 DAO层
    private FurnishingDAO furnishingDAO = new FurnishingDAOImpl();

    /**
     * @return : 返回furnishing表中保存的所有摆设，以提供给摆设管理页面来展示
     */
    @Override
    public List<Furnishing> queryFurnishings() {
        return furnishingDAO.queryFurnishings();
    }

    /**
        @return : 根据传入的id返回对应的摆设对象，用于furn_update页面进行回显
     */
    @Override
    public Furnishing queryFurnishingById(Integer id) {
        return furnishingDAO.queryFurnishingById(id);
    }

    /**
     * @return : 向furnishing表中添加新的摆设，返回表受影响的行数。
     */
    @Override
    public int addFurnishing(Furnishing furnishing) {
        int affectedRows = furnishingDAO.addFurnishing(furnishing);
        return affectedRows;
    }

    /**
     * @return : 根据传入的摆设id从furnishing表中删除指定的摆设，返回表受影响的行数。
     */
    @Override
    public int deleteFurnishing(Integer id) {
        int affectedRows = furnishingDAO.deleteFurnishing(id);
        return affectedRows;
    }

    /**
     * @return : 前端页面将要修改的内容封装到furnishing对象中，DAO层会根据该对象封装的信息
     *              完成对对应表的修改操作。
     */
    @Override
    public int updateFurnishing(Furnishing furnishing) {
        int affectedRows = furnishingDAO.updateFurnishing(furnishing);

        return affectedRows;
    }

    //=================分页相关方法开始=================
    /**
     * (FurnishingServlet的paging方法中使用)
     * @param pageNumber : 当前要显示的分页的页码
     * @param rows : 每页要显示的行数
     * @return : 返回封装好的page对象
     * @PS : 注意！！！——— 此处Service层的getPage方法同时调用了DAO层的多个方法，因此
     *          形参也不同。
     */
    @Override
    public Page<Furnishing> getPage(int pageNumber, int rows) {
        //先创建一个要返回的Page对象
        Page<Furnishing> page = new Page<>();

        //初始化Page对象
        int recordSum = furnishingDAO.getRecordSum();
        page.setRecordSum(recordSum);
        page.setPageSize(rows);
            //计算pageAmount
        int pageAmount = recordSum / rows;
        if (recordSum % rows > 0) {
            pageAmount += 1;
        }
        page.setPageAmount(pageAmount);
        page.setPageNumber(pageNumber);
            //计算start(该start服务于DAO层的getPageItems方法的形参列表)。
        int start = rows * (pageNumber - 1);
        List<Furnishing> pageItems = furnishingDAO.getPageItems(start, rows);
        page.setItems(pageItems);

        //url与分页导航相关
        //page.setUrl();


        return page;
    }
    /**
     * @return : 返回根据用户搜索的name 值查询到的摆设对象。
     *              注意联系此方法与getPage(...)方法的不同。
     */
    @Override
    public Page<Furnishing> getPageByName(int pageNumber, int rows, String name) {
        //先创建一个要返回的Page对象
        Page<Furnishing> page = new Page<>();

        //初始化Page对象
        int recordSum = furnishingDAO.getRecordSumByName(name);
        page.setRecordSum(recordSum);
        page.setPageSize(rows);
        //计算pageAmount (注意对不足rows的最后一页的处理)
        int pageAmount = recordSum / rows;
        if (recordSum % rows > 0) {
            pageAmount += 1;
        }
        page.setPageAmount(pageAmount);
        page.setPageNumber(pageNumber);
        //计算start(该start服务于DAO层的getPageItemsByName方法的形参列表)。
            //对非法页码进行处理，防止SQL错误。
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        int start = rows * (pageNumber - 1);

        List<Furnishing> pageItems = furnishingDAO.getPageItemsByName(start, rows, name);
        page.setItems(pageItems);

        //url与分页导航相关(具体使用见PresentToUsersServlet的pagingByName)
        //page.setUrl();

        return page;
    }
    //=================分页相关方法结束=================
}
