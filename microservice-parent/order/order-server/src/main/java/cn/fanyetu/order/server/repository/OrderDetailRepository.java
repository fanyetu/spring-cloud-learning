package cn.fanyetu.order.server.repository;

import cn.fanyetu.order.server.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);

}
