package plus.ldl.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author ldl.plus
 * @date 2020年05月07日  22:01
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "PaymentFallbackService.paymentInfoOk.....";
    }

    @Override
    public String paymentInfoError(Integer id) {
        return "PaymentFallbackService.paymentInfoError.....";
    }
}
