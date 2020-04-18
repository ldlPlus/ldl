package plus.ldl.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserServiceImpl implements UserDetailsService {

    private Map<String, User> map = new HashMap<>();

    {
        User user1 = new User();
        user1.setUsername("admin");
        // 明文密码（没有加密）
        user1.setPassword("admin");

        User user2 = new User();
        user2.setUsername("xiaoming");
        user2.setPassword("1234");

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
        System.out.println("UserService.loadUserByUsername");
        System.out.println("username = " + username);

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
            }

            return new org.springframework.security.core.userdetails.User(username, "{noop}" + user.getPassword(), list);
        }
    }
}
