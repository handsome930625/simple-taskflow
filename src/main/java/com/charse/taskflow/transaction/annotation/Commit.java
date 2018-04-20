package com.charse.taskflow.transaction.annotation;

import java.lang.annotation.*;

/**
 * description: 事务提交注解，需要配置回滚方法
 *
 * @author wangyj on 2018/4/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Commit {
    /**
     * 回滚方法参数
     */
    String[] args() default {};

    /**
     * 回滚方法
     */
    String rollBackMethod() default "";
}
