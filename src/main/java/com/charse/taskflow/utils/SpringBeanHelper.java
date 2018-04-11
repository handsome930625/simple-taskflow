package com.charse.taskflow.utils;

import com.charse.taskflow.configure.DefaultConfigure;
import com.charse.taskflow.constant.ErrorMessageConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 21:32
 * @Description: 获取xml上注册的bean
 **/
public class SpringBeanHelper implements ApplicationContextAware {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfigure.class);

    /**
     * spring 上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * 本地上下文
     */
    private static Map<String, Object> localIoc = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * <p>功能描述: 如果有beanId 选择去spring ioc 容器中获取，如果没有选择自己加载</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/11 21:40 </p>
     *
     * @param bean      spring beanId
     * @param className 全类名
     * @param clazz     bean的类型
     * @return bean 的例子
     * @throws Exception 异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String bean, String className, Class<T> clazz) throws Exception {
        if (StringUtils.isNotBlank(bean)) {
            return applicationContext.getBean(bean, clazz);
        } else if (StringUtils.isNotBlank(className)) {
            try {
                if (localIoc.containsKey(className)) {
                    return (T) localIoc.get(className);
                }
                T t = (T) Class.forName(className).newInstance();
                // 并发的时候可能会出现多例，但是之后都会被gc掉，所以可以忽略
                localIoc.put(className, t);
                return t;
            } catch (Exception e) {
                LOGGER.error(ErrorMessageConstant.CLASS_NOT_FOUND + ":" + className, e);
                throw new ClassNotFoundException(e.getMessage(), e);
            }
        }
        return null;
    }

    public static Object getBean(String bean, String className) throws Exception {
        return getBean(bean, className, Object.class);
    }
}


