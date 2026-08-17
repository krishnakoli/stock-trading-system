package com.krishna.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        long startTime = System.currentTimeMillis();

        log.info("====================================================");
        log.info("Incoming Request");
        log.info("Method : {}", exchange.getRequest().getMethod());
        log.info("URI    : {}", exchange.getRequest().getURI());
        log.info("Path   : {}", exchange.getRequest().getPath());
        log.info("====================================================");

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {

                    long executionTime =
                            System.currentTimeMillis() - startTime;

                    log.info("====================================================");
                    log.info("Outgoing Response");
                    log.info("Status : {}",
                            exchange.getResponse().getStatusCode());
                    log.info("Time   : {} ms", executionTime);
                    log.info("====================================================");

                }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}