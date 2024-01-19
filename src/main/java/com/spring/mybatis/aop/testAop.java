package com.spring.mybatis.aop;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class testAop {
    public void testAopStart(){
        System.out.println("com.spring.mybatis.aop :: testAopStart()");
    }
}
