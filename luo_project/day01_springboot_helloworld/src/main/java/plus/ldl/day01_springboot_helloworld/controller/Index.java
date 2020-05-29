package plus.ldl.day01_springboot_helloworld.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author ldl.plus
 * @date 2020年05月18日  11:49
 */
@RestController
// @ConfigurationProperties("person")
public class Index {

    @Value("${name:那么}")
    private String name = "那么";
    @Value("${age:" + "那么" + "}")
    private String age = "不那么";
    @Value("${age1:10}")
    private int age1;

    @Autowired
    private JdbcTemplate template;


    @GetMapping("/")
    public String getIndex() throws JsonProcessingException {
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("age1 = " + age1);
        List<Map<String, Object>> maps = template.queryForList("select * from eladmin.sys_menu");
        String value = new ObjectMapper().writeValueAsString(maps);
        System.out.println("value = " + value);
        return value;
    }

    @GetMapping("/sys")
    public String getSystem() throws JsonProcessingException {
        Properties system = System.getProperties();
        return new ObjectMapper().writeValueAsString(system);
    }
}
