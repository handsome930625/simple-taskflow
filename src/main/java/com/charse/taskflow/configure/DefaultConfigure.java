package com.charse.taskflow.configure;

import com.charse.taskflow.Exception.DefinitionException;
import com.charse.taskflow.configure.definition.FilterDefinition;
import com.charse.taskflow.configure.definition.TaskFlowDefinition;
import com.charse.taskflow.configure.definition.TaskFlowsDefinition;
import com.charse.taskflow.constant.ErrorMessageConstant;
import com.charse.taskflow.enumer.TaskFlowEnum;
import com.charse.taskflow.filter.Filter;
import com.charse.taskflow.filter.FirstFilter;
import com.charse.taskflow.filter.LastFilter;
import com.charse.taskflow.taskflow.DefaultTaskFlow;
import com.charse.taskflow.taskflow.ITaskFlow;
import com.charse.taskflow.utils.SpringBeanHelper;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * @Author: wangyj
 * @Date: 2018/4/10 22:43
 * @Description: 默认的xml解析器
 **/
public class DefaultConfigure implements IConfigure {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfigure.class);
    /**
     * 引用apache xml 规则解析器
     */
    private Digester xmlConfigureParser;

    /**
     * xml 转 java 类
     */
    private TaskFlowsDefinition taskFlowsDefinition;

    /**
     * <p>功能描述: 初始化配置文件解析器</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/10 22:45 </p>
     *
     * @param configResources 配置的task xml 文件
     */
    public DefaultConfigure(List<Resource> configResources) {
        try {
            Resource resource = new ClassPathResource(DEFAULT_TASK_FLOW_RULE_XML);
            xmlConfigureParser = DigesterLoader.createDigester(resource.getURL());
            xmlConfigureParser.setValidating(false);
            xmlConfigureParser.setUseContextClassLoader(true);
        } catch (Exception e) {
            // 解析xml 规则失败，让程序停止
            throw new DefinitionException(ErrorMessageConstant.DEFINITION_XML_RULE_ERROR, e);
        }
        taskFlowsDefinition = parse(configResources);
    }

    /**
     * <p>功能描述: 将所有文件的任务流解析完整合到一起</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/11 20:29 </p>
     *
     * @param resources 所有 taskflow 文件
     * @return 所有的任务流
     */
    private TaskFlowsDefinition parse(List<Resource> resources) {
        if (CollectionUtils.isEmpty(resources)) {
            LOGGER.warn("taskflow files is empty,please check it");
            return null;
        }

        Map<String, TaskFlowDefinition> taskFlowDefinitionByTaskFlowIdMap = new LinkedHashMap<>();
        for (Resource resource : resources) {
            TaskFlowsDefinition taskFlowsDefinition = parseImpl(resource);
            for (TaskFlowDefinition taskFlowDefinition : taskFlowsDefinition.getTaskFlowList()) {
                if (taskFlowDefinitionByTaskFlowIdMap.containsKey(taskFlowDefinition.getId())) {
                    String filePath = "";
                    try {
                        filePath = resource.getFile().getAbsolutePath();
                    } catch (IOException e) {
                        LOGGER.error("get same taskflow filename failed");
                    }
                    LOGGER.warn("there has two same taskflow file:{}", filePath);
                    throw new IllegalArgumentException("'taskflow.id - " + taskFlowDefinition.getId() + " repeat, " + filePath);
                }
                taskFlowDefinitionByTaskFlowIdMap.put(taskFlowDefinition.getId(), taskFlowDefinition);
            }
        }
        // map 转 list
        TaskFlowsDefinition taskFlowsDefinition = new TaskFlowsDefinition();
        for (TaskFlowDefinition taskFlowDefinition : taskFlowDefinitionByTaskFlowIdMap.values()) {
            taskFlowsDefinition.addTaskFlow(taskFlowDefinition);
        }
        return taskFlowsDefinition;
    }

    /**
     * <p>功能描述: 将resource 解析成配置对象</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/11 20:27 </p>
     *
     * @param resource 需要解析的文件
     * @return 每个文件解析的结果
     */
    private TaskFlowsDefinition parseImpl(Resource resource) {
        try {
            return (TaskFlowsDefinition) xmlConfigureParser.parse(resource.getInputStream());
        } catch (Exception e) {
            throw new DefinitionException(ErrorMessageConstant.DEFINITION_XML_TASKFLOW_ERROR, e);
        }
    }


    @Override
    public List<ITaskFlow> buildTaskFlow() {
        List<ITaskFlow> taskFlowList = new ArrayList<>();
        for (TaskFlowDefinition taskFlowDefinition : taskFlowsDefinition.getTaskFlowList()) {
            // 目前就一种实现方式,不排除出现多种情况
            ITaskFlow taskFlow;
            if (StringUtils.isBlank(taskFlowDefinition.getImplType())
                    || TaskFlowEnum.DEFAULT.getValue().equals(taskFlowDefinition.getImplType())) {
                DefaultTaskFlow defaultTaskFlow = new DefaultTaskFlow();
                defaultTaskFlow.setId(taskFlowDefinition.getId());
                // 组装过滤器
                LinkedList<Filter> filterList = new LinkedList<>();
                filterList.add(new FirstFilter());
                List<FilterDefinition> filterDefinitionList = taskFlowDefinition.getFilters().getFilterList();
                for (FilterDefinition filterDefinition : filterDefinitionList) {
                    Filter filter = SpringBeanHelper.getBean(filterDefinition.getBeanId(), filterDefinition.getClassName(), Filter.class);
                    filterList.add(filter);
                }
                filterList.add(new LastFilter());
                defaultTaskFlow.setFilters(filterList);
                taskFlow = defaultTaskFlow;
            } else {
                continue;
            }
            taskFlow.setStartTaskId(taskFlowDefinition.getStartTask());
            taskFlow.setTask(taskFlowDefinition.getTaskList());
            taskFlowList.add(taskFlow);
        }
        return taskFlowList;
    }

    public static void main(String[] args) {
//        Resource[] resources = new Resource[1];
//        Resource resource = new ClassPathResource("taskflow-example.xml");
//        resources[0] = resource;
//        IConfigure configure = new DefaultConfigure(resources);
//        try {
//            configure.buildTaskFlow();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
