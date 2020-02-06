package cn.kgc.cloud.zuul.routes;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: zuul
 * @description: TODO
 * @author: cuihao
 * @create: 2020-02-06 10:55
 * @version: V1.0
 **/
@Component
public class MyFilter extends ZuulFilter {
    /**
     * 过滤的类型 一共四种
     *  PRE_TYPE;    前置过滤器(url之前执行)
     *  POST_TYPE;   后置过滤器
     *  route：在路由请求时候被调用
     *  ERROR_TYPE;  异常过滤器
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器的级别 越小级别越高
     * @return
     */
    @Override
    public int filterOrder() {
        return -1;
    }

    /**
     * 过滤是否开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤执行体
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("第一个过滤器");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();
        HttpServletRequest request = requestContext.getRequest();
        // 跨域请求一共会进行两次请求 先发送options 是否可以请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            //requestContext.setSendZuulResponse(false); //验证请求不进行路由
            //response.setHeader("Access-Control-Allow-Origin", "*");  //通过网关转发 所以不用加上支持跨域
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, x-access-token");
            response.setHeader("Access-Control-Expose-Headers", "X-forwared-port, X-forwarded-host");
            response.setHeader("Vary", "Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
            //requestContext.setResponseStatusCode(HttpStatus.OK.value());//返回验证成功的状态码
            return null;
        }
        String auth = request.getHeader("Authorization");
        System.out.println(auth);
        if (null == auth || auth.equals("")) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(3001);
            requestContext.setResponseBody("{\"msg\":\"3001,error\"}");
        }else if (auth.equals("masao")) {
            return "pass";
        }else {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(3002);
            requestContext.setResponseBody("{\"msg\":\"3002,error auth\"}");
        }

        return null;
    }
}
