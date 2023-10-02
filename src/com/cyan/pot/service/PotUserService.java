package com.cyan.pot.service;

import com.cyan.pot.domain.PotUser;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public interface PotUserService {
    public abstract boolean registerPotUser(PotUser potUser);

    boolean isExistsByUsername(String username);

    PotUser login(PotUser potUser);
}
