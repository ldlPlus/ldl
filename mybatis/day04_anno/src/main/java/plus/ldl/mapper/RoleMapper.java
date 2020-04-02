package plus.ldl.mapper;

import org.apache.ibatis.annotations.Select;
import plus.ldl.domain.Role;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月23日  16:40
 */
public interface RoleMapper {

    @Select("select * from sys_user_role ur,sys_role r where ur.roleId=r.id and ur.userId = #{id}")
    public List<Role> findByUid(int id);
}
