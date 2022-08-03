package com.mb.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
// @Configuration
public class CentralLoggingHandler
{

	private Logger logger = LogManager.getLogger();

	// @Autowired
	// private HttpServletRequest request;

	@Before("execution(* com.mb.controller.*.*(..))")
	public void before(JoinPoint joinPoint)
	{

		logger.info(" Allowed execution for {}", joinPoint);
	}

	@AfterReturning(value = "execution(* com.mb.controller.*.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result)
	{
		logger.info("returned with value {}", result);
	}

	@After(value = "execution(* com.mb.controller.*.*(..))")
	public void after(JoinPoint joinPoint)
	{
		logger.info("after method {}", joinPoint);
	}

	@AfterThrowing(pointcut = "execution(* com.mb.controller.*.*(..))", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e)
	{
		logger.error("exception thrown ");
		logger.error(e);
	}
}
