package com.charse.taskflow.taskflow;

import com.charse.taskflow.configure.definition.InvokeDefinition;
import com.charse.taskflow.configure.definition.TaskDefinition;
import com.charse.taskflow.filter.Filter;
import com.charse.taskflow.filter.FilterChain;
import com.charse.taskflow.utils.SpringBeanHelper;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 20:35
 * @Description: 默认任务流实现
 **/
public class DefaultTaskFlow implements ITaskFlow {

    /**
     * flow id
     */
    private String id;

    /**
     * 过滤器集合
     */
    private List<Filter> filters;

    /**
     * 第一个任务的id
     */
    private String startTaskId;

    /**
     * flow 中 任务的集合
     */
    private Map<String, ITask> taskMap;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void executeTask(Object params, TaskFlowContext taskFlowContext) throws Exception {
        FilterChain filterChain = new FilterChain(filters);
        filterChain.doFilter(params, taskFlowContext);
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public ITask getStartTask() {
        return null;
    }

    @Override
    public ITask getTask(String taskId) {
        return null;
    }

    public String getStartTaskId() {
        return startTaskId;
    }

    @Override
    public void setStartTaskId(String startTaskId) {
        this.startTaskId = startTaskId;
    }

    public Map<String, ITask> getTaskMap() {
        return taskMap;
    }

    @Override
    public void setTask(List<TaskDefinition> taskDefinitionList) {
        for (TaskDefinition taskDefinition : taskDefinitionList) {
            String taskId = taskDefinition.getId();
            DefaultTask task = new DefaultTask();
            task.setTaskId(taskId);
            if (taskDefinition.getResults() != null) {
                task.setResultDefinitionList(taskDefinition.getResults().getResults());
            }
            InvokeDefinition invokeDefinition = taskDefinition.getInvoke();
            // 目标方法
            Object invokeObject = SpringBeanHelper.getBean(invokeDefinition.getBean(), invokeDefinition.getClassName());
            task.setInvokeObject(invokeObject);
            task.setMethod(invokeDefinition.getMethod());
            taskMap.put(taskId, task);
        }
    }
}