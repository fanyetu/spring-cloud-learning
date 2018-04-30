package cn.fanyetu.product.server.service;


import cn.fanyetu.product.common.DecreaseStockInput;
import cn.fanyetu.product.common.ProductInfoOutput;
import cn.fanyetu.product.server.dataobject.ProductInfo;

import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
public interface ProductService {

    /**
     * 扣库存
     *
     * @param inputList
     */
    void decreaseStop(List<DecreaseStockInput> inputList);

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
    List<ProductInfoOutput> findList(List<String> productIdList);

}
