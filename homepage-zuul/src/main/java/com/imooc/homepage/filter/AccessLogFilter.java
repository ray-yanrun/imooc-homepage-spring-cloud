package com.imooc.homepage.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 对应URI的请求响应时间
 */
@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {

    /**
     * 过滤器类型为请求之后
     * @return post
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * 过滤器顺序：在post之前调用
     * @return 1000-1
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        Long startTime = (Long)ctx.get("startTime");
        System.out.println("============================="+startTime);
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        long duration = System.currentTimeMillis()-startTime;
        System.out.println("============================="+duration);
        log.info("uri: {}, duration: {}ms", uri, duration/1000.0);
        return null;
    }
}
