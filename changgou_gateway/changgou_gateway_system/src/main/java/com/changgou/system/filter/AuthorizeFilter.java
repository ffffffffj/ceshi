package com.changgou.system.filter;

import com.changgou.system.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求
        ServerHttpRequest request = exchange.getRequest();
        //响应请求
        ServerHttpResponse response = exchange.getResponse();
        //如果是登录请求放行
        if (request.getURI().getPath().contains("/admin/login")){
            return chain.filter(exchange);
        }
        //获取请求的头
        HttpHeaders headers = request.getHeaders();
        //获取令牌
        String jwtToken = headers.getFirst("token");
        //判断请求头中是否携带令牌
        if (StringUtils.isEmpty(jwtToken)){
            //响应返回的状态码,是否有权限
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //返回
            return response.setComplete();
        }
        //如果头令牌合格解析令牌
        try {
            JwtUtil.parseJWT(jwtToken);
        }catch (Exception e){
            //解析jwt  是否合法
            e.printStackTrace();
            //返回
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
