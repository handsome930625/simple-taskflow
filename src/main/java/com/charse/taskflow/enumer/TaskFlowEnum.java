package com.charse.taskflow.enumer;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 20:55
 * @Description: taskflow 实现类型
 **/
public enum TaskFlowEnum {

    /**
     * 默认
     */
    DEFAULT("default");

    /**
     * taskflow 实现类型
     */
    private String value;

    TaskFlowEnum(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
