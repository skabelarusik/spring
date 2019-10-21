package by.epam.crackertracker.aspect;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@EnableAspectJAutoProxy
public class CrackerLogAspect {

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Pointcut("execution(public * by.epam.crackertracker.service.UserService.*())")
    public void callAllService(){}

    @Before("callAllService()")
    public void beforeCallService(JoinPoint point){
        String args = Arrays.stream(point.getArgs())
                .map(a -> a.toString())
                .collect(Collectors.joining(","));
        LOGGER.debug("before " + point.toString() + ", args=[" + args + "]");
    }


    @After("callAllService()")
    public void afterCallAt(JoinPoint jp) {
        LOGGER.debug("after " + jp.toString());
    }
}
