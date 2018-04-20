package com.charse.taskflow.transaction.aspect;

import com.charse.taskflow.transaction.annotation.Commit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * description:提交切面
 *
 * @author wangyj on 2018/4/20
 */
@Aspect
public class CommitAspect {

    @Pointcut("@annotation(com.charse.taskflow.transaction.annotation.Commit)")
    public void pointcut() {
    }

    /**
     * 环绕通知 around advice
     * 这个切的是 com.lxk.service 包下面以及子包下所有，后面又 && 同时满足带有注解 MethodLog
     */
    @Around(value = "pointcut()")
    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //执行目标方法，并获得对应方法的返回值
        Object result = joinPoint.proceed();
        return result;
    }
}
