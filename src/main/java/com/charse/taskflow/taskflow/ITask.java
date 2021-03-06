package com.charse.taskflow.taskflow;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 20:35
 * @Description: 定义每一个任务的接口
 **/
public interface ITask {

    /**
     * <p>功能描述: 该task执行的目标方法</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/11 20:39 </p>
     *
     * @param params          用户参数
     * @param taskFlowContext 上下文
     * @return 结果
     * @throws Exception 业务代码可能抛出各种异常
     */
    Object invokeMethod(Object params, TaskFlowContext taskFlowContext) throws Exception;

    /**
     * <p>功能描述: 调用下一个方法</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/11 20:41 </p>
     *
     * @param resultValue     该task执行结果
     * @param params          用户参数
     * @param taskFlowContext 上下文
     * @return 返回下一个task
     */
    ITask nextTask(Object resultValue, Object params, TaskFlowContext taskFlowContext);
}
