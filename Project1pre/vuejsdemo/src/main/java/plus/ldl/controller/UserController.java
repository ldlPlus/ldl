package plus.ldl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.domain.User;
import plus.ldl.service.UserService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月26日  23:01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/findById")
    public User findById(Integer id) { // 使用vue异步请求时，参数不能使用@RequestParam注解---没有收到id的值
        return userService.findById(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody User user) {
        userService.updateUser(user);
    }
}
