package cn.fanyetu.product.server.exception;


import cn.fanyetu.product.server.enums.ResultEnum;

/**
 * @author zhanghaonan
 * @date 2018/4/29
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
