package plus.ldl.springtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.springtest.domain.Role;
import plus.ldl.springtest.service.RoleService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月16日  16:31
 */
@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv) {

        List<Role> roles = roleService.findAll();

        mv.addObject("roles", roles);
        mv.setViewName("role-list");
        return mv;
    }
}
