package plus.ldl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import plus.ldl.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年04月17日  21:24
 * spring测试
 */
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Map<String, User> map = new HashMap<>();

    public void init() {
        User user1 = new User();
        user1.setUsername("admin");
        // 明文密码（没有加密）
        user1.setPassword(passwordEncoder.encode("admin"));

        User user2 = new User();
        user2.setUsername("xiaoming");
        user2.setPassword(passwordEncoder.encode("1234"));

        map.put(user1.getUsername(), user1);
        map.put(user2.getUsername(), user2);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 实现类User，如果null则登录失败
     * @throws UsernameNotFoundException 用户名错误
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        init();
        User user = map.get(username);

        if (user == null) {
            return null;
        } else {
            List<GrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority("permission_A"));
            list.add(new SimpleGrantedAuthority("permission_B"));

            String admin = "admin";
            if (admin.equals(username)) {
                list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                list.add(new SimpleGrantedAuthority("add"));
            }

            System.out.println("username = " + user.getPassword());
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
        }
    }
}
