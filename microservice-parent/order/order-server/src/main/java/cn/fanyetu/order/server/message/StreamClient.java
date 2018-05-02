package cn.fanyetu.order.server.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 通过spring cloud stream 操作消息队列
 *
 * @author zhanghaonan
 * @date 2018/5/2
 */
public interface StreamClient {

    String INPUT = "streamMessage";

    /**
     * 接收端
     * @return
     */
    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    /**
     * 发送端
     * @return
     */
    @Output(StreamClient.INPUT)
    MessageChannel output();

}
