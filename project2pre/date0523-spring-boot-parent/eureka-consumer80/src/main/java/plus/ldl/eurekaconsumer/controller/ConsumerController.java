package plus.ldl.eurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年05月23日  12:01
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String URL_PRE = "http://eureka-provider/";

    @GetMapping("/")
    public Map findOne() {
        return restTemplate.getForObject(URL_PRE + "/provider/find", Map.class);
    }
}
