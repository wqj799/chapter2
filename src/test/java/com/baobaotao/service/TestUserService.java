package com.baobaotao.service;

import com.baobaotao.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by WQJ on 6/18/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        Assert.assertTrue(b1);
        Assert.assertFalse(b2);
    }

    @Test
    public void findUserName() {
        User user = userService.findUserByUserName("admin");
        Assert.assertEquals(user.getUserName(), "admin");
    }
}
