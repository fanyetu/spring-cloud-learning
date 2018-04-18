package cn.fanyetu.product.service.impl;

import cn.fanyetu.product.dataobject.ProductCategory;
import cn.fanyetu.product.repository.ProductCategoryRepository;
import cn.fanyetu.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
