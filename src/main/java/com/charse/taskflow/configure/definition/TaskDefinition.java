package com.charse.taskflow.configure.definition;

import java.io.Serializable;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 19:16
 * @Description: 任务解析
 **/
public class TaskDefinition implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -9209248275364816431L;

    /**
     * 任务ID
     */
    private String id;

    /**
     * 任务类名(保留)
     */
    private String className;

    /**
     * 任务处理器
     */
    private InvokeDefinition invoke;

    /**
     * 任务路由集合
     */
    private ResultsDefinition results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public InvokeDefinition getInvoke() {
        return invoke;
    }

    public void setInvoke(InvokeDefinition invoke) {
        this.invoke = invoke;
    }

    public ResultsDefinition getResults() {
        return results;
    }

    public void setResults(ResultsDefinition results) {
        this.results = results;
    }
}