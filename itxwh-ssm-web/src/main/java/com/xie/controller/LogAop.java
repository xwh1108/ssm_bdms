package com.xie.controller;

import com.xie.domain.SysLog;
import com.xie.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//访问时间
    private Class classzz;
    private Method method;

    @Before("execution(* com.xie.controller.*.*(..)) && !execution(* com.xie.controller.SysLogController.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime=new Date();
        classzz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args==null||args.length==0){
            method = classzz.getMethod(methodName);
        }else {
            Class[] classArgs=new Class[args.length];
            for (int i=0;i<args.length;i++){
                classArgs[i]=args[i].getClass();
            }
            classzz.getMethod(methodName,classArgs);
        }

    }

    @After("execution(* com.xie.controller.*.*(..)) && !execution(* com.xie.controller.SysLogController.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {

        String url="";

        long time=new Date().getTime()-visitTime.getTime();

        String ip=request.getRemoteAddr();

        if (classzz!=null&&method!=null&&classzz!=LogAop.class){
            RequestMapping classzzAnnotation = (RequestMapping) classzz.getAnnotation(RequestMapping.class);
            if (classzzAnnotation!=null){
                String[] classValue = classzzAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url="/"+classValue[0]+"/"+methodValue[0];

                    SecurityContext context= SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();

                    String username = user.getUsername();

                    SysLog sysLog=new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名] "+classzz.getName()+" [方法名] "+method.getName());

                    sysLogService.save(sysLog);
                }
            }
        }


    }
}
