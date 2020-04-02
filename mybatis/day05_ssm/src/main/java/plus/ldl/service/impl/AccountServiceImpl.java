package plus.ldl.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.ldl.domain.Account;
import plus.ldl.mapper.AccountMapper;
import plus.ldl.service.AccountService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月24日  10:01
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @Override
    public void save(Account account) {
        accountMapper.save(account);
    }
}
