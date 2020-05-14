package plus.ldl.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import plus.ldl.springcloud.entitier.CommonResult;
import plus.ldl.springcloud.entitier.Payment;
import plus.ldl.springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ldl.plus
 * @date 2020年05月01日  22:29
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        logger.info("********插入结果：" + result);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功，serverPort：" + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据库失败");
        }
    }

    @GetMapping("/{id}")
    public CommonResult<Payment> findPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.findPaymentById(id);
        logger.info("********查询结果：" + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功，serverPort：" + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录，查询ID：" + id);
        }
    }

    @GetMapping
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            logger.info("************service: " + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            logger.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin() {
        return "hi,i`am paymentzipkin server fall back.welcome to atguigu.hahahahahhahahah";
    }


}
