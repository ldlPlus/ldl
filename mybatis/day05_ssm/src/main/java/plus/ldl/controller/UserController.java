package plus.ldl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.domain.User;
import plus.ldl.service.UserService;

import javax.servlet.http.HttpSession;


/**
 * @author ldl.plus
 * @date 2020年03月24日  16:34
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ModelAndView login(ModelAndView mv, User user, HttpSession session) {
        User loginUser = userService.login(user);
        if (loginUser == null) {
            //登录失败
            mv.addObject("msg", "帐号或密码错误");
            mv.setViewName("forward:/login.jsp");
            return mv;
        } else {
            session.setAttribute("user", loginUser);
            mv.setViewName("redirect:/index.jsp");
            return mv;
        }
    }
}
