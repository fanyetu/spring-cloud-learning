package cn.fanyetu.apigetway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * @author zhanghaonan
 * @date 2018/5/14
 */
@Configuration
public class CorsConfig {

    /**
     * 创建跨域过滤器
     * @return
     */
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setMaxAge(300L); // 设置不用验证的有效时间

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
