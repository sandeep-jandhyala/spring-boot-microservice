/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employee.ws.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author jan_s
 */
@Aspect
@Component
public class PerformanceMonitorAspect {
    
    
    
    public static final Logger logger = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

       
    
    @Around("execution(* com.mycompany.employee.ws.service.EmployeeServiceImpl.*(..))")
	public Object captureFinish(ProceedingJoinPoint jp) throws Throwable {
                long start = System.currentTimeMillis();
                Object result = jp.proceed();
               long end = System.currentTimeMillis();
               long  executionTime = end-start;
                 MethodSignature sig  = (MethodSignature) jp.getSignature();            
		logger.info("Completed: " +sig.getMethod().getName() +" in : " +executionTime +" milli seconds");
                return result;
	}
    
}
