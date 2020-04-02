package com.itheima.test;

import com.itheima.prictice.config.SpringConfiguration;
import com.itheima.prictice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AopTest {
    @Autowired
    private UserService userService;

    @Test
    public void testFind() {
        userService.find(1);
    }

    @Test
    public void testSave() {

        userService.save();
    }

    @Test
    public void testUpdate() {

        userService.update();
    }

    @Test
    public void testDelete() {
        userService.delete(1);
    }
}
