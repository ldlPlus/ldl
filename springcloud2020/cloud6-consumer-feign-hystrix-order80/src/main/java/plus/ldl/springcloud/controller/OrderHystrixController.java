package plus.ldl.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.springcloud.service.PaymentHystrixService;

/**
 * @author ldl.plus
 * @date 2020年05月07日  21:06
 */
@RestController
@DefaultProperties(defaultFallback = "paymentGlobalErrorHandler")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoOk(id);
    }

    /*@HystrixCommand(fallbackMethod = "paymentInfoErrorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })*/
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/error/{id}")
    public String paymentInfoError(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoError(id);
    }

    public String paymentInfoErrorHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "   80--- paymentInfoErrorHandler,id: " + id + "\t" +
                "落泪(┬＿┬)";
    }

    public String paymentGlobalErrorHandler() {
        return "80端口全局异常处理信息，DefaultProperties";
    }
}
