package com.charse.taskflow.filter.local;

import java.util.UUID;

/**
 * description:
 *
 * @author wangyj on 2018/4/13
 */
public class TaskFlowVar {
    /**
     * 开始时间
     */
    private long startTime;
    /**
     * 结束时间
     */
    private long endTime;
    /**
     * taskId
     */
    private String taskId;
    /**
     * 唯一id
     */
    private String uuid;

    /**
     * 任务执行前方法
     */
    public void beforeExecute(String taskId) {
        this.startTime = System.currentTimeMillis();
        uuid = UUID.randomUUID().toString();
        this.taskId = taskId;
    }

    /**
     * 执行之后方法
     */
    public void afterExecute(long startNano) {
        long endTime = System.currentTimeMillis();
    }


    public String successLog() {
        StringBuilder builder = new StringBuilder();
        builder.append("taskflow-id:").append(taskId).append("唯一性id:").append(uuid).append("耗时:").append("");
        return super.toString();
    }
}
