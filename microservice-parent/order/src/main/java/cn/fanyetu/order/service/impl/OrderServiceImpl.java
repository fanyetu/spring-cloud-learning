package cn.fanyetu.order.service.impl;

import cn.fanyetu.order.dataobject.OrderMaster;
import cn.fanyetu.order.dto.OrderDTO;
import cn.fanyetu.order.enums.OrderStatusEnum;
import cn.fanyetu.order.enums.PayStatusEnum;
import cn.fanyetu.order.repository.OrderDetailRepository;
import cn.fanyetu.order.repository.OrderMasterRepository;
import cn.fanyetu.order.service.OrderService;
import cn.fanyetu.order.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        // TODO 查询商品信息
        // TODO 计算总价
        // TODO 扣库存

        // 订单入库
        orderDTO.setOrderId(KeyUtils.generateUniqueKey()); // 设置orderDto的id
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(1)); //
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getStatus());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getStatus());

        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
