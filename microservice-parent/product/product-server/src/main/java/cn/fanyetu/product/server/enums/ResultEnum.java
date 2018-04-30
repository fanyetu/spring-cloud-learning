package cn.fanyetu.product.server.enums;

import lombok.Getter;

/**
 * @author zhanghaonan
 * @date 2018/4/29
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXISTS(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
