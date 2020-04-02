package plus.ldl.mapper;

import plus.ldl.domain.Account;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月24日  9:45
 */
public interface AccountMapper {
    List<Account> findAll();

    void save(Account account);
}
