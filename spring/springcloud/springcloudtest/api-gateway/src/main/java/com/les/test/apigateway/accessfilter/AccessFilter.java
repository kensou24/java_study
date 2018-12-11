package com.les.test.apigateway.accessfilter;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @ClassName: accessfilter
 * @Description:
 * @Author: king
 * @CreateDate: 2018/12/11 14:49
 */
@Slf4j
@Component
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        com.netflix.zuul.context.RequestContext ctx = com.netflix.zuul.context.RequestContext.getCurrentContext();
        log.info("send {} request to {}", ctx.getRequest().getMethod(), ctx.getRequest().getRequestURL().toString());
        Object token = ctx.getRequest().getParameter("token");
        if(null == token)
        {
            log.warn("token is missing");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("token is ok");
        return null;
    }
}
