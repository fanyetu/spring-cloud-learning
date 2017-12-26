package cn.fanyetu.microservicesimpleconsumermovie.controller;

import cn.fanyetu.microservicesimpleconsumermovie.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 通过id获取user
     * 使用restTemplate调用其它服务
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return this.restTemplate.getForObject("http://localhost:8080/" + id, User.class);
    }
}
