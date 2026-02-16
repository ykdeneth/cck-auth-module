package com.cck.cck_app.aspect;

import com.cck.cck_app.entity.AuditLog;
import com.cck.cck_app.service.AuditService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private final AuditService auditService;

    @Autowired
    private HttpServletRequest request;

    // All methods inside your auth module
    @Pointcut(
            "("+
            "execution(* com.cck.cck_app.rest..*(..)) || "+
            "execution(* com.cck.cck_app.service..*(..)) || " +
            "execution(* com.cck.cck_app.exceptions..*(..))" +
                    ")" +
                    " && !execution(* com.cck.cck_app.service.AuditService.*(..))"
//            "execution(* com.cck.cck_app.rest..*(..))"
    )
    public void applicationPackagePointcut() {}

    // Method Call Logging
    @Around("applicationPackagePointcut()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        LocalDateTime now = LocalDateTime.now();

        String username = "ANONYMOUS";

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            username = SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getName();
        }


        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String ip = request.getRemoteAddr();
//        LocalDateTime now = LocalDateTime.now();

        logger.info("METHOD CALLED → Class: {} | Method: {} | User: {} | IP: {} | Time: {}",
                className, methodName, username, ip, now);

//        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            AuditLog audit = AuditLog.builder()
                    .username(username)
                    .className(className)
                    .methodName(methodName)
                    .ipAddress(ip)
                    .timestamp(now)
                    .executionTime(executionTime)
                    .status("SUCCESS")
                    .build();
            auditService.saveAudit(audit);

            logger.info("METHOD SUCCESS → {}.{} | Execution Time: {} ms",
                    className, methodName, executionTime);

            return result;

        } catch (Exception ex) {

            long executionTime = System.currentTimeMillis() - start;

            AuditLog audit = AuditLog.builder()
                    .username(username)
                    .className(className)
                    .methodName(methodName)
                    .ipAddress(ip)
                    .timestamp(now)
                    .executionTime(executionTime)
                    .status("FAILED")
                    .errorMessage(ex.getMessage())
                    .build();

            auditService.saveAudit(audit);
            logger.error("METHOD ERROR → {}.{} | Message: {} | Time: {}",
                    className, methodName, ex.getMessage(), now);

            throw ex;
        }
    }
}
