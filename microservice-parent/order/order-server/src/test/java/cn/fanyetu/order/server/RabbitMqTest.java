package cn.fanyetu.order.server;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhanghaonan
 * @date 2018/5/1
 */
@Component
public class RabbitMqTest extends OrderApplicationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test1() {
        // 发送消息
        rabbitTemplate.convertAndSend("myQueue", "now: " + new Date());
    }

    /**
     * 向电脑提供商服务发送消息，通过routerKey进行路由
     */
    @Test
    public void test2(){
        rabbitTemplate.convertAndSend("orderExchange", "computer", "now: " + new Date());
    }
}
