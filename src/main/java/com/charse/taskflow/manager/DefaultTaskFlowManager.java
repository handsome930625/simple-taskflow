package com.charse.taskflow.manager;

import com.charse.taskflow.Exception.TaskFlowNotFoundException;
import com.charse.taskflow.configure.DefaultConfigure;
import com.charse.taskflow.configure.IConfigure;
import com.charse.taskflow.taskflow.ITaskFlow;
import com.charse.taskflow.taskflow.TaskFlowContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyj
 * @Date: 2018/4/10 22:30
 * @Description: 默认管理器的实现
 **/
public class DefaultTaskFlowManager implements ITaskFlowManager {

    /**
     * 资源文件
     */
    private List<Resource> resources = new ArrayList<>();
    /**
     * taskflow map
     */
    private Map<String, ITaskFlow> taskFlowMap = new HashMap<>();

    @Override
    public void initManager() {
        IConfigure configure = new DefaultConfigure(resources);
        List<ITaskFlow> taskFlowList = configure.buildTaskFlow();
        for (ITaskFlow taskFlow : taskFlowList) {
            taskFlowMap.put(taskFlow.getId(), taskFlow);
        }
    }

    @Override
    public Map<String, Object> executeFlow(String taskFlowId, Object params) throws Exception {
        ITaskFlow taskFlow = taskFlowMap.get(taskFlowId);
        if (taskFlow == null) {
            throw new TaskFlowNotFoundException("'taskFlowId - " + taskFlowId + "'不存在");
        }
        TaskFlowContext taskFlowContext = new TaskFlowContext(taskFlow);
        taskFlow.executeTask(params, taskFlowContext);
        return taskFlowContext.getReturnValues();
    }

    @Override
    public void destroyManager() {

    }


    public void setLocations(String[] locations) {
        if (locations == null || locations.length == 0) {
            throw new IllegalArgumentException("locations 参数不能为空");
        }
        for (String location : locations) {
            Resource resource = new ClassPathResource(location);
            resources.add(resource);
        }
    }
}
