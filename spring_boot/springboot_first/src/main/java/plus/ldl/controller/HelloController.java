package plus.ldl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.domain.User;
import plus.ldl.service.UserService;

/**
 * @author ldl.plus
 * @date 2020年03月29日  13:56
 */
@RestController
@RequestMapping("/user")
public class HelloController {

    public static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User hello(@PathVariable Long id) {
        return userService.findById(id);
    }

}
