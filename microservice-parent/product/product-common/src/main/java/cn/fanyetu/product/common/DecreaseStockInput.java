package cn.fanyetu.product.common;

import lombok.Data;

/**
 * @author zhanghaonan
 * @date 2018/4/30
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
