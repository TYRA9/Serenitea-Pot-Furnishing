package com.cyan.pot.dao.impl;

import com.cyan.pot.dao.BasicDAO;
import com.cyan.pot.dao.PotUserDAO;
import com.cyan.pot.domain.PotUser;

public class PotUserDAOImpl extends BasicDAO<PotUser> implements PotUserDAO {
    /**
        @function : 根据传入的用户名检索对应的用户；若找不到返回null
     */
    @Override
    public PotUser queryPotUserByUsername(String username) {
        String sql = "SELECT `id`, `username`, `password`, `email` \n" +
                "\t\tFROM `pot_user`\n" +
                "\t\tWHERE `username` = ?;";

        return querySingle(sql, PotUser.class, username);
    }

    /**
        @function : 保存注册的用户到数据库的pot_user表中；返回受影响的行数
     */
    @Override
    public int savePotUser(PotUser potUser) {
        //注意SQL语句在IDEA中的格式(尤其注意空格)
        String sql = "INSERT INTO pot_user " +
                        "VALUES(NULL, ?, MD5(?), ?);";

        int affectedRows = update(sql, potUser.getUsername(), potUser.getPassword(), potUser.getEmail());
        return affectedRows;
    }

    /**
     * @function : 根据登录传入的用户名和密码到数据库的pot_user表中检索用户，找不到就返回null
     */
    @Override
    public PotUser queryPotUserByUsernameAndPassword(String username, String password) {
        //注意密码要使用MD5方法加密
        String sql = "SELECT `id`,`username`,`password`,`email` " +
                        "FROM `pot_user` " +
                        "WHERE username = ? AND password = MD5(?);";

        return querySingle(sql, PotUser.class, username, password);
    }
}
