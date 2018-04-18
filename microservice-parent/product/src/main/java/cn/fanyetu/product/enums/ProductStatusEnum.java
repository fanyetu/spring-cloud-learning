package cn.fanyetu.product.enums;

import lombok.Getter;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "上架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
