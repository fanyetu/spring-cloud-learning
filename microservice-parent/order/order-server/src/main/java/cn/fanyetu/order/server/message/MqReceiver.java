package cn.fanyetu.order.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhanghaonan
 * @date 2018/5/1
 */
@Component
@Slf4j
public class MqReceiver {

    @RabbitListener(queues = "myQueue")
    public void process(String msg) {
        log.info("MqReceiver: {}", msg);
    }
}
