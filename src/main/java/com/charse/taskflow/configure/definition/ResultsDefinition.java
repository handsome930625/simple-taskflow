package com.charse.taskflow.configure.definition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: wangyj
 * @Date: 2018/4/11 19:16
 * @Description: 任务处理结果路由集合解析
 **/
public class ResultsDefinition implements Serializable {

    /**
     * 序列id
     */
    private static final long serialVersionUID = -4990095181304557234L;

    /**
     * 路由集合
     */
    private List<ResultDefinition> results = new ArrayList<>();

    public void addResult(ResultDefinition result) {
        results.add(result);
    }

    public List<ResultDefinition> getResults() {
        return results;
    }

    public void setResults(List<ResultDefinition> results) {
        this.results = results;
    }
}