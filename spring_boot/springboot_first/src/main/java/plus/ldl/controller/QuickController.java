package plus.ldl.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ldl.plus
 * @date 2020年03月28日  0:30
 */
@Controller
public class QuickController {

    @Value("${name}")
    private String name;

    @GetMapping("/quick2")
    @ResponseBody
    public String quick2() {
        return "name:  " + name;
    }

}
