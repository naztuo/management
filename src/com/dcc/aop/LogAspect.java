package com.dcc.aop;

import com.dcc.base.Response;
import com.dcc.service.LogService;
import com.google.common.collect.Lists;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * @ClassName:LogAspect
 * @Description: 日志切面
 * @Author: zhouhc
 * @Date: 2020/1/7 10:49
 * @Version:1.0
 **/
@Aspect
@Order(1)
@Component
class LogAspect {
	private ThreadLocal<Long> startTime = new ThreadLocal<>();


	@Pointcut("execution(public * com.dcc.controller..*.*(..))")//扫描所有controller包下的所有方法
	public void writeLog(){}

	@Pointcut("execution(public * com.dcc.controller..*.*(..))")//扫描所有bzz下所有controller包下的所有方法
	public void writeAfterLog(){}

	/**
	 * 切面前操作
	 * @param joinPoint
	 */
	@Before("writeLog()")
	public void doBefore(JoinPoint joinPoint) {
		startTime.set(System.currentTimeMillis());

		String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();//调用类名
		Object[] args = joinPoint.getArgs();
		List<Object> paramList = Lists.newArrayList();
		for(Object parameter: args){
			if(parameter instanceof ServletRequest || parameter instanceof ServletResponse || parameter instanceof MultipartFile){
				continue;
			}
			paramList.add(parameter);
		}
		LogHelpler.logStart(classMethod, JSON.toJSONString(paramList));
	}

	/**
	 * 切面后操作
	 * @param rsp
	 */
	@AfterReturning(returning = "rsp", pointcut = "writeAfterLog()")
	public void doAfterReturning(Response rsp)  {
		LogHelpler.logEnd(rsp, startTime.get());
	}

}