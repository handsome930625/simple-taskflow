package com.charse.taskflow.taskflow;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangyj
 * @Date: 2018/4/10 22:37
 * @Description: taskflow 上下文
 **/
public class TaskFlowContext {

    /**
     * 执行的taskflow
     */
    private ITaskFlow taskFlow;

    /**
     * 任务结果集
     */
    private Map<String, Object> returnValues = new HashMap<>();

    public TaskFlowContext(ITaskFlow taskFlow) {
        this.taskFlow = taskFlow;
    }

    public Map<String, Object> getReturnValues() {
        return returnValues;
    }

    public void setReturnValues(Map<String, Object> returnValues) {
        this.returnValues = returnValues;
    }

    public ITaskFlow getTaskFlow() {
        return taskFlow;
    }
}
