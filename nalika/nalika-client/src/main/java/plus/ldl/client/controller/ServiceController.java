package plus.ldl.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.client.core.ServiceManager;

/**
 * @author ldl.plus
 * @date 2020年05月18日  19:36
 * 服务操作控制器
 */
@RestController
@RequestMapping("/nalika/service")
public class ServiceController {
    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    protected ServiceManager serviceManager;

    // @GetMapping("/")

}
