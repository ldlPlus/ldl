package plus.ldl.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import plus.ldl.springcloud.entitier.CommonResult;
import plus.ldl.springcloud.entitier.Payment;
import plus.ldl.springcloud.service.PaymentService;

import javax.annotation.Resource;

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

    @GetMapping("/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
