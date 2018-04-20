package com.charse.taskflow.taskflow.manager;

import com.charse.taskflow.Exception.TaskFlowNotFoundException;
import com.charse.taskflow.configure.DefaultConfigure;
import com.charse.taskflow.configure.IConfigure;
import com.charse.taskflow.taskflow.ITaskFlow;
import com.charse.taskflow.taskflow.TaskFlowContext;
import com.charse.taskflow.utils.ResourceUtils;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.util.*;

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
        List<Resource> resourceList = new ArrayList<Resource>();
        for (String locationPattern : locations) {
            if (StringUtils.isEmpty(locationPattern)) {
                continue;
            }

            Resource[] resources = ResourceUtils.getResources(locationPattern);
            resourceList.addAll(Arrays.asList(resources));
        }
        resources = resourceList;
    }
}
