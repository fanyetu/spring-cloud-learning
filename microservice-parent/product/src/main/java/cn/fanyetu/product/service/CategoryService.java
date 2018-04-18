package cn.fanyetu.product.service;

import cn.fanyetu.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
public interface CategoryService {

    /**
     * 查询指定的类目
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}