package plus.ldl.service.impl;

import org.springframework.stereotype.Service;
import plus.ldl.dao.UserDao;
import plus.ldl.entity.Role;
import plus.ldl.entity.User;
import plus.ldl.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author ldl.plus
 * @since 2020-03-16 18:32:30
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {

        List<User> users = this.userDao.queryAllByLimit(offset, limit);
        for (User user : users) {
            Long id = user.getId();
            List<Role> roles = userDao.queryRoleById(id);
            ArrayList<String> roleNames = new ArrayList<>();
            for (Role role : roles) {
                roleNames.add(role.getRolename());
            }
            user.setRoleNames(roleNames);
        }
        return users;
    }

    /**
     * 新增数据
     *
     * @param user    实例对象
     * @param roleIds
     */
    @Override
    public void insert(User user, Long[] roleIds) {
        Long userId = this.userDao.insert(user);
        userDao.insertUserRoleRel(userId, roleIds);
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public User login(String username, String password) {
        return userDao.queryByUsernameAndPassword(username, password);
    }
}