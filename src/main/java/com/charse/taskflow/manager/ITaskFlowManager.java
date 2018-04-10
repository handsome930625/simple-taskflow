package com.charse.taskflow.manager;

import java.util.Map;

/**
 * @author: wangyj
 * @date: 2018/4/10 21:56
 * @description: 定义一个TaskFlow 管理器
 * 负责初始化、执行任务链
 **/
public interface ITaskFlowManager {

    /**
     * <p>功能描述: 初始化taskflow 管理器</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/10 22:06 </p>
     */
    void initManager();

    /**
     * <p>功能描述: </p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/10 22:08 </p>
     *
     * @param taskFlowId taskFlow 标识
     * @param params     入参
     * @throws Exception 执行任务链中抛出的异常
     */
    Map<String, Object> executeFlow(String taskFlowId, Object params) throws Exception;

    /**
     * <p>功能描述: 销毁taskflow 管理器</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/10 22:11 </p>
     */
    void destroyManager();
}
