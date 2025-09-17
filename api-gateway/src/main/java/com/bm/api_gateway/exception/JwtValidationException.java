package com.bm.api_gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class JwtValidationException {

    /**
     * При получении статуса 401 от auth-service заворачивает его так же в 401, по умолчанию выдавалось бы 500 в retrieve()
     * @param exchange
     * @return
     */
    @ExceptionHandler(WebClientResponseException.Unauthorized.class)
    public Mono<Void> handleWebClientResponseException(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
