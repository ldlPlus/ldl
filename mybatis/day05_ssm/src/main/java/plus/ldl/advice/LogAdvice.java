package plus.ldl.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * @author ldl.plus
 * @date 2020年03月24日  17:13
 */
@Component("logAdvice")
public class LogAdvice {

    public void beforeLog(JoinPoint jp) {
        String simpleName = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        System.out.println(simpleName + "类的" + methodName + "方法执行前");
    }

    public void afterReturningLog(JoinPoint jp) {
        String simpleName = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        System.out.println(simpleName + "类的" + methodName + "方法正常执行后");
    }

    public void afterThrowingLog(JoinPoint jp, Exception e) {
        String simpleName = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        System.out.println(simpleName + "类的" + methodName + "方法出现了异常，异常原因是：" + e.getMessage());
    }

    public void afterLog(JoinPoint jp) {
        String simpleName = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        System.out.println(simpleName + "类的" + methodName + "方法最终执行结束");
    }

    public Object aroundTime(ProceedingJoinPoint pjp) {
        long start = 0;
        long end = 0;
        try {
            start = System.currentTimeMillis();
            Object proceed = pjp.proceed();
            end = System.currentTimeMillis();
            return proceed;
        } catch (Throwable throwable) {
            end = System.currentTimeMillis();
            throwable.printStackTrace();
            return null;
        } finally {
            System.err.println(pjp.getTarget().getClass().getSimpleName() + "类的" + pjp.getSignature().getName() + "方法执行耗时：" + (end - start) + "毫秒");
        }
    }
}
