package com.cyan.pot.service.impl;

import com.cyan.pot.dao.PotUserDAO;
import com.cyan.pot.dao.impl.PotUserDAOImpl;
import com.cyan.pot.domain.PotUser;
import com.cyan.pot.service.PotUserService;

public class PotUserServiceImpl implements PotUserService {
    //多态
    private PotUserDAO potUserDAO = new PotUserDAOImpl();

    /**
     * @function : 进行用户注册(Service层调用DAO层)
     */
    @Override
    public boolean registerPotUser(PotUser potUser) {
        int affectedRows = potUserDAO.savePotUser(potUser);
        return affectedRows == 1 ? true : false;
    }

    /**
     * @function : 根据用户名判断该用户是否已经存在
     */
    @Override
    public boolean isExistsByUsername(String username) {
        PotUser potUser = potUserDAO.queryPotUserByUsername(username);
        return potUser == null ? false : true;
    }

    /**
     * @function : 以用户传入的用户名和密码构建PotUser对象作为形参，扩展性更强。
     *              仍然是Service层 --> 调用  DAO层
     */
    @Override
    public PotUser login(PotUser potUser) {
        PotUser user = potUserDAO.queryPotUserByUsernameAndPassword(potUser.getUsername(), potUser.getPassword());
        return user;
    }
}
