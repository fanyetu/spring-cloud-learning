package cn.fanyetu.apigetway.filter;

import cn.fanyetu.apigetway.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 限流拦截器
 *
 * 对请求进行限流
 *
 * 使用令牌桶算法
 *
 * @author zhanghaonan
 * @date 2018/5/9
 */
public class RateLimitFilter extends ZuulFilter{

    /**
     * 使用google的guava组件
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100); // 每秒钟向令牌桶放多少个令牌

    /**
     * 设置为pre过滤器
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 放到过滤器链的最前面
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 如果没有拿到令牌
        if (!RATE_LIMITER.tryAcquire()){
            // 直接抛出异常，结束过滤器链
            throw new RateLimitException();
        }
        return null;
    }
}
