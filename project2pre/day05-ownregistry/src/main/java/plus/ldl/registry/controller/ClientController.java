package plus.ldl.registry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年05月06日  22:02
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private Map<String, String> data = new HashMap<>();

    /**
     * 注册服务
     *
     * @param name 服务名称
     * @param ip   IP
     * @param port 端口
     * @return 成功
     */
    @PostMapping
    public String registry(String name, String ip, Integer port) {
        data.put(name, ip + ":" + port);
        return "success: " + name;
    }

    /**
     * 服务发现
     *
     * @param name 服务名称
     * @return ip与端口
     */
    @GetMapping
    public String registry(String name) {
        return data.get(name);
    }

}
