package plus.ldl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.service.DemoService;

/**
 * @author ldl.plus
 * @date 2020年03月19日  10:00
 */
@Controller
@RequestMapping("/target")
public class TargetController {

    @GetMapping("/test1")
    public String test1(ModelAndView modelAndView) {
        System.out.println("TargetController.test1");
        return "index";
    }

    @Autowired
    private DemoService demoService;

    @RequestMapping("/show1")
    public String show1() {
        demoService.show1();
        return "index";
    }

    @RequestMapping("/show2")
    public String show2() {
        demoService.show2();
        return "index";
    }

    @RequestMapping("/show3")
    public String show3() {
        demoService.show3();
        return "index";
    }
}
