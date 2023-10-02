package com.cyan.pot.service.impl;

import com.cyan.pot.dao.PotManagerDAO;
import com.cyan.pot.dao.impl.PotManagerDAOImpl;
import com.cyan.pot.domain.PotManager;
import com.cyan.pot.service.PotManagerService;

public class PotManagerServiceImpl implements PotManagerService {
    //仍然是Service层 调用 DAO层
    private PotManagerDAO potManagerDAO = new PotManagerDAOImpl();

    /**
     * @function : 以传入的用户名和密码构建PotManager对象作为形参，扩展性更强。
     */
    @Override
    public PotManager login(PotManager potManager) {
        PotManager manager = potManagerDAO.queryPotManagerByUsernameAndPassword(potManager.getUsername(), potManager.getPassword());
        return manager;
    }
}
