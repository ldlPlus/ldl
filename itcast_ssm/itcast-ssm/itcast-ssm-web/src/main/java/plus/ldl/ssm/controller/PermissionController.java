package plus.ldl.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.ssm.domain.Permission;
import plus.ldl.ssm.service.PermissionService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月24日  21:38
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv) throws Exception {
        List<Permission> permissions = permissionService.findAll();
        mv.addObject("permissions", permissions);
        mv.setViewName("permission-list");
        return mv;
    }
}
