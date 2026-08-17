package com.krishna.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class RateLimiterFilter implements GlobalFilter, Ordered {

    private static final int MAX_REQUESTS = 10;
    private static final long WINDOW_SECONDS = 60;

    private final Map<String, RequestInfo> requestTracker = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        String clientIp = exchange.getRequest().getRemoteAddress() != null
                ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
                : "unknown";

        long now = Instant.now().getEpochSecond();

        RequestInfo info = requestTracker.compute(clientIp, (ip, existing) -> {

            if (existing == null || now - existing.windowStart >= WINDOW_SECONDS) {
                return new RequestInfo(now,1);
            }

            existing.count++;
            return existing;
        });

        if(info.count > MAX_REQUESTS){

            log.warn("Rate Limit Exceeded for {}",clientIp);

            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);

            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    static class RequestInfo{

        long windowStart;
        int count;

        RequestInfo(long windowStart,int count){
            this.windowStart=windowStart;
            this.count=count;
        }
    }
}

//
//Client
//
//↓
//
//Extract IP
//
//↓
//
//Check HashMap
//
//↓
//
//Less than 10 requests ?
//
//        ↓
//
//YES
//
//↓
//
//Next Filter
//
//↓
//
//Order Service

//
//How it works internally
//
//Suppose your IP is
//
//192.168.1.10
//
//The HashMap becomes
//
//{
//    192.168.1.10
//
//    count = 7
//
//    windowStart = 11:30:00
//}
//
//Another request
//
//↓
//
//count = 8
//
//After
//
//60 seconds
//
//it automatically resets
//
//        count = 1