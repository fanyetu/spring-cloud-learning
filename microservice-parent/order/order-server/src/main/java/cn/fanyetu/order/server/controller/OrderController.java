package cn.fanyetu.order.server.controller;

import cn.fanyetu.order.server.dto.OrderDTO;
import cn.fanyetu.order.server.service.OrderService;
import cn.fanyetu.order.server.utils.ResultVOUtils;
import cn.fanyetu.order.server.viewobject.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @PostMapping("/finish")
    public ResultVO<OrderDTO> finish(@RequestParam String orderId){
        return ResultVOUtils.success(orderService.finish(orderId));
    }

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@RequestBody OrderDTO orderDTO) {
        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());

        return ResultVOUtils.success(map);
    }
}
