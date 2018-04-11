package com.charse.taskflow.configure.definition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 19:16
 * @Description: 过滤器集合解析
 **/
public class FiltersDefinition implements Serializable {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6904899168458958399L;

    /**
     * 过滤器集合
     */
    private List<FilterDefinition> filterList = new ArrayList<>();


    public void addFilter(FilterDefinition filter) {
        filterList.add(filter);
    }

    public List<FilterDefinition> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<FilterDefinition> filterList) {
        this.filterList = filterList;
    }
}