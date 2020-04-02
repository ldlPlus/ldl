package plus.ldl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import plus.ldl.entity.Account;
import plus.ldl.service.AccountService;

// import org.springframework.web.bind.annotation.*;

/**
 * (Account)表控制层
 *
 * @author ldl.plus
 * @since 2020-03-12 14:36:22
 */
// @RestController
// @RequestMapping("account")

public class AccountController {
    /**
     * 服务对象
     */
    @Autowired
    private AccountService accountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    // @GetMapping("selectOne")
    public Account selectOne(Integer id) {
        return accountService.queryById(id);
    }

    public static void main(String[] args) {

        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService = app.getBean("accountService", AccountService.class);

        Account zhangsan = new Account();
        zhangsan.setId(1);
        zhangsan.setName("zhangsan");
        zhangsan.setBalance(+100);

        Account wangwu = new Account();
        wangwu.setId(3);
        wangwu.setName("wangwu");
        wangwu.setBalance(-100);

        Account zhang = accountService.update(zhangsan);
        Account wang = accountService.update(wangwu);
        System.out.println("wang = " + wang.getBalance());
        System.out.println("zhang = " + zhang.getBalance());
    }

}