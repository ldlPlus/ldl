package plus.ldl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ldl.plus
 * @date 2020年03月14日  19:38
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test3")
    public String test1() {
        return "success";
    }

    @GetMapping("test2")
    public String test2(String username, int age) {
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        return "success";
    }

    @GetMapping("test1")
    public String test3(String username, int age) {
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        return "forward:/WEB-INF/jsp/success.jsp";
    }

}
