package plus.ldl.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.springcloud.entitier.CommonResult;
import plus.ldl.springcloud.entitier.Payment;
import plus.ldl.springcloud.service.PaymentFeignService;

/**
 * @author ldl.plus
 * @date 2020年05月03日  13:12
 */
@RestController
public class OrderFeignController {
    private static final Logger logger = LoggerFactory.getLogger(OrderFeignController.class);

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> findPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.findPaymentById(id);
    }

    @GetMapping("/payment/get/lb")
    public String getPaymentLB() {
        return paymentFeignService.getPaymentLB();
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.paymentFeignTimeout();
    }
}
