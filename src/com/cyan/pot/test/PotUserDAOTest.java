package com.cyan.pot.test;

import com.cyan.pot.dao.PotUserDAO;
import com.cyan.pot.dao.impl.PotUserDAOImpl;
import com.cyan.pot.domain.PotUser;
import org.junit.jupiter.api.Test;

public class PotUserDAOTest {
    private PotUserDAO potUserDAO = new PotUserDAOImpl();

    @Test
    public void test() {
        PotUser cyan = potUserDAO.queryPotUserByUsername("Cyan");
        System.out.println("cyan = " + cyan);

        int affectedRows =
                potUserDAO.savePotUser(new PotUser(null, "Five", "five55555", "Five@five.com"));

        System.out.println("affectedRows = " + affectedRows);

        PotUser five = potUserDAO.queryPotUserByUsername("Five");
        System.out.println("five = " + five);
    }

    @Test
    public void testQueryPotUserByUsernameAndPassword() {
        PotUser potUser = potUserDAO.queryPotUserByUsernameAndPassword("Cyan", "RA9Cyan");
        System.out.println("potUser = " + potUser);
    }
}
