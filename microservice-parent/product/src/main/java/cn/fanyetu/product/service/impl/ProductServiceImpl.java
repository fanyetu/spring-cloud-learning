package cn.fanyetu.product.service.impl;

import cn.fanyetu.product.dataobject.ProductInfo;
import cn.fanyetu.product.enums.ProductStatusEnum;
import cn.fanyetu.product.repository.ProductInfoRepository;
import cn.fanyetu.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
