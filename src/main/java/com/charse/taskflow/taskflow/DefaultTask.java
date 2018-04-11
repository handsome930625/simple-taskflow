package com.charse.taskflow.taskflow;

import com.charse.taskflow.configure.definition.ResultDefinition;

import java.util.List;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 20:35
 * @Description: 默认任务实现
 **/
public class DefaultTask implements ITask {

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 结果解析类
     */
    private List<ResultDefinition> resultDefinitionList;

    /**
     * 调用类
     */
    private Object invokeObject;

    /**
     * 调用方法
     */
    private String method;

    @Override
    public Object invokeMethod(Object params, TaskFlowContext taskFlowContext) throws Exception {
        return null;
    }

    @Override
    public ITask nextTask(Object resultValue, Object params, TaskFlowContext taskFlowContext) {
        return null;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<ResultDefinition> getResultDefinitionList() {
        return resultDefinitionList;
    }

    public void setResultDefinitionList(List<ResultDefinition> resultDefinitionList) {
        this.resultDefinitionList = resultDefinitionList;
    }

    public Object getInvokeObject() {
        return invokeObject;
    }

    public void setInvokeObject(Object invokeObject) {
        this.invokeObject = invokeObject;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}