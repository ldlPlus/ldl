package plus.ldl.springcloud.service;

import plus.ldl.springcloud.entitier.Payment;

/**
 * @author ldl.plus
 * @date 2020年05月01日  22:27
 */
public interface PaymentService {
    int create(Payment payment);

    Payment findPaymentById(Long id);
}
