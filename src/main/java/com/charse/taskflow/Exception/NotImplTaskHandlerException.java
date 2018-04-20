package com.charse.taskflow.Exception;

/**
 * description:
 *
 * @author wangyj on 2018/4/13
 */
public class NotImplTaskHandlerException extends RuntimeException {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6082826494392712501L;

    /**
     * <p>功能描述: 没有实现ITaskHandler 异常</p>
     * <p>创建人: wangyj </p>
     * <p>创建日期: 2018/4/13 17:41 </p>
     *
     * @param message msg
     */
    public NotImplTaskHandlerException(String message) {
        super(message);
    }
}
