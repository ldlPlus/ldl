package plus.ldl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.domain.Account;
import plus.ldl.service.AccountService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月24日  10:01
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv) throws Exception {
        List<Account> accounts = accountService.findAll();
        mv.addObject("accounts", accounts);
        mv.setViewName("account-list");
        return mv;
    }


    @PostMapping("/save")
    public String save(Account account) throws Exception {
        accountService.save(account);
        return "redirect:/account/findAll";
    }

}
