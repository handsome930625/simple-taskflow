package com.charse.taskflow.taskflow;

/**
 * @Author: wangyj
 * @Date: 2018/4/10 22:36
 * @Description: 任务流处理类
 **/
public interface ITaskHandler {

    /**
     * 执行任务处理逻辑
     *
     * @param params          用户参数
     * @param taskFlowContext 任务流上下文
     * @return 方法返回值
     * @throws Exception
     */
    Object handle(Object params, TaskFlowContext taskFlowContext) throws Exception;
}