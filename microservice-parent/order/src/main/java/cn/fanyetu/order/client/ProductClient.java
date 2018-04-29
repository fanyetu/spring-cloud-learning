package cn.fanyetu.order.client;

import cn.fanyetu.order.dataobject.CartDto;
import cn.fanyetu.order.dataobject.ProductInfo;
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

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStop")
    void decreaseStop(@RequestBody List<CartDto> cartDtoList);
}
