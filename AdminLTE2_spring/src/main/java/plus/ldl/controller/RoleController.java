package plus.ldl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.entity.Role;
import plus.ldl.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRole)表控制层
 *
 * @author ldl.plus
 * @since 2020-03-16 18:32:53
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    // @GetMapping("selectOne")
    public Role selectOne(Long id) {
        return this.roleService.queryById(id);
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    public ModelAndView selectAll(ModelAndView mv, Integer page) {
        if (page == null) {
            page = 1;
        }
        List<Role> roles = this.roleService.queryAllByLimit(10 * (page - 1), 10);
        mv.addObject("roles", roles);
        mv.setViewName("role-list");
        return mv;
    }

    @PostMapping("/save")
    public String insert(Role role) {
        roleService.insert(role);
        return "redirect:/role/list";
    }

    @GetMapping("/delete")
    public String deleteById(Long id) {
        roleService.deleteById(id);
        return "redirect:/role/list";
    }
}