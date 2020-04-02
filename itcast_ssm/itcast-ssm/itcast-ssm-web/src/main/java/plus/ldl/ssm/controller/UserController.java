package plus.ldl.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.ssm.domain.User;
import plus.ldl.ssm.service.UserService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月21日  12:31
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv) throws Exception {
        List<User> users = userService.findAll();
        mv.addObject("users", users);
        mv.setViewName("user-list");
        return mv;
    }

    @GetMapping("/findById")
    public ModelAndView findById(ModelAndView mv, @RequestParam("id") Integer id) throws Exception {
        User user = userService.findById(id);
        System.out.println("user = " + user);
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    @GetMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(ModelAndView mv, @RequestParam("id") Integer id) throws Exception {
        User user = userService.findUserByIdAndAllRole(id);
        mv.addObject("user", user);
        mv.setViewName("user-add");
        return mv;
    }

    @PostMapping("/save")
    public String save(User user) throws Exception {
        userService.save(user);
        return "redirect:/user/findAll";
    }
    
}
