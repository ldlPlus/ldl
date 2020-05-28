package plus.ldl.eurekaprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年05月23日  12:01
 */
@RestController
@RequestMapping("/provider")
public class ConsumerController {


    @GetMapping("/find")
    public Map<String, String> findOne() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "lixiang");
        map.put("sex", "男");
        map.put("111", "111");
        return map;
    }
}
