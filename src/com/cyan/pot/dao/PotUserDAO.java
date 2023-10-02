package com.cyan.pot.dao;

import com.cyan.pot.domain.PotUser;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public interface PotUserDAO {
    public abstract PotUser queryPotUserByUsername(String username);

    int savePotUser(PotUser potUser);

    PotUser queryPotUserByUsernameAndPassword(String username, String password);
}
