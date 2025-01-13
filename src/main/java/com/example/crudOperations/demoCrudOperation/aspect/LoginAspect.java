/*
package com.example.crudOperations.demoCrudOperation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class LoginAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoginAspect.class);
*/
/*@Pointcut("execution(* com.example.crudOperations.demoCrudOperation.service.*.*(..))")
    public void allMethod(){

}*//*



    @Before("execution(* com.example.crudOperations.demoCrudOperation.service.EmployeeService.saveemployeeWithAddress(..))")
    public void logBeforeMethodExecution() {
        logger.info(" Before Method execution started...");
        System.out.println(" Before Method execution started...");
    }
    @After("execution(* com.example.crudOperations.demoCrudOperation.service.EmployeeService.getAllemployees(..))")
    public void logAfterMethodExecution() {
        logger.info(" After Method execution started...");
        System.out.println("Method execution started...");
    }

    @Around("execution(* com.example.crudOperations.demoCrudOperation.service.*.*(..))")
    public Object logBeforeAndAfterMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(" Around Method execution started...");
        Object proceed = joinPoint.proceed();
        System.out.println(" Around Method execution started...");
        return proceed;
    }
}
*/
