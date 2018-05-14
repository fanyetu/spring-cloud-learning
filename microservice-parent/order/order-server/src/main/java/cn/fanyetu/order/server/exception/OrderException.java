package cn.fanyetu.order.server.exception;

import cn.fanyetu.order.server.enums.ResultEnums;

/**
 * @author zhanghaonan
 * @date 2018/5/14
 */
public class OrderException extends RuntimeException {

    public OrderException(ResultEnums resultEnums) {
        super(resultEnums.getMessage());
    }
}
