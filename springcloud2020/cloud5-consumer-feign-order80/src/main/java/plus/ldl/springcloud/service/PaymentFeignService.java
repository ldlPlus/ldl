package plus.ldl.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import plus.ldl.springcloud.entitier.CommonResult;
import plus.ldl.springcloud.entitier.Payment;

/**
 * @author ldl.plus
 * @date 2020年05月03日  13:06
 */
@Service
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/{id}")
    CommonResult<Payment> findPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/lb")
    public String getPaymentLB();

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();
}
