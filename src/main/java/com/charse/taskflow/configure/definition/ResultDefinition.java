package com.charse.taskflow.configure.definition;

import java.io.Serializable;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 19:16
 * @Description: 任务处理结果路由解析
 **/
public class ResultDefinition implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6569167310143979310L;

    /**
     * 路由名称，支持EL
     */
    private String name;

    /**
     * 下一个任务
     */
    private String nextTask;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextTask() {
        return nextTask;
    }

    public void setNextTask(String nextTask) {
        this.nextTask = nextTask;
    }
}