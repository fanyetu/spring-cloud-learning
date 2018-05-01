package cn.fanyetu.order.server.controller;

import cn.fanyetu.order.server.property.GirlProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaonan
 * @date 2018/5/1
 */
@RestController
public class GirlController {

    @Autowired
    private GirlProperty girlProperty;

    @RequestMapping("/girl/print")
    public String print() {
        return "name: " + girlProperty.getName() + " age: " + girlProperty.getAge();
    }
}
