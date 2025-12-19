package com.mycompany.ordersystem.aspect;

import com.mycompany.ordersystem.domain.Customer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
// 근데 여기 Component는 나중에 getBean 하지도 않던데...Aspect는 그런건가...
public class LoggingAspect {

    // getClass()는 런타임에서 this.class를 반환한다고...
    private Logger logger = LoggerFactory.getLogger(getClass());

    // 아직 어떻게 작동하는지는 모르겠는데...
    // 아무튼 대상 조인포인트의 정보(클래스, 메서드, 매개변수들)를 메시지로 전달한다...
    private String buildJoinPoint(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String message = className + " 클래스의 " + methodName + "(";
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            message += arg.getClass().getTypeName();
            if (i != args.length - 1) {
                message += ", ";
            }
        }
        message += ")";
        return message;
    }

    // AOP 삽입 지점을 만드는데, 일단 여기저기 가져다 쓸 경우라면 이렇게 한 곳에 모아서...
    @Pointcut("execution(* com.mycompany.ordersystem.*.*.*.get*(..)) || " +
            "execution(* com.mycompany.ordersystem.*.*.*.find*(..))")
    public void getLogging() {}

    @Before("getLogging()")
    //    이래놓고 여기서는 어드바이스라는 것들을 만들어 놓고,
    //    그게 어디에 끼워맞춰지는지는 XML이나 어노테이션으로 설정하면 스프링이 자동으로 실행시킨다...뭐 그런건가?
    public void logBefore(JoinPoint joinPoint) {
        String message = buildJoinPoint(joinPoint);
        message += " 메서드 실행 시작 before";
        logger.info(message);
    }

    @After("getLogging()" +
            "")
    public void logAfter(JoinPoint joinPoint) {
        String message = buildJoinPoint(joinPoint);
        message += " 메서드 실행 공통 종료";
        logger.info(message);
    }

    @AfterReturning("getLogging()")
    public void logAfterReturning(JoinPoint joinPoint) {
        String message = buildJoinPoint(joinPoint);
        message += " 메서드 실행 정상 종료";
        logger.info(message);
    }

    @AfterThrowing("getLogging()")
    public void logAfterThrowing(JoinPoint joinPoint) {
        String message = buildJoinPoint(joinPoint);
        message += " 메서드 실행 에러";
        logger.error(message);
    }

    @Around("execution(* com.mycompany.ordersystem.*.*.*.save*(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.info("================== 시작 ====================");
        String message = buildJoinPoint(joinPoint);
        message += " 메서드 실행 시작";
        logger.info(message);

        // 이게 중간에 원래 로직이 돌아가게 하는 거인 모양....이건 예외 처리 필요...
        joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        logger.info("실행 시간: " + duration + " ms");
        logger.info("================== 종료 ====================");
    }

    // XML에서는 and, 여기서는 && 주의!!
    @Pointcut("execution(* com.mycompany.ordersystem.*.service.*.update*(" +
            "com.mycompany.ordersystem.domain.Customer)) && args(customer)")
    public void updateLogging(Customer customer) {}

    @Before("updateLogging(customer)")
    public void logBeforeUpdate(JoinPoint joinPoint, Customer customer) {
        String message = buildJoinPoint(joinPoint);
        message += " 업데이트 로깅 시작";
        logger.info(message);
        logger.info("변경 정보: " + customer.toString());
    }
}
