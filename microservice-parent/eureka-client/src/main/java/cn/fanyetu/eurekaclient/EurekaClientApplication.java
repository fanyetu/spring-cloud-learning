package cn.fanyetu.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhanghaonan
 * @date 2018/4/17
 */
//@EnableEurekaClient
@EnableDiscoveryClient // 和EnableEurekaClient效果相同，但是使用这个的的时候可以使用非Eureka的注册中心
@SpringBootApplication
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
