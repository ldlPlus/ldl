package plus.ldl.service;

import plus.ldl.entity.User;

import java.util.List;

/**
 * (SysUser)表服务接口
 *
 * @author ldl.plus
 * @since 2020-03-16 18:32:30
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user    实例对象
     * @param roleIds 实例对象的角色ID
     */
    void insert(User user, Long[] roleIds);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    User login(String username, String password);
}