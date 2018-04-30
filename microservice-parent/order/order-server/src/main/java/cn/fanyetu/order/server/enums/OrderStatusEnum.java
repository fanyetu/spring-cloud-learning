package cn.fanyetu.order.server.enums;

import lombok.Getter;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISH(1, "完结"),
    CANCEL(2, "取消");

    OrderStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    private Integer status;

    private String message;


}
