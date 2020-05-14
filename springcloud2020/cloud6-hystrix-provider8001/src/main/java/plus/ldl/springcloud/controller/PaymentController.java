package plus.ldl.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.springcloud.service.PaymentService;

/**
 * @author ldl.plus
 * @date 2020年05月07日  20:42
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("server.port")
    private String serverPort;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfoOk(id);
        System.err.println("/hystrix/ok/  result = " + result);
        return result;
    }


    @GetMapping("/hystrix/error/{id}")
    public String paymentInfoError(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfoError(id);
        System.err.println("/hystrix/error/  result = " + result);
        return result;
    }

    // ========服务熔断

    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        System.err.println("PaymentController.paymentCircuitBreaker");
        return result;
    }

}
