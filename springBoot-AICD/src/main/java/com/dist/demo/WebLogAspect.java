package com.dist.demo;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 
 * @author 刘新杨
 *   菩提本无树，
 *   明镜亦非台。
 *   
 *   执行流程
 *   1.设置controller下所有的方法为切入点，只要一有请求来到controller中就执行weblog方法
 *  2. 但是在执行weblog方法之前会先执行 doBefore()方法
 *   在doBefore()方法中有获取所有的请求和请求参数等操作，并且记录在logger中
 *   3.在记录完成之后，webLog()方法也执行完成了就会 执行doAfterReturning()方法并且记录 返回的结果信息
 */


@Aspect
@Component
public class WebLogAspect {
	private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	@Pointcut("execution(public * com.dist.controller..*.*(..))") //切入点为controller包下的所有方法
	public void webLog() {
	logger.info("AOP已记录实时日志信息");
	}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		Enumeration<String> enu = request.getParameterNames();//获取得到的参数并且进行取值，和记录
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			logger.info("name:{},value:{}");
		}
	}
	@AfterReturning(returning = "ret", pointcut = "webLog()")  
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
	}
}