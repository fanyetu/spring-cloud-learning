package cn.fanyetu.product.service;

import cn.fanyetu.product.dataobject.ProductInfo;
import cn.fanyetu.product.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
public interface ProductService {

    /**
     * 扣库存
     *
     * @param cartDtoList
     */
    void decreaseStop(List<CartDto> cartDtoList);

    /**
     * 查询所有上架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     *
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

}
