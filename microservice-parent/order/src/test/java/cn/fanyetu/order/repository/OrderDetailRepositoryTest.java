package cn.fanyetu.order.repository;

import cn.fanyetu.order.OrderApplicationTest;
import cn.fanyetu.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
@Component
public class OrderDetailRepositoryTest extends OrderApplicationTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("123");
        orderDetail.setProductId("234");
        orderDetail.setProductName("ceshi");
        orderDetail.setProductPrice(new BigDecimal(2.3));
        orderDetail.setProductQuantity(10);
        orderDetail.setProductIcon("http://123:8080/qq.jgp");

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertTrue(result != null); // 如果不为null则插入成功
    }
}