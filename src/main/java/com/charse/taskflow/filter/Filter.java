package com.charse.taskflow.filter;

import com.charse.taskflow.taskflow.TaskFlowContext;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 21:06
 * @Description: 过滤器接口
 **/
public interface Filter {
    /**
     * 执行过滤
     *
     * @param params          用户参数
     * @param taskFlowContext 任务流上下文
     * @param filterChain     过滤链
     * @throws Exception 执行链时发现错误
     */
    void doFilter(Object params, TaskFlowContext taskFlowContext, FilterChain filterChain) throws Exception;
}
