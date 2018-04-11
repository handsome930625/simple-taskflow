package com.charse.taskflow.configure.definition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: wangyj
 * @Date: 2018/4/11 19:16
 * @Description: 任务流解析
 **/
public class TaskFlowDefinition implements Serializable {

    /**
     * 序列id
     */
    private static final long serialVersionUID = 6962765418746113739L;

    /**
     * 任务流ID
     */
    private String id;

    /**
     * 启动任务ID
     */
    private String startTask;

    /**
     * 任务流实现选择
     */
    private String implType;

    /**
     * 过滤器集合
     */
    private FiltersDefinition filters = new FiltersDefinition();

    /**
     * 任务集合
     */
    private List<TaskDefinition> taskList = new ArrayList<>();

    public void addTask(TaskDefinition task) {
        taskList.add(task);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTask() {
        return startTask;
    }

    public void setStartTask(String startTask) {
        this.startTask = startTask;
    }

    public String getImplType() {
        return implType;
    }

    public void setImplType(String implType) {
        this.implType = implType;
    }

    public FiltersDefinition getFilters() {
        return filters;
    }

    public void setFilters(FiltersDefinition filters) {
        this.filters = filters;
    }

    public List<TaskDefinition> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDefinition> taskList) {
        this.taskList = taskList;
    }
}