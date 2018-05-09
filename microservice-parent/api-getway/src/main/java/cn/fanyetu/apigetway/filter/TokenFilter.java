package cn.fanyetu.apigetway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhanghaonan
 * @date 2018/5/9
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * 设置当前filter的类型为前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 设置filter的执行顺序(这里放到PreDecorationFilter之前)
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * 打开filter，让run方法被执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * filter实际执行方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        // 获取上下文和请求
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        // 获取token并验证
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)){
            currentContext.setSendZuulResponse(false); // 设置zuul不通过
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value()); // 设置状态码为权限不足
        }

        return null;
    }
}
