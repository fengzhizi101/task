package com.task.miracletask.library.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.spi.LoggerContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mysql.cj.Session;
import com.task.miracletask.utils.IpUtil;


@Aspect
@Component
@Order(1)
public class LogAspect {
	
	//切点入口 Controller包下面所有类的所有方法
	private final String pointcut = "execution(public * com.task.miracletask.controller.UserController.*(..))";
	Logger logger = LoggerFactory.getLogger(LoggerContext.class);
	
	@Pointcut(value= pointcut)
	public void log() {
		//logger.info("调用 log() ");
	}
	
	// 使用log函数定义的 execution，不执行log()方法
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = IpUtil.getIpAddr(request);
        logger.info("IP:" + ip);
        HttpSession session = attributes.getRequest().getSession();
        logger.info("session id: " + session.getId()); 
        logger.info("class name:" + joinPoint.getTarget().getClass().getName());
        logger.info("function name:" + signature.getMethod().getName());
        Object[] args = joinPoint.getArgs();
        String[] params = signature.getParameterNames();
        logger.info("参数名1："+ Arrays.toString(params));
        //for (int i = 0; i < params.length; i++) {
        //	logger.info("参数名："+ params[i] + " = " +args[i]);
        //
        //}
        long startTime = System.currentTimeMillis();
        //((ProceedingJoinPoint) joinPoint).proceed();
        long endTime = System.currentTimeMillis();
        logger.info("耗时: " + (endTime - startTime));
    }
    
    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }

	
//	@Around(value = "log()")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        Object result = null;
//        StringBuilder sbLog = new StringBuilder("\n");
//        try {
//            sbLog.append(String.format("类名：%s\r\n", proceedingJoinPoint.getTarget().getClass().getName()));
//
//            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//            sbLog.append(String.format("方法：%s\r\n", methodSignature.getMethod().getName()));
//
//            Object[] args = proceedingJoinPoint.getArgs();
//            for (Object o : args) {
//                sbLog.append(String.format("参数：%s\r\n", JSON.toJSON(o)));
//            }
//
//            long startTime = System.currentTimeMillis();
//            result = proceedingJoinPoint.proceed();
//            long endTime = System.currentTimeMillis();
//            sbLog.append(String.format("返回：%s\r\n", JSON.toJSON(result)));
//            sbLog.append(String.format("耗时：%ss", endTime - startTime));
//            logger.info("测试切面====================");
//            logger.info(sbLog.toString());
//        } catch (Exception ex) {
//            sbLog.append(String.format("异常：%s", ex.getMessage()));
//        } finally {
//            logger.info(sbLog.toString());
//        }
//        return result;
//    }
}
