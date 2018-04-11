package com.charse.taskflow.filter;

import com.charse.taskflow.taskflow.TaskFlowContext;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 21:09
 * @Description:
 **/
public class FilterChain {

    /**
     * 过滤链
     */
    private LinkedList<Filter> filters;

    public FilterChain(LinkedList<Filter> filters) {
        this.filters = filters;
    }

    /**
     * <p>功能描述: </p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/11 21:28 </p>
     *
     * @param params          用户自定义参数
     * @param taskFlowContext 上下文
     * @throws Exception 执行异常
     */
    public void doFilter(Object params, TaskFlowContext taskFlowContext) throws Exception {
        Filter filter;
        try {
            filter = filters.pop();
        } catch (NoSuchElementException e) {
            // 链到头了 直接返回
            return;
        }
        filter.doFilter(params, taskFlowContext, this);
    }
}
