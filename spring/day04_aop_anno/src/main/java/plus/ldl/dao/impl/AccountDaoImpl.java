package plus.ldl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import plus.ldl.dao.AccountDao;
import plus.ldl.entity.Account;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月12日  14:42
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate template;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Account queryById(Integer id) {
        try {
            return template.queryForObject(
                    "select * from db4.account where id = ?",
                    new BeanPropertyRowMapper<>(Account.class), id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Account> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param account 实例对象
     * @return 对象列表
     */
    @Override
    public List<Account> queryAll(Account account) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(Account account) {
        return 0;
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    @Override
    public int update(Account account) {
        return template.update(
                "update db4.account set balance = balance + ? where name = ?",
                account.getBalance(), account.getName());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Integer id) {
        return 0;
    }
}
