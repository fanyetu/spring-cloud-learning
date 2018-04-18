package cn.fanyetu.product.service;

import cn.fanyetu.product.dataobject.ProductInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
public interface ProductService {

    /**
     * 查询所有上架商品列表
     */
    List<ProductInfo> findUpAll();

}
