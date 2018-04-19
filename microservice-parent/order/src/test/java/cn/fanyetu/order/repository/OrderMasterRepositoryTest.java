package cn.fanyetu.order.repository;

import cn.fanyetu.order.OrderApplicationTest;
import cn.fanyetu.order.dataobject.OrderMaster;
import cn.fanyetu.order.enums.OrderStatusEnum;
import cn.fanyetu.order.enums.PayStatusEnum;
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
public class OrderMasterRepositoryTest extends OrderApplicationTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerPhone("1232232894887");
        orderMaster.setBuyerAddress("重庆市");
        orderMaster.setBuyerOpenid("9234njk2348njsdf");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getStatus());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getStatus());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null); // 如果不为null则插入成功
    }

}