package plus.ldl.springcloud.service.impl;

import org.springframework.stereotype.Service;
import plus.ldl.springcloud.dao.PaymentDao;
import plus.ldl.springcloud.entitier.Payment;
import plus.ldl.springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author ldl.plus
 * @date 2020年05月01日  22:27
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment findPaymentById(Long id) {
        return paymentDao.findPaymentById(id);
    }
}
