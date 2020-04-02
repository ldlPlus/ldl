package plus.ldl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ldl.plus
 * @date 2020年03月14日  14:20
 */
@Controller
public class UserController {

    @GetMapping("/user")
    public String save() {
        System.out.println("UserController.save");
        return "index.jsp";
    }
}
