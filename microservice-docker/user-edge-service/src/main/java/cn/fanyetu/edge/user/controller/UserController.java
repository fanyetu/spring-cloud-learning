package cn.fanyetu.edge.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaonan
 * @date 2018/9/9
 */
@RestController
public class UserController {

    @PostMapping("/login")
    public void login(@RequestParam("username") String username,
                      @RequestParam("password") String password){

    }

}
