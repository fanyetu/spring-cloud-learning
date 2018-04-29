package cn.fanyetu.order.dataobject;

import lombok.Data;

/**
 * @author zhanghaonan
 * @date 2018/4/29
 */
@Data
public class CartDto {

    public CartDto() {
    }

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;
}
