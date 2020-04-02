package plus.ldl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.domain.User;

import java.util.Date;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月15日  9:58
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private String viewName = "success";

    @GetMapping("/test1")
    public ModelAndView test1() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", "ldl");
        mv.setViewName(viewName);
        System.out.println("mv = " + mv);
        return mv;
    }

    @GetMapping("/test2")
    public ModelAndView test2(ModelAndView mv) {
        mv.addObject("user", "ldl");
        mv.setViewName(viewName);
        System.out.println("mv = " + mv);
        return mv;
    }

    @GetMapping("/test3")
    public String test3(Model model) {
        model.addAttribute("user", "ldl");
        System.out.println("model = " + model);
        return viewName;
    }

    @GetMapping(path = "/test4", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String test4() {
        System.out.println("UserController.test4");
        return "你好哇哈喽哈喽";
    }


    @GetMapping(path = "/test5", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String test5() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setAge(18);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("UserController.test5");

        return mapper.writeValueAsString(user);

    }

    @ResponseBody
    @GetMapping("/test6")
    public User test6() {
        User user = new User();
        user.setId(2);
        user.setUsername("张三123123");
        user.setAge(118);

        System.out.println("user = " + user);
        return user;
    }


    @ResponseBody
    @RequestMapping(path = "/test7", method = {RequestMethod.GET, RequestMethod.POST})
    public User test7(User user) {
        System.out.println("user = " + user);
        return user;
    }

    @PostMapping("/test8")
    public String test8(@RequestBody List<User> userList) {
        System.out.println("userList = " + userList);
        return viewName;
    }

    @PostMapping("/test9")
    public String test9(List<User> userList) {
        System.out.println("userList = " + userList);
        return viewName;
    }

    @RequestMapping(path = "/test10", method = RequestMethod.GET)
    public String test10(@RequestParam("username") String user) {
        System.out.println("user = " + user);
        return viewName;
    }

    @GetMapping("/test11/{uid}")
    @ResponseBody
    public void test11(@PathVariable("uid") String id) {
        System.out.println("UserController.test11");
        System.out.println("id = " + id);
    }

    @PostMapping("/test11/date")
    @ResponseBody
    public void test11(Date id) {
        System.out.println("UserController.test11");
        System.out.println("id = " + id);
    }


    @GetMapping("/test12")
    @ResponseBody
    public void test12(@RequestHeader("User-Agent") String userAgent) {
        System.out.println("UserController.test12");
        System.out.println("userAgent = " + userAgent);
    }

    @GetMapping("/test13")
    @ResponseBody
    public void test13(@CookieValue("JESESSIONID") String session) {
        System.out.println("UserController.test13");
        System.out.println("session = " + session);
    }
}
