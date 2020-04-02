package plus.ldl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import plus.ldl.dao.RoleDao;
import plus.ldl.entity.Role;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月16日  19:10
 */
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate template;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Long id) {
        return null;
    }

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return template.query(
                "select * from sys_role limit ?, ?",
                new BeanPropertyRowMapper<>(Role.class), offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param role 实例对象
     * @return 对象列表
     */
    @Override
    public List<Role> queryAll(Role role) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(Role role) {
        return template.update(
                "insert into sys_role(roleName, roleDesc) VALUES (?,?)",
                role.getRolename(), role.getRoledesc());
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    @Override
    public int update(Role role) {
        return 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        int i = template.update("delete from sys_user_role where roleId = ?", id);
        i += template.update("delete from sys_role where id = ?", id);
        return i;
    }
}
