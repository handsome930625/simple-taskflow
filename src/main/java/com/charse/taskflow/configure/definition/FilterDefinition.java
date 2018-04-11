package com.charse.taskflow.configure.definition;

import java.io.Serializable;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 19:16
 * @Description: 过滤器解析
 **/
public class FilterDefinition implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 3683634682417487302L;

    /**
     * 过滤器类名
     */
    private String className;

    /**
     * 过滤器beanId (spring ioc)
     */
    private String beanId;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }
}