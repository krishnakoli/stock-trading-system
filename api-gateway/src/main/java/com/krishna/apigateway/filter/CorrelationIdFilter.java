package com.krishna.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
public class CorrelationIdFilter implements GlobalFilter, Ordered {

    private static final String HEADER = "X-Correlation-Id";

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        String correlationId =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HEADER);

        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }

        final String finalCorrelationId = correlationId;

        ServerWebExchange modifiedExchange =
                exchange.mutate()
                        .request(builder ->
                                builder.header(HEADER, finalCorrelationId))
                        .build();

        log.info("CorrelationId={}", finalCorrelationId);

        return chain.filter(modifiedExchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}