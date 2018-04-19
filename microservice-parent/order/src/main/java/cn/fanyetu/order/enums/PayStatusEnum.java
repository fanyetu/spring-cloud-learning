package cn.fanyetu.order.enums;

import lombok.Getter;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
@Getter
public enum PayStatusEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    PayStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    private Integer status;

    private String message;
}
