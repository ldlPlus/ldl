package com.itheima.controller;

import com.itheima.config.SpringConfiguration;
import com.itheima.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AccountController {
    public static void main(String[] args) {
        // ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AccountService accountService = app.getBean("accountService", AccountService.class);
        accountService.transfer("tom", "lucy", -500);
    }
}
