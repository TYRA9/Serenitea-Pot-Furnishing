package com.cyan.pot.test;

import com.cyan.pot.domain.PotUser;
import com.cyan.pot.service.PotUserService;
import com.cyan.pot.service.impl.PotUserServiceImpl;
import org.junit.jupiter.api.Test;

public class PotUserServiceTest {
    PotUserService potUserService = new PotUserServiceImpl();
    @Test
    public void testIsExistsByUsername() {
        if (potUserService.isExistsByUsername("Cyand")) {
            System.out.println("存在的啦");
        } else {
            System.out.println("nonono");
        }
    }

    @Test
    public void testRegisterPotUser() {
        PotUser potUser = new PotUser(null, "Rain", "rainbow", "Rain@rain.com");
        if (potUserService.registerPotUser(potUser)) {
            System.out.println("注册成功了捏~");
        } else {
            System.out.println("nonono");
        }
    }

    @Test
    public void testLogin() {
        PotUser potUser = new PotUser(null, "Rain", "rainbow", "Kaiyu@kaiyu.com");
        if (potUserService.login(potUser) != null) {
            System.out.println("牛逼，登录成功!");
        } else {
            System.out.println("gun!");
        }
    }
}
