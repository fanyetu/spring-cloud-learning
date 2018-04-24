package cn.fanyetu.order.controller;

import cn.fanyetu.order.client.ProduceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProduceClient produceClient;

    /**
     * 演示调用restTemplate的三种方式
     *
     * @return
     */
    @GetMapping("/msg")
    public String clientMsg() {
        // 第一种方式(使用restTemplate调用服务接口，将服务端的接口地址写死)
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8081/server/msg", String.class);
//        log.info("response = {}", response);

        // 第二种方式(使用loadBalancerClient选择一个服务端，拼装服务端的url，然后通过restTemplate进行调用)
//        ServiceInstance instance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", instance.getHost(), instance.getPort()) + "/server/msg";
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);
//        log.info("response = {}", response);

        // 第三种方式(通过@LoadBalanced创建restTemplate的bean，然后直接使用服务端的服务名即可调用)
        String response = restTemplate.getForObject("http://PRODUCT/server/msg", String.class);
        log.info("response = {}", response);

        return response;
    }

    /**
     * 使用feign调用接口
     *
     * @return
     */
    @GetMapping("/productMsg")
    public String productMsg() {
        String msg = produceClient.productMsg();

        log.info("response = {}", msg);
        return msg;
    }
}
