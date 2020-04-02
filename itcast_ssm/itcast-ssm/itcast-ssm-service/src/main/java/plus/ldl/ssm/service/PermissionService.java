package plus.ldl.ssm.service;

import plus.ldl.ssm.domain.Permission;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月24日  21:39
 */
public interface PermissionService {
    List<Permission> findAll() throws Exception;
}
