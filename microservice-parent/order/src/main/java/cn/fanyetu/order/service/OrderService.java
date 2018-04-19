package cn.fanyetu.order.service;

import cn.fanyetu.order.dto.OrderDTO;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);
}
