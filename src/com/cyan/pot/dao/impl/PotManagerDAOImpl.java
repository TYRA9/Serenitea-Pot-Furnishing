package com.cyan.pot.dao.impl;

import com.cyan.pot.dao.BasicDAO;
import com.cyan.pot.dao.PotManagerDAO;
import com.cyan.pot.domain.PotManager;

public class PotManagerDAOImpl extends BasicDAO<PotManager> implements PotManagerDAO {
    /**
     @function : 根据登录传入的用户名和密码到数据库的pot_manager表中检索管理员，
                 找不到就返回null
     */
    @Override
    public PotManager queryPotManagerByUsernameAndPassword(String username, String password) {
        //密码要使用MD5加密
        String sql = "SELECT `id`,`username`,`password` FROM `pot_manager` " +
                        "WHERE username = ? AND password = MD5(?);";

        return querySingle(sql, PotManager.class, username, password);
    }
}
