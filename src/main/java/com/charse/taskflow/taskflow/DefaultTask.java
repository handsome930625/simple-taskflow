package com.charse.taskflow.taskflow;

import com.charse.taskflow.Exception.TaskFlowNotFoundException;
import com.charse.taskflow.configure.definition.ResultDefinition;
import com.charse.taskflow.utils.SpringExpressionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (!(invokeObject instanceof ITaskHandler)) {
            throw new TaskFlowNotFoundException(invokeObject.getClass().getName() + "don't implement ITaskHandler interface");
        }
        ITaskHandler taskHandler = (ITaskHandler) invokeObject;
        return taskHandler.handle(params, taskFlowContext);
    }

    @Override
    public ITask nextTask(Object resultValue, Object params, TaskFlowContext taskFlowContext) {
        boolean isResultValueEmpty = resultValue == null || StringUtils.isBlank(resultValue.toString());
        if (isResultValueEmpty && CollectionUtils.isEmpty(resultDefinitionList)) {
            return null;
        } else if (isResultValueEmpty) {
            throw new IllegalArgumentException("任务处理的返回值不能为空,taskFlowId:" + taskFlowContext.getTaskFlow().getId() + ", taskId:" + taskId);
        }
        if (CollectionUtils.isEmpty(resultDefinitionList)) {
            return null;
        }
        String nextTaskId = null;
        for (ResultDefinition resultDefinition : resultDefinitionList) {
            String resultDefinitionName = resultDefinition.getName().trim();
            if (resultDefinitionName.startsWith("#")) {
                SpringExpressionUtils el = SpringExpressionUtils.getInstance();
                Map<String, Object> contextParamMap = new HashMap<>();
                contextParamMap.put("R", resultValue);
                contextParamMap.put("P", params);
                contextParamMap.put("C", taskFlowContext);
                boolean isPass = el.parseSpel(resultDefinitionName, contextParamMap, Boolean.class);
                if (isPass) {
                    nextTaskId = resultDefinition.getNextTask();
                    break;
                }
            } else if (resultValue.toString().trim().equals(resultDefinitionName)) {
                nextTaskId = resultDefinition.getNextTask();
                break;
            }
        }

        if (nextTaskId == null) {
            throw new IllegalArgumentException(resultValue + "未能匹配到下游任务，taskFlowId:" + taskFlowContext.getTaskFlow().getId() + ", taskId:" + taskId);
        }
        return taskFlowContext.getTaskFlow().getTask(nextTaskId);
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