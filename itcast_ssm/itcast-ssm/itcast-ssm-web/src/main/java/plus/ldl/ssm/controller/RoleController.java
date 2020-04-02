package plus.ldl.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.ssm.domain.Role;
import plus.ldl.ssm.service.RoleService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月22日  21:04
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv) throws Exception {
        List<Role> roles = roleService.findAll();
        mv.addObject("roles", roles);
        mv.setViewName("role-list");
        return mv;
    }

    @GetMapping("/findById")
    public ModelAndView findById(ModelAndView mv, @RequestParam(name = "id", defaultValue = "1") int id) throws Exception {
        List<Role> roles = roleService.findById(id);
        mv.addObject("role", roles);
        mv.setViewName("role-show");
        return mv;
    }

    @PostMapping("/save")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:/role/findAll";
    }
}
