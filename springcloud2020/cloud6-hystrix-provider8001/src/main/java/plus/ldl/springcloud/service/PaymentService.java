package plus.ldl.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ldl.plus
 * @date 2020年05月07日  20:38
 */
@Service
public class PaymentService {

    /**
     * 正常访问方法
     *
     * @param id ID
     * @return
     */
    public String paymentInfoOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoOk,id: " + id + "\t" + "😄";
    }

    /**
     * 错误访问方法
     *
     * @param id ID
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoErrorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoError(Integer id) {
        int time = 3000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoError,id: " + id + "\t" + "😄，耗时" + time + "秒钟";
    }

    public String paymentInfoErrorHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "   8001--- paymentInfoErrorHandler,id: " + id + "\t" +
                "落泪(┬＿┬)";
    }

    // ====================服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = " 10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        return Thread.currentThread().getName() + "   调用成功，流水号：" + IdUtil.simpleUUID();
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "ID 不能为负数，请稍后再试(┬＿┬) (┬＿┬) ID：" + id;
    }

}
