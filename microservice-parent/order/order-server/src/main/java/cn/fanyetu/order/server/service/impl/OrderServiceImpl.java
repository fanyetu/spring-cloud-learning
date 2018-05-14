package cn.fanyetu.order.server.service.impl;

import cn.fanyetu.order.server.dataobject.OrderDetail;
import cn.fanyetu.order.server.dataobject.OrderMaster;
import cn.fanyetu.order.server.dto.OrderDTO;
import cn.fanyetu.order.server.enums.OrderStatusEnum;
import cn.fanyetu.order.server.enums.PayStatusEnum;
import cn.fanyetu.order.server.enums.ResultEnums;
import cn.fanyetu.order.server.exception.OrderException;
import cn.fanyetu.order.server.repository.OrderDetailRepository;
import cn.fanyetu.order.server.repository.OrderMasterRepository;
import cn.fanyetu.order.server.service.OrderService;
import cn.fanyetu.order.server.utils.KeyUtils;
import cn.fanyetu.product.client.feign.ProductClient;
import cn.fanyetu.product.common.DecreaseStockInput;
import cn.fanyetu.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private ProductClient productClient;

    /**
     * 完结订单，只能卖家操作
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(String orderId){
        // 查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()){
            throw new OrderException(ResultEnums.ORDER_NOT_EXIST);
        }

        // 判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (!OrderStatusEnum.NEW.getStatus().equals(orderMaster.getOrderStatus())){
            throw new OrderException(ResultEnums.ORDER_STATUS_ERROR);
        }

        // 修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getStatus());
        orderMasterRepository.save(orderMaster);

        // 查询订单详情
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)){
            throw new OrderException(ResultEnums.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtils.generateUniqueKey();

        // 查询商品信息
        List<String> productIdList = orderDTO.getOrderDetailList()
                .stream().map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        // 计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    // 单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    // 设置orderDetail的值
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtils.generateUniqueKey());

                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }

        }

        // 扣库存
        List<DecreaseStockInput> cartDtoList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStop(cartDtoList);

        // 订单入库
        orderDTO.setOrderId(orderId); // 设置orderDto的id
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(1)); //
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getStatus());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getStatus());

        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
