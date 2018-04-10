package com.charse.taskflow.configure;

import com.charse.taskflow.taskFlow.ITaskFlow;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * @Author: wangyj
 * @Date: 2018/4/10 22:43
 * @Description: 默认的xml解析器
 **/
public class DefaultConfigure implements IConfigure {

    /**
     * <p>功能描述: 初始化配置文件解析器</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/10 22:45 </p>
     * @param configResources 配置的task xml 文件
     */
    public DefaultConfigure(Resource[] configResources) {
    }

    @Override
    public List<ITaskFlow> buildTaskFlow() {
        return null;
    }
}
