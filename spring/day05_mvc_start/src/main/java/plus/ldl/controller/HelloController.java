package plus.ldl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ldl.plus
 * @date 2020年03月13日  11:33
 */
// @Controller
@RequestMapping("/user")
public class HelloController {

    @GetMapping(path = "/hello")
    public String sayHello() {
        System.out.println("HelloController.sayHello");
        return "success";
    }


    @GetMapping("/test")
    public String testRequest() {
        System.out.println("HelloController.testRequest");
        return "success";
    }
}
