package cn.fanyetu.order.server.message;

import cn.fanyetu.order.server.utils.JsonUtils;
import cn.fanyetu.product.common.ProductInfoOutput;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/5/7
 */
@Component
@Slf4j
public class ProductInfoReveiver {

    public static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String msg){

        List<ProductInfoOutput> result = JsonUtils.fromJson(msg, new
                TypeReference<List<ProductInfoOutput>>() {
        });
        log.info("从{}接收到消息: {}", "productInfo", result);

        // 存储到redis中
        for (ProductInfoOutput productInfoOutput : result) {
            redisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
