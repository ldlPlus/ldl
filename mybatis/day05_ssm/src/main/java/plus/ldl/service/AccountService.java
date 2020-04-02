package plus.ldl.service;

import plus.ldl.domain.Account;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月24日  10:00
 */
public interface AccountService {
    List<Account> findAll() throws Exception;

    void save(Account account) throws Exception;
}
