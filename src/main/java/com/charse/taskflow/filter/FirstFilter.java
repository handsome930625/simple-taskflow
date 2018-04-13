package com.charse.taskflow.filter;

import com.charse.taskflow.filter.local.TaskFlowVar;
import com.charse.taskflow.filter.local.TaskflowThreadLocal;
import com.charse.taskflow.taskflow.ITaskFlow;
import com.charse.taskflow.taskflow.TaskFlowContext;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 21:06
 * @Description: 头过滤器
 **/
public class FirstFilter implements Filter {

    @Override
    public void doFilter(Object params, TaskFlowContext taskFlowContext, FilterChain filterChain) throws Exception {
        ITaskFlow taskFlow = taskFlowContext.getTaskFlow();
        String taskFlowId = taskFlow.getId();
        TaskFlowVar taskFlowVar = TaskflowThreadLocal.getTaskFlowVar();
        //taskFlowVar.beforeExecute();
        filterChain.doFilter(params, taskFlowContext);
    }
}