package com.cyan.pot.dao.impl;

import com.cyan.pot.dao.BasicDAO;
import com.cyan.pot.dao.FurnishingDAO;
import com.cyan.pot.domain.Furnishing;

import java.util.List;

public class FurnishingDAOImpl extends BasicDAO<Furnishing> implements FurnishingDAO {
    /**
     * 查询op数据库中的furnishing表，并以List集合的形式返回查询到的所有摆设。
     */
    @Override
    public List<Furnishing> queryFurnishings() {
        String sql = "SELECT `id`,`name`,`enterprise`,`price`,`sales`,`stock`,`img_path` AS `imgPath` " +
                "FROM furnishing;";

        List<Furnishing> furnishings = queryMultiply(sql, Furnishing.class);

        return furnishings;
    }

    /**
     * 根据id，查询op数据库中的furnishing表中响应的记录，并以Furnishing对象的形式返回记录
     */
    @Override
    public Furnishing queryFurnishingById(Integer id) {
        String sql = "SELECT `id`,`name`,`enterprise`,`price`,`sales`,`stock`,`img_path` AS `imgPath` " +
                "FROM furnishing " +
                "WHERE `id` = ?;";

        Furnishing furnishing = querySingle(sql, Furnishing.class, id);

        return furnishing;
    }

    /**
        向op数据库中的furnishing表中添加记录，所给的字段由形参构建的摆设对象指定。
     */
    @Override
    public int addFurnishing(Furnishing furnishing) {
        int affectedRows = 0;
        String sql = "INSERT INTO furnishing " +
                        "VALUES " +
                        "(NULL, ?, ?, ?, ?, ?, ?);";

        affectedRows = update(sql,furnishing.getName(),furnishing.getEnterprise(),furnishing.getPrice(),furnishing.getSales(),furnishing.getStock(),furnishing.getImgPath());

        return affectedRows;
    }

    /**
        从op数据库中的furnishing表中删除记录，根据id确定要删除的记录
        (id来自furn_manage页面中的超链接，借助EL表达式来实现)。
     */
    @Override
    public int deleteFurnishing(Integer id) {
        int affectedRows = 0;
        String sql = "DELETE FROM furnishing " +
                        "WHERE id = ?;";
        affectedRows = update(sql, id);

        return affectedRows;
    }

    /**
        修改op数据库中furnishing表的记录;
        判断条件的id以及具体要修改的数据，均通过形参Furnishing对象的getter方法来获取。
     */
    @Override
    public int updateFurnishing(Furnishing furnishing) {
        String sql = "UPDATE furnishing " +
                "SET `name` = ?, `enterprise` = ?, `price` = ?, " +
                    "`sales` = ?, `stock` = ?, img_path = ? " +
                "WHERE `id` = ?;";
        return update(sql, furnishing.getName(),furnishing.getEnterprise(),
                furnishing.getPrice(),furnishing.getSales(),furnishing.getStock(),
                furnishing.getImgPath(),furnishing.getId());
    }

    //=================分页相关方法开始=================
    /**
        统计op数据库furnishing表中总共的记录条数，并以intValue返回。
     */
    @Override
    public int getRecordSum() {
        String sql = "SELECT COUNT(*) FROM `furnishing`;";

        return ((Number)queryScalar(sql)).intValue();
    }
    /**
        (FurnishingServiceImpl的getPage方法中使用，最终要服务于摆设后台管理页面)
        利用分页查询语句(LIMIT start, rows;) 来获取指定的多条摆设记录。
        拾遗 : start，表示从(start + 1)行记录开始展示；
                rows, 表示每个分页显示的记录数。
     */
    @Override
    public List<Furnishing> getPageItems(int start, int rows) {
        String sql = "SELECT `id`,`name`,`enterprise`,`price`,`sales`,`stock`,`img_path` AS `imgPath` " +
                        "FROM furnishing " +
                        "LIMIT ?, ?;";

        return queryMultiply(sql, Furnishing.class, start,rows);
    }

    /**
        根据name搜索并统计op数据库furnishing表中总共的记录条数，并以intValue返回。
        注意此处采用了模糊查询！
     */
    @Override
    public int getRecordSumByName(String name) {
        String sql = "SELECT COUNT(*) FROM `furnishing` " +
                        "WHERE `name` LIKE ? AND `stock` > 0;";

        /*
            注意，COUNT(*)统计函数返回的数据是单行单列，
            对应于BasicDAO中的queryScalar(...)
         */

        return ((Number)queryScalar(sql, ("%" + name + "%"))).intValue();
    }
    /**
         根据传入的name值进行模糊查询，并利用分页查询LIMIT，返回符合搜索条件的所有摆设(记录)
     */
    @Override
    public List<Furnishing> getPageItemsByName(int start, int rows, String name) {
        String sql = "SELECT `id`,`name`,`enterprise`,`price`,`sales`,`stock`,`img_path` AS `imgPath` " +
                "FROM furnishing " +
                "WHERE `name` LIKE ? AND `stock` > 0 " +
                "LIMIT ?, ?;";

        return queryMultiply(sql, Furnishing.class, ("%" + name + "%"),start,rows);
    }
    //=================分页相关方法结束=================
}
