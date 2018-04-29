package cn.fanyetu.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Data // 使用lombok插件自动生成get set方法
//@Table(name = "product_info") // 因为实体类和表名称一样，不需要再指定
@Entity // 使实体类和表进行关联
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
