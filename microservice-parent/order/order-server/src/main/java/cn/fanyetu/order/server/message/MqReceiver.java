package cn.fanyetu.order.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhanghaonan
 * @date 2018/5/1
 */
@Component
@Slf4j
public class MqReceiver {

    // 1. 手动创建队列并监听@RabbitListener(queues = "myQueue")
    // 2. 自动创建队列并监听@RabbitListener(queuesToDeclare = @Queue("myQueue"))
    // 3. 自动创建队列，并将Exchange和Queue进行绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String msg) {
        log.info("MqReceiver: {}", msg);
    }


    // 通过exchange 和 routerKey 实现消息的路由

    /**
     * 电脑提供商服务
     *
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("orderExchange"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void computerProcess(String msg) {
        log.info("computer MqReceiver: {}", msg);
    }

    /**
     * 水果提供商服务
     *
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("orderExchange"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void fruitProcess(String msg) {
        log.info("fruit MqReceiver: {}", msg);
    }
}
