package cn.fanyetu.apigetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zhanghaonan
 * @date 2018/5/8
 */
@SpringBootApplication
@EnableZuulProxy // 开启zuul转发
public class ApiGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGetwayApplication.class, args);
    }
}
