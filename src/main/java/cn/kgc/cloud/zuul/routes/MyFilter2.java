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
 * @create: 2020-02-06 16:08
 * @version: V1.0
 **/
@Component
public class MyFilter2 extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        return requestContext.sendZuulResponse();

    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("第二个过滤器");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();
        HttpServletRequest request = requestContext.getRequest();
        //response.setHeader("Access-Control-Allow-Origin", "*");
        //response.setHeader("Access-Control-Allow-Credentials", "true");
        //response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");
        //response.setHeader("Access-Control-Allow-Headers", "Authorization, type ,Content-Type, X-Requested-With, x-access-token");
        //response.setHeader("Access-Control-Expose-Headers", "X-forwared-port, X-forwarded-host");
        //response.setHeader("Vary", "Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
        // 跨域请求一共会进行两次请求 先发送options 是否可以请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            //requestContext.setSendZuulResponse(false); //验证请求不进行路由,这个有问题 网上给的有问题
            //response.setHeader("Access-Control-Allow-Origin", "*"); //通过网关转发 所以不用加上支持跨域
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, type ,Content-Type, X-Requested-With, x-access-token");
            response.setHeader("Access-Control-Expose-Headers", "X-forwared-port, X-forwarded-host");
            response.setHeader("Vary", "Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
            //requestContext.setResponseStatusCode(HttpStatus.OK.value());//返回验证成功的状态码
            return null;
        }
        String type = request.getHeader("type");
        System.out.println("type:"+type);
        if (null == type || type.equals("")) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(3001);
            requestContext.setResponseBody("{\"msg\":\"3001,error\"}");
        }else if (type.equals("1")) {
            System.out.println("这是第一个类型");
            return "pass";
        } else if (type.equals("2")) {
            System.out.println("这是第二个类型");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(4005);
            return "error";
        }else {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(3002);
            requestContext.setResponseBody("{\"msg\":\"3002,error auth\"}");
        }
        return null;
    }
}
