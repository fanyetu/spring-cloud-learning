package cn.fanyetu.product.service.impl;

import cn.fanyetu.product.ProductApplicationTest;
import cn.fanyetu.product.dataobject.ProductInfo;
import cn.fanyetu.product.dto.CartDto;
import cn.fanyetu.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 不需要使用测试的那两个注解，
 * 使用component注解并继承测试主类可以实现同样的效果
 *
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Component
public class ProductServiceImplTest extends ProductApplicationTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findList(){
        List<ProductInfo> productInfoList = productService.findList(Arrays.asList("157875196366160022",
                "157875227953464068"));
        Assert.assertTrue(productInfoList.size() > 0);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productService.findUpAll();
        Assert.assertTrue(upAll.size() > 0);
    }

    @Test
    public void decreaseStop() throws Exception{
        CartDto cartDto = new CartDto("157875196366160022", 2);
        productService.decreaseStop(Collections.singletonList(cartDto));

    }
}