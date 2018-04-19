package cn.fanyetu.order.repository;

import cn.fanyetu.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    // 不需要些方法，直接存储即可
}
