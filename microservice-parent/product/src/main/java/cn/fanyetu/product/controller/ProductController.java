package cn.fanyetu.product.controller;

import cn.fanyetu.product.dataobject.ProductCategory;
import cn.fanyetu.product.dataobject.ProductInfo;
import cn.fanyetu.product.dto.CartDto;
import cn.fanyetu.product.service.CategoryService;
import cn.fanyetu.product.service.ProductService;
import cn.fanyetu.product.utils.ResultVOUtils;
import cn.fanyetu.product.viewobject.ProductInfoVO;
import cn.fanyetu.product.viewobject.ProductVO;
import cn.fanyetu.product.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 扣库存
     *
     * @param cartDtoList
     */
    @PostMapping("/decreaseStop")
    public void decreaseStop(@RequestBody List<CartDto> cartDtoList) {
        productService.decreaseStop(cartDtoList);
    }

    /**
     * 获取商品列表（给订单服务用s）
     *
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findList(productIdList);
    }

    @GetMapping("/list")
    public ResultVO<List<ProductVO>> list() {
        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtils.success(productVOList);
    }


}
