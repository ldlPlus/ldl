package plus.ldl.springtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.springtest.domain.User;
import plus.ldl.springtest.service.UserService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月16日  17:27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv) {
        List<User> users = userService.findAll();

        mv.addObject("users", users);
        mv.setViewName("user-list");
        return mv;
    }
}
