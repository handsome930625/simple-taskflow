package com.charse.taskflow.configure;

import com.charse.taskflow.taskflow.ITaskFlow;

import java.util.List;

/**
 * @Author: wangyj
 * @Date: 2018/4/10 22:32
 * @Description: 定义xml 解析的接口
 **/
public interface IConfigure {

    /**
     * 默认的task类
     */
    String DEFAULT_TASK_CLASS_NAME = "com.gome.architect.taskflow.DefaultTask";

    /**
     * 默认的xml规则
     */
    String DEFAULT_TASK_FLOW_RULE_XML = "TaskFlowRule.xml";

    /**
     * <p>功能描述: 读取配置文件创建task集合</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/10 22:41 </p>
     *
     * @return 每一个文件的task集合
     */
    List<ITaskFlow> buildTaskFlow();
}
