package cn.fanyetu.order.server.service;


import cn.fanyetu.order.server.dto.OrderDTO;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);
}
