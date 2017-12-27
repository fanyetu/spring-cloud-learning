package cn.fanyetu.microserviceconsumermovieribboncustomizing.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用ribbonClient,为特定name的ribbon client自定义配置
 */
@Configuration
@RibbonClient(name = "microservice-provider-user",
        configuration = RibbonConfiguration.class)
public class RibbonClientConfiguration {
}
