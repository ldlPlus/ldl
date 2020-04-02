package plus.ldl.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.ldl.ssm.dao.RoleDao;
import plus.ldl.ssm.domain.Role;
import plus.ldl.ssm.service.RoleService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月22日  21:09
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findById(int id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }
}
