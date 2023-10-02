package com.cyan.pot.dao;

import com.cyan.pot.domain.PotManager;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public interface PotManagerDAO {
    PotManager queryPotManagerByUsernameAndPassword(String username, String password);
}
