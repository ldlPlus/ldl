package plus.ldl.ssm.service;

import plus.ldl.ssm.domain.Role;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月22日  21:04
 */
public interface RoleService {

    List<Role> findAll() throws Exception;

    List<Role> findById(int id) throws Exception;

    void save(Role role) throws Exception;
}
