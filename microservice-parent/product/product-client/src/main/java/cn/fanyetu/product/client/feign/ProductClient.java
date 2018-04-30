package cn.fanyetu.product.client.feign;

import cn.fanyetu.product.common.DecreaseStockInput;
import cn.fanyetu.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 使用fegin调用接口
 * <p>
 * fegin不是rpc，是伪rpc
 *
 * @author zhanghaonan
 * @date 2018/4/24
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/server/msg")
    String productMsg();

    /**
     * 获取商品列表
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    /**
     * 减库存
     *
     * @param cartDtoList
     */
    @PostMapping("/product/decreaseStop")
    void decreaseStop(@RequestBody List<DecreaseStockInput> inputList);
}
