package plus.ldl.day01springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.day01springboot.entity.Person;

/**
 * @author ldl.plus
 * @date 2020年04月27日  16:01
 */
@RestController
public class PersonController {

    @Autowired
    private Person person;

    @Autowired
    private Environment environment;

    @GetMapping("/person1")
    public Person person1() {
        System.out.println("environment = " + environment);
        return person;
    }
}
