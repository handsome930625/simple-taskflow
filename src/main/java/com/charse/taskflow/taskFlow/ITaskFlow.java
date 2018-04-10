package com.charse.taskflow.taskFlow;

/**
 * @Author: wangyj
 * @Date: 2018/4/10 22:36
 * @Description: 定义taskflow
 **/
public interface ITaskFlow {

    /**
     * <p>功能描述: 执行每一个task步骤</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/10 22:38 </p>
     *
     * @param params          用户自定义参数
     * @param taskFlowContext taskflow 上下文
     * @throws Exception 执行步骤会出现的异常
     */
    void executeTask(Object params, TaskFlowContext taskFlowContext) throws Exception;

}
