package cn.fanyetu.order.server.message;

import cn.fanyetu.order.server.utils.JsonUtils;
import cn.fanyetu.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhanghaonan
 * @date 2018/5/7
 */
@Component
@Slf4j
public class ProductInfoReveiver {

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String msg){
        ProductInfoOutput productInfoOutput = JsonUtils.fromJson(msg, ProductInfoOutput.class);
        log.info("从{}接收到消息: {}", "productInfo", productInfoOutput);

        // 存储到redis中
    }
}
