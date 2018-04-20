package com.charse.taskflow.filter;

import com.charse.taskflow.taskflow.ITask;
import com.charse.taskflow.taskflow.ITaskFlow;
import com.charse.taskflow.taskflow.TaskFlowContext;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 21:57
 * @Description: 尾过滤器 工作过滤器
 **/
public class LastFilter implements Filter {

    @Override
    public void doFilter(Object params, TaskFlowContext taskFlowContext, FilterChain filterChain) throws Exception {
        ITaskFlow taskFlow = taskFlowContext.getTaskFlow();
        ITask currentTask = taskFlow.getStartTask();
        while (currentTask != null) {
            Object resultValue = currentTask.invokeMethod(params, taskFlowContext);
            currentTask = currentTask.nextTask(resultValue, params, taskFlowContext);
        }
    }
}
