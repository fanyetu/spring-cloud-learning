package cn.fanyetu.apigetway.filter;

import cn.fanyetu.apigetway.constant.CookieConstant;
import cn.fanyetu.apigetway.utils.CookieUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限拦截
 * 区分卖家和买家
 *
 * @author zhanghaonan
 * @date 2018/5/10
 */
@Component
public class AuthBuyerFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        // /order/create只能买家访问(cookie里面有openid)
        if ("/order/order/create".equals(request.getRequestURI())){
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        Cookie cookie = CookieUtils.get(request, CookieConstant.OPENID);
        if (cookie == null || StringUtils.isEmpty(cookie.getValue())){
            // 如果没有登陆设置权限校验失败
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }
}
