package com.epam.spring.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {

	public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.info(StringUtils.join(joinPoint.getSignature().getName(), "() called"));

		try {
			Object result = joinPoint.proceed();
			logger.info(StringUtils.join(joinPoint.getSignature().getName(), "() executed"));
			return result;
		} catch (Throwable ex) {
			logger.warn(StringUtils.join(joinPoint.getSignature().getName(), "() failed"));
			throw ex;
		}
	}
}
