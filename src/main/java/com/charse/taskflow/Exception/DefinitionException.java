package com.charse.taskflow.Exception;

/**
 * @Author: wangyj
 * @Date: 2018/4/11 20:05
 * @Description: 解析异常
 **/
public class DefinitionException extends RuntimeException {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6082826494392712501L;

    /**
     * 解析错误异常类
     */
    public DefinitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
