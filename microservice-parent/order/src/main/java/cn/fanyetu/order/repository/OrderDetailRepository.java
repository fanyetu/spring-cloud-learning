package cn.fanyetu.order.repository;

import cn.fanyetu.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

}
