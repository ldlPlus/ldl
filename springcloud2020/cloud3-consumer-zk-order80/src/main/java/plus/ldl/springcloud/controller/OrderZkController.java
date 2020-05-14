package plus.ldl.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ldl.plus
 * @date 2020年05月02日  12:12
 */
@RestController
@RequestMapping("/consumer")
public class OrderZkController {
    private static final Logger logger = LoggerFactory.getLogger(OrderZkController.class);

    public static final String INVOKE_URL = "http://colud-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/payment/zk")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
    }
}
