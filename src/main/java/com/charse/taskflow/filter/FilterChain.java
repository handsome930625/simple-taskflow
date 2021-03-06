package com.charse.taskflow.filter;

import com.charse.taskflow.taskflow.TaskFlowContext;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 21:09
 * @Description:
 **/
public class FilterChain {

    /**
     * 过滤链
     */
    private List<Filter> filters;

    private Iterator<Filter> iterator;

    public FilterChain(List<Filter> filters) {
        if (filters == null) {
            filters = Collections.emptyList();
        }
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
        if (this.iterator == null) {
            this.iterator = this.filters.iterator();
        }
        if (this.iterator.hasNext()) {
            Filter nextFilter = this.iterator.next();
            nextFilter.doFilter(params, taskFlowContext, this);
        }
    }
}
