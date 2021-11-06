package com.acid6001.aop;

import java.util.Arrays;
import java.util.StringJoiner;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	
//	@Before("execution(* com.acid6001.service.SampleService*.*(..))")
	public void logAdvice(JoinPoint jp) {
		log.info("===========================================");
	}
	
//	@Around("execution(* com.acid6001.service.*.*(..))") // 범인
	public Object logTime(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();
		Object obj = null; // 비 초기화 지역변수는 사용할 수 없음.
		long end = System.currentTimeMillis();
		Object[] args = pjp.getArgs();
		String[] strs = new String[args.length];
		for(int i = 0; i< args.length ; i++) {
			strs[i] = args[i].toString();
		}
		String str = String.join(",", strs);
		log.info(String.format("%s.%s(%s) :: %d ms",
				pjp.getTarget().getClass().getSimpleName(),
				pjp.getSignature().getName(),
				str,
				end-start));
		return obj;
		
	}
}
