package cn.fanyetu.microservicesimpleprovideruser.controller;

import cn.fanyetu.microservicesimpleprovideruser.dao.UserRepository;
import cn.fanyetu.microservicesimpleprovideruser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 通过id获取一个用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id){
        User user = this.userRepository.findOne(id);
        return user;
    }
}
