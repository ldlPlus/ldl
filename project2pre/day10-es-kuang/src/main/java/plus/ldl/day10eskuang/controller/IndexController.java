package plus.ldl.day10eskuang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ldl.plus
 * @date 2020年05月15日  18:29
 */
@RestController
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}
