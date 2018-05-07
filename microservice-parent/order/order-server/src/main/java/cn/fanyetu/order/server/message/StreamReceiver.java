package cn.fanyetu.order.server.message;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhanghaonan
 * @date 2018/5/2
 */
//@Component
//@EnableBinding(StreamClient.class) // 设置stream的binding
@Slf4j
public class StreamReceiver {

    /**
     * 接受消息
     * @param message
     */
//    @StreamListener(StreamClient.INPUT)
    public void process(Object message) {
        log.info("streamReceiver: {}", message);
    }
}
