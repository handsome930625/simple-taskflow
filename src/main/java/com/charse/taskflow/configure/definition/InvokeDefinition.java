package com.charse.taskflow.configure.definition;

import java.io.Serializable;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 19:16
 * @Description: 任务处理器解析
 **/
public class InvokeDefinition implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 2602311055548127334L;

    /**
     * 任务执行类名
     */
    private String className;

    /**
     * 任务beanId (spring ioc)
     */
    private String bean;

    /**
     * 任务方法
     */
    private String method;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}