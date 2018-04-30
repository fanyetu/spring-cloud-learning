package cn.fanyetu.product.server.impl;

import cn.fanyetu.product.server.ProductApplicationTest;
import cn.fanyetu.product.server.dataobject.ProductCategory;
import cn.fanyetu.product.server.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Component
public class CategoryServiceImplTest extends ProductApplicationTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = categoryService.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(list.size() > 0);
    }
}