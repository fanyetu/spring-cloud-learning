package cn.fanyetu.product.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping("/msg")
    public String serverMsg() {
        return "this is product msg";
    }
}
