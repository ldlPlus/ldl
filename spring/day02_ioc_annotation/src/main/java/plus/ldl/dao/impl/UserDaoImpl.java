package plus.ldl.dao.impl;

import org.springframework.stereotype.Repository;
import plus.ldl.dao.UserDao;

/**
 * @author ldl.plus
 * @date 2020年03月08日  10:14
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("UserDaoImpl.save......");
    }
}
