package plus.ldl.ssm.dao;

import org.apache.ibatis.annotations.Select;
import plus.ldl.ssm.domain.Permission;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月23日  21:42
 */
public interface PermissionDao {

    @Select("select * from permission p,role_permission rp where p.id = rp.permissionId and rp.roleId = #{id} ")
    List<Permission> findByIdOnRoleId(int id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;
}
