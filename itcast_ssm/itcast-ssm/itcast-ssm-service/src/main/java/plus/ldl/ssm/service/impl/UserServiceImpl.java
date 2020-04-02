package plus.ldl.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.ldl.ssm.dao.UserDao;
import plus.ldl.ssm.domain.Role;
import plus.ldl.ssm.domain.User;
import plus.ldl.ssm.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月21日  12:57
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public User findById(Integer id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public User findUserByIdAndAllRole(Integer id) throws Exception {
        return userDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void save(User user) throws Exception {
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), "{noop}" + user.getPassword(),
                getAuthorities(user.getRoles()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        return list;
    }
}
