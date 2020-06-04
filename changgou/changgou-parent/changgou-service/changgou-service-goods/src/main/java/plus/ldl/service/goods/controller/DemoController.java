package plus.ldl.service.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ldl.plus
 * @date 2020年06月02日  10:40
 */
@RestController
@RequestMapping("/test")
public class DemoController {

    @GetMapping
    public String demo() {
        return "111111111111111";
    }
}
