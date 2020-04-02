package plus.ldl.springtest.dao;

import plus.ldl.springtest.domain.Role;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月16日  16:27
 */
public interface RoleDao {

    List<Role> findAll();
}
