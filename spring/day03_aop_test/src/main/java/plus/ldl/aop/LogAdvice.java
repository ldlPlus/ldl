package plus.ldl.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


/**
 * @author ldl.plus
 * @date 2020年03月11日  15:08
 */
@Component
public class LogAdvice {
    public void beforeLog(JoinPoint jp) {
        System.out.println(jp.getTarget().getClass().getSimpleName() + "的" + jp.getSignature().getName() + "方法执行前--" + LocalDate.now());

    }

    public void afterLog() {
        System.out.println("LogAdvice.afterLog");
    }

    public void afterThrowing(JoinPoint jp, Exception e) {
        System.out.println(jp.getTarget().getClass().getSimpleName() + "的" + jp.getSignature().getName() + "方法出现异常，异常原因是" + e.getMessage());
    }

    public Object aroundTime(ProceedingJoinPoint pjp) {
        Object proceed = null;
        long end = 0;
        long start = 0;
        try {
            //前置
            start = System.currentTimeMillis();
            proceed = pjp.proceed();
            //正常后置
            end = System.currentTimeMillis();
        } catch (Throwable throwable) {
            //错误后置
            end = System.currentTimeMillis();
            System.err.println();
            throwable.printStackTrace();
        } finally {
            //最终
            System.out.println("LogAdvice.aroundTime最终时间：" + (end - start) + "毫秒");
        }
        return proceed;
    }
}
