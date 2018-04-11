package com.charse.taskflow.configure.definition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 19:16
 * @Description: 任务流集合解析
 **/
public class TaskFlowsDefinition implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1165830038351860306L;

    /**
     * 任务流集合
     */
    private List<TaskFlowDefinition> taskFlowList = new ArrayList<>();

    public void addTaskFlow(TaskFlowDefinition taskFlow) {
        taskFlowList.add(taskFlow);
    }

    public List<TaskFlowDefinition> getTaskFlowList() {
        return taskFlowList;
    }

    public void setTaskFlowList(List<TaskFlowDefinition> taskFlowList) {
        this.taskFlowList = taskFlowList;
    }
}