package com.mycompany.ordersystem.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

// @Configuration
// @EnableAspectJAutoProxy
public class TransactionConfig {

    // @Autowired
    private DataSourceTransactionManager txManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        // 메서드 지정에 쓰일 트랜잭션 속성
        RuleBasedTransactionAttribute txSupport = new RuleBasedTransactionAttribute();
        txSupport.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        txSupport.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

        RuleBasedTransactionAttribute txRequired = new RuleBasedTransactionAttribute();
        txRequired.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        txRequired.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        // 메서드와 트랜잭션 속성 연결
        HashMap<String, TransactionAttribute> txMethods = new HashMap<String, TransactionAttribute>();
        txMethods.put("get*", txSupport);
        txMethods.put("find*", txSupport);
        txMethods.put("save*", txRequired);
        txMethods.put("update*", txRequired);
        txMethods.put("delete*", txRequired);

        // 어드바이스 작성
        NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();
        txAttributeSource.setNameMap(txMethods);
        return new TransactionInterceptor(txManager, txAttributeSource);
    }

    @Bean
    public Advisor txAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.mycompany.ordersystem.services.*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
