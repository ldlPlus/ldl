package plus.ldl.springbootthymeleaf.plus.ldl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import plus.ldl.springbootthymeleaf.plus.ldl.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年06月09日  21:33
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "hello thymeleaf!");
        List<User> users = new ArrayList<>();
        users.add(new User(1, "张三1", "深圳1"));
        users.add(new User(2, "张三2", "深圳2"));
        users.add(new User(3, "张三3", "深圳3"));
        model.addAttribute("users", users);

        HashMap<String, String> map = new HashMap<>();
        map.put("No", "123");
        map.put("address", "深圳");
        model.addAttribute("map", map);

        model.addAttribute("date", new Date());

        model.addAttribute("age", 22);

        return "demo1";
    }
}
