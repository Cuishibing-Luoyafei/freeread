package com.wooread.gateway;

import com.google.gson.Gson;
import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadbase.jwt.JwtUtils;
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
import static com.wooread.wooreadbase.jwt.JwtUtils.TOKEN_HEADER_NAME;
import static com.wooread.wooreadbase.tools.MessageTools.message;


public class JwtTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtTokenGatewayFilterFactory.Config> {

    public JwtTokenGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String token = headers.getFirst(TOKEN_HEADER_NAME);
            if (StringUtils.isEmpty(token)) {// 没有token
                return returnMessage(exchange, ofFail(message("no-token")));
            } else {// 验证token
                JwtUtils.DecodedToken decodedToken;
                try {
                    decodedToken = new JwtUtils.DecodedToken(token);
                } catch (Throwable e) {
                    return returnMessage(exchange, ofFail(message("invalid-token")));
                }
                if (!decodedToken.isTrust()) {
                    return returnMessage(exchange, ofFail(message("invalid-token")));
                } else if (decodedToken.isExp()) {
                    return returnMessage(exchange, ofFail(message("expire-token")));
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }

    private Mono<Void> returnMessage(ServerWebExchange exchange, BaseServiceOutput<String> message) {
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders responseHeaders = response.getHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(new Gson().toJson(message).getBytes());
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

}
