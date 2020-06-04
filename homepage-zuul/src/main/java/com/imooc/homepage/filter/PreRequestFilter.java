package com.imooc.homepage.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 自定义的过滤器，需要继承ZuulFilter
 * 此过滤器存储客户端发起请求的时间戳
 * Component: 标识过滤器是Spring中的Bean
 */
@Component
public class PreRequestFilter extends ZuulFilter {

    /**
     * 过滤器类型
     * @return 请求之前: pre
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器的顺序
     * @return 0 数字越小优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否启用过滤器
     * @return 启动
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        // 获取当前请求上下文，RequestContext用于在过滤器之间传递消息
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime", System.currentTimeMillis());
        return false;
    }
}
