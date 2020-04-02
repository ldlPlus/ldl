package plus.ldl.springtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.ldl.springtest.dao.RoleDao;
import plus.ldl.springtest.domain.Role;
import plus.ldl.springtest.service.RoleService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月16日  16:26
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
