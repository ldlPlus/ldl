package plus.ldl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ldl.plus
 * @date 2020年04月18日  11:43
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 调用此方法必须要有add权限
     *
     * @return
     */
    @PreAuthorize("hasAuthority('add')")
    @GetMapping("/add")
    public String add() {
        System.out.println("UserController.add");
        return "success";
    }

    /**
     * 调用此方法必须要有ROLE_ADMIN角色
     *
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String delete() {
        System.out.println("UserController.delete");
        return "delete";
    }
}
