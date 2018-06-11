package com.example.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Aspect
@Component
public class HttpAspect {

    private final  static Logger logger= LogManager.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.example.controller..*(..))")
    public void log(){}
    /**
     * @Before 在方法执行之前执行
     * */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        if(logger.isInfoEnabled()){
            logger.info("==执行controller前置通知==" + joinPoint);
        }
    }
    /**
     * @After在方法执行之后执行
     * */
    @After("log()")
    public  void after(JoinPoint joinPoint) throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        try {
            String targetName = joinPoint.getTarget().getClass().getName();//请求类
            String methodName = joinPoint.getSignature().getName();//请求方法名
            //*========控制台输出=========*//
            logger.info("=====controller后置通知开始====="+ LocalDateTime.now().toString());
            logger.info("请求方法:{" + targetName + "." + methodName+"}");
            logger.info("方法参数:" + getArgs(joinPoint));
            logger.info("请求IP:" + ip);
            logger.info("=====controller后置通知结束====="+LocalDateTime.now().toString());
        }  catch (Exception e) {
            logger.error("==后置通知异常开始=="+LocalDateTime.now().toString());
            logger.error("异常信息:", e);
            logger.error("=====后置通知异常结束====="+LocalDateTime.now().toString());
        }
    }
    //获取所有的参数名和值，组装为String
    private String getArgs(JoinPoint point) {
        try {
            String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                sb.append(parameterNames[i] + ":");
                if(point.getArgs()[i]!=null){
                    sb.append(point.getArgs()[i].toString()+";");
                }else {
                    sb.append("null;");
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return "{}";
        }
    }
}