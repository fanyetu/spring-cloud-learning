package cn.fanyetu.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 使用fegin调用接口
 * <p>
 * fegin不是rpc，是伪rpc
 *
 * @author zhanghaonan
 * @date 2018/4/24
 */
@FeignClient(name = "product")
public interface ProduceClient {

    @GetMapping("/server/msg")
    String productMsg();
}
