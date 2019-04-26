package com.task.miracletask.library.aop;

import java.util.HashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.task.miracletask.library.annotation.Auth;


@Aspect
@Component
public class AuthAspect {
		
	@Pointcut("@annotation(com.task.miracletask.library.annotation.Auth)")
	public void doAuth() {
		//logger.info("调用 log() ");
	}
	
	// 使用log函数定义的 execution，不执行log()方法
	@Before("doAuth()")
    public Object doBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        System.out.println("方法名：" + methodName);
        Auth auth = signature.getMethod().getAnnotation(Auth.class);
        String token = auth.token();
        System.out.println("token：" + token);
        if ( ! validate(token)) {
        	System.out.println("============no auth");
        	HashMap<String, String> response = new HashMap<String, String>();
        	response.put("code", "403");
        	response.put("message", "no authority");
        	response.put("data", null);
            return "no authority";
        } 
        
        try {
            return ((ProceedingJoinPoint) joinPoint).proceed();
        } catch (Throwable throwable) {
            return "fail";
        }
    }
    
	
    private boolean validate(String token) {
        // TODO 实现自己的鉴权功能
    	if (token.equals("123")) {
    		return true;
    	}
    	
        return false;
    }
}
