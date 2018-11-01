package com.darraghblake.customermanagersoftware.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CMSLoggingAspect {
	
	// Setup Logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// Setup @Pointcut declarations
	@Pointcut("execution(* com.darraghblake.customermanagersoftware.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.darraghblake.customermanagersoftware.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.darraghblake.customermanagersoftware.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	
	// Add @Before Advice Type
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("------> in @Before: calling method: " + theMethod);
	}
}
