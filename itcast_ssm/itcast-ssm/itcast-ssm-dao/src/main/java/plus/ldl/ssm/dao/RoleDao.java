package plus.ldl.ssm.dao;

import org.apache.ibatis.annotations.*;
import plus.ldl.ssm.domain.Role;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月22日  21:04
 */
public interface RoleDao {

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Select("select * from role where id in (select roleId from users_role where userId = #{id} ) ")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleName"),
            @Result(property = "permissions", column = "id", javaType = List.class, many = @Many(select = "plus.ldl.ssm.dao.PermissionDao.findByIdOnRoleId"))
    })
    List<Role> findById(int id) throws Exception;

    @Insert("insert into role(roleName, roleDesc) VALUES (#{roleName} ,#{roleDesc} )")
    void save(Role role) throws Exception;
}
