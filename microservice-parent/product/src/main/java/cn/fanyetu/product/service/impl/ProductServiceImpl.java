package cn.fanyetu.product.service.impl;

import cn.fanyetu.product.dataobject.ProductInfo;
import cn.fanyetu.product.dto.CartDto;
import cn.fanyetu.product.enums.ProductStatusEnum;
import cn.fanyetu.product.enums.ResultEnum;
import cn.fanyetu.product.exception.ProductException;
import cn.fanyetu.product.repository.ProductInfoRepository;
import cn.fanyetu.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    @Transactional(rollbackFor = ProductException.class)
    public void decreaseStop(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDto.getProductId());
            // 判断商品是否存在
            if (!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXISTS);
            }

            ProductInfo productInfo = productInfoOptional.get();

            // 判断库存是否足够
            int result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            // 完成扣库存操作
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }
}
