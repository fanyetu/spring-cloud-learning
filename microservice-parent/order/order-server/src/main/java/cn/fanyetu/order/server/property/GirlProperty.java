package cn.fanyetu.order.server.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author zhanghaonan
 * @date 2018/5/1
 */
@Data
@Component
@ConfigurationProperties("girl")
@RefreshScope
public class GirlProperty {

    private String name;

    private Integer age;
}
