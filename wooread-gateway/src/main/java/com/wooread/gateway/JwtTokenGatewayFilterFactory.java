package com.wooread.gateway;

import com.google.gson.Gson;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;


public class JwtTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtTokenGatewayFilterFactory.Config> {

    public static final String TOKEN_HEADER_NAME = "Authorization";

    public JwtTokenGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String token = headers.getFirst(TOKEN_HEADER_NAME);
            if (StringUtils.isEmpty(token)) {// 没有token
                ServerHttpResponse response = exchange.getResponse();
                HttpHeaders responseHeaders = response.getHeaders();
                responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                DataBuffer bodyDataBuffer = response.bufferFactory().wrap(new Gson().toJson(ofFail("error", "未登录或会话过期!")).getBytes());
                return response.writeWith(Mono.just(bodyDataBuffer));
            }else{// 验证token
                //TODO:
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }

}
