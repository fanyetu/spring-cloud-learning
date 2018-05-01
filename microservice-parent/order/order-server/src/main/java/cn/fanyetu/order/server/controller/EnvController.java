package cn.fanyetu.order.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaonan
 * @date 2018/5/1
 */
@RestController
@RefreshScope // 加上这个注解才能进行配置刷新
public class EnvController {

    @Value("${env}")
    private String env;

    @RequestMapping("/env/print")
    public String print(){
        return this.env;
    }
}
