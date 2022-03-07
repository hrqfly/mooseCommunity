package com.moose.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {

    @Pointcut("execution(* com.moose.community.service.*.*(..))")
    public void pointcut() {

    }

    //在运行前织入
    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

    //在运行后织入
    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }


    //在运行时记录织入
    @AfterReturning("pointcut()")
    public void afterRetuning() {
        System.out.println("afterRetuning");
    }


    //在抛出异常时织入
    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }


    //在织入前后都有要处理的逻辑
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        Object obj = joinPoint.proceed();
        System.out.println("around after");
        return obj;
    }

}
