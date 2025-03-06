package com.t1.task3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Arrays;

@Aspect
@EnableConfigurationProperties(AspectProperties.class)
@ConditionalOnProperty(name = "aspect.enabled", havingValue = "true", matchIfMissing = true)
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private final AspectProperties aspectProperties;

    public LoggingAspect(AspectProperties aspectProperties) {
        this.aspectProperties = aspectProperties;
    }


    @Before("execution(* *(..)) && within(@org.springframework.web.bind.annotation.RestController *)")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsString = Arrays.toString(args);

        String logLevel = aspectProperties.getLogLevel();

        switch (logLevel.toLowerCase()) {
            case "debug":
                logger.debug("Вызов метода: {}, Аргументы: {}", methodName, argsString);
                break;
            case "info":
                logger.info("Вызов метода: {}, Аргументы: {}", methodName, argsString);
                break;
            case "warn":
                logger.warn("Вызов метода: {}, Аргументы: {}", methodName, argsString);
                break;
            case "error":
                logger.error("Вызов метода: {}, Аргументы: {}", methodName, argsString);
                break;
            default:
                logger.info("Вызов метода: {}, Аргументы: {}", methodName, argsString);
        }
    }


    @AfterReturning(pointcut = "execution(* *(..)) && within(@org.springframework.web.bind.annotation.RestController *)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();

        String logLevel = aspectProperties.getLogLevel();

        switch (logLevel.toLowerCase()) {
            case "debug":
                logger.debug("Результат метода {}: {}", methodName, result);
                break;
            case "info":
                logger.info("Результат метода {}: {}", methodName, result);
                break;
            case "warn":
                logger.warn("Результат метода {}: {}", methodName, result);
                break;
            case "error":
                logger.error("Результат метода {}: {}", methodName, result);
                break;
            default:
                logger.info("Результат метода {}: {}", methodName, result);
        }
    }
}

