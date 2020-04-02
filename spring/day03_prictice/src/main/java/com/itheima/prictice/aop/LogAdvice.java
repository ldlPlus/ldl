package com.itheima.prictice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ldl
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class LogAdvice {

    @Pointcut("execution(* com.itheima.prictice.service.impl.*.*(..))")
    public void pt1() {
    }

    @Before("pt1()")
    public void before(JoinPoint jp) {
        // 输出：xxx类的xxx方法执行前
        System.out.println(LocalDateTime.now() + "  " + jp.getTarget().getClass().getSimpleName() + "类的" + jp.getSignature().getName() + "方法执行前");
    }

    @AfterReturning("pt1()")
    public void afterReturning(JoinPoint jp) {
        // 输出：xxx类的xxx方法正常执行
        System.out.println(LocalDateTime.now() + "  " + jp.getTarget().getClass().getSimpleName() + "类的" + jp.getSignature().getName() + "正常执行");
    }

    @AfterThrowing(pointcut = "pt1()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        // 输出xxx类的xxx方法出现异常，异常原因为：/xxxxx
        System.out.println(LocalDateTime.now() + "  " + jp.getTarget().getClass().getSimpleName() + "类的" + jp.getSignature().getName() + "方法出现异常，异常原因为：" + e.getMessage());
    }

    @After("pt1()")
    public void after(JoinPoint jp) {
        // 输出xxx类的xxx方法执行结束了
        System.out.println(LocalDateTime.now() + "  " + jp.getTarget().getClass().getSimpleName() + "类的" + jp.getSignature().getName() + "执行结束了");
    }

    @Around("pt1()")
    public Object around(ProceedingJoinPoint pjp) {
        Object proceed = null;
        try {
            System.out.println(LocalDateTime.now() + "  " + pjp.getTarget().getClass().getSimpleName() + "类的" + pjp.getSignature().getName() + "方法执行前");
            Object[] args = pjp.getArgs();
            proceed = pjp.proceed(args);
            System.out.println(LocalDateTime.now() + "  " + pjp.getTarget().getClass().getSimpleName() + "类的" + pjp.getSignature().getName() + "正常执行");
        } catch (Throwable throwable) {
            System.out.println(LocalDateTime.now() + "  " + pjp.getTarget().getClass().getSimpleName() + "类的" + pjp.getSignature().getName() + "方法出现异常，异常原因为：" + throwable.getMessage());
        } finally {
            System.out.println(LocalDateTime.now() + "  " + pjp.getTarget().getClass().getSimpleName() + "类的" + pjp.getSignature().getName() + "执行结束了");
        }
        return proceed;
    }
}
