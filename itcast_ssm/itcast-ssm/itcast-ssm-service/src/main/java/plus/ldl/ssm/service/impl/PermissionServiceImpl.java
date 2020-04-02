package plus.ldl.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.ldl.ssm.dao.PermissionDao;
import plus.ldl.ssm.domain.Permission;
import plus.ldl.ssm.service.PermissionService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月24日  21:40
 */
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }
}
