package cn.fanyetu.product.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Data
public class ProductVO {

    /**
     * 语义是类目名称，在json转换时转为name
     */
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;


}
