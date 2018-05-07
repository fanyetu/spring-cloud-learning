package cn.fanyetu.order.server.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * 通过spring cloud stream 发送消息
 *
 * @author zhanghaonan
 * @date 2018/5/2
 */
@RestController("/stream")
public class StreamMessageController {

//    @Autowired
//    private StreamClient streamClient;
//
//    @RequestMapping("/send")
//    public String send(){
//        String message = "now " + new Date();
//        // 通过streamClient发送消息
//        streamClient.output().send(MessageBuilder.withPayload(message).build());
//        return "ok";
//    }

}
