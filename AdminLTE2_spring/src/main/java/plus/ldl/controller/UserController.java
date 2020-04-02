package plus.ldl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.entity.Role;
import plus.ldl.entity.User;
import plus.ldl.service.RoleService;
import plus.ldl.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (SysUser)表控制层
 *
 * @author ldl.plus
 * @since 2020-03-16 18:32:31
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    // @GetMapping("selectOne")
    public User selectOne(Long id) {
        return this.userService.queryById(id);
    }


    @GetMapping("/list")
    public ModelAndView selectAll(ModelAndView mv, Integer page) {
        if (page == null) {
            page = 1;
        }

        List<User> users = this.userService.queryAllByLimit((page - 1) * 10, 10);

        mv.addObject("users", users);
        mv.setViewName("user-list");
        return mv;
    }

    @GetMapping("/addRole")
    public ModelAndView roleOption(ModelAndView mv) {

        List<Role> roles = roleService.queryAllByLimit(0, 100);

        mv.addObject("roles", roles);
        mv.setViewName("user-add");
        return mv;
    }

    @PostMapping("/save")
    public String insert(User user, Long[] roleIds) {
        userService.insert(user, roleIds);

        return "redirect:/user/list";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        userService.deleteById(id);
        return "redirect:/user/list";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/index.jsp";
        }
        return "redirect:/login.jsp";
    }


}