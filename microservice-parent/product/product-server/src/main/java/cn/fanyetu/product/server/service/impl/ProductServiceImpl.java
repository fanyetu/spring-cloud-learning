package cn.fanyetu.product.server.service.impl;

import cn.fanyetu.product.common.DecreaseStockInput;
import cn.fanyetu.product.common.ProductInfoOutput;
import cn.fanyetu.product.server.dataobject.ProductInfo;
import cn.fanyetu.product.server.enums.ProductStatusEnum;
import cn.fanyetu.product.server.enums.ResultEnum;
import cn.fanyetu.product.server.exception.ProductException;
import cn.fanyetu.product.server.repository.ProductInfoRepository;
import cn.fanyetu.product.server.service.ProductService;
import cn.fanyetu.product.server.utils.JsonUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void decreaseStop(List<DecreaseStockInput> inputList) {
        List<ProductInfo> list = this.decreaseStopProcess(inputList);

        List<ProductInfoOutput> productInfoOutputList = list.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());

        // 发送mq消息
        amqpTemplate.convertAndSend("productInfo", JsonUtils.toJson(productInfoOutputList));
    }


    @Transactional(rollbackFor = ProductException.class)
    public List<ProductInfo> decreaseStopProcess(List<DecreaseStockInput> inputList) {
        List<ProductInfo> productInfos = new ArrayList<>();
        for (DecreaseStockInput input : inputList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(input.getProductId());
            // 判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXISTS);
            }

            ProductInfo productInfo = productInfoOptional.get();

            // 判断库存是否足够
            int result = productInfo.getProductStock() - input.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            // 完成扣库存操作
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

            productInfos.add(productInfo);
        }
        return productInfos;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList)
                .stream()
                .map(e -> {
                    ProductInfoOutput productInfoOutput = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, productInfoOutput);
                    return productInfoOutput;
                })
                .collect(Collectors.toList());
    }
}
